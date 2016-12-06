package com.junl.dms.mvc.fangzhl;

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
@Table(tableName = "DMS_PZ_fzhlBuJian")
public class FangZHL extends BaseModel<FangZHL> {

	private static final long serialVersionUID = -83871528360607637L;

	private static Logger log = Logger.getLogger(FangZHL.class);

	public static final FangZHL dao = new FangZHL();

	public static final String sqlId_splitPageSelect = "manage.fangzhl.splitPageSelect";
	public static final String sqlId_splitPageFrom = "manage.fangzhl.splitPageFrom";

	public List<FangZHL> findList() {
		String sql = getSql("manage.fangzhl.findList");
		List<FangZHL> list = FangZHL.dao.find(sql);
		return list;
	}
}
