package com.junl.dms.mvc.yanghuyhuizong;



import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.jfinal.kit.PropKit;
import com.junl.dms.mvc.weixiurecord.CompressedFileUtil;
import com.platform.annotation.Controller;
import com.platform.mvc.base.BaseController;
import com.platform.tools.ToolDirFile;


@Controller(controllerKey = "/jf/manage/yanghuyhuizong")
public class YangHuHuiZongController extends BaseController {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(YangHuHuiZongController.class);
	
	
	/**
	 * 首页
	 * @throws ParseException 
	 */
	public void index() throws ParseException {
		String searchTime = getPara("searchtime");
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM");
		if(searchTime != null){
			Date date = sim.parse(searchTime);
			cal.setTime(date);
		}else{
			Date dates =  cal.getTime();
			searchTime = sim.format(dates);   
		}
		// 计量汇总  950fc083899d4c6cbdc7ad8f554930cf
		Map<String,Object> mapInfo = YangHuLogService.service.getHuiZong("950fc083899d4c6cbdc7ad8f554930cf",String.valueOf(cal.get(Calendar.YEAR)),searchTime,true);
		
		Map<String,Object> huizMap = new HashMap<String, Object>();
		huizMap.put("jlhuiz", mapInfo.get("tabList"));
		huizMap.put("price", mapInfo.get("price"));
		huizMap.put("titleName", String.valueOf(cal.get(Calendar.YEAR))+"年"+PropKit.get("PROJECT.NAME")+"日常养护"+String.valueOf(cal.get(Calendar.MONTH)+1)+"月计量汇总表");
		setAttr("huizMap",huizMap);
		//清单明细  d0db9c14e5a343859e0f9b216096bf15
		Map<String,Object>  detailmapInfo = YangHuLogService.service.getHuiZong("d0db9c14e5a343859e0f9b216096bf15",String.valueOf(cal.get(Calendar.YEAR)),searchTime,false);
		Map<String,Object> detailMap = new HashMap<String, Object>();
		detailMap.put("detaillist", detailmapInfo.get("tabList"));
		detailMap.put("price", detailmapInfo.get("price"));
		detailMap.put("mxTitle", String.valueOf(cal.get(Calendar.YEAR))+"年"+"日常养护"+String.valueOf(cal.get(Calendar.MONTH)+1)+"月清单明细表");
		setAttr("detailMap",detailMap);
		
		// 单价承包
		List<Map<String,Object>> tabList = YangHuLogService.service.getTabInfo("5c254454ef804c9b9328dc8d148681ac",String.valueOf(cal.get(Calendar.YEAR)),String.valueOf(cal.get(Calendar.MONTH)+1),searchTime);
		setAttr("tabList",tabList);
		
		setAttr("searchtime",searchTime);
		render("/manage/yanghuhuizong/list.html");
	}
	

	@SuppressWarnings("unchecked")
	public void exportword(){
		JSONObject jsonObject = new JSONObject();
		try {
			String path = getWordPath();
			String searchTime = getPara("searchtime");
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM");
			if(searchTime != null){
				Date date = sim.parse(searchTime);
				cal.setTime(date);
			}else{
				Date dates =  cal.getTime();
				searchTime = sim.format(dates);   
			}
			String[] st = searchTime.split("-");
			String foldName = searchTime+File.separator;
			
			File file = new File(path + foldName);
			ToolDirFile.delete(file);
			
			// 计量汇总  950fc083899d4c6cbdc7ad8f554930cf
			Map<String,Object> mapInfo = YangHuLogService.service.getHuiZong("950fc083899d4c6cbdc7ad8f554930cf",String.valueOf(cal.get(Calendar.YEAR)),searchTime,true);
			Map<String,Object> huizMap = new HashMap<String, Object>();
			huizMap.put("jlhuiz", mapInfo.get("tabList"));
			huizMap.put("price", mapInfo.get("price"));
			huizMap.put("titleName", String.valueOf(cal.get(Calendar.YEAR))+"年"+PropKit.get("PROJECT.NAME")+"日常养护"+String.valueOf(cal.get(Calendar.MONTH)+1)+"月计量汇总表");
			baseCreateWord(huizMap, "huizong.ftl", st[0]+"年"+PropKit.get("PROJECT.NAME")+"日常养护"+st[1]+"月计量汇总表.doc",foldName);
			
		//清单明细  d0db9c14e5a343859e0f9b216096bf15
			Map<String,Object> detailInfo = YangHuLogService.service.getHuiZong("d0db9c14e5a343859e0f9b216096bf15",String.valueOf(cal.get(Calendar.YEAR)),searchTime,false);
			Map<String,Object> detailMap = new HashMap<String, Object>();
			detailMap.put("detaillist", detailInfo.get("tabList"));
			detailMap.put("price", detailInfo.get("price"));
			detailMap.put("mxTitle", String.valueOf(cal.get(Calendar.YEAR))+"年"+"日常养护"+String.valueOf(cal.get(Calendar.MONTH)+1)+"月清单明细表");
			baseCreateWord(detailMap, "zcbao.ftl", st[0]+"年日常养护"+st[1]+"月清单明细表-总价承包部分.doc",foldName);

			// 单价承包
			List<Map<String,Object>> tabList = YangHuLogService.service.getTabInfo("5c254454ef804c9b9328dc8d148681ac",String.valueOf(cal.get(Calendar.YEAR)),String.valueOf(cal.get(Calendar.MONTH)+1),searchTime);
			for (int i = 0; i < tabList.size(); i++) {
				Map<String,Object> map = tabList.get(i);
				if (map.get("isparent").equals("false")) {
					baseCreateWord(map, "mingxi.ftl", st[0]+"年日常养护"+st[1]+"月清单明细表_"+map.get("levelNum")+map.get("firstLevelNumberDetail")+".doc",foldName);
				}else{
					List<Map<String,Object>> anqTabList = (List<Map<String,Object>>)map.get("anqTabList");
					for (int j = 0; j < anqTabList.size(); j++) {
						Map<String,Object> anqMap = anqTabList.get(j);
						String levelNum = anqMap.get("levelNum").toString();
						String firstLevelNumberDetail = anqMap.get("firstLevelNumberDetail").toString();
						anqMap.put("titleName", map.get("titleName"));
						anqMap.put("isparent", map.get("isparent"));
						anqMap.put("levelNum", map.get("levelNum"));
						anqMap.put("firstLevelNumberDetail", map.get("firstLevelNumberDetail"));
						anqMap.put("threeName", levelNum+firstLevelNumberDetail);
						
						anqMap.put("sumprice", map.get("sumprice"));
						anqMap.put("price", map.get("price"));
						anqMap.put("gcl", map.get("gcl"));
						anqMap.put("sumgcl", map.get("sumgcl"));
						baseCreateWord(anqMap, "mingxi.ftl", st[0]+"年日常养护"+st[1]+"月清单明细表_"+anqMap.get("threeName")+".doc",foldName);
					}
					
				}
			}
			
			CompressedFileUtil csf = new CompressedFileUtil();
			csf.compressedFile(path + foldName, path);
			String downloadPath = PropKit.get("tomcat.WordPath")+"/"+searchTime+".zip"; // 下载路径
			jsonObject.put("downloadPath", downloadPath);
			jsonObject.put("isSuccess", true);
		} catch (Exception e) {
			jsonObject.put("isSuccess", false);
		}
		renderJson(jsonObject.toString());
	}
	

	

}


