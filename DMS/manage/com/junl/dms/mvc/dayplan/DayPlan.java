package com.junl.dms.mvc.dayplan;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.junl.dms.mvc.luxian.LuXian;
import com.platform.annotation.Table;
import com.platform.mvc.base.BaseModel;
import com.platform.mvc.base.BaseModelCache;
import com.platform.mvc.param.Param;
import com.platform.plugin.ParamInitPlugin;
import com.platform.tools.ToolCache;
import com.test.mvc.blog.Blog;


@SuppressWarnings("unused")
@Table(tableName = "DMS_LH_yangHuDay")
public class DayPlan extends BaseModel<DayPlan> {
	
	private static final long serialVersionUID = 5979434062234466436L;

	private static Logger log = Logger.getLogger(DayPlan.class);
	
	public static final DayPlan dao = new DayPlan();
	
	public static final String sqlId_splitPageSelect = "manage.dayplan.splitPageSelect";
	public static final String sqlId_splitPageFrom = "manage.dayplan.splitPageFrom";
	
	public static final String sqlId_splitPageSelect_findByDate = "manage.dayplan.splitPageSelect_findByDate";
	public static final String sqlId_splitPageFrom_findByDate = "manage.dayplan.splitPageFrom_findByDate";
	
	
	
	
	
	
	public List<DayPlan> findByMonthIds(String ids){
		String sql = getSql("manage.dayplan.findByMonthIds");
		List<DayPlan> list = DayPlan.dao.find(sql,ids);
		return list;
	}
	
	
	/**
	 * 获取本月数据
	 * @return
	 */
	public List<DayPlan> findByDate(){
		String sql = getSql("manage.dayplan.findByDate");
		List<DayPlan> list = DayPlan.dao.find(sql);
		return list;
	}
	/**
	 * 获取指定月数据
	 * @return
	 */
	public List<DayPlan> findByDate1(String date){
		String sql = getSql("manage.dayplan.findByDate1");
		List<DayPlan> list = DayPlan.dao.find(sql,date);
		return list;
	}
	
	
}
