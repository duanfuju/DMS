package com.junl.dms.mvc.monthplan;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.jfinal.plugin.activerecord.Db;
import com.junl.dms.mvc.dayplan.DayPlan;
import com.junl.dms.mvc.dayplan.DayPlanService;
import com.platform.mvc.base.BaseService;
import com.platform.tools.StringUtils;

public class MonthPlanService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(MonthPlanService.class);

	public static final MonthPlanService service = new MonthPlanService();

	public boolean save(MonthPlan monthplan, String[] days) {
		boolean flag=false;
		monthplan.set("state", 0);
		monthplan.set("planNumber", days.length);
		monthplan.set("createTime", new Timestamp(System.currentTimeMillis()));
		flag= monthplan.save();
		if (flag) {
			if (days.length>0) {
				for (int i = 0; i < days.length; i++) {
					MonthPlanRelate r=new MonthPlanRelate();
					r.set("monthIds", monthplan.get("ids"));
					r.set("dayIds",days[i]);
					r.set("createTime",new Timestamp(System.currentTimeMillis()));
					r.set("state",0);
					r.save();
					DayPlan day=DayPlanService.service.findById(days[i]);
					day.set("state", 1);
					day.update();
				}
			}
		}
		return flag;
	}

	public boolean delete(String ids) {
		boolean flag=false;
		flag=MonthPlan.dao.deleteById(ids);
		if (flag) {
			List<MonthPlanRelate> mprList=MonthPlanRelate.dao.find("select * from DMS_LH_yangHuMonth_day_relate where monthIds='"+ids+"'");
			for (int i = 0; i < mprList.size(); i++) {
				MonthPlanRelate mpr=mprList.get(i);
				Db.update("update  DMS_LH_yangHuDay set state=0  where ids='"+mpr.getStr("dayIds")+"'");
				mpr.delete();
			}
			
		}
		return flag;
	}

	public MonthPlan findById(String ids) {
		MonthPlan monthplan = MonthPlan.dao.findById(ids);
		return monthplan;
	}
	
	public boolean update(MonthPlan monthplan, String[] days){
		boolean flag=false;
		//删除之前所有的关系
		List<MonthPlanRelate> mprList=MonthPlanRelate.dao.find("select * from DMS_LH_yangHuMonth_day_relate where monthIds='"+monthplan.getStr("ids")+"'");
		for (int i = 0; i < mprList.size(); i++) {
			MonthPlanRelate mpr=mprList.get(i);
			Db.update("update  DMS_LH_yangHuDay set state=0  where ids='"+mpr.getStr("dayIds")+"'");
			mpr.delete();
		}
		int count=0;
		//建立新的联系
		if (days.length>0) {
			for (int i = 0; i < days.length; i++) {
				if (!days[i].equals("")) {
					MonthPlanRelate r=new MonthPlanRelate();
					r.set("monthIds", monthplan.get("ids"));
					r.set("dayIds",days[i]);
					r.set("createTime",new Timestamp(System.currentTimeMillis()));
					r.set("state",0);
					r.save();
					DayPlan day=DayPlanService.service.findById(days[i]);
					day.set("state", 1);
					day.update();
					count++;
				}
			}
		}
		//重新设置计划数
		monthplan.set("planNumber",count );
		flag=monthplan.update();
		return flag;
	}
	/**
	 *  删除日计划和月计划的关联	
	 * @param planMonthIds
	 * @param dayIds
	 * @return
	 */
	public boolean deleteMonthAndDayRelate(String planMonthIds, String dayIds) {
		boolean flag=false;
		int n = Db.update("delete from DMS_LH_yangHuMonth_day_relate where monthIds='"+planMonthIds+"' and dayIds='"+dayIds+"'");
		n=Db.update("update  DMS_LH_yangHuDay set state=0  where ids='"+dayIds+"'");
		n=Db.update("update  DMS_LH_yangHuMonth set planNumber=planNumber-1  where ids='"+planMonthIds+"'");
		if (n>0) {
			flag=true;
		}
		return flag;
	}

	public List<Map<String, Object>> findDayPlanByMonthIds(String monthIds) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<DayPlan> dpList=DayPlanService.service.findByMonthIds(monthIds);
		if (dpList.size()>0) {
			for (int i = 0; i < dpList.size(); i++) {
				Map<String, Object> map=new HashMap<String, Object>();
				DayPlan dp=dpList.get(i);
				map.put("ids",  dp.get("ids"));
				/*map.put("luXian",  dp.get("luXian"));
				map.put("wzType",  dp.get("wzType"));
				map.put("wzName",  dp.get("wzName"));
				map.put("wzMiaoShu",  dp.get("wzMiaoShu"));
				map.put("startZHK",  dp.get("startZHK"));
				map.put("startZHM",  dp.get("startZHM"));
				map.put("endZHK",  dp.get("endZHK"));
				map.put("endZHM",  dp.get("endZHM"));*/
				map.put("workPlace",  StringUtils.changNull(dp.get("workPlace")));
				map.put("content",  StringUtils.changNull(dp.get("content")));
				map.put("fuZePeople",  StringUtils.changNull(dp.get("fuZePeople")));
				map.put("jiXieSheBei",  StringUtils.changNull(dp.get("jiXieSheBei")));
				Date date=dp.get("planTime");
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				map.put("planTime",  sdf.format(date));
				map.put("remarks",  StringUtils.changNull(dp.get("remarks")));
				list.add(map);
			}
		}else{
			return null;
		}
		return list;
	}
}
