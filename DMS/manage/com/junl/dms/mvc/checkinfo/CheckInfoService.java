package com.junl.dms.mvc.checkinfo;

import java.util.List;
import java.util.Map;

public interface CheckInfoService {
	
	
	/**
	 * 获取父级菜单   ztree  json格式
	 * {id:1, pId:0, name: "父节点1"},
	 * {id:11, pId:1, name: "子节点1"},
	 * {id:12, pId:1, name: "子节点2"}
	 * @return
	 */
	List<Map> getParentMenuMap();
	
	/**
	 * 查询最顶级的菜单
	 * @return
	 */
	List<Map> getTopParentMenuMap();

}
