package com.junl.dms.mvc.checkinfo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.junl.dms.mvc.mappoint.MapPointController;
import com.platform.annotation.Controller;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseController;

@Controller(controllerKey = "/jf/manage/checkInfo")
public class CheckInfoController extends BaseController {
	
	private static Logger log = Logger.getLogger(CheckInfoController.class);
	
	/**
	 * 跳转验收信息配置首页
	 */ 
	public void index() {
		render("/manage/checkinfo/index.html");
	}
	
	/**
	 * 获取父级菜单
	 */
	public void getParentMenu()
	{
		JSONObject json = new JSONObject();
		List<Map> menuMapList = CheckInfoServiceImpl.getIntance().getParentMenuMap();
		json.put("menu", menuMapList);
		renderJson(json.toJSONString());
	}
	/**
	 * /jf/manage/checkInfo/menuList
	 */
	public void menuList(){
		paging(ConstantInit.db_dataSource_main, splitPage, CheckInfo.sqlId_splitPageSelect, CheckInfo.sqlId_splitPageFrom);
		render("/manage/checkinfo/list.html");
	}
	
	public void add()
	{
		JSONObject json = new JSONObject();
		boolean isSuccess = false;
		String parentId = "";
		boolean isAdd = false;
		CheckInfo checkInfo = getModel(CheckInfo.class);
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			checkInfo.set("updateTime", sdf.format(new Date()) );
			String ids = checkInfo.get("ids");			
			if(ids.trim() != null && !ids.trim().equals(""))
			{
				checkInfo.set("isdel", 0);
				isSuccess =  checkInfo.update();
				isAdd = false;
			}
			else
			{
				isSuccess =  checkInfo.save();
				isAdd = true;
			}
			parentId = checkInfo.get("parentId");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("/jf/manage/checkInfo/add   验收信息配置新增 或修改出错");
		}
		json.put("isSuccess", isSuccess);
		json.put("isparent", checkInfo.get("isparent"));
		json.put("ids", checkInfo.get("ids"));
		json.put("name", checkInfo.get("name"));
		json.put("parentId", checkInfo.get("parentId"));
		
		//json.put("checkInfo", checkInfo);
		json.put("isAdd", isAdd);
		renderJson(json.toJSONString());
	}
	
	public void getCheckInfo(CheckInfo checkInfo)
	{
		if(getPara("name") == null)
		{
			checkInfo.set("name", "");
		}
		else
		{
			checkInfo.set("name", getPara("name"));
		}
		
	}
	
	
	public void update()
	{
		render("/manage/checkinfo/update.html");
	}
	
	public void delete()
	{
		JSONObject json = new JSONObject();
		boolean isSuccess = false;
		try {
			String id = getPara("ids");
			CheckInfo checkInfo = new CheckInfo();
			checkInfo = checkInfo.findById(id);
			checkInfo.set("isdel", 1);
//			String sql = " update DMS_PZ_checkInfo set isdel = 1 where ids = '"+id+"'";
			isSuccess = checkInfo.update();
		} catch (Exception e) {
			log.error("/jf/manage/checkInfo/delete   验收信息配置删除出错");
		}
		json.put("isSuccess", isSuccess);
		renderJson(json.toJSONString());
	}
	
	public void getCheckInfoByIds()
	{
		String id = getPara("ids");
		CheckInfo checkInfo = new CheckInfo();
		checkInfo = checkInfo.findById(id);
		setAttr("checkInfo", checkInfo);
		render("/manage/checkinfo/add.html");
	}
	

}
