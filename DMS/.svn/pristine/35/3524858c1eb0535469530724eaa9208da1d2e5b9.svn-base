package com.junl.dms.mvc.roadassessment.linestate;

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

@Controller(controllerKey = "/jf/manage/roadassessment/lineState")
public class LineStateController extends BaseController {
	
	public void index()
	{
		render("/manage/roadassessment/linestate/list.html");
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
			
			String sql = "SELECT * FROM  [dbo].[F_LKPG_TCI]('"+startTime+"','"+endTime+"','"+luXian+"','"+fangXiang+"')";
			List<Record> recordList = Db.find(sql);
			if(recordList != null && recordList.size() > 0)
			{
				for (int i = 0; i < recordList.size(); i++) {
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("KSZH", recordList.get(i).get("KSZH"));
					map.put("CD", recordList.get(i).get("CD"));
					map.put("FHSSQS1", recordList.get(i).get("防护设施缺损轻"));
					map.put("FHSSQS3", recordList.get(i).get("防护设施缺损重"));
					map.put("GLSSH", recordList.get(i).get("隔离栅损坏"));
					map.put("BZQS", recordList.get(i).get("标志缺损"));
					map.put("BXQS", recordList.get(i).get("标线缺损"));
					map.put("LHGHBS", recordList.get(i).get("绿化管护不善"));
					map.put("TCI", recordList.get(i).get("TCI"));
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
		render("/manage/roadassessment/linestate/table.html");
	}

}