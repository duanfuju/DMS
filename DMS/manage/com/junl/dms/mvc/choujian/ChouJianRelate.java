package com.junl.dms.mvc.choujian;

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
@Table(tableName = "DMS_CJ_chouJian_relate")
public class ChouJianRelate extends BaseModel<ChouJianRelate> {
	
	private static final long serialVersionUID = 5979434062234466436L;

	private static Logger log = Logger.getLogger(ChouJianRelate.class);
	
	public static final ChouJianRelate dao = new ChouJianRelate();
	
}
