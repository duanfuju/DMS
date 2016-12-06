package com.junl.dms.mvc.fangxiang;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.junl.dms.mvc.weizhi.WeiZhi;
import com.platform.annotation.Table;
import com.platform.mvc.base.BaseModel;
import com.platform.mvc.base.BaseModelCache;
import com.platform.mvc.param.Param;
import com.platform.plugin.ParamInitPlugin;
import com.platform.tools.ToolCache;
import com.test.mvc.blog.Blog;


@SuppressWarnings("unused")
@Table(tableName = "DMS_PZ_fangXiang")
public class FangXiang extends BaseModel<FangXiang> {
	
	private static final long serialVersionUID = -7025718258866632568L;

	private static Logger log = Logger.getLogger(FangXiang.class);
	
	public static final FangXiang dao = new FangXiang();
	
	public static final String sqlId_splitPageSelect = "manage.fangxiang.splitPageSelect";
	public static final String sqlId_splitPageFrom = "manage.fangxiang.splitPageFrom";
	
	
	public List<FangXiang> findByLuXianId(String luXianId){
		String sql = getSql("manage.fangxiang.findByLuXianId");
		List<FangXiang> list = FangXiang.dao.find(sql,luXianId);
		return list;
	}
	
}
