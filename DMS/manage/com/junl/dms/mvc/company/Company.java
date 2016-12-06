package com.junl.dms.mvc.company;

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
@Table(tableName = "DMS_PZ_company")
public class Company extends BaseModel<Company> {
	
	private static final long serialVersionUID = 5979434062234466436L;

	private static Logger log = Logger.getLogger(Company.class);
	
	public static final Company dao = new Company();
	
	public static final String sqlId_splitPageSelect = "manage.company.splitPageSelect";
	public static final String sqlId_splitPageFrom = "manage.company.splitPageFrom";
	public List<Company> findList() {
		String sql = getSql("manage.company.findList");
		List<Company> list = Company.dao.find(sql);
		return list;
	}

	

}
