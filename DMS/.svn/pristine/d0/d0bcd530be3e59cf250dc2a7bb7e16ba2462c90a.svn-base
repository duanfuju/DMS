package com.junl.dms.mvc.binghaiindex;

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
@Table(tableName = "DMS_PZ_bingHaiIndex")
public class BingHaiIndex extends BaseModel<BingHaiIndex> {

	private static final long serialVersionUID = -7025718258866632568L;

	private static Logger log = Logger.getLogger(BingHaiIndex.class);

	public static final BingHaiIndex dao = new BingHaiIndex();

	public static final String sqlId_splitPageSelect = "manage.binghaiindex.splitPageSelect";
	public static final String sqlId_splitPageFrom = "manage.binghaiindex.splitPageFrom";

	public List<BingHaiIndex> findByWxmkId(String wxmkId) {
		String sql = getSql("manage.binghaiindex.findByWxmkId");
		List<BingHaiIndex> list = BingHaiIndex.dao.find(sql, wxmkId);
		return list;
	}

}
