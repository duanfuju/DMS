package com.junl.dms.mvc.shenqingzhifubiaolist;

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
@Table(tableName = "DMS_LH_yangHuZhiFuShenQing")
public class Shenqingzhifubiao extends BaseModel<Shenqingzhifubiao> {
	
	private static final long serialVersionUID = 5979434062234466436L;

	private static Logger log = Logger.getLogger(Shenqingzhifubiao.class);
	
	public static final Shenqingzhifubiao dao = new Shenqingzhifubiao();
	
	public static final String sqlId_splitPageSelect = "manage.shenqingzhifubiao.splitPageSelect";
	public static final String sqlId_splitPageFrom = "manage.shenqingzhifubiao.splitPageFrom";
	
	public static final String sqlId_splitPageSelect_findByDate = "manage.shenqingzhifubiao.splitPageSelect_findByDate";
	public static final String sqlId_splitPageFrom_findByDate = "manage.shenqingzhifubiao.splitPageFrom_findByDate";
	
	
	
	
	
	
	public List<Shenqingzhifubiao> findByMonthIds(String ids){
		String sql = getSql("manage.shenqingzhifubiao.ShenqingzhifubiaoIds");
		List<Shenqingzhifubiao> list = Shenqingzhifubiao.dao.find(sql,ids);
		return list;
	}
	
	
}
