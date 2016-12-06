package com.junl.dms.mvc.youwuchuli;



import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.kit.PropKit;
import com.jfinal.upload.UploadFile;
import com.junl.dms.tools.ToolsFile;
import com.platform.annotation.Controller;
import com.platform.constant.ConstantInit;
import com.platform.constant.ConstantWebContext;
import com.platform.mvc.base.BaseController;
import com.platform.mvc.base.BaseService;
import com.platform.tools.StringUtils;


@Controller(controllerKey = "/jf/manage/youwu")
public class YouWuController extends BaseController {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(YouWuController.class);
	
	
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
		paging(ConstantInit.db_dataSource_main, splitPage, Youwu.sqlId_splitPageSelect, Youwu.sqlId_splitPageFrom);
		render("/manage/youwu/list.html");
	}
	
	public void save(){
		JSONObject jsonObject=new JSONObject();
		try {
			Youwu youwu = getModel(Youwu.class);
			
			String  userIds = getAttr(ConstantWebContext.request_cUserIds);
			int zhs=youwu.getInt("startZHK")*1000+youwu.getInt("startZHM");
			int zhe = 0;
			if(StringUtils.isEmpty(StringUtils.changNull(youwu.get("endZHK"))) || StringUtils.isEmpty(StringUtils.changNull(youwu.get("endZHM"))))
			{
				zhe = zhs;
			}
			else
			{
				zhe=youwu.getInt("endZHK")*1000+youwu.getInt("endZHM");
			}
			//判断桩号是否符合范围
			if (BaseService.service.judgeZHScope(BaseService.service.getZHFanWeibyUserId(userIds),zhs,zhe)) {
				//图片数组
				String[] paras = getParas("youwuImgs");
				youwu.set("createUserId", getAttr(ConstantWebContext.request_cUserIds));
				boolean result = YouWuService.service.save(youwu,paras);
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
	
	/**
	 * 删除方法改造，
	 * hank
	 */
	public void delete(){
		JSONObject jsonObject=new JSONObject();
		try {
			String ids = getPara("ids");
			boolean result = YouWuService.service.delete(getRequest(),ids);
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
		String ids = getPara(0);
		Youwu youwu = YouWuService.service.findById(ids);
		Youwu youwuTask = YouWuService.service.findTask(ids);
		String imgsStr = youwu.getStr("imgs");
		String[] imgs = imgsStr.split(",");
		
		setAttr("imgs", imgs);
		setAttr("youwu", youwu);
		setAttr("wzMiaoShu", youwu.get("wzMiaoShu"));
		setAttr("remark", youwu.get("remark"));
		setAttr("youwuTask", youwuTask);
		render("/manage/youwu/update.html");
	}
	
	public void update(){
		JSONObject jsonObject=new JSONObject();
		try {
			Youwu youwu = getModel(Youwu.class);
			String  userIds = getAttr(ConstantWebContext.request_cUserIds);
			int zhs=youwu.getInt("startZHK")*1000+youwu.getInt("startZHM");
			int zhe = 0;
			if(StringUtils.isEmpty(StringUtils.changNull(youwu.get("endZHK"))) || StringUtils.isEmpty(StringUtils.changNull(youwu.get("endZHM"))))
			{
				zhe = zhs;
			}
			else
			{
				zhe=youwu.getInt("endZHK")*1000+youwu.getInt("endZHM");
			}
			//判断桩号是否符合范围
			if (BaseService.service.judgeZHScope(BaseService.service.getZHFanWeibyUserId(userIds),zhs,zhe)) {
				String[] paras = getParas("youwuImgs");
				boolean result = YouWuService.service.update(youwu,paras);
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
		Youwu youwu = Youwu.dao.findById(ids);
		
		Youwu youwuTask = YouWuService.service.findTask(ids);
		
		setAttr("youwu", youwu);
		setAttr("wzMiaoShu", youwu.get("wzMiaoShu"));
		setAttr("remark", youwu.get("remark"));
		setAttr("youwuTask", youwuTask);
		render("/manage/youwu/view.html");
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
			//String url = "/files/upload";
//			Constants con = new Constants();
//			con.setBaseUploadPath(getRequest().getServletContext().getRealPath("")+"/files/upload");
//			System.out.println(getRequest().getServletContext().getRealPath(""));
//			System.out.println(PathKit.getWebRootPath());
			
			
//			UploadFile file = getFile("uploadImg",url);
//			jsonObject.put("fileName", file.getFileName());
			
			
			
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
			
			//renderJson(jsonObject.toJSONString());
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		renderJson(jsonObject.toJSONString());
	}
	
}

