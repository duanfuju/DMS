package com.junl.dms.mvc.baoyan;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.junl.dms.mvc.luxian.LuXian;
import com.junl.dms.mvc.rwtask.RwTask;
import com.platform.annotation.Table;
import com.platform.mvc.base.BaseModel;
import com.platform.mvc.base.BaseModelCache;
import com.platform.mvc.param.Param;
import com.platform.plugin.ParamInitPlugin;
import com.platform.tools.ToolCache;
import com.test.mvc.blog.Blog;


@SuppressWarnings("unused")
@Table(tableName = "xz_wx")
public class WxView extends BaseModel<WxView> {
	
	private static final long serialVersionUID = 5979434062234466436L;

	private static Logger log = Logger.getLogger(WxView.class);
	
	public static final WxView dao = new WxView();
	
	public static final String sqlId_splitPageSelect = "manage.wxview.splitPageSelect";
	public static final String sqlId_splitPageFrom = "manage.wxview.splitPageFrom";
	

	
		//查询所有
		public List<WxView> findAll(){
			String sql = getSql("manage.wxview.findAll");
			List<WxView> list = WxView.dao.find(sql);
			return list;
		}

}
