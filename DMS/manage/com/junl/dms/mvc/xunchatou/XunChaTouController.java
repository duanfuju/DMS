package com.junl.dms.mvc.xunchatou;



import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.junl.dms.mvc.company.CompanyService;
import com.platform.annotation.Controller;
import com.platform.constant.ConstantInit;
import com.platform.constant.ConstantWebContext;
import com.platform.mvc.base.BaseController;
import com.platform.mvc.base.BaseService;



@Controller(controllerKey = "/jf/manage/xunChaTou")
public class XunChaTouController extends BaseController {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(XunChaTouController.class);
	
	
	/**
	 * 首页
	 */
	public void index() {
		String  userIds = getAttr(ConstantWebContext.request_cUserIds);
		//当用户的权限中有超级管理员的权限，则将查询条件中的用户的userIds设置为管理员的ids
		String  roles = getCUser().getGroupids();
		//8a40c0353fa828a6013fa898d4ac0023 		role权限ids
		//03a44ba0aa4e4905bea726d4da976ba5		user管理员的ids
		if (roles.indexOf("8a40c0353fa828a6013fa898d4ac0023")>=0) {
			userIds="03a44ba0aa4e4905bea726d4da976ba5";
		}else{
			//根据用户ID查询该用户所在路段权限
			String sql=BaseService.service.getZHbyUserId(userIds,"xct.xunChaStartZHK1","xct.xunChaStartZHM1");
			splitPage.getQueryParam().put("sql", sql);
		}
		
		splitPage.getQueryParam().put("createUserId", userIds);
		if((splitPage.getOrderColunm() == null || ("").equals(splitPage.getOrderColunm()))
				&& (splitPage.getOrderMode() == null || ("").equals(splitPage.getOrderMode()))) {
			splitPage.setOrderColunm("createTime");
			splitPage.setOrderMode("desc");
		}
		paging(ConstantInit.db_dataSource_main, splitPage, XunChaTou.sqlId_splitPageSelect, XunChaTou.sqlId_splitPageFrom);
		render("/manage/xunchatou/list.html");
	}
	
	
	
	
	
	/**
	 * 显示新增页面
	 */
	public void add(){
		/*List<LuXian> luXianList = LuXianService.service.findList();
		setAttr("luXianList", luXianList);*/
		setAttr("startTime", new Timestamp(System.currentTimeMillis()));
		render("/manage/xunchatou/add.html");
	}
	
	
	/**
	 * 完结 state=2
	 */
	public void over() {
		JSONObject jsonObject = new JSONObject();
		try {
			boolean flag =XunChaTouService.service.over(getPara() == null ? ids : getPara());
			String result="完结失败！";
			if (flag) {
				result="完结成功！";
			}
			jsonObject.put("result", result);
		} catch (Exception e1) {
			e1.printStackTrace();
			jsonObject.put("result", "操作失败");
		}
		renderJson(jsonObject.toJSONString());
	}

	
	/**
	 * 保存
	 */
	public void save() {
		JSONObject jsonObject = new JSONObject();
		try {
			String result="新增失败！";
			XunChaTou entity=getModel(XunChaTou.class);
			String  userIds = getAttr(ConstantWebContext.request_cUserIds);
			int zhs1=entity.getInt("xunChaStartZHK1")*1000+entity.getInt("xunChaStartZHM1");
			int zhe1=entity.getInt("xunChaEndZHK1")*1000+entity.getInt("xunChaEndZHM1");
			//判断桩号是否符合范围
			if (BaseService.service.judgeZHScope(BaseService.service.getZHFanWeibyUserId(userIds),zhs1,zhe1)) {
				int zhs2=entity.getInt("xunChaStartZHK2")*1000+entity.getInt("xunChaStartZHM2");
				int zhe2=entity.getInt("xunChaEndZHK2")*1000+entity.getInt("xunChaEndZHM2");
				if (BaseService.service.judgeZHScope(BaseService.service.getZHFanWeibyUserId(userIds),zhs2,zhe2)) {
					boolean flag =XunChaTouService.service.save(entity,userIds);
					if (flag) {
						result="新增成功！";
						jsonObject.put("flag", true);
					}
				}else{
					jsonObject.put("flag", false);
					result+="输入的桩号不在范围内";
				}
			}else{
				jsonObject.put("flag", false);
				result+="输入的桩号不在范围内";
			}
			jsonObject.put("result", result);
		} catch (Exception e1) {
			e1.printStackTrace();
			jsonObject.put("flag", false);
			jsonObject.put("result", "操作失败");
		}
		renderJson(jsonObject.toJSONString());
	}
	
	/**
	 * 准备更新
	 */
	public void edit() {
		String ids = getPara("ids");
		Integer pageNumber = getParaToInt("pageNumber");
		XunChaTou xunChaTou = XunChaTou.dao.findById(ids);
		List<Map<String, String>> list=CompanyService.service.findList();
		setAttr("xunChaTou", xunChaTou);
		setAttr("luXianId", xunChaTou.getStr("luXianId"));
		setAttr("cList", list);
		setAttr("pageNumber", pageNumber);
		render("/manage/xunchatou/update.html");
		
	}
	
	/**
	 * 更新
	 */
	public void update() {
		JSONObject jsonObject = new JSONObject();
		try {
			XunChaTou entity=getModel(XunChaTou.class);
			
			//判断桩号是否符合范围
			String  userIds = getAttr(ConstantWebContext.request_cUserIds);
			int zhs1=entity.getInt("xunChaStartZHK1")*1000+entity.getInt("xunChaStartZHM1");
			int zhe1=entity.getInt("xunChaEndZHK1")*1000+entity.getInt("xunChaEndZHM1");
			if (BaseService.service.judgeZHScope(BaseService.service.getZHFanWeibyUserId(userIds),zhs1,zhe1)) {
				int zhs2=entity.getInt("xunChaStartZHK2")*1000+entity.getInt("xunChaStartZHM2");
				int zhe2=entity.getInt("xunChaEndZHK2")*1000+entity.getInt("xunChaEndZHM2");
				if (BaseService.service.judgeZHScope(BaseService.service.getZHFanWeibyUserId(userIds),zhs2,zhe2)) {
					boolean flag =XunChaTouService.service.update(entity);
					if (flag) {
						jsonObject.put("flag", true);
						jsonObject.put("result", "修改成功!");
					}
				}else{
					jsonObject.put("flag", false);
					jsonObject.put("result", "操作失败,输入的桩号不在范围内");
				}
			}else{
				jsonObject.put("result", "操作失败,输入的桩号不在范围内");
				jsonObject.put("flag", false);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			jsonObject.put("flag", false);
			jsonObject.put("result", "操作失败");
		}
		renderJson(jsonObject.toJSONString());
	}
	/**
	 * 查看
	 */
	public void view() {
		String ids = getPara("ids");
		Integer pageNumber = getParaToInt("pageNumber");
		XunChaTou xunChaTou = XunChaTou.dao.findById(ids);
		List<Map<String, String>> list=CompanyService.service.findList();
		setAttr("xunChaTou", xunChaTou);
		setAttr("luXianId", xunChaTou.getStr("luXianId"));
		setAttr("cList", list);
		setAttr("pageNumber", pageNumber);
		render("/manage/xunchatou/view.html");
	}
	/**
	 * 删除
	 */
	public void delete() {
		JSONObject jsonObject=new JSONObject();
		try {
			String ids = getPara("ids");
			boolean flag=XunChaTouService.service.delete(ids);
			String result_desc="删除失败！";
			if(flag ){
				result_desc="删除成功！";
			}
			jsonObject.put("result", flag);
			jsonObject.put("result_desc", result_desc);
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("result", false);
			jsonObject.put("result_desc", "操作失败！");
		}
		renderJson(jsonObject.toJSONString());
	}
	
	

}


