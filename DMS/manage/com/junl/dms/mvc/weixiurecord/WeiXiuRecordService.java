package com.junl.dms.mvc.weixiurecord;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.platform.mvc.base.BaseService;

/** 
 * @author  hank E-mail: hankun@junl.cn 
 * @date 创建时间：2016年9月4日 下午5:23:43 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 */
public class WeiXiuRecordService extends BaseService {
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(WeiXiuRecordService.class);

	public static final WeiXiuRecordService service = new WeiXiuRecordService();
	
	
	
	
	public JSONArray searchWeiXiuSize(String searchTime){
		JSONArray jsonArr = new JSONArray();
		String sql = "SELECT * FROM DMS_PZ_chooseInfo WHERE type = '2'  ORDER BY sort";
		List<Record> list = Db.find(sql);
		
		String sizeSql = "SELECT count(*) FROM #TableName wx LEFT JOIN DMS_RW_task_info_relate tir ON wx.taskInfoRelate = tir.ids "
				+ "LEFT JOIN DMS_XC_xunChaInfo xi ON xi.ids = tir.xunChaInfoId "
				+ "LEFT JOIN DMS_PZ_chooseInfo ci ON ci.ids = xi.wxmkId"
				+ " WHERE 1 = 1 and CONVERT(varchar(100), wx.createTime, 23) = '"+searchTime+"'";
		for (int i = 0; i < list.size(); i++) {
			JSONObject jsonObj = new JSONObject();
			Record rec = list.get(i);
			jsonObj.put("ids", rec.getStr("ids"));
			jsonObj.put("name", rec.getStr("name"));
			jsonObj.put("key", rec.getStr("key"));
			String tableName = rec.getStr("tablename");
			jsonObj.put("size", Db.queryInt(sizeSql.replace("#TableName", tableName)));
			jsonArr.add(jsonObj);
		}
		return jsonArr;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void youwuWord(String path,String fileName,String searchtime)throws Exception{
		Map<String, Object> dataMap = new HashMap<String, Object>();
		String name ="油污处理";
		String sql = getSql("manage.weixiurecord.seachlistaa");
		List<Record>list1=Db.find(sql,name,searchtime);
		dataMap.put("youwulist", list1);
		Float result = (float) 0;
		for(int j = 0;j<list1.size();j++){
			Float mianji = list1.get(j).get("mianJi");
			result = result+mianji;
		}
		
		dataMap.put("mianjihe", result);
		System.out.println(result);
		String sql2 = getSql("manage.weixiurecord.seachjxaa");
		List<Record> list2 = Db.find(sql2,name,searchtime);
		dataMap.put("shigongjixie", list2);
		String sql3 = getSql("manage.weixiurecord.seachsgryaa");
		String sql4 = getSql("manage.weixiurecord.seachaqyaa");
		String sql5 = getSql("manage.weixiurecord.seachfzraa");
		List<Record>list3=Db.find(sql3,name,searchtime);
		List<Record>list4=Db.find(sql4,name,searchtime);
		List<Record>list5=Db.find(sql5,name,searchtime);
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
		Date date=null;
		date=sim.parse(searchtime);
		cal.setTime(date);
		dataMap.put("year", cal.get(Calendar.YEAR));
		dataMap.put("month", cal.get(Calendar.MONTH)+1);
		dataMap.put("day", cal.get(Calendar.DAY_OF_MONTH));
		dataMap.put("shiGongJiXie", list2);
		dataMap.put("shiGongRenYuan", list3);
		dataMap.put("anQuanYuan", list4);
		dataMap.put("fuZeRen", list5);
		List lista = new ArrayList();
		if(list1.size()<10){
			for(int i = 0;i<9-list1.size();i++){
				lista.add(0);
			}
			dataMap.put("nullobject", lista);
		}
		dataMap.put("size", list1.size());
		createWord(dataMap, "youwu.ftl",fileName+"ywcl.doc",path);
	}
	
	/**
	 * 通用维修
	 * @param path
	 * @param fileName
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void generalWord(String path,String fileName,String searchtime)throws Exception{
		Map<String, Object> dataMap = new HashMap<String, Object>();
		String name = "通用维修";
		String sql = getSql("manage.weixiurecord.seachlist2aa");
		List<Record>list1=Db.find(sql,name,searchtime);
		dataMap.put("yanghulist", list1);
		String sql2 = getSql("manage.weixiurecord.seachsgjx2aa");
		String sql3 = getSql("manage.weixiurecord.seachwxry2aa");
		String sql4 = getSql("manage.weixiurecord.seachaqy2aa");
		String sql5 = getSql("manage.weixiurecord.seachfzr2aa");
		String sql6 = getSql("manage.weixiurecord.seachyhxm2aa");
		String sql7 = getSql("manage.weixiurecord.seachyhfw2aa");
		String sql8 = getSql("manage.weixiurecord.seachreason2aa");
		List<Record>list2=Db.find(sql2,name,searchtime);
		List<Record>list3=Db.find(sql3,name,searchtime);
		List<Record>list4=Db.find(sql4,name,searchtime);
		List<Record>list5=Db.find(sql5,name,searchtime);
		List<Record>list6=Db.find(sql6,name,searchtime);
		List<Record>list7=Db.find(sql7,name,searchtime);
		List<Record>list8=Db.find(sql8,name,searchtime);
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
		Date date=null;
		date=sim.parse(searchtime);
		cal.setTime(date);
		dataMap.put("weather", list1.get(0).get("weatherState"));
		dataMap.put("year", cal.get(Calendar.YEAR));
		dataMap.put("month", cal.get(Calendar.MONTH)+1);
		dataMap.put("day", cal.get(Calendar.DAY_OF_MONTH));
		dataMap.put("shiGongJiXie", list2);
		dataMap.put("weiXiuRenYuan", list3);
		dataMap.put("anQuanYuan", list4);
		dataMap.put("fuZeRen", list5);
		dataMap.put("yanghuxiangmu", list6);
		dataMap.put("yanghufanwei", list7);
		dataMap.put("reason", list8);
		List lista = new ArrayList();
		if(list1.size()<6){
			for(int i = 0;i<6-list1.size();i++){
				lista.add(0);
			}
			dataMap.put("nullobject", lista);
		}
		dataMap.put("size", list1.size());
		createWord(dataMap, "yanghu.ftl",fileName+"tywx.doc",path);
	}
	/**
	 * 交通安全设施
	 * @param path
	 * @param fileName
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void jiaotongWord(String path,String fileName,String searchtime)throws Exception{
		Map<String, Object> dataMap = new HashMap<String, Object>();
		String name = "交通安全设施";
		String sql = getSql("manage.weixiurecord.seachlist3aa");
		List<Record>list1=Db.find(sql,name,searchtime);
		dataMap.put("jiaotonglist", list1);
		String sql2 = getSql("manage.weixiurecord.seachsgjx3aa");
		String sql3 = getSql("manage.weixiurecord.seachwxry3aa");
		String sql4 = getSql("manage.weixiurecord.seachaqy3aa");
		String sql5 = getSql("manage.weixiurecord.seachfzr3aa");
		
		List<Record>list2=Db.find(sql2,name,searchtime);
		List<Record>list3=Db.find(sql3,name,searchtime);
		List<Record>list4=Db.find(sql4,name,searchtime);
		List<Record>list5=Db.find(sql5,name,searchtime);
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
		Date date=null;
		date=sim.parse(searchtime);
		cal.setTime(date);
		dataMap.put("year", cal.get(Calendar.YEAR));
		dataMap.put("month", cal.get(Calendar.MONTH)+1);
		dataMap.put("day", cal.get(Calendar.DAY_OF_MONTH));
		dataMap.put("shiGongJiXie", list2);
		dataMap.put("weiXiuRenYuan", list3);
		dataMap.put("anQuanYuan", list4);
		dataMap.put("fuZeRen", list5);
		List lista = new ArrayList();
		if(list1.size()<5){
			for(int i = 0;i<5-list1.size();i++){
				lista.add(0);
			}
			dataMap.put("nullobject", lista);
		}
		dataMap.put("size", list1.size());
		createWord(dataMap, "jiaotong.ftl",fileName+"jtaqss.doc",path);
	}
	
	/**
	 * 裂缝处理
	 * @param path
	 * @param fileName
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void liefengWord(String path,String fileName,String searchtime)throws Exception{
		Map<String, Object> dataMap = new HashMap<String, Object>();
		String name = "裂缝处理";
		String sql = getSql("manage.weixiurecord.seachlist2bb");
		List<Record>list1=Db.find(sql,name,searchtime);
		dataMap.put("yanghulist", list1);
		String sql2 = getSql("manage.weixiurecord.seachsgjx2bb");
		String sql3 = getSql("manage.weixiurecord.seachwxry2bb");
		String sql4 = getSql("manage.weixiurecord.seachaqy2bb");
		String sql5 = getSql("manage.weixiurecord.seachfzr2bb");
		List<Record>list2=Db.find(sql2,name,searchtime);
		List<Record>list3=Db.find(sql3,name,searchtime);
		List<Record>list4=Db.find(sql4,name,searchtime);
		List<Record>list5=Db.find(sql5,name,searchtime);
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
		Date date=null;
		date=sim.parse(searchtime);
		cal.setTime(date);
		dataMap.put("year", cal.get(Calendar.YEAR));
		dataMap.put("month", cal.get(Calendar.MONTH)+1);
		dataMap.put("day", cal.get(Calendar.DAY_OF_MONTH));
		dataMap.put("shiGongJiXie", list2);
		dataMap.put("weiXiuRenYuan", list3);
		dataMap.put("anQuanYuan", list4);
		dataMap.put("fuZeRen", list5);
		List lista = new ArrayList();
		if(list1.size()<6){
			for(int i = 0;i<6-list1.size();i++){
				lista.add(0);
			}
			dataMap.put("nullobject", lista);
		}
		dataMap.put("size", list1.size());
		createWord(dataMap, "liefeng.ftl",fileName+"lfcl.doc",path);
	}
	
	/**
	 * 防撞护栏
	 * @param path
	 * @param fileName
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void fangzhuangWord(String path,String searchtime)throws Exception{
		Map<String, Object> dataMap = new HashMap<String, Object>();
		String name = "防撞护栏";
		String sql = getSql("manage.weixiurecord.searchfhzlsize");
		List<Record> list = Db.find(sql, name,searchtime);
		for(int i = 0;i<list.size();i++){
			dataMap.put("list", list.get(i));
			String ids = list.get(i).get("ids");
			String sql2 = getSql("manage.weixiurecord.fzhllist");
			List<Record> list2 = Db.find(sql2,ids);
			dataMap.put("fzhllist", list2);			
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sim2=new SimpleDateFormat("yyyy-MM-dd");
			Date date2=null;
			date2=sim2.parse(searchtime);
			cal.setTime(date2);
			dataMap.put("year", cal.get(Calendar.YEAR));
			dataMap.put("month", cal.get(Calendar.MONTH)+1);
			dataMap.put("day", cal.get(Calendar.DAY_OF_MONTH));
			List lista = new ArrayList();
			if(list2.size()<8){
				for(int j = 0;j<8-list2.size();j++){
					lista.add(0);
				}
				dataMap.put("nullobject", lista);
			}
			dataMap.put("size", list2.size());
			createWord(dataMap, "fzhlwx.ftl", ids+".doc",path);
		}
	}
	

	/**
	 * 路面病害
	 * @param path
	 * @param searchtime
	 * @throws Exception
	 */
	public void lumianWord(String path,String searchtime) throws Exception{
		Map<String, Object> dataMap = new HashMap<String, Object>();
		String name = "路面病害";
		String sql = getSql("manage.weixiurecord.searchlumianbinghai");
		List<Record> list = Db.find(sql, name,searchtime);
		for(int i = 0;i<list.size();i++){
			dataMap.put("list", list.get(i));
			String ids = list.get(i).get("ids");
			String sql2 = getSql("manage.weixiurecord.ceng");
			List<Record> shang = Db.find(sql2, ids,1);
			List<Record> zhong = Db.find(sql2, ids,2);
			List<Record> xia = Db.find(sql2, ids,3);
			List<Record> jiceng = Db.find(sql2, ids,4);
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sim2=new SimpleDateFormat("yyyy-MM-dd");
			Date date2=null;
			date2=sim2.parse(searchtime);
			cal.setTime(date2);
			dataMap.put("year", cal.get(Calendar.YEAR));
			dataMap.put("month", cal.get(Calendar.MONTH)+1);
			dataMap.put("day", cal.get(Calendar.DAY_OF_MONTH));
			dataMap.put("shang",shang);
			dataMap.put("ceng1",zhong);
			dataMap.put("ceng2",xia);
			dataMap.put("ceng3",jiceng);
			createWord(dataMap, "lumian.ftl", ids+".doc",path);
		}
	}
	
	/**
	 * 获取高速公路沥青路面状况数据（List<Map>）
	 */
	public List<Map<String,Object>> getRoadState(List<Record> recordList)
	{
		List<Map<String,Object>> resultData = new ArrayList<Map<String,Object>>();
		if(recordList != null && recordList.size() > 0)
		{
			for (int i = 0; i < recordList.size(); i++) {
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("KSZH", recordList.get(i).get("KSZH")==null?"":recordList.get(i).get("KSZH").toString());
				map.put("CD", recordList.get(i).get("CD")==null?"":recordList.get(i).get("CD").toString());
				map.put("GL1", recordList.get(i).get("龟裂轻")==null?"":recordList.get(i).get("龟裂轻").toString());
				map.put("GL2", recordList.get(i).get("龟裂中")==null?"":recordList.get(i).get("龟裂中").toString());
				map.put("GL3", recordList.get(i).get("龟裂重")==null?"":recordList.get(i).get("龟裂重").toString());
				map.put("KZLF1", recordList.get(i).get("块状裂缝轻")==null?"":recordList.get(i).get("块状裂缝轻").toString());
				map.put("KZLF3", recordList.get(i).get("块状裂缝重")==null?"":recordList.get(i).get("块状裂缝重").toString());
				map.put("ZXLF1", recordList.get(i).get("纵向裂缝轻")==null?"":recordList.get(i).get("纵向裂缝轻").toString());
				map.put("ZXLF3", recordList.get(i).get("纵向裂缝重")==null?"":recordList.get(i).get("纵向裂缝重").toString());
				map.put("HXLF1", recordList.get(i).get("横向裂缝轻")==null?"":recordList.get(i).get("横向裂缝轻").toString());
				map.put("HXLF3", recordList.get(i).get("横向裂缝重")==null?"":recordList.get(i).get("横向裂缝重").toString());
				map.put("KC1", recordList.get(i).get("坑槽轻")==null?"":recordList.get(i).get("坑槽轻").toString());
				map.put("LC3", recordList.get(i).get("坑槽重")==null?"":recordList.get(i).get("坑槽重").toString());
				map.put("SS1", recordList.get(i).get("松散轻")==null?"":recordList.get(i).get("松散轻").toString());
				map.put("SS3", recordList.get(i).get("松散重")==null?"":recordList.get(i).get("松散重").toString());
				map.put("CX1", recordList.get(i).get("沉陷轻")==null?"":recordList.get(i).get("沉陷轻").toString());
				map.put("CX3", recordList.get(i).get("沉陷重")==null?"":recordList.get(i).get("沉陷轻").toString());
				map.put("CC1", recordList.get(i).get("车辙轻")==null?"":recordList.get(i).get("车辙轻").toString());
				map.put("CC3", recordList.get(i).get("车辙重")==null?"":recordList.get(i).get("车辙重").toString());
				map.put("PLYB1", recordList.get(i).get("波浪拥包轻")==null?"":recordList.get(i).get("波浪拥包轻").toString());
				map.put("PLYB3", recordList.get(i).get("波浪拥包重")==null?"":recordList.get(i).get("波浪拥包重").toString());
				map.put("FY", recordList.get(i).get("泛油")==null?"":recordList.get(i).get("泛油").toString());
				map.put("XBBL", recordList.get(i).get("修补不良")==null?"":recordList.get(i).get("修补不良").toString());
				map.put("DR", recordList.get(i).get("DR")==null?"":recordList.get(i).get("DR").toString());
				map.put("PCI", recordList.get(i).get("PCI")==null?"":recordList.get(i).get("PCI").toString());
				resultData.add(map);
			}
		}
		return resultData;
	}
	
	
	/**
	 * 获取路基损坏状况数据（List<Map>）
	 */
	public List<Map<String,Object>> getRoadBedState(List<Record> recordList)
	{
		List<Map<String,Object>> resultData = new ArrayList<Map<String,Object>>();
		if(recordList != null && recordList.size() > 0)
		{
			for (int i = 0; i < recordList.size(); i++) {
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("KSZH", recordList.get(i).get("KSZH")==null?"":recordList.get(i).get("KSZH").toString());
				map.put("CD", recordList.get(i).get("CD")==null?"":recordList.get(i).get("CD").toString());
				map.put("LJBGBJ", recordList.get(i).get("路肩、边沟不洁")==null?"":recordList.get(i).get("路肩、边沟不洁").toString());
				map.put("LJSH1", recordList.get(i).get("路肩损坏轻")==null?"":recordList.get(i).get("路肩损坏轻").toString());
				map.put("LJSH3", recordList.get(i).get("路肩损坏重")==null?"":recordList.get(i).get("路肩损坏重").toString());
				map.put("BPTT1", recordList.get(i).get("边坡坍塌轻")==null?"":recordList.get(i).get("边坡坍塌轻").toString());
				map.put("BPTT2", recordList.get(i).get("边坡坍塌中")==null?"":recordList.get(i).get("边坡坍塌中").toString());
				map.put("BPTT3", recordList.get(i).get("边坡坍塌重")==null?"":recordList.get(i).get("边坡坍塌重").toString());
				map.put("SHCG1", recordList.get(i).get("水毁冲沟轻")==null?"":recordList.get(i).get("水毁冲沟轻").toString());
				map.put("SHCG2", recordList.get(i).get("水毁冲沟中")==null?"":recordList.get(i).get("水毁冲沟中").toString());
				map.put("SHCG3", recordList.get(i).get("水毁冲沟重")==null?"":recordList.get(i).get("水毁冲沟重").toString());
				map.put("LJGZW1", recordList.get(i).get("路基构造物轻")==null?"":recordList.get(i).get("路基构造物轻").toString());
				map.put("LJGZW2", recordList.get(i).get("路基构造物中")==null?"":recordList.get(i).get("路基构造物中").toString());
				map.put("LJGZW3", recordList.get(i).get("路基构造物重")==null?"":recordList.get(i).get("路基构造物重").toString());
				map.put("LYSQS", recordList.get(i).get("路缘石缺损")==null?"":recordList.get(i).get("路缘石缺损").toString());
				map.put("LJZTCJ1", recordList.get(i).get("路基整体沉降轻")==null?"":recordList.get(i).get("路基整体沉降轻").toString());
				map.put("LJZTCJ2", recordList.get(i).get("路基整体沉降中")==null?"":recordList.get(i).get("路基整体沉降中").toString());
				map.put("LJZTCJ3", recordList.get(i).get("路基整体沉降重")==null?"":recordList.get(i).get("路基整体沉降重").toString());
				map.put("PSXTYS1", recordList.get(i).get("排水系统淤塞轻")==null?"":recordList.get(i).get("排水系统淤塞轻").toString());
				map.put("PSXTYS3", recordList.get(i).get("排水系统淤塞重")==null?"":recordList.get(i).get("排水系统淤塞重").toString());
				map.put("SCI", recordList.get(i).get("SCI")==null?"":recordList.get(i).get("SCI").toString());
				resultData.add(map);
			}
		}
		return resultData;
	}
	
	/**
	 * 获取沿线设施状况（List<Map>）
	 */
	public List<Map<String,Object>> getlineState(List<Record> recordList)
	{
		List<Map<String,Object>> resultData = new ArrayList<Map<String,Object>>();
		if(recordList != null && recordList.size() > 0)
		{
			for (int i = 0; i < recordList.size(); i++) {
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("KSZH", recordList.get(i).get("KSZH")==null?"":recordList.get(i).get("KSZH").toString());
				map.put("CD", recordList.get(i).get("CD")==null?"":recordList.get(i).get("CD").toString());
				double a = recordList.get(i).get("防护设施缺损轻");
				map.put("FHSSQS1", recordList.get(i).get("防护设施缺损轻")==null?"":recordList.get(i).get("防护设施缺损轻").toString());
				map.put("FHSSQS3", recordList.get(i).get("防护设施缺损重")==null?"":recordList.get(i).get("防护设施缺损重").toString());
				map.put("GLSSH", recordList.get(i).get("隔离栅损坏")==null?"":recordList.get(i).get("隔离栅损坏").toString());
				map.put("BZQS", recordList.get(i).get("标志缺损")==null?"":recordList.get(i).get("标志缺损").toString());
				map.put("BXQS", recordList.get(i).get("标线缺损")==null?"":recordList.get(i).get("标线缺损").toString());
				map.put("LHGHBS", recordList.get(i).get("绿化管护不善")==null?"":recordList.get(i).get("绿化管护不善").toString());
				map.put("TCI", recordList.get(i).get("TCI")==null?"":recordList.get(i).get("TCI").toString());
				resultData.add(map);
			}
		}
		return resultData;
	}
	
	
	
	/**
	 * 获取公路技术状况评定明细表（List<Map>）
	 */
	public List<Map<String,Object>> getRoadTechnicStateMX(List<Record> recordList)
	{
		List<Map<String,Object>> resultMXData = new ArrayList<Map<String,Object>>();
		if(recordList != null && recordList.size() > 0)
		{
			
			for (int i = 0; i < recordList.size(); i++) {
				Map<String,Object> map = new HashMap<String,Object>();
				String zh = recordList.get(i).get("startZhText").toString()+"-"+recordList.get(i).get("endZhText").toString();
				map.put("ZH", zh);
				map.put("CD",recordList.get(i).get("cd"));
				map.put("MQI", recordList.get(i).get("MQI"));
				map.put("PQI", recordList.get(i).get("PQI"));
				map.put("PCI", recordList.get(i).get("PCI"));
				map.put("RQI", recordList.get(i).get("RQI"));
				map.put("RDI", recordList.get(i).get("RDI"));
				map.put("SRI", recordList.get(i).get("SRI"));
				map.put("PSSI", recordList.get(i).get("PSSI"));
				map.put("SCI", recordList.get(i).get("SCI"));
				map.put("BCI", recordList.get(i).get("BCI"));
				map.put("TCI", recordList.get(i).get("TCI"));
				map.put("luxian", recordList.get(i).get("luxian"));
				resultMXData.add(map);
			}
		}
		return resultMXData;
	}
	
	
	public String getExcelName(String type)
	{
		String excelName = "Excel";
		if(type.equals("raodState"))
		{
			excelName = "高速公路沥青路面状况";
		}
		else if(type.equals("roadbedstate"))
		{
			excelName = "高速公路路基损坏状况";
		}
		else if(type.equals("linestate"))
		{
			excelName = "高速公路沿线设施状况";
		}
		else if(type.equals("roadtechnicstateMX"))
		{
			excelName = "公路技术状况评定明细";
		}
		else if(type.equals("roadtechnicstateHZ"))
		{
			excelName = "公路技术状况评定汇总";
		}
		return excelName;
	}
}
