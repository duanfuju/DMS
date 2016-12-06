package com.junl.dms.mvc.bycenter;

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
@Table(tableName = "DMS_PZ_byCenter")
public class ByCenter extends BaseModel<ByCenter> {
	
	private static final long serialVersionUID = 5979434062234466436L;

	private static Logger log = Logger.getLogger(ByCenter.class);
	
	public static final ByCenter dao = new ByCenter();
	
	public static final String sqlId_splitPageSelect = "manage.bycenter.splitPageSelect";
	public static final String sqlId_splitPageFrom = "manage.bycenter.splitPageFrom";
	public List<ByCenter> findList() {
		String sql = getSql("manage.bycenter.findList");
		List<ByCenter> list = ByCenter.dao.find(sql);
		return list;
	}

	

}
