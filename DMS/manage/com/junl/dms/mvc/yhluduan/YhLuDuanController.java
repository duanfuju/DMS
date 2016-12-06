package com.junl.dms.mvc.yhluduan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.Record;
import com.junl.dms.mvc.luxian.LuXian;
import com.junl.dms.mvc.luxian.LuXianService;
import com.platform.annotation.Controller;
import com.platform.constant.ConstantInit;
import com.platform.constant.ConstantWebContext;
import com.platform.mvc.base.BaseController;

@Controller(controllerKey = "/jf/manage/yhluduan")
public class YhLuDuanController extends BaseController {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(YhLuDuanController.class);

	/**
	 * 首页
	 */
	public void index() {
		paging(ConstantInit.db_dataSource_main, splitPage, YhLuDuan.sqlId_splitPageSelect,
				YhLuDuan.sqlId_splitPageFrom);
		render("/manage/yhluduan/list.html");
	}

	/**
	 * 显示新增页面
	 */
	public void showAdd() {
		List<LuXian> luXianList = LuXianService.service.findList();
		setAttr("luXianList", luXianList);
		render("/manage/yhluduan/add.html");
	}

	public void save() {
		JSONObject jsonObject = new JSONObject();
		try {
			YhLuDuan yhluduan = getModel(YhLuDuan.class);
			boolean result = YhLuDuanService.service.save(yhluduan);
			String result_desc = "新增失败！";
			if (result) {
				result_desc = "新增成功！";
			}
			jsonObject.put("result", result);
			jsonObject.put("result_desc", result_desc);
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("result", false);
			jsonObject.put("result_desc", "操作失败！");
		}
		renderJson(jsonObject.toJSONString());
	}

	public void delete() {
		JSONObject jsonObject = new JSONObject();
		try {
			String ids = getPara("ids");
			boolean result = YhLuDuanService.service.delete(ids);
			String result_desc = "删除失败！";
			if (result) {
				result_desc = "删除成功！";
			}
			jsonObject.put("result", result);
			jsonObject.put("result_desc", result_desc);
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("result", false);
			jsonObject.put("result_desc", "操作失败！");
		}
		renderJson(jsonObject.toJSONString());
	}

	/**
	 * 显示修改页面
	 */
	public void showUpdate() {
		String ids = getPara("ids");
		Integer pageNumber = getParaToInt("pageNumber");
		YhLuDuan yhLuDuan = YhLuDuanService.service.findById(ids);
		setAttr("yhLuDuan", yhLuDuan);
		List<LuXian> luXianList = LuXianService.service.findList();
		setAttr("luXianList", luXianList);
		setAttr("pageNumber", pageNumber);
		render("/manage/yhluduan/update.html");
	}

	public void update() {
		JSONObject jsonObject = new JSONObject();
		try {
			YhLuDuan yhluduan = getModel(YhLuDuan.class);
			boolean result = YhLuDuanService.service.update(yhluduan);
			String result_desc = "修改失败！";
			if (result) {
				result_desc = "修改成功！";
			}
			jsonObject.put("result", result);
			jsonObject.put("result_desc", result_desc);
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("result", false);
			jsonObject.put("result_desc", "操作失败！");
		}
		renderJson(jsonObject.toJSONString());
	}
	
	/**
	 * 选择养护路段
	 */
	public void choose() {
		paging(ConstantInit.db_dataSource_main, splitPage, YhLuDuan.sqlId_splitPageSelect,
				YhLuDuan.sqlId_splitPageFrom);
		render("/manage/yhluduan/choose.html");
	}
	
	/**
	 * 根据				
	 */
	public void findLuXianByUserIds(){
		JSONObject jsonObject = new JSONObject();
		String  userIds = getAttr(ConstantWebContext.request_cUserIds);
		//根据用户的ids获取所需的养护路段的ids集合
		List<Record> i=YhLuDuan.dao.findYangHuLuDuanIdByUserInfoIds(userIds);
		//切割用户信息表中养护路段的ids集合
		String[] yhld_s=i.get(0).getStr("yangHuLuDuanId").split(",");
		//根据用户信息表中的养护路段的ids获取养护路段信息
		List<Map<String, String>> list =new ArrayList<Map<String, String>>();
		try {
			if (yhld_s.length>0) {
				for (int j = 0; j < yhld_s.length; j++) {
					YhLuDuan y=YhLuDuan.dao.findById(yhld_s[j]);
					//根据养护路段信息中的路线ids获取路线的信息
					LuXian luXian=LuXianService.service.findById(y.getStr("luXianId"));
					Map<String, String> map = new HashMap<String, String>();
					map.put("luXianName", luXian.getStr("luXianName"));
					map.put("luXianId", luXian.getStr("ids"));
					map.put("startZHK", luXian.get("startZHK").toString());
					map.put("startZHM", luXian.get("startZHM").toString());
					map.put("endZHK", luXian.get("endZHK").toString());
					map.put("endZHM", luXian.get("endZHM").toString());
					list.add(map);
				}
			}
			jsonObject.put("result_code", 1);
			jsonObject.put("result", list);
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("result", false);
			jsonObject.put("result_desc", "操作失败！");
		}
		renderJson(jsonObject.toJSONString());
		
	}

}
