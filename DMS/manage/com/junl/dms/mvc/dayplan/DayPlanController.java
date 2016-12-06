package com.junl.dms.mvc.dayplan;



import java.util.List;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.junl.dms.mvc.luxian.LuXian;
import com.junl.dms.mvc.luxian.LuXianService;
import com.platform.annotation.Controller;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseController;


@Controller(controllerKey = "/jf/manage/dayplan")
public class DayPlanController extends BaseController {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(DayPlanController.class);
	
	
	/**
	 * 首页
	 */
	public void index() {
		paging(ConstantInit.db_dataSource_main, splitPage, DayPlan.sqlId_splitPageSelect, DayPlan.sqlId_splitPageFrom);
		render("/manage/dayplan/list.html");
	}
	
	/**
	 * /jf/manage/dayplan/select
	 */
	public void select(){
		splitPage.setPageSize(50);
		paging(ConstantInit.db_dataSource_main, splitPage, DayPlan.sqlId_splitPageSelect_findByDate, DayPlan.sqlId_splitPageFrom_findByDate);
		render("/manage/monthplan/chooseCheckBox.html");
	}
	
	
	/**
	 * 向新增界面跳转
	 */
	public void add() {
		List<LuXian> luXianList = LuXianService.service.findList();
		setAttr("luXianList", luXianList);
		render("/manage/dayplan/add.html");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public void save(){
		JSONObject jsonObject=new JSONObject();
		try {
			DayPlan dayPlan = getModel(DayPlan.class);
			boolean result = DayPlanService.service.save(dayPlan);
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
			boolean result = DayPlanService.service.delete(ids);
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
		DayPlan dayPlan = DayPlanService.service.findById(ids);
		setAttr("dayPlan", dayPlan);
		setAttr("pageNumber", pageNumber);
		List<LuXian> luXianList = LuXianService.service.findList();
		setAttr("luXianList", luXianList);
		render("/manage/dayplan/update.html");
	}
	
	public void view(){
		String ids = getPara("ids");
		Integer pageNumber = getParaToInt("pageNumber");
		DayPlan dayPlan = DayPlanService.service.findById(ids);
		setAttr("dayPlan", dayPlan);
		setAttr("pageNumber", pageNumber);
		List<LuXian> luXianList = LuXianService.service.findList();
		setAttr("luXianList", luXianList);
		render("/manage/dayplan/view.html");
	}
	
	
	public void update(){
		JSONObject jsonObject=new JSONObject();
		try {
			DayPlan dayPlan = getModel(DayPlan.class);
			boolean result = DayPlanService.service.update(dayPlan);
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
		renderJson(jsonObject.toJSONString());
	}

	

}


