package com.junl.dms.mvc.pingfenlist;

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
@Table(tableName = "DMS_LH_zhiLiangPingFen")
public class Pingfen extends BaseModel<Pingfen> {
	
	private static final long serialVersionUID = 5979434062234466436L;

	private static Logger log = Logger.getLogger(Pingfen.class);
	
	public static final Pingfen dao = new Pingfen();
	
	public static final String sqlId_splitPageSelect = "manage.pingfen.splitPageSelect";
	public static final String sqlId_splitPageFrom = "manage.pingfen.splitPageFrom";
	
	public static final String sqlId_splitPageSelect_findByDate = "manage.pingfen.splitPageSelect_findByDate";
	public static final String sqlId_splitPageFrom_findByDate = "manage.pingfen.splitPageFrom_findByDate";
	
	
	
	
	
	
	public List<Pingfen> findByMonthIds(String ids){
		String sql = getSql("manage.pingfen.PingfenIds");
		List<Pingfen> list = Pingfen.dao.find(sql,ids);
		return list;
	}
	
	
}
