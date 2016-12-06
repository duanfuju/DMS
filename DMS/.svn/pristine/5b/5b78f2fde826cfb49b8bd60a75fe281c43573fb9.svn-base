package com.junl.dms.mvc.waydisease;



import java.util.List;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.upload.UploadFile;
import com.platform.annotation.Controller;
import com.platform.constant.ConstantInit;
import com.platform.constant.ConstantWebContext;
import com.platform.mvc.base.BaseController;
import com.platform.mvc.base.BaseService;
import com.platform.tools.StringUtils;


@Controller(controllerKey = "/jf/manage/waydisease")
public class WayDiseaseController extends BaseController {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(WayDiseaseController.class);
	
	
	/**
	 * 首页
	 */
	public void index() {
		String  userIds = getAttr(ConstantWebContext.request_cUserIds);
		String  roles = getCUser().getGroupids();
		//8a40c0353fa828a6013fa898d4ac0023 		role权限ids
		//03a44ba0aa4e4905bea726d4da976ba5		user管理员的ids
		if (roles.indexOf("8a40c0353fa828a6013fa898d4ac0023")>=0) {
			userIds="03a44ba0aa4e4905bea726d4da976ba5";
		}else{
			//根据用户ID查询该用户所在路段权限
			String sql=BaseService.service.getZHbyUserId(userIds,"startZHK","startZHM");
			splitPage.getQueryParam().put("sql", sql);
		}
		splitPage.getQueryParam().put("createUserId", userIds);
		if((splitPage.getOrderColunm() == null || ("").equals(splitPage.getOrderColunm()))
				&& (splitPage.getOrderMode() == null || ("").equals(splitPage.getOrderMode()))) {
			splitPage.setOrderColunm("createTime");
			splitPage.setOrderMode("desc");
		}
		paging(ConstantInit.db_dataSource_main, splitPage, WayDisease.sqlId_splitPageSelect, WayDisease.sqlId_splitPageFrom);
		render("/manage/waydisease/list.html");
	}
	
	public void save(){
		JSONObject jsonObject=new JSONObject();
		try {
			WayDisease way = getModel(WayDisease.class,"way");
			String  userIds = getAttr(ConstantWebContext.request_cUserIds);
			int zhs=way.getInt("startZHK")*1000+way.getInt("startZHM");
			int zhe = 0;
			if(StringUtils.isEmpty(StringUtils.changNull(way.get("endZHK"))) || StringUtils.isEmpty(StringUtils.changNull(way.get("endZHM"))))
			{
				zhe = zhs;
			}
			else
			{
				zhe=way.getInt("endZHK")*1000+way.getInt("endZHM");
			}
			//判断桩号是否符合范围
			if (BaseService.service.judgeZHScope(BaseService.service.getZHFanWeibyUserId(userIds),zhs,zhe)) {
				way.set("createUserId", getAttr(ConstantWebContext.request_cUserIds));
				//图片数组
				String[] paras = getParas("youwuImgs");
				boolean result = WayDiseaseService.service.save(way,paras);
				//获取刚才新增的DMS_WX_luMianBingHaiWeiXiu的ids
				String ids = way.getPKValue();
				//根据刚才的ids更新DMS_WX_luMianBingHaiWeiXiu_ceng_relate表  
				WayDiseaseCeng up = getModel(WayDiseaseCeng.class,"up");//获取上面层
				WayDiseaseCeng middle = getModel(WayDiseaseCeng.class,"middle");//获取中面层
				WayDiseaseCeng down = getModel(WayDiseaseCeng.class,"down");//获取下面层
				WayDiseaseCeng basic = getModel(WayDiseaseCeng.class,"basic");//基层
				
				WayDiseaseService.service.saveTable(up,ids);
				WayDiseaseService.service.saveTable(middle,ids);
				WayDiseaseService.service.saveTable(down,ids);
				WayDiseaseService.service.saveTable(basic,ids);
				
				String result_desc="新增失败！";
				if(result){
					result_desc="新增成功！";
				}
				jsonObject.put("result", result);
				jsonObject.put("result_desc", result_desc);
			}else{
				jsonObject.put("result", false);
				jsonObject.put("result_desc", "新增失败！输入的桩号不在范围内");
			}
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
			String imgs = WayDiseaseService.service.findById(ids).getStr("imgs");
			boolean result = WayDiseaseService.service.delete(getRequest(),ids);
			
			WayDiseaseService.service.deleteTable(ids);
			
			String result_desc="删除失败！";
			if(result){
				result_desc="删除成功！";
				deleteImages(imgs);
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
		String ids = getPara(0);
		WayDisease waydisease = WayDiseaseService.service.findById(ids);
		WayDisease wayTask = WayDiseaseService.service.findTask(ids);
		List<WayDiseaseCeng> findTable = WayDiseaseService.service.findTable(waydisease.getStr("ids"));
		for(int i=0;i<findTable.size();i++){
			setAttr("ceng"+i, findTable.get(i));
		}
		setAttr("way", waydisease);
		setAttr("wzMiaoShu", waydisease.get("wzMiaoShu"));
		setAttr("remark", waydisease.get("remark"));
		setAttr("wayTask", wayTask);
		render("/manage/waydisease/update.html");
	}
	
	public void update(){
		JSONObject jsonObject=new JSONObject();
		try {
			WayDisease way = getModel(WayDisease.class,"way");
			String  userIds = getAttr(ConstantWebContext.request_cUserIds);
			int zhs=way.getInt("startZHK")*1000+way.getInt("startZHM");
			int zhe = 0;
			if(StringUtils.isEmpty(StringUtils.changNull(way.get("endZHK"))) || StringUtils.isEmpty(StringUtils.changNull(way.get("endZHM"))))
			{
				zhe = zhs;
			}
			else
			{
				zhe=way.getInt("endZHK")*1000+way.getInt("endZHM");
			}
			//判断桩号是否符合范围
			if (BaseService.service.judgeZHScope(BaseService.service.getZHFanWeibyUserId(userIds),zhs,zhe)) {
				
				String[] paras = getParas("youwuImgs");
				boolean result = WayDiseaseService.service.update(way,paras);
				
				String weiXiuId = way.getStr("ids");
				
				//上面层
				WayDiseaseCeng ceng0 = getModel(WayDiseaseCeng.class, "ceng0");
				//中面层
				WayDiseaseCeng ceng1 = getModel(WayDiseaseCeng.class, "ceng1");
				//下面层
				WayDiseaseCeng ceng2 = getModel(WayDiseaseCeng.class, "ceng2");
				//基层
				WayDiseaseCeng ceng3 = getModel(WayDiseaseCeng.class, "ceng3");
				
				WayDiseaseService.service.updateTable(ceng0,weiXiuId);
				WayDiseaseService.service.updateTable(ceng1,weiXiuId);
				WayDiseaseService.service.updateTable(ceng2,weiXiuId);
				WayDiseaseService.service.updateTable(ceng3,weiXiuId);
				
				String result_desc="修改失败！";
				if(result){
					result_desc="修改成功！";
				}
				jsonObject.put("result", result);
				jsonObject.put("result_desc", result_desc);
			}else{
				jsonObject.put("result", false);
				jsonObject.put("result_desc", "修改失败！输入的桩号不在范围内");
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("result", false);
			jsonObject.put("result_desc", "操作失败！");
		}
		renderJson(jsonObject.toJSONString());
	}


	
	public void view(){
		String ids = getPara(0);
		WayDisease waydisease = WayDiseaseService.service.view(ids);
		
		WayDisease wayTask = WayDiseaseService.service.findTask(ids);
		setAttr("remark", waydisease.get("remark"));
		setAttr("way", waydisease);
		setAttr("wayTask", wayTask);
		
		List<WayDiseaseCeng> findTable = WayDiseaseService.service.findTable(waydisease.getStr("ids"));
		for(int i=0;i<findTable.size();i++){
			setAttr("ceng"+i, findTable.get(i));
		}
		render("/manage/waydisease/view.html");
	}
	
	
	/**
	 * 
	 * @method saveImgToJson
	 * @author tongzhenghan
	 * @date 2016年7月14日 下午4:51:02
	 * @description
	 *		新增上传图片
	 *
	 */
	public void saveImgToJson(){
		JSONObject jsonObject=new JSONObject();
		try{
			UploadFile file = getFile("uploadImg");
			jsonObject.put("fileName", file.getFileName());
		}catch(Exception e){
			e.printStackTrace();
		}
		renderJson(jsonObject.toJSONString());
	}
}


