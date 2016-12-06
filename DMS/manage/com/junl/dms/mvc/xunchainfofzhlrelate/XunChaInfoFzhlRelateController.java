package com.junl.dms.mvc.xunchainfofzhlrelate;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.platform.annotation.Controller;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseController;
@Controller(controllerKey = "/jf/manage/xunchainfofzhlrelate")
public class XunChaInfoFzhlRelateController extends BaseController {
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(XunChaInfoFzhlRelateController.class);
	
	public void index() {
		paging(ConstantInit.db_dataSource_main, splitPage, XunChaInfoFzhlRelate.sqlId_splitPageSelect, XunChaInfoFzhlRelate.sqlId_splitPageFrom);
		render("/manage/xunchainfofzhlrelate/list.html");
	}
	
	public void add(){
		JSONObject jsonObject=new JSONObject();
		try{
			XunChaInfoFzhlRelate xunChaInfoFzhlRelate = getModel(XunChaInfoFzhlRelate.class);
			boolean result = XunChaInfoFzhlRelateService.service.save(xunChaInfoFzhlRelate);
			String result_desc="新增失败！";
			if(result){
				result_desc="新增成功！";
			}
			jsonObject.put("result", result);
			jsonObject.put("result_desc", result_desc);
		}catch(Exception e){
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
			boolean result = XunChaInfoFzhlRelateService.service.delete(ids);
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
	
	public void showUpdate(){
		String ids = getPara("ids");
		Integer pageNumber = getParaToInt("pageNumber");
		XunChaInfoFzhlRelate xunChaInfoFzhlRelate = XunChaInfoFzhlRelateService.service.findById(ids);
		setAttr("fangZHL", xunChaInfoFzhlRelate);
		setAttr("pageNumber", pageNumber);
		render("/manage/xunchainfofzhlrelate/update.html");
	}
	
	public void update(){
		JSONObject jsonObject=new JSONObject();
		try {
			XunChaInfoFzhlRelate xunChaInfoFzhlRelate = getModel(XunChaInfoFzhlRelate.class);
			boolean result = XunChaInfoFzhlRelateService.service.update(xunChaInfoFzhlRelate);
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
