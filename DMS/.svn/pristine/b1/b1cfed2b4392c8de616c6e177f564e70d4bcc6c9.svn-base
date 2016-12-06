package com.junl.dms.mvc.fangxiang;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.junl.dms.mvc.luxian.LuXian;
import com.junl.dms.mvc.luxian.LuXianService;
import com.platform.annotation.Controller;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseController;

@Controller(controllerKey = "/jf/manage/fangxiang")
public class FangXiangController extends BaseController {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(FangXiangController.class);

	/**
	 * 首页
	 */
	public void index() {
		paging(ConstantInit.db_dataSource_main, splitPage, FangXiang.sqlId_splitPageSelect,
				FangXiang.sqlId_splitPageFrom);
		render("/manage/fangxiang/list.html");
	}

	/**
	 * 显示新增页面
	 */
	public void showAdd() {
		List<LuXian> luXianList = LuXianService.service.findList();
		setAttr("luXianList", luXianList);
		render("/manage/fangxiang/add.html");
	}

	public void save() {
		JSONObject jsonObject = new JSONObject();
		try {
			FangXiang fangXiang = getModel(FangXiang.class);
			boolean result = FangXiangService.service.save(fangXiang);
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
			boolean result = FangXiangService.service.delete(ids);
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
		FangXiang fangXiang = FangXiangService.service.findById(ids);
		setAttr("fangXiang", fangXiang);
		List<LuXian> luXianList = LuXianService.service.findList();
		setAttr("luXianList", luXianList);
		setAttr("pageNumber", pageNumber);
		render("/manage/fangxiang/update.html");
	}

	public void update() {
		JSONObject jsonObject = new JSONObject();
		try {
			FangXiang fangXiang = getModel(FangXiang.class);
			boolean result = FangXiangService.service.update(fangXiang);
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
	 * /jf/manage/fangxiang/findByLuXianId
	 */
	public void findByLuXianId(){
		JSONObject jsonObject=new JSONObject();
		try {
			String luXianId = getPara("luXianId");
			List<Map<String, String>> list = FangXiangService.service.findByLuXianId(luXianId);
			jsonObject.put("result_code", 1);
			jsonObject.put("result", list);
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("result_code", 0);
		}
		renderJson(jsonObject.toJSONString());
	}


}
