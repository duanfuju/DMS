package com.junl.dms.mvc.company;



import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.platform.annotation.Controller;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseController;


@Controller(controllerKey = "/jf/manage/company")
public class CompanyController extends BaseController {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(CompanyController.class);
	
	
	/**
	 * 首页
	 */
	public void index() {
		paging(ConstantInit.db_dataSource_main, splitPage, Company.sqlId_splitPageSelect, Company.sqlId_splitPageFrom);
		render("/manage/company/list.html");
	}
	
	public void save(){
		JSONObject jsonObject=new JSONObject();
		try {
			Company company = getModel(Company.class);
			company.set("state", "1");
			boolean result = CompanyService.service.save(company);
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
			Company company = CompanyService.service.findById(ids);
			company.set("state", "0");
//			boolean result = CompanyService.service.delete(ids);
			boolean result = CompanyService.service.update(company);
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
		Company company = CompanyService.service.findById(ids);
		setAttr("company", company);
		setAttr("pageNumber", pageNumber);
		render("/manage/company/update.html");
	}
	
	public void update(){
		JSONObject jsonObject=new JSONObject();
		try {
			Company company = getModel(Company.class);
			boolean result = CompanyService.service.update(company);
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

	public void getList(){
		JSONObject jsonObject=new JSONObject();
		try {
			List<Map<String, String>> list=CompanyService.service.findList();
			jsonObject.put("result_code", 1);
			jsonObject.put("result", list);
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("result_code", 0);
		}
		renderJson(jsonObject.toJSONString());
	}
}