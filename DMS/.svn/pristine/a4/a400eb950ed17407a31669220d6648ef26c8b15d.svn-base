package com.junl.dms.mvc.weizhi;

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
@Table(tableName = "DMS_PZ_weiZhi")
public class WeiZhi extends BaseModel<WeiZhi> {
	
	private static final long serialVersionUID = 5979434062234466436L;

	private static Logger log = Logger.getLogger(WeiZhi.class);
	
	public static final WeiZhi dao = new WeiZhi();
	
	public static final String sqlId_splitPageSelect = "manage.weizhi.splitPageSelect";
	public static final String sqlId_splitPageFrom = "manage.weizhi.splitPageFrom";
	
	/**
	 * 根据路线id返回包含的位置类型
	 */
	public List<WeiZhi> findWzTypeByLuXianId(String luXianId){
		String sql = getSql("manage.weizhi.findWzTypeByLuXianId");
		List<WeiZhi> list = WeiZhi.dao.find(sql,luXianId);
		return list;
	}
	
	/**
	 * 根据路线id和位置类型id查询包含的位置名称
	 * @param luXianId
	 * @param wzTypeId
	 * @return
	 */
	public List<WeiZhi> findWzNameByLxIdAndWzType(String luXianId,String wzTypeId){
		String sql = getSql("manage.weizhi.findWzNameByLxIdAndWzType");
		List<WeiZhi> list = WeiZhi.dao.find(sql,luXianId,wzTypeId);
		return list;
	}
	
	/**
	 * 根据路线id、位置类型id、位置名称查询包含的位置描述
	 * 
	 * @param luXianId
	 * @param wzTypeId
	 * @param wzName
	 * @return
	 */
	public List<WeiZhi> findWzMiaoShuByLxIdAndWzTypeAndName(String luXianId,String wzTypeId,String wzName){
		String sql = getSql("manage.weizhi.findWzMiaoShuByLxIdAndWzTypeAndName");
		List<WeiZhi> list = WeiZhi.dao.find(sql, luXianId,wzTypeId,wzName);
		return list;
	}
}
