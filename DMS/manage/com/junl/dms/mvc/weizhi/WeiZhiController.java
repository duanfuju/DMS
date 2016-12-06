package com.junl.dms.mvc.weizhi;



import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.junl.dms.mvc.chooseinfo.ChooseInfo;
import com.junl.dms.mvc.chooseinfo.ChooseInfoService;
import com.junl.dms.mvc.luxian.LuXian;
import com.junl.dms.mvc.luxian.LuXianService;
import com.platform.annotation.Controller;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseController;


@Controller(controllerKey = "/jf/manage/weizhi")
public class WeiZhiController extends BaseController {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(WeiZhiController.class);
	
	
	/**
	 * 首页
	 */
	public void index() {
		if((splitPage.getOrderColunm() == null || ("").equals(splitPage.getOrderColunm()))
				&& (splitPage.getOrderMode() == null || ("").equals(splitPage.getOrderMode()))) {
			splitPage.setOrderColunm("wzTypeName");
			splitPage.setOrderMode("desc");
		}
		List<LuXian> luXianList = LuXianService.service.findList();
		setAttr("luXianList", luXianList);
		paging(ConstantInit.db_dataSource_main, splitPage, WeiZhi.sqlId_splitPageSelect, WeiZhi.sqlId_splitPageFrom);
		render("/manage/weizhi/list.html");
	}
	
	
	/**
	 * 显示新增页面
	 */
	public void showAdd(){
		List<LuXian> luXianList = LuXianService.service.findList();
		setAttr("luXianList", luXianList);
		List<ChooseInfo> wzTypeList = ChooseInfoService.service.findListByType(1);
		setAttr("wzTypeList", wzTypeList);
		render("/manage/weizhi/add.html");
	}
	
	
	public void save(){
		JSONObject jsonObject=new JSONObject();
		try {
			WeiZhi weiZhi = getModel(WeiZhi.class);
			boolean result =WeiZhiService.service.save(weiZhi);	
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
			boolean result = WeiZhiService.service.delete(ids);
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
	
	/**
	 * 显示新增页面
	 */
	public void showUpdate(){
		String ids = getPara("ids");
		Integer pageNumber = getParaToInt("pageNumber");
		WeiZhi weiZhi = WeiZhiService.service.findById(ids);
		setAttr("weiZhi", weiZhi);
		List<LuXian> luXianList = LuXianService.service.findList();
		setAttr("luXianList", luXianList);
		List<ChooseInfo> wzTypeList = ChooseInfoService.service.findListByType(1);
		setAttr("wzTypeList", wzTypeList);
		setAttr("pageNumber", pageNumber);
		render("/manage/weizhi/update.html");
	}
	
	public void update(){
		JSONObject jsonObject=new JSONObject();
		try {
			WeiZhi weiZhi = getModel(WeiZhi.class);
			boolean result = WeiZhiService.service.update(weiZhi);
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
	
	/**
	 * 得到位置类型
	 */
	public void getWzType(){
		JSONObject jsonObject=new JSONObject();
		try {
			String luXianId = getPara("luXianId");
			List<Map<String, String>> list = WeiZhiService.service.findWzTypeByLuXianId(luXianId);
			jsonObject.put("result_code", 1);
			jsonObject.put("result", list);
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("result_code", 0);
		}
		renderJson(jsonObject.toJSONString());
	}
	
	/**
	 * 得到位置名称
	 */
	public void getWzName(){
		JSONObject jsonObject=new JSONObject();
		try {
			String luXianId = getPara("luXianId");
			String wzTypeId = getPara("wzTypeId");
			List<Map<String, String>> list = WeiZhiService.service.findWzNameByLxIdAndWzType(luXianId,wzTypeId);
			jsonObject.put("result_code", 1);
			jsonObject.put("result", list);
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("result_code", 0);
		}
		renderJson(jsonObject.toJSONString());
	}
	
	/**
	 * 得到位置描述
	 */
	public void getWzMiaoShu(){
		JSONObject jsonObject=new JSONObject();
		try {
			String luXianId = getPara("luXianId");
			String wzTypeId = getPara("wzTypeId");
			String wzName = getPara("wzName");
			List<Map<String, String>> list = WeiZhiService.service.findWzMiaoShuByLxIdAndWzTypeAndName(luXianId,wzTypeId,wzName);
			jsonObject.put("result_code", 1);
			jsonObject.put("result", list);
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("result_code", 0);
		}
		renderJson(jsonObject.toJSONString());
	}

	

}


