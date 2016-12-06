package com.junl.dms.mvc.yhluduan;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.junl.dms.mvc.luxian.LuXian;
import com.platform.annotation.Table;
import com.platform.mvc.base.BaseModel;
import com.platform.mvc.base.BaseModelCache;
import com.platform.mvc.param.Param;
import com.platform.mvc.user.UserInfo;
import com.platform.plugin.ParamInitPlugin;
import com.platform.tools.ToolCache;
import com.test.mvc.blog.Blog;


@SuppressWarnings("unused")
@Table(tableName = "DMS_PZ_yhLuDuan")
public class YhLuDuan extends BaseModel<YhLuDuan> {
	
	private static final long serialVersionUID = -7025718258866632568L;

	private static Logger log = Logger.getLogger(YhLuDuan.class);
	
	public static final YhLuDuan dao = new YhLuDuan();
	
	public static final String sqlId_splitPageSelect = "manage.yhluduan.splitPageSelect";
	public static final String sqlId_splitPageFrom = "manage.yhluduan.splitPageFrom";
	
	
	public List<Record> findYangHuLuDuanIdByUserInfoIds(String ids){
		String sql = getSql("manage.yhluduan.findYangHuLuDuanIdByUserInfoIds");
		List<Record> i = Db.find(sql, ids);
		return i;
	}
	
	public List<YhLuDuan> findLuXianIdsByYangHuLuDuanIds(String ids){
		String sql = getSql("manage.yhluduan.findLuXianIdsByYangHuLuDuanIds");
		List<YhLuDuan> list = YhLuDuan.dao.find(sql,ids);
		return list;
	}
	
	
	
	
}
