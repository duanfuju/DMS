package com.junl.dms.mvc.binghaiindex;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.junl.dms.mvc.chooseinfo.ChooseInfo;
import com.junl.dms.mvc.chooseinfo.ChooseInfoService;
import com.platform.annotation.Controller;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseController;

@Controller(controllerKey = "/jf/manage/binghaiindex")
public class BingHaiIndexController extends BaseController {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(BingHaiIndexController.class);

	/**
	 * 首页
	 */
	public void index() {
		paging(ConstantInit.db_dataSource_main, splitPage, BingHaiIndex.sqlId_splitPageSelect,
				BingHaiIndex.sqlId_splitPageFrom);
		render("/manage/binghaiindex/list.html");
	}

	/**
	 * 显示新增页面
	 */
	public void showAdd() {
		List<ChooseInfo> wxmkList = ChooseInfoService.service.findListByType(2);
		setAttr("wxmkList", wxmkList);
		render("/manage/binghaiindex/add.html");
	}

	public void save() {
		JSONObject jsonObject = new JSONObject();
		try {
			BingHaiIndex bingHaiIndex = getModel(BingHaiIndex.class);
			boolean result = BingHaiIndexService.service.save(bingHaiIndex);
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
			boolean result = BingHaiIndexService.service.delete(ids);
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
		BingHaiIndex bingHaiIndex = BingHaiIndexService.service.findById(ids);
		setAttr("bingHaiIndex", bingHaiIndex);
		/*String level = bingHaiIndex.getStr("level");
		if(level!=null){
			setAttr("levels", level.split("\\|"));
		}else{
			setAttr("levels", "");
		}
		String gclDw = bingHaiIndex.getStr("gclDw");
		if(gclDw!=null){
			setAttr("gclDws", gclDw.split("\\|"));
		}else{
			setAttr("gclDws", "");
		}*/
		List<ChooseInfo> wxmkList = ChooseInfoService.service.findListByType(2);
		setAttr("wxmkList", wxmkList);
		setAttr("pageNumber", pageNumber);
		render("/manage/binghaiindex/update.html");
	}

	public void update() {
		JSONObject jsonObject = new JSONObject();
		try {
			BingHaiIndex bingHaiIndex = getModel(BingHaiIndex.class);
			boolean result = BingHaiIndexService.service.update(bingHaiIndex);
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
	 * 根据病害类型，查询病害指标
	 */
	public void getBingHai(){
		JSONObject jsonObject = new JSONObject();
		try {
			String wxmkId = getPara("wxmkId");
			List<Map<String, Object>> list = BingHaiIndexService.service.findByWxmkId(wxmkId);
			jsonObject.put("result", list);
			jsonObject.put("result_code", 1);
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("result_code", 0);
		}
		renderJson(jsonObject.toJSONString());
	}

}
