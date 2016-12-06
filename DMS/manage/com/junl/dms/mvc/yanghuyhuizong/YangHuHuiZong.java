package com.junl.dms.mvc.yanghuyhuizong;

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
public class YangHuHuiZong extends BaseModel<YangHuHuiZong> {
	
	private static final long serialVersionUID = 5979434062234466436L;

	private static Logger log = Logger.getLogger(YangHuHuiZong.class);
	
	public static final YangHuHuiZong dao = new YangHuHuiZong();
	
	public static final String sqlId_splitPageSelect = "manage.yanghuyhuizong.splitPageSelect";
	public static final String sqlId_splitPageFrom = "manage.yanghuyhuizong.splitPageFrom";
	
}
