package com.junl.dms.mvc.baoyan;

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
@Table(tableName = "DMS_BY_weiXiuBaoYanF")
public class BaoYanRelate extends BaseModel<BaoYanRelate> {

	private static final long serialVersionUID = 8279220916018574729L;

	private static Logger log = Logger.getLogger(BaoYanRelate.class);
	
	public static final BaoYanRelate dao = new BaoYanRelate();
	
	
	
		//获取本月的任务数
		public List<BaoYanRelate> selectDMS_BY_weiXiuBaoYanFByByIds(String ids){
			String sql = getSql("manage.baoyan.selectDMS_BY_weiXiuBaoYanFByByIds");
			List<BaoYanRelate> list = BaoYanRelate.dao.find(sql,ids);
			return list;
		}
}
