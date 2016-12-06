package com.junl.dms.mvc.roadassessment.roadbedstate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.platform.annotation.Controller;
import com.platform.mvc.base.BaseController;

@Controller(controllerKey = "/jf/manage/roadassessment/roadBedState")
public class RoadBedStateController extends BaseController {
	
	public void index()
	{
		render("/manage/roadassessment/roadbedstate/list.html");
	}

	
	/**
	 * 获取数据列表
	 */
	public void getList()
	{
		List<Map<String,Object>> resultData = new ArrayList<Map<String,Object>>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		String startTime = getPara("startTime");
		String endTime = getPara("endTime");
		String luXian = getPara("luXian");
		String fangXiang = getPara("fangXiang");
		if(startTime != null || endTime != null || luXian != null || fangXiang != null)
		{
			try {
				endTime = endTime+" 23:59:59";
				startTime += " 00:00:00";
				startTime = sdf.format(sdf.parse(startTime));
				endTime = sdf.format(sdf.parse(endTime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			String sql = "SELECT * FROM  [dbo].[F_LKPG_SCI]('"+startTime+"','"+endTime+"','"+luXian+"','"+fangXiang+"')";
			List<Record> recordList = Db.find(sql);
			if(recordList != null && recordList.size() > 0)
			{
				for (int i = 0; i < recordList.size(); i++) {
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("KSZH", recordList.get(i).get("KSZH"));
					map.put("CD", recordList.get(i).get("CD"));
					map.put("LJBGBJ", recordList.get(i).get("路肩、边沟不洁"));
					map.put("LJSH1", recordList.get(i).get("路肩损坏轻"));
					map.put("LJSH3", recordList.get(i).get("路肩损坏重"));
					map.put("BPTT1", recordList.get(i).get("边坡坍塌轻"));
					map.put("BPTT2", recordList.get(i).get("边坡坍塌中"));
					map.put("BPTT3", recordList.get(i).get("边坡坍塌重"));
					map.put("SHCG1", recordList.get(i).get("水毁冲沟轻"));
					map.put("SHCG2", recordList.get(i).get("水毁冲沟中"));
					map.put("SHCG3", recordList.get(i).get("水毁冲沟重"));
					map.put("LJGZW1", recordList.get(i).get("路基构造物轻"));
					map.put("LJGZW2", recordList.get(i).get("路基构造物中"));
					map.put("LJGZW3", recordList.get(i).get("路基构造物重"));
					map.put("LYSQS", recordList.get(i).get("路缘石缺损"));
					map.put("LJZTCJ1", recordList.get(i).get("路基整体沉降轻"));
					map.put("LJZTCJ2", recordList.get(i).get("路基整体沉降中"));
					map.put("LJZTCJ3", recordList.get(i).get("路基整体沉降重"));
					map.put("PSXTYS1", recordList.get(i).get("排水系统淤塞轻"));
					map.put("PSXTYS3", recordList.get(i).get("排水系统淤塞重"));
					map.put("SCI", recordList.get(i).get("SCI"));
					resultData.add(map);
				}
			}
			try {
				endTime = sdf1.format(sdf.parse(endTime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		setAttr("startTime", startTime);
		setAttr("endTime", endTime);
		setAttr("luXian", luXian);
		setAttr("fangXiang", fangXiang);
		setAttr("resultData", resultData);
		render("/manage/roadassessment/roadbedstate/table.html");
	}
}
