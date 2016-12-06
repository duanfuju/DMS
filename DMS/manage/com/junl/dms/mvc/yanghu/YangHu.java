package com.junl.dms.mvc.yanghu;

import java.sql.Timestamp;
import java.util.ArrayList;
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
@Table(tableName = "DMS_WX_yangHuTongYongWeiXiu")
public class YangHu extends BaseModel<YangHu> {
	
	private static final long serialVersionUID = 7271172856471855849L;

	private static Logger log = Logger.getLogger(YangHu.class);
	
	public static final YangHu dao = new YangHu();
	
	public static final String sqlId_splitPageSelect = "manage.yanghu.splitPageSelect";
	public static final String sqlId_splitPageFrom = "manage.yanghu.splitPageFrom";
	
	
	
	
	
	
	
}
