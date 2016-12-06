package com.junl.dms.mvc.xunchainfo;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.junl.dms.mvc.rwtask.RwTask;
import com.platform.annotation.Table;
import com.platform.mvc.base.BaseModel;
import com.platform.mvc.base.BaseModelCache;
import com.platform.mvc.param.Param;
import com.platform.plugin.ParamInitPlugin;
import com.platform.tools.ToolCache;
import com.test.mvc.blog.Blog;


@SuppressWarnings("unused")
@Table(tableName = "DMS_XC_xunChaInfo")
public class XunChaInfo extends BaseModel<XunChaInfo> {
	

	private static final long serialVersionUID = -7802585012688776795L;

	private static Logger log = Logger.getLogger(XunChaInfo.class);
	
	public static final XunChaInfo dao = new XunChaInfo();
	public static final String sqlId_splitPageSelect = "manage.xunchainfo.splitPageSelect";
	public static final String sqlId_splitPageFrom = "manage.xunchainfo.splitPageFrom";
	
	
	public List<XunChaInfo> findListByXunChaTouId(String id){
		String sql = getSql("manage.xunchainfo.findListByXunChaTouId");
		List<XunChaInfo> list = XunChaInfo.dao.find(sql,id);
		return list;
	}
	
	
	
	
	
	
}
