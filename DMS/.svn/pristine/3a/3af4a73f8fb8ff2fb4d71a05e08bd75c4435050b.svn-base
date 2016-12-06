package com.junl.dms.mvc.yangHuLog;



import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.junl.dms.mvc.bycenter.ByCenterService;
import com.junl.dms.mvc.dayplan.DayPlan;
import com.junl.dms.mvc.dayplan.DayPlanService;
import com.junl.dms.mvc.luxian.LuXian;
import com.junl.dms.mvc.luxian.LuXianService;
import com.junl.dms.mvc.monthplan.MonthPlan;
import com.platform.annotation.Controller;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseController;


@Controller(controllerKey = "/jf/manage/yanghulog")
public class YangHuLogController extends BaseController {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(YangHuLogController.class);
	
	
	/**
	 * 首页
	 */
	public void index() {
		paging(ConstantInit.db_dataSource_main, splitPage, YangHuLog.sqlId_splitPageSelect, YangHuLog.sqlId_splitPageFrom);
		render("/manage/yanghulog/list.html");
	}
	
	
	/**
	 * 跳转选择列表	/jf/manage/yanghulog/choose
	 */
	public void choose() {
		paging(ConstantInit.db_dataSource_main, splitPage, MonthPlan.sqlId_splitPageSelect, MonthPlan.sqlId_splitPageFrom);
		render("/manage/yanghulog/choose.html");
	}
	
	
	
	
	
	
	
	/**
	 * 向新增界面跳转
	 */
	public void add() {
		List<Map<String, String>> byList=ByCenterService.service.findList();
		setAttr("byList", byList);
		List<LuXian> luXianList = LuXianService.service.findList();
		setAttr("luXianList", luXianList);
		render("/manage/yanghulog/add.html");
	}
	
	
	
	
	
	
	
	
	public void save(){
		JSONObject jsonObject=new JSONObject();
		try {
			YangHuLog yangHuLog = getModel(YangHuLog.class);
			boolean result = YangHuLogService.service.save(yangHuLog);
			String result_desc="新增失败！";
			if(result){
				result_desc="新增成功！";
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
	
	public void delete(){
		JSONObject jsonObject=new JSONObject();
		try {
			String ids = getPara("ids");
			boolean result = YangHuLogService.service.delete(ids);
			String result_desc="删除失败！";
			if(result){
				result_desc="删除成功！";
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
	
	public void edit(){
		String ids = getPara("ids");
		Integer pageNumber = getParaToInt("pageNumber");
		YangHuLog yangHuLog = YangHuLogService.service.findById(ids);
		DayPlan dayPlan = DayPlanService.service.findById(yangHuLog.getStr("dayIds"));
		setAttr("yangHuLog", yangHuLog);
		setAttr("dayplan", dayPlan);
		setAttr("pageNumber", pageNumber);
		
		List<LuXian> luXianList = LuXianService.service.findList();
		setAttr("luXianList", luXianList);
		
		List<Map<String, String>> bcList=ByCenterService.service.findList();
		setAttr("bcList", bcList);
		render("/manage/yanghulog/update.html");
	}
	public void view(){
		String ids = getPara("ids");
		Integer pageNumber = getParaToInt("pageNumber");
		YangHuLog yangHuLog = YangHuLogService.service.findById(ids);
		DayPlan dayPlan = DayPlanService.service.findById(yangHuLog.getStr("dayIds"));
		setAttr("yangHuLog", yangHuLog);
		setAttr("dayplan", dayPlan);
		setAttr("pageNumber", pageNumber);
		
		List<LuXian> luXianList = LuXianService.service.findList();
		setAttr("luXianList", luXianList);
		
		List<Map<String, String>> bcList=ByCenterService.service.findList();
		setAttr("bcList", bcList);
		render("/manage/yanghulog/view.html");
	}
	
	public void update(){
		JSONObject jsonObject=new JSONObject();
		try {
			YangHuLog yangHuLog = getModel(YangHuLog.class);
			boolean result = YangHuLogService.service.update(yangHuLog);
			String result_desc="修改失败！";
			if(result){
				result_desc="修改成功！";
			}
			jsonObject.put("result", result);
			jsonObject.put("result_desc", result_desc);
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("result", false);
			jsonObject.put("result_desc", "操作失败！");
		}
		System.out.println("yanghulog!!");
		renderJson(jsonObject.toJSONString());
	}

	

}


