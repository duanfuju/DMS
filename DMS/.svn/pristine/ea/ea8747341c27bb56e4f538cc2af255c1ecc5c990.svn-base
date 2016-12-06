package com.junl.dms.mvc.baoyanlist;



import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.Record;
import com.junl.dms.mvc.baoyan.BaoYan;
import com.junl.dms.mvc.baoyan.BaoYanService;
import com.platform.annotation.Controller;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseController;


@Controller(controllerKey = "/jf/manage/baoyanlist")
public class BaoYanListController extends BaseController {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(BaoYanListController.class);
	
	
	/**
	 * 首页
	 */
	public void index() {
		if((splitPage.getOrderColunm() == null || ("").equals(splitPage.getOrderColunm()))
				&& (splitPage.getOrderMode() == null || ("").equals(splitPage.getOrderMode()))) {
			splitPage.setOrderColunm("createTime");
			splitPage.setOrderMode("desc");
		}
		paging(ConstantInit.db_dataSource_main, splitPage, BaoYanList.sqlId_splitPageSelect, BaoYanList.sqlId_splitPageFrom);
		String basePath = getRequest().getScheme()
                + "://" + getRequest().getServerName() + ":" 
                + getRequest().getServerPort();   //controller中获取basepath
		setAttr("basePath",basePath);
		render("/manage/baoyanlist/list.html");
	}
	
	
	public void exportword() {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		BaoYan baoYan =BaoYanService.service.finddata(ids);
		dataMap.put("baoYan", baoYan);
		dataMap.put("state", Integer.valueOf(baoYan.get("state").toString()));
		Calendar cal = Calendar.getInstance();
	    cal.setTime(baoYan.getDate("baoYanTime"));
	    dataMap.put("baoYanTimeyear", cal.get(Calendar.YEAR));
	    dataMap.put("baoYanTimemonth", cal.get(Calendar.MONTH)+1);
	    dataMap.put("baoYanTimeday", cal.get(Calendar.DAY_OF_MONTH));
	    Calendar cal2 = Calendar.getInstance();
	    cal2.setTime(baoYan.getDate("shenHeTime"));
	    dataMap.put("shenHeTimeyear", cal2.get(Calendar.YEAR));
	    dataMap.put("shenHeTimemonth", cal2.get(Calendar.MONTH)+1);
	    dataMap.put("shenHeTimeday", cal2.get(Calendar.DAY_OF_MONTH));
	    Calendar cal3 = Calendar.getInstance();
	    cal3.setTime(baoYan.getDate("yanShouTime"));
	    dataMap.put("yanShouTimeyear", cal3.get(Calendar.YEAR));
	    dataMap.put("yanShouTimemonth", cal3.get(Calendar.MONTH)+1);
	    dataMap.put("yanShouTimeday", cal3.get(Calendar.DAY_OF_MONTH));
		List<Record>list = BaoYanService.service.findrenwu(ids);
		List list2 = new ArrayList();
		if(list.size()<12){
			for(int i = 0;i<13-list.size();i++){
				list2.add(0);
			}
			dataMap.put("nullobject", list2);
		}
		dataMap.put("size", list.size());
		dataMap.put("baoyanlist", list);
		 
		JSONObject json = new JSONObject();
		
		try {
			baseCreateWord(dataMap, "baoyan.ftl", "baoYan-" + baoYan.get("ids")+".doc");
			json.put("isSuccess", true);
		} catch (Exception e) {
			json.put("isSuccess", false);
		}
		renderJson(json.toJSONString());
	}
	
}