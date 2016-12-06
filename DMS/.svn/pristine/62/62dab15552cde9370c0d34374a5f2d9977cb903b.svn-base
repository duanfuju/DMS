package com.junl.dms.mvc.luxian;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.platform.annotation.Table;
import com.platform.mvc.base.BaseModel;
import com.platform.mvc.base.BaseModelCache;
import com.platform.mvc.param.Param;
import com.platform.plugin.ParamInitPlugin;
import com.platform.tools.ToolCache;
import com.test.mvc.blog.Blog;


@SuppressWarnings("unused")
@Table(tableName = "DMS_PZ_luXian")
public class LuXian extends BaseModel<LuXian> {
	
	private static final long serialVersionUID = 5979434062234466436L;

	private static Logger log = Logger.getLogger(LuXian.class);
	
	public static final LuXian dao = new LuXian();
	
	public static final String sqlId_splitPageSelect = "manage.luxian.splitPageSelect";
	public static final String sqlId_splitPageFrom = "manage.luxian.splitPageFrom";
	
	
	public List<LuXian> findList(){
		String sql = getSql("manage.luxian.findList");
		List<LuXian> list = LuXian.dao.find(sql);
		return list;
	}
}
