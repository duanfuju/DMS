package com.junl.dms.mvc.dayplan;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.junl.dms.mvc.monthplan.MonthPlan;
import com.junl.dms.mvc.monthplan.MonthPlanRelate;
import com.platform.mvc.base.BaseService;

public class DayPlanService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(DayPlanService.class);

	public static final DayPlanService service = new DayPlanService();

	public boolean save(DayPlan dayplan) {
		dayplan.set("state", 1);
		dayplan.set("createTime", new Timestamp(System.currentTimeMillis()));
		boolean flag=dayplan.save();
		//当日计划新增成功的时候
		//通过日期的查询看看月计划的表中是否有计划
		//没有就新增月计划，然后添加关联关系
		if (flag) {
			Date date=dayplan.get("planTime");
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
			String month=sdf.format(date);
			MonthPlan monthPlan=MonthPlan.dao.findByPlanMonth(month);
			boolean f= false;
			if (monthPlan==null) {
				monthPlan = new MonthPlan();
				monthPlan.set("planMonth", date);
				monthPlan.set("state", 0);
				monthPlan.set("planNumber", 1);
				monthPlan.set("createTime", new Timestamp(System.currentTimeMillis()));
				f=monthPlan.save();
			}else{
				monthPlan.set("planNumber", Integer.valueOf(monthPlan.get("planNumber").toString())+1);
				f=monthPlan.update();
			}
			//保存月计划成功后，创建月计划和日计划的关系
			if (f) {
				MonthPlanRelate r=new MonthPlanRelate();
				r.set("monthIds", monthPlan.get("ids"));
				r.set("dayIds",dayplan.get("ids"));
				r.set("createTime",new Timestamp(System.currentTimeMillis()));
				r.set("state",0);
				r.save();
			}
			
		}
		return flag;
	}

	public boolean delete(String ids) {
		//删除日计划
		boolean flag=DayPlan.dao.deleteById(ids);
		//删除日计划成功后，需要删除月计划的关联表
		//并且月计划的计划数需要-1
		if (flag) {
			MonthPlanRelate monthPlanRelate =MonthPlanRelate.dao.findPlanMonthRelateByDayIds(ids);
			MonthPlan monthPlan=MonthPlan.dao.findById(monthPlanRelate.get("monthIds").toString());
			monthPlan.set("planNumber", Integer.valueOf(monthPlan.get("planNumber").toString())-1);
			monthPlanRelate.delete();
			monthPlan.update();
		}
		return flag;
	}

	public DayPlan findById(String ids) {
		DayPlan dayplan = DayPlan.dao.findById(ids);
		return dayplan;
	}
	
	public boolean update(DayPlan dayplan){
		return dayplan.update();
	}
	public List<DayPlan> findByDate(){
		List<DayPlan> list = DayPlan.dao.findByDate();
		return list;
	}

	public List<DayPlan> findByMonthIds(String ids) {
		List<DayPlan> list = DayPlan.dao.findByMonthIds(ids);
		return list;
	}

	public List<DayPlan> findByDate1(String date) {
		List<DayPlan> list = DayPlan.dao.findByDate1(date);
		return list;
	}

	
}
