package com.junl.dms.mvc.mappoint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.junl.dms.mvc.chooseinfo.ChooseInfo;
import com.junl.dms.mvc.chooseinfo.ChooseInfoService;
import com.platform.annotation.Controller;
import com.platform.mvc.base.BaseController;

import freemarker.template.SimpleDate;

/** 
 * @author  hank E-mail: hankun@junl.cn 
 * @date 创建时间：2016年8月30日 下午4:55:37 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 */
@Controller(controllerKey = "/jf/manage/mapPoint")
public class MapPointController extends BaseController {
	
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(MapPointController.class);
	
	/**
	 * 首页
	 */
	public void index() {
		List<MapPoint> mappoint = new ArrayList<MapPoint>();
		String sql = "select * from DMS_APP_PointInfo";
		mappoint = MapPoint.dao.find(sql);
		setAttr("mappoint", mappoint);
		render("/manage/mappoint/index.html");
	}
	
	/**
	 * 获取树状菜单父节点 (没有子节点)
	 */
	public void getTreeMenuAndChildren()
	{
		JSONObject jsonObject = new JSONObject();
		List<Map> returnList = new ArrayList<Map>();
		List<Map> nameAndCountList = new ArrayList<Map>();
		List<ChooseInfo> menuList = new ArrayList<ChooseInfo>();
		menuList = ChooseInfoService.service.findListByType(2);
		String startDate = getPara("startDate");
		String endDate = getPara("endDate");
		for (int i = 0; i < menuList.size(); i++) {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("id", menuList.get(i).get("ids"));
			map.put("pId", 0);
			map.put("isParent", true);
			map.put("name", menuList.get(i).get("name"));
			returnList.add(map);
			//根据id 查巡查 和维修 有几处
			//巡查
			int xcCount = getXCCount(menuList.get(i).get("ids").toString(),startDate,endDate);
			//维修
			int wxCount = getWXCount(menuList.get(i).get("ids").toString(),startDate,endDate);
			map = new HashMap<String,Object>();
			map.put("xcCount", xcCount);
			map.put("wxCount", wxCount);
			map.put("id", menuList.get(i).get("ids"));
			map.put("name", menuList.get(i).get("name"));
			map.put("type", (i+1));
			nameAndCountList.add(map);
		}
		jsonObject.put("nameAndCount", nameAndCountList);
		jsonObject.put("menu", returnList);
		renderJson(jsonObject.toJSONString());
	}
	
	
	public int getXCCount(String id,String startDate,String endDate)
	{
		int count = 0;
		//默认  锡张高速公路  主线
		String sql = "select count(*) as count from DMS_XC_xunChaInfo where wxmkId = '"+id+"' and luXian = '锡张高速公路' and wzType = '主线' ";
		//23：yyyy-mm-dd
		if(startDate != null && !startDate.equals(""))
		{
			sql += "and CONVERT(varchar(12) , xunChaTime, 23 ) >= CONVERT(varchar(12) , '"+startDate+"', 23 ) ";
		}
		if(endDate != null && !endDate.equals(""))
		{
			sql += "and CONVERT(varchar(12) , xunChaTime, 23 ) <= CONVERT(varchar(12) , '"+endDate+"', 23 )";
		}
		List<Record> recordList = Db.find(sql);
		if(recordList != null && recordList.size() > 0)
		{
			count = recordList.get(0).get("count");
		}
		return count;
	}
	
	
	public int getWXCount(String id,String startDate,String endDate)
	{
		int count = 0;
		//默认  锡张高速公路  主线
		String sql = "select count(*) as count from  xz_wx w   ";
		sql += " LEFT JOIN DMS_RW_task_info_relate t  on t.ids = w.taskInfoRelate ";
		sql += " LEFT JOIN DMS_XC_xunChaInfo i on i.ids = t.xunChaInfoId ";
		sql += " where i.wxmkId = '"+id+"' and w.luXian = '锡张高速公路' and w.wzType = '主线' ";
		//23：yyyy-mm-dd 
		if(startDate != null && !startDate.equals(""))
		{
			sql += "and CONVERT(varchar(12) , w.weiXiuTime, 23 ) >= CONVERT(varchar(12) , '"+startDate+"', 23 ) ";
		}
		if(endDate != null && !endDate.equals(""))
		{
			sql += "and CONVERT(varchar(12) , w.weiXiuTime, 23 ) <= CONVERT(varchar(12) , '"+endDate+"', 23 )";
		}
		List<Record> recordList = Db.find(sql);
		if(recordList != null && recordList.size() > 0)
		{
			count = recordList.get(0).get("count");
		}
		return count;
	}
	
	/**
	 * 根据父节点Id 获取子节点
	 * @param parentId
	 */
	public void getChildrenMenuByParentId()
	{
		String parentId = getPara("parentId");
		List<Map<String,Object>> returnList = new ArrayList<Map<String,Object>>();
		String sql = "select * from DMS_PZ_bingHaiIndex where wxmkId = '"+parentId+"'";
		List<Record> recordList = Db.find(sql);
		if(recordList != null && recordList.size() > 0)
		{
			for (int i = 0; i < recordList.size(); i++) {
				Record record = recordList.get(i);
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("id", record.get("ids"));
				map.put("pId", parentId);
				map.put("isParent", false);
				map.put("name", record.get("name"));
				returnList.add(map);
			}
		}
		JSONObject json = new JSONObject();
		json.put("childrenMenu", returnList);
		renderJson(json.toJSONString());
	}
	
	/**
	 * 获取巡查 经纬度
	 */
	public void getXCLngAndLat()
	{
		JSONObject json = new JSONObject();
		List<Map> returnList = new ArrayList<Map>();
		String startDate = getPara("startDate");
		String endDate = getPara("endDate");
		String name = getPara("name");
		String wxmkId = "";
		//name查询wxmkId
		String sql = "select wxmkId from DMS_PZ_bingHaiIndex where name = '"+name+"'";
		List<Record> recordList = Db.find(sql);
		if(recordList != null && recordList.size() > 0)
		{
			wxmkId = recordList.get(0).get("wxmkId");
		}
		//查询巡查记录  根据name  和 wxmkId
		String xunChaSql = " select i.xunChaStartZHK,i.xunChaStartZHM,f.name,i.luXian,i.wzType from DMS_XC_xunChaInfo i ";
		xunChaSql += " left join DMS_XC_xunChaTou t on t.ids = i.xunChaTouId ";
		xunChaSql += " left join DMS_PZ_fangXiang f on t.xunChaFangXiang1 = f.ids ";
		xunChaSql += " where i.wxmkId = '"+wxmkId+"' and i.binghaiOrQueXianType = '"+name+"'";
		
		if(startDate != null && !startDate.equals(""))
		{
			xunChaSql += " and CONVERT(varchar(12) , i.xunChaTime, 23 ) >= CONVERT(varchar(12) , '"+startDate+"', 23 ) ";
		}
		if(endDate != null && !endDate.equals(""))
		{
			xunChaSql += " and CONVERT(varchar(12) , i.xunChaTime, 23 ) <= CONVERT(varchar(12) , '"+endDate+"', 23 ) ";
		}
		List<Record> recordListXunCha = Db.find(xunChaSql);
		if(recordListXunCha != null && recordListXunCha.size() > 0)
		{
			String roadName = "";
			String fangxiang = "";
			double startKM = 0.00;
			for (int i = 0; i < recordListXunCha.size(); i++) {
				Record record = recordListXunCha.get(i);
				String luxian = (record.get("luXian")==null?"":record.get("luXian").toString());
				String wzType = (record.get("wzType")==null?"":record.get("wzType").toString());
				roadName = luxian+wzType;
				fangxiang = record.get("name");
				double startK = 0.0;
				double startM = 0.0;
				if(record.get("xunChaStartZHK") != null)
				{
					startK = Double.valueOf(record.get("xunChaStartZHK").toString());
				}
				if(record.get("xunChaStartZHM") != null)
				{
					startM = Double.valueOf(record.get("xunChaStartZHM").toString());
				}
				startKM = startK+startM/1000;
				MapPointerPro pro1 = new MapPointerPro(roadName, fangxiang, startKM, "getLngAndLat");
				MapPointerPro pro2 = new MapPointerPro(roadName, fangxiang, startKM, "getLngAndLat2");
				Db.execute(pro1);
				Db.execute(pro2);
				Map<String,Object> map = new HashMap<String, Object>();
				if(pro1.getResult() != 0 || pro2.getResult() != 0)
				{
					map.put("lng", pro1.getResult());
					map.put("lat", pro2.getResult());
					returnList.add(map);
				}
			}
		}
		if(returnList != null && returnList.size() > 0)
		{
			json.put("lngAndLat", returnList);
		}
		else
		{
			json.put("lngAndLat", "noData");
		}
		renderJson(json.toJSONString());
	}
	
	/**
	 * 获取维修经纬度
	 */
	public void getWXLngAndLat()
	{

		JSONObject json = new JSONObject();
		List<Map> returnList = new ArrayList<Map>();
		String startDate = getPara("startDate");
		String endDate = getPara("endDate");
		String name = getPara("name");
		String wxmkId = "";
		//name查询wxmkId
		String sql = "select wxmkId from DMS_PZ_bingHaiIndex where name = '"+name+"'";
		List<Record> recordList = Db.find(sql);
		if(recordList != null && recordList.size() > 0)
		{
			wxmkId = recordList.get(0).get("wxmkId");
		}
		//查询维修记录  根据name  和 wxmkId
		String weixiuSql = " select w.startZHK,w.startZHM,w.wzName,w.luXian,w.wzType from  xz_wx w ";
		weixiuSql += " LEFT JOIN DMS_RW_task_info_relate t  on t.ids = w.taskInfoRelate ";
		weixiuSql += " LEFT JOIN DMS_XC_xunChaInfo i on i.ids = t.xunChaInfoId ";
		weixiuSql += " where i.wxmkId = '"+wxmkId+"' and i.binghaiOrQueXianType = '"+name+"'";
		if(startDate != null && !startDate.equals(""))
		{
			weixiuSql += " and CONVERT(varchar(12) , w.weiXiuTime, 23 ) >= CONVERT(varchar(12) , '"+startDate+"', 23 ) ";
		}
		if(endDate != null && !endDate.equals(""))
		{
			weixiuSql += " and CONVERT(varchar(12) , w.weiXiuTime, 23 ) <= CONVERT(varchar(12) , '"+endDate+"', 23 ) ";
		}
		List<Record> recordListXunCha = Db.find(weixiuSql);
		if(recordListXunCha != null && recordListXunCha.size() > 0)
		{
			String roadName = "";
			String fangxiang = "";
			double startKM = 0.00;
			for (int i = 0; i < recordListXunCha.size(); i++) {
				Record record = recordListXunCha.get(i);
				String luxian = (record.get("luXian")==null?"":record.get("luXian").toString());
				String wzType = (record.get("wzType")==null?"":record.get("wzType").toString());
				roadName = luxian+wzType;
				fangxiang = record.get("wzName");
				double startK = 0.0;
				double startM = 0.0;
				if(record.get("startZHK") != null)
				{
					startK = Double.valueOf(record.get("startZHK").toString());
				}
				if(record.get("startZHM") != null)
				{
					startM = Double.valueOf(record.get("startZHM").toString());
				}
				startKM = startK+startM/1000;
				MapPointerPro pro1 = new MapPointerPro(roadName, fangxiang, startKM, "getLngAndLat");
				MapPointerPro pro2 = new MapPointerPro(roadName, fangxiang, startKM, "getLngAndLat2");
				Db.execute(pro1);
				Db.execute(pro2);
				Map<String,Object> map = new HashMap<String, Object>();
				if(pro1.getResult() != 0 || pro2.getResult() != 0)
				{
					map.put("lng", pro1.getResult());
					map.put("lat", pro2.getResult());
					returnList.add(map);
				}
			}
		}
		if(returnList != null && returnList.size() > 0)
		{
			json.put("lngAndLat", returnList);
		}
		else
		{
			json.put("lngAndLat", "noData");
		}
		renderJson(json.toJSONString());
	}
	
	
	//获取   定位菜单 
	@SuppressWarnings("unchecked")
	public void getLocationMenuAndPointAll()
	{
		ArrayList<Map<String,Object>> menuList = new ArrayList<Map<String,Object>>(); 
		Map<String,Object> menuMap = new HashMap<String, Object>();
		//取最新的坐标
		String date = getPara("locationDate");
		String startTime = date+" 00:00:00";
		String endTime = date+" 23:59:59";
		String sql = "select * from pt_user u where EXISTS (select DISTINCT createUserId from DMS_APP_latAndLonCollect l where l.createUserId is not NULL and l.createTime >= '"+startTime+"' and l.createTime <= '"+endTime+"'   and l.createUserId = u.ids) ";
		List<Record> recordList = Db.find(sql);
		if(recordList != null && recordList.size() > 0)
		{
			for (int i = 0; i < recordList.size(); i++) {
				try {
					Map<String,Object> map = new HashMap<String, Object>();
					Record record = recordList.get(i);
					map.put("id", record.get("ids"));
					map.put("pId", 0);
					map.put("isParent", false);
					map.put("name", record.get("username"));
					menuMap.put(record.get("ids").toString(), map);
					map = new HashMap<String, Object>();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		for (String key : menuMap.keySet()) {
			menuList.add((Map<String, Object>) menuMap.get(key));
		}
		JSONObject json = new JSONObject();
		json.put("menuList", menuList);
		renderJson(json.toJSONString());
	}
	
	
	public void getPointByUserIdAndDate()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		List<Map<String,Object>> lngLatList = new ArrayList<Map<String,Object>>();
		String date = getPara("locationDate");
		String startTime = "";
		String endTime = "";
		startTime = date+" 00:00:00";
		endTime = date+" 23:59:59";
		String userId = getPara("userId");
		//查询 是否有巡查 是否有维修 和姓名
		String searchXCSql = " select count(*) as count from DMS_XC_xunChaTou where xunChaStartTime <= l.createTime and xunChaEndTime >= l.createTime and createUserId = l.createUserId ";
		String searchWXSql = " select count(*) as count from xz_wx w WHERE w.createUserId = l.createUserId and w.weiXiuTime >= '"+startTime+"' and w.weiXiuTime <= '"+endTime+"'  ";
		String userNameSql = " select p.username from pt_user p where p.ids = l.createUserId ";
		String sql = "";
		if(userId != null && !userId.equals(""))
		{
			sql = " select lat,lon,createTime,createUserId,("+searchXCSql+") as xcCount,("+searchWXSql+") as wxCount,("+userNameSql+") as userName from DMS_APP_latAndLonCollect l where 1=1 and createUserId is not null ";
			sql += " and createUserId = '"+userId+"' ";
		}
		else
		{
			sql = " select createUserId,max(createTime) as createTime from DMS_APP_latAndLonCollect where 1=1 and createUserId is not null ";
		}
		
		if(date != null && !date.equals(""))
		{
			sql += " and createTime >= '"+startTime+"' and createTime <= '"+endTime+"' ";
		}
		
		if(userId == null || userId.equals(""))
		{
			sql += " GROUP BY createUserId ";
		}
		List<Record> recordList = Db.find(sql);
		if(recordList != null && recordList.size() > 0)
		{
			for (int i = 0; i < recordList.size(); i++) {
				Record record = recordList.get(i);
				if(userId == null || userId.equals(""))
				{
					String userIdTmp = record.get("createUserId").toString();
					String createTime = record.get("createTime").toString();
					//查询是否是巡查   根据userId和date
					String tmpSql = " select l.lat,l.lon,l.createTime,("+searchXCSql+") as xcCount,("+searchWXSql+") as wxCount,("+userNameSql+") as username from DMS_APP_latAndLonCollect l where l.createUserId = '"+userIdTmp+"' and l.createTime = '"+createTime+"' ";
					List<Record> recordListTmp = Db.find(tmpSql);
					if(recordListTmp != null && recordListTmp.size() > 0)
					{
						for (int j = 0; j < recordListTmp.size(); j++) {
							try {
								Map<String,Object> map = new HashMap<String, Object>();
								map.put("lng", recordListTmp.get(j).get("lon"));
								map.put("lat", recordListTmp.get(j).get("lat"));
								map.put("userName", recordListTmp.get(j).get("username"));
								map.put("createTime", recordListTmp.get(j).get("createTime")==null?"":sdf.format(sdf.parse(recordListTmp.get(j).get("createTime").toString())));
								if(recordListTmp.get(j).get("xcCount") != null && Integer.valueOf(recordListTmp.get(j).get("xcCount").toString()) > 0)
								{
									map.put("isXC",true);
								}
								else
								{
									map.put("isXC",false);
								}
								if(recordListTmp.get(j).get("wxCount") != null && Integer.valueOf(recordListTmp.get(j).get("wxCount").toString()) > 0)
								{
									map.put("isWX",true);
								}
								else
								{
									map.put("isWX",false);
								}
								lngLatList.add(map);
							} catch (ParseException e) {
								e.printStackTrace();
							}
						}
					}
				}
				else
				{
					try {
						Map<String,Object> map = new HashMap<String, Object>();
						map.put("lng", record.get("lon"));
						map.put("lat", record.get("lat"));
						map.put("createTime", record.get("createTime")==null?"":sdf.format(sdf.parse(record.get("createTime").toString())));
						map.put("userName", record.get("userName"));
						if(record.get("xcCount") != null && Integer.valueOf(record.get("xcCount").toString()) > 0)
						{
							map.put("isXC",true);
						}
						else
						{
							map.put("isXC",false);
						}
						if(record.get("wxCount") != null && Integer.valueOf(record.get("wxCount").toString()) > 0)
						{
							map.put("isWX",true);
						}
						else
						{
							map.put("isWX",false);
						}
						lngLatList.add(map);
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			}
		}
		JSONObject json = new JSONObject();
		json.put("lngLatList", lngLatList);
		renderJson(json.toJSONString());
	}
}
