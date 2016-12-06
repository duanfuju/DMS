package com.junl.dms.mvc.baoyanlist;

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
@Table(tableName = "DMS_BY_weiXiuBaoYanP")
public class BaoYanList extends BaseModel<BaoYanList> {
	
	private static final long serialVersionUID = 5979434062234466436L;

	private static Logger log = Logger.getLogger(BaoYanList.class);
	
	public static final BaoYanList dao = new BaoYanList();
	
	public static final String sqlId_splitPageSelect = "manage.baoyan.splitPageSelect";
	public static final String sqlId_splitPageFrom = "manage.baoyan.splitPageFrom";
	

	//获取本月的任务数
		public List<BaoYanList> getTaskNo(){
			String sql = getSql("manage.baoyan.getTaskNo");
			List<BaoYanList> list = BaoYanList.dao.find(sql);
			return list;
		}

}
