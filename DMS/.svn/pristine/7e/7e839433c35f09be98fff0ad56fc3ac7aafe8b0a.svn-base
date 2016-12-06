package com.platform.mvc.index;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.platform.mvc.base.BaseService;
import com.platform.mvc.menu.Menu;
import com.platform.mvc.role.Role;
import com.platform.mvc.station.Station;
import com.platform.mvc.user.User;

public class IndexService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(IndexService.class);

	public static final IndexService service  = new IndexService();
	
	
	
	
	//维修人员需要知道的
	public Map<String, Object> getNewsByWeiXiuRenYuan(){
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			List<Record> list=Db.find("select * from DMS_RW_task_info_relate where not exists(select 1 from xz_wx  where DMS_RW_task_info_relate.ids = xz_wx.taskInfoRelate)");
			if (list!=null) {
				map.put("info", "您当前有"+list.size()+"处任务未维修！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	//验收人员需要知道的
	public Map<String, Object> getNewsByYanShouRenYan(){
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			List<Record> list=Db.find("select * from DMS_CJ_chouJian  where state=1");
			if (list!=null) {
				map.put("info", "您当前有"+list.size()+"处未验收！");
			}else{
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	//获取小铃铛需要的信息
	public List<Map<String, Object>> getNews(User user) {
		List<Map<String, Object>> _list=new ArrayList<Map<String,Object>>();
		//根据登陆的用户获取他的权限
		String role=user.getGroupids();
		String[] role_s=role.substring(0, role.length()-1).split(",");
		String ids="";
		for (int i = 0; i < role_s.length; i++) {
			if (i==role_s.length-1) {
				ids+=role_s[i];
			}else{
				ids+=role_s[i]+"\',\'";
			}
		}
		List<Record> list=Db.find("select ids,version,numbers,names,description from pt_role where ids in('"+ids+"')");
		//根据权限选择合适的数据
		for (int i = 0; i < list.size(); i++) {
			Record r=list.get(i);
			String name=r.getStr("names");
			if (name.indexOf("维修")>=0) {
				Map<String, Object> map=getNewsByWeiXiuRenYuan();
				if (map!=null) {
					_list.add(map);
				}
			}else if (name.indexOf("验收")>=0) {
				Map<String, Object> map=getNewsByYanShouRenYan();
				if (map!=null) {
					_list.add(map);
				}
			}else if(name.indexOf("超级管理员")>=0){
				_list.clear();
				Map<String, Object> map1=getNewsByYanShouRenYan();
				if (map1!=null) {
					_list.add(map1);
				}
				Map<String, Object> map=getNewsByWeiXiuRenYuan();
				if (map!=null) {
					_list.add(map);
				}
				return _list;
			}
		}
		return _list;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	/**
	 * 查询用户可操作的菜单
	 * @param systemsIds
	 * @param user
	 * @param i18n
	 * @return
	 */
	public List<Menu> menu(String systemsIds, User user, String i18nColumnSuffix){
		String names = "names" + i18nColumnSuffix + " as names";
		
		//基于缓存查询
		StringBuilder operatorIdsSb = new StringBuilder();
		
		//根据分组查询权限
		String groupIds = user.getStr(User.column_groupids);
		if(null != groupIds){
			String[] groupIdsArr = groupIds.split(",");
			for (String roleIdsTemp : groupIdsArr) {
				Role role = Role.dao.cacheGet(roleIdsTemp);
				String operatorIdsStr = role.getStr(Role.column_operatorids);
				operatorIdsSb.append(operatorIdsStr);
			}
//			for (String groupIdsTemp : groupIdsArr) {
//				Group group = Group.dao.cacheGet(groupIdsTemp);
//				String roleIdsStr = group.getStr(Group.column_roleids);
//				String[] roleIdsArr = roleIdsStr.split(",");
//				for (String roleIdsTemp : roleIdsArr) {
//					Role role = Role.dao.cacheGet(roleIdsTemp);
//					String operatorIdsStr = role.getStr(Role.column_operatorids);
//					operatorIdsSb.append(operatorIdsStr);
//				}
//			}
		}
		
		//根据岗位查询权限
//		String stationIds = user.getStr(User.column_stationids);
//		if(null != stationIds){
//			String[] stationIdsArr = stationIds.split(",");
//			for (String ids : stationIdsArr) {
//				Station station = Station.dao.cacheGet(ids);
//				if(station!=null){
//					String operatorIdsStr = station.getStr(Station.column_operatorids);
//					operatorIdsSb.append(operatorIdsStr);
//				}
//				
//			}
//		}
		
		// 转换成过滤SQL
		String fitler = sqlIn(operatorIdsSb.toString()).replace("operator_", "");
		
		// 查询根菜单节点
		Menu menu = Menu.dao.findFirst(getSql(Menu.sqlId_rootId), systemsIds);
		String parentmenuids = menu.getPKValue();
		
		// 一级菜单
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("names", names);
		List<Menu> oneList = Menu.dao.find(getSqlByBeetl(Menu.sqlId_child, param), parentmenuids);
		param.put("fitler", fitler);
		for (Menu oneMenu : oneList) {
			// 二级菜单
			String sql = getSqlByBeetl(Menu.sqlId_operator, param);
			List<Menu> twoList = Menu.dao.find(sql, oneMenu.getPKValue());
			List<Menu> twoAllList = Menu.dao.find(getSqlByBeetl(Menu.sqlId_child, param), oneMenu.get("ids"));
			for (Menu thrMenu : twoAllList) {
				// 三级菜单
				String thrsql = getSqlByBeetl(Menu.sqlId_operator, param);
				List<Menu> thrList = Menu.dao.find(thrsql, thrMenu.getPKValue());
//				oneMenu.put("subList", twoList);
				if(thrList.size()>0){
					thrMenu.put("url", "isparent");
					List<Menu> threeList = Menu.dao.find(sql, thrMenu.getPKValue());
					thrMenu.put("subList", threeList);
					twoList.add(thrMenu);
				}
			}
			oneMenu.put("subList", twoList);
		}
		
		return oneList;
	}

	
	
}
