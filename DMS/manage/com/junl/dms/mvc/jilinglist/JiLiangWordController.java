package com.junl.dms.mvc.jilinglist;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.junl.dms.mvc.jiling.JiLiang;
import com.junl.dms.mvc.jiling.JiLiangService;
import com.junl.dms.mvc.jiling.JiLiangXiMu;
import com.junl.dms.mvc.jiling.jiLingTuiHui;
import com.junl.dms.tools.WordUtil;
import com.platform.annotation.Controller;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseController;


@Controller(controllerKey = "/jf/manage/jiliangword")
public class JiLiangWordController extends BaseController {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(JiLiangWordController.class);
	/**
	 * 首页
	 */
	public void index() {
		paging(ConstantInit.db_dataSource_main, splitPage, JiLiang.sqlId_splitPageSelect, JiLiang.sqlId_splitPageFrom);
		String ids = getPara("ids");
		System.out.println("IDS = " + ids );
		String basePath = getRequest().getScheme() + "://" + getRequest().getServerName() + ":"  + getRequest().getServerPort();
		setAttr("basePath",basePath);
		render("/manage/jiliang/jilianglist.html");
	}
	
	public void daochuWord() throws Exception{
		JSONObject jsonObject=new JSONObject();
		try {
			String ids = getPara("ids");  //jlIds
			String basePath = getRequest().getScheme() + "://" + getRequest().getServerName() + ":"  + getRequest().getServerPort();
			if(ids!=null){
				Map<String, Object> map = JiLiangService.service.getWord1(ids);
				map.put("one", map.get("one"));
				List l = (List) map.get("list");
				map.put("dataList", l );
				List<Integer> l2 = new ArrayList<>();
				if(l!=null && l.size()<25){
					for (int i=0;i<25-l.size();i++) {
						l2.add(0);
					}
				}
				map.put("size", l.size());
				map.put("list2", l2);
				map.put("date", new SimpleDateFormat("yyyy年MM月dd日").format(new Date()));
				String fileName = ids + ".doc";
	//			String path = getRequest().getSession().getServletContext().getRealPath("");
	//			fileName = path.substring(0,path.length()-11) + "uploadword\\" + "jiliang-" + fileName;
	//			WordUtil.create(map , "richangyanghuzhongjianjiliang.ftl", fileName );
				
				baseCreateWord(map, "richangyanghuzhongjianjiliang.ftl", "jiliang-" + fileName);
			}
			jsonObject.put("basePath", basePath);
			jsonObject.put("flag", true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("flag", false);
		}
		renderJson(jsonObject.toJSONString());
	}
	
	
	/**
	 * 获取验收细目的数据			/jf/manage/jiliang/getYanShouXiMu
	 */
	public void getYanShouXiMu(){
		JSONObject jsonObject=new JSONObject();
		try {
			String  yanShouXiMu = getPara("yanShouXiMu");
			List<Map<String, Object>> result = JiLiangService.service.getYanShouXiMu(yanShouXiMu);
			jsonObject.put("result", result);
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("result", 0);
		}
		renderJson(jsonObject.toJSONString());
	}

	
	
	/**
	 * 跳转查看页面
	 */
	public void view(){
		String ids = getPara("ids");
		//根据ids获取当前的计量数据
		JiLiang jiliang=JiLiangService.service.findByIds(ids);
		//根据计量数据所关联的报验编号查询维修数量
		int weiXiuNum=JiLiangService.service.getWeiXiuNum(jiliang.getStr("byanIds"));
		//根据任务编号获取所有的维修记录数
		List<Map<String, Object>> list= JiLiangService.service.getWeiXiuRecord(jiliang.getStr("byanIds"));
		setAttr("list",list);
		setAttr("jiliang",jiliang);
		setAttr("weiXiuNum",weiXiuNum);
		render("/manage/jiliang/view.html");
	}
	
	
	/**
	 * 跳转计量操作页面
	 */
	public void meterage(){
		String ids = getPara("ids");
		//根据ids获取当前的计量数据
		JiLiang jiliang=JiLiangService.service.findByIds(ids);
		//根据计量数据所关联的报验编号查询维修数量
		int weiXiuNum=JiLiangService.service.getWeiXiuNum(jiliang.getStr("byanIds"));
		//根据任务编号获取所有的维修记录数
		List<Map<String, Object>> list= JiLiangService.service.getWeiXiuRecord(jiliang.getStr("byanIds"));
		setAttr("list",list);
		setAttr("jiliang",jiliang);
		setAttr("weiXiuNum",weiXiuNum);
		render("/manage/jiliang/meterage.html");
	}
	
	
	
	
	
	
	
	
	/**
	 * 获取维修数	
	 */
	public void getWeiXiuNum(){
		JSONObject jsonObject=new JSONObject();
		try {
			String  ids = getPara("ids");
			int num = JiLiangService.service.getWeiXiuNum(ids);
			jsonObject.put("result", num);
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("result", 0);
		}
		renderJson(jsonObject.toJSONString());
	}
	/**
	 * 弹出计量细目
	 */
	public void tomItems(){
		String  ids = getPara("ids");
		String  jlIds = getPara("jlIds");
		setAttr("ids",ids);
		setAttr("jlIds",jlIds);
		render("/manage/jiliang/mitems.html");
	}
	/**
	 * 弹出退回
	 */
	public void toReturn(){
		String  ids = getPara("ids");
		String  jlIds = getPara("jlIds");
		setAttr("ids",ids);
		setAttr("jlIds",jlIds);
		render("/manage/jiliang/return.html");
	}
	
	/**
	 * 计量细目的新增	/jf/manage/jiliang/saveItems
	 */
	public void saveItems(){
		JSONObject jsonObject=new JSONObject();
		try{
			JiLiangXiMu jiLiangXiMu = getModel(JiLiangXiMu.class);
			String[] paras = getParas("uploadImgs");
			boolean result=JiLiangService.service.saveItems(jiLiangXiMu, paras);
			String result_desc="计量失败！";
			if(result){
				result_desc="计量成功！";
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
	
	/**
	 * 退回的新增	/jf/manage/jiliang/saveReturn
	 */
	public void saveReturn(){
		JSONObject jsonObject=new JSONObject();
		try{
			jiLingTuiHui jiLingTuiHui = getModel(jiLingTuiHui.class);
			String[] paras = getParas("uploadImgs");
			boolean result=JiLiangService.service.saveReturn(jiLingTuiHui, paras);
			String result_desc="退回失败！";
			if(result){
				result_desc="退回成功！";
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
	
	/**
	 * 改变计量的状态和确认时间	/jf/manage/jiliang/changeJiLiangState
	 */
	public void changeJiLiangState(){
		JSONObject jsonObject=new JSONObject();
		try{
			String ids = getPara("ids");
			boolean result=JiLiangService.service.changeJiLiangState(ids);
			String result_desc="修改计量状态失败！";
			if(result){
				result_desc="修改计量状态成功！";
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
	
	
	
	
	
	
	
	
	
	
}