package com.junl.dms.mvc.xunchainfo;




import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.upload.UploadFile;
import com.junl.dms.mvc.chooseinfo.ChooseInfo;
import com.junl.dms.mvc.chooseinfo.ChooseInfoService;
import com.junl.dms.mvc.fangzhl.FangZHLService;
import com.junl.dms.mvc.fzhlwx.FzhlWeiXiuService;
import com.junl.dms.mvc.luxian.LuXian;
import com.junl.dms.mvc.luxian.LuXianService;
import com.junl.dms.mvc.xunchainfofzhlrelate.XunChaInfoFzhlRelate;
import com.junl.dms.mvc.xunchatou.XunChaTou;
import com.junl.dms.mvc.xunchatou.XunChaTouService;
import com.junl.dms.tools.ToolsFile;
import com.platform.annotation.Controller;
import com.platform.constant.ConstantInit;
import com.platform.constant.ConstantWebContext;
import com.platform.mvc.base.BaseController;
import com.platform.mvc.base.BaseService;
import com.platform.tools.StringUtils;


@Controller(controllerKey = "/jf/manage/xunChaInfo")
public class XunChaInfoController extends BaseController {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(XunChaInfoController.class);

	
	/**
	 * 首页
	 */
	public void index() {
		if((splitPage.getOrderColunm() == null || ("").equals(splitPage.getOrderColunm()))
				&& (splitPage.getOrderMode() == null || ("").equals(splitPage.getOrderMode()))) {
			splitPage.setOrderColunm("createTime");
			splitPage.setOrderMode("desc");
		}
		String  userIds = getAttr(ConstantWebContext.request_cUserIds);
		//当用户的权限中有超级管理员的权限，则将查询条件中的用户的userIds设置为管理员的ids
		String  roles = getCUser().getGroupids();
		//8a40c0353fa828a6013fa898d4ac0023 		role权限ids
		//03a44ba0aa4e4905bea726d4da976ba5		user管理员的ids
		if (roles.indexOf("8a40c0353fa828a6013fa898d4ac0023")>=0) {
			userIds="03a44ba0aa4e4905bea726d4da976ba5";
		}else{
			//根据用户ID查询该用户所在路段权限
			String sql=BaseService.service.getZHbyUserId(userIds,"info.xunChaStartZHK","info.xunChaStartZHM");
			splitPage.getQueryParam().put("sql", sql);
		}
		
		
		splitPage.getQueryParam().put("xunChaUserId", userIds);
		paging(ConstantInit.db_dataSource_main, splitPage, XunChaInfo.sqlId_splitPageSelect, XunChaInfo.sqlId_splitPageFrom);
		render("/manage/xunchainfo/list.html");
	}
	
	/**
	 * 根据ids获取巡查信息	/jf/manage/xunChaInfo/getXunChaInfoByIds
	 */
	public void getXunChaInfoByIds(){
		JSONObject jsonObject = new JSONObject();
		try{
			String ids=getPara("ids");
			Map<String, Object> result = XunChaInfoService.service.getXunChaInfoByIds(ids);
			boolean flag=false;
			if (result.size()>0) {
				flag=true;
			}
			jsonObject.put("result", result);
			jsonObject.put("flag", flag);
		}catch(Exception e){
			e.printStackTrace();
			jsonObject.put("flag", false);
		}
		renderJson(jsonObject.toJSONString());
	}
	
	
	/**
	 * 查询巡查头，将巡查头ids绑定到巡查信息中
	 */
	public void bindingXunChaTouId() {	
		
		if((splitPage.getOrderColunm() == null || ("").equals(splitPage.getOrderColunm()))
				&& (splitPage.getOrderMode() == null || ("").equals(splitPage.getOrderMode()))) {
			splitPage.setOrderColunm("createTime");
			splitPage.setOrderMode("desc");
		}
		//设置条件
		//1.未完结的巡查头
		//state<2
		/*splitPage.getQueryParam().put("state", 0);*/
		//2.只能被自己查询到
		String  userIds = getAttr(ConstantWebContext.request_cUserIds);
		splitPage.getQueryParam().put("xunChaUserId", userIds);
		
		paging(ConstantInit.db_dataSource_main, splitPage, XunChaTou.sqlId_splitPageSelect, XunChaTou.sqlId_splitPageFrom);
		render("/manage/xunchainfo/select.html");
	}
	/**
	 * 保存
	 */
	public void save() {
		JSONObject jsonObject = new JSONObject();
		try{
			boolean flag=false;
			String result_desc="新增失败！";
			//图片数组
			String[] paras = getParas("uploadImgs");
			XunChaInfo entity=getModel(XunChaInfo.class);
			//判断输入的桩号是否符合范围
			String  userIds = getAttr(ConstantWebContext.request_cUserIds);
			int zhs=entity.getInt("xunChaStartZHK")*1000+entity.getInt("xunChaStartZHM");
			int zhe = 0;
			if(StringUtils.isEmpty(StringUtils.changNull(entity.get("xunChaEndZHK"))) || StringUtils.isEmpty(StringUtils.changNull(entity.get("xunChaEndZHM"))))
			{
				zhe = zhs;
			}
			else
			{
				zhe=entity.getInt("xunChaEndZHK")*1000+entity.getInt("xunChaEndZHM");
			}
			if (BaseService.service.judgeZHScope(BaseService.service.getZHFanWeibyUserId(userIds),zhs,zhe)) {
				List<XunChaInfoFzhlRelate> chaInfoFzhlRelates = getModels(XunChaInfoFzhlRelate.class);
				entity.set("xunChaUserId",	userIds);
				 flag = XunChaInfoService.service.save(entity,paras,chaInfoFzhlRelates);	
				if(flag){
					result_desc="新增成功！";
				}
				jsonObject.put("flag", flag);
				jsonObject.put("result_desc", result_desc);
			}else{
				jsonObject.put("flag", flag);
				jsonObject.put("result_desc", result_desc+"输入的桩号不在范围内");
			}
		}catch(Exception e){
			e.printStackTrace();
			jsonObject.put("flag", false);
			jsonObject.put("result_desc", "操作失败！");
		}
		renderJson(jsonObject.toJSONString());
	}
	/**
	 * 获取文件名以及上传路径
	 */
	public void getFileName(){
		JSONObject jsonObject=new JSONObject();
		try{

			UploadFile upFile = getFile();
			File file = upFile.getFile();

			String serverpath = ToolsFile.getPathUrl(getRequest());

			//上传的绝对路径
			String filePath = serverpath   + file.getName();
			
			File destFile = new File(filePath);
			FileUtils.copyFile(file, destFile);

			String uploadFileName = PropKit.get("tomcat.imgFolderName");
			jsonObject.put("url", uploadFileName+"/file/"+ToolsFile.getPathDate()+"/"+file.getName());
			jsonObject.put("fileName", upFile.getFileName());

		}catch(Exception e){
			e.printStackTrace();
		}
		renderJson(jsonObject.toJSONString());
	}
	
	/**
	 * 准备更新
	 */
	public void edit() {
		String ids = getPara("ids");
		//主表部分
		XunChaInfo xunChaInfo = XunChaInfoService.service.findById(ids);
		//主表中某字段的下拉框
		List<ChooseInfo> chooseInfo=ChooseInfoService.service.findListByType(2);
		//子表的集合
		List<Record> fzhlRelateList  = XunChaInfoService.service.select_DMS_XC_xunChaInfo_fzhl_relate(ids);
		//获取巡查头的信息
		String xunChaTou_ids=xunChaInfo.getStr("xunChaTouId");
		XunChaTou xunChaTou=XunChaTouService.service.findById(xunChaTou_ids);
		Integer pageNumber = getParaToInt("pageNumber");
		setAttr("list",fzhlRelateList);
		setAttr("listSize",fzhlRelateList.size());
		List<Map<String, Object>> fzhlList = FangZHLService.service.findList();
		setAttr("fzhlList", fzhlList);
		setAttr("pageNumber", pageNumber);
		setAttr("xunChaInfo", xunChaInfo);
		setAttr("xunChaTou", xunChaTou);
		setAttr("chooseInfo", chooseInfo);
		List<LuXian> luXianList = LuXianService.service.findList();
		setAttr("luXianList", luXianList);
		render("/manage/xunchainfo/update.html");
	}
	/**
	 * 向新增界面跳转
	 */
	public void add() {
		List<ChooseInfo> chooseInfo=ChooseInfoService.service.findListByType(2);
		setAttr("chooseInfo", chooseInfo);
		List<LuXian> luXianList = LuXianService.service.findList();
		setAttr("luXianList", luXianList);
		render("/manage/xunchainfo/add.html");
	}
	
	/**
	 * 更新
	 */
	public void update() {
		JSONObject jsonObject=new JSONObject();
		try {
			String[] paras = getParas("uploadImgs");
			XunChaInfo entity=getModel(XunChaInfo.class);
			List<XunChaInfoFzhlRelate> chaInfoFzhlRelates = getModels(XunChaInfoFzhlRelate.class);
			//判断输入的桩号是否符合范围
			String  userIds = getAttr(ConstantWebContext.request_cUserIds);
			int zhs=entity.getInt("xunChaStartZHK")*1000+entity.getInt("xunChaStartZHM");
			int zhe = 0;
			if(StringUtils.isEmpty(StringUtils.changNull(entity.get("xunChaEndZHK"))) || StringUtils.isEmpty(StringUtils.changNull(entity.get("xunChaEndZHM"))))
			{
				zhe = zhs;
			}
			else
			{
				zhe=entity.getInt("xunChaEndZHK")*1000+entity.getInt("xunChaEndZHM");
			}
			if (BaseService.service.judgeZHScope(BaseService.service.getZHFanWeibyUserId(userIds),zhs,zhe)) {
				boolean flag = XunChaInfoService.service.update(entity,chaInfoFzhlRelates,paras);
				String result_desc="修改失败！";
				if(flag){
					result_desc="修改成功！";
					String delId = getPara("delIds");
					String[] delIds = delId.split(",");
					if (delIds.length>0) {
						for (int i = 0; i < delIds.length; i++) {
							if(!"".equals(delIds[i])){
								FzhlWeiXiuService.service.delete("DMS_XC_xunChaInfo_fzhl_relate", delIds[i]);
							}
						}
					}
				}
				jsonObject.put("flag", flag);
				jsonObject.put("result_desc", result_desc);
			}else{
				jsonObject.put("flag", false);
				jsonObject.put("result_desc", "修改失败！输入的桩号不在范围内");
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("flag", false);
			jsonObject.put("result_desc", "操作失败！");
		}
		renderJson(jsonObject.toJSONString());
	}
	/**
	 * 查看
	 */
	public void view() {
		String ids = getPara("ids");
		//主表部分
		XunChaInfo xunChaInfo = XunChaInfoService.service.findById(ids);
		//主表中某字段的下拉框
		List<ChooseInfo> chooseInfo=ChooseInfoService.service.findListByType(2);
		//子表的集合
		List<Record> fzhlRelateList  = XunChaInfoService.service.select_DMS_XC_xunChaInfo_fzhl_relate(ids);
		//获取巡查头的信息
		String xunChaTou_ids=xunChaInfo.getStr("xunChaTouId");
		XunChaTou xunChaTou=XunChaTouService.service.findById(xunChaTou_ids);
		Integer pageNumber = getParaToInt("pageNumber");
		setAttr("list",fzhlRelateList);
		setAttr("listSize",fzhlRelateList.size());
		setAttr("pageNumber", pageNumber);
		setAttr("xunChaInfo", xunChaInfo);
		setAttr("xunChaTou", xunChaTou);
		setAttr("chooseInfo", chooseInfo);
		List<LuXian> luXianList = LuXianService.service.findList();
		setAttr("luXianList", luXianList);
		List<Map<String, Object>> fzhlList = FangZHLService.service.findList();
		setAttr("fzhlList", fzhlList);
		render("/manage/xunchainfo/view.html");
	}
	/**
	 * 删除
	 */

	public void delete() {
		JSONObject jsonObject=new JSONObject();
		try {
			String ids = getPara("ids");
			XunChaInfo xc = XunChaInfoService.service.findById(ids);
			String imgs = xc.getStr("imgs");
			boolean flag=XunChaInfoService.service.delete(ids);
			String result_desc="删除失败！";
			if(flag ){
				result_desc="删除成功！";
				deleteImages(imgs);
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


