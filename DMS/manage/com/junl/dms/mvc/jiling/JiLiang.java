package com.junl.dms.mvc.jiling;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.junl.dms.mvc.luxian.LuXian;
import com.junl.dms.mvc.weizhi.WeiZhi;
import com.platform.annotation.Table;
import com.platform.mvc.base.BaseModel;
import com.platform.mvc.base.BaseModelCache;
import com.platform.mvc.param.Param;
import com.platform.plugin.ParamInitPlugin;
import com.platform.tools.ToolCache;
import com.test.mvc.blog.Blog;


@SuppressWarnings("unused")
@Table(tableName = "DMS_JL_jiLing")
public class JiLiang extends BaseModel<JiLiang> {
	
	private static final long serialVersionUID = 5979434062234466436L;

	private static Logger log = Logger.getLogger(JiLiang.class);
	
	public static final JiLiang dao = new JiLiang();
	
	public static final String sqlId_splitPageSelect = "manage.jiliang.splitPageSelect";
	public static final String sqlId_splitPageFrom = "manage.jiliang.splitPageFrom";
	
	public JiLiang findByIds(String ids){
		String sql = getSql("manage.jiliang.findByIds");
		JiLiang jiLiang = JiLiang.dao.find(sql,ids).get(0);
		return jiLiang;
	}
}
