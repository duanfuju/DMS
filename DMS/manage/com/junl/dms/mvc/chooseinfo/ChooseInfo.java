package com.junl.dms.mvc.chooseinfo;

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
@Table(tableName = "DMS_PZ_chooseInfo")
public class ChooseInfo extends BaseModel<ChooseInfo> {

	private static final long serialVersionUID = 5979434062234466436L;

	private static Logger log = Logger.getLogger(ChooseInfo.class);

	public static final ChooseInfo dao = new ChooseInfo();

	public List<ChooseInfo> findListByType(int type) {
		String sql = getSql("manage.chooseinfo.findListByType");
		List<ChooseInfo> list = this.dao.find(sql, type);
		return list;
	}
}
