package com.junl.dms.mvc.weizhi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.platform.mvc.base.BaseService;

public class WeiZhiService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(WeiZhiService.class);

	public static final WeiZhiService service = new WeiZhiService();

	public boolean save(WeiZhi weiZhi) {
		return weiZhi.save();
	}

	public boolean delete(String id) {
		return WeiZhi.dao.deleteById(id);
	}

	public WeiZhi findById(String id) {
		WeiZhi weiZhi = WeiZhi.dao.findById(id);
		return weiZhi;
	}

	public boolean update(WeiZhi weiZhi) {
		return weiZhi.update();
	}

	/**
	 * 据路线id返回包含的位置类型
	 * 
	 * @param luXianId
	 * @return
	 */
	public List<Map<String, String>> findWzTypeByLuXianId(String luXianId) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		List<WeiZhi> weiZhiList = WeiZhi.dao.findWzTypeByLuXianId(luXianId);
		for (WeiZhi weiZhi : weiZhiList) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("value", weiZhi.getStr("wzType"));
			map.put("name", weiZhi.getStr("name"));
			list.add(map);
		}
		return list;
	}

	/**
	 * 根据路线id和位置类型id查询包含的位置名称
	 * 
	 * @param luXianId
	 * @return
	 */
	public List<Map<String, String>> findWzNameByLxIdAndWzType(String luXianId, String wzTypeId) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		List<WeiZhi> weiZhiList = WeiZhi.dao.findWzNameByLxIdAndWzType(luXianId, wzTypeId);
		for (WeiZhi weiZhi : weiZhiList) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("value", weiZhi.getStr("wzName"));
			map.put("name", weiZhi.getStr("wzName"));
			map.put("zhk",  weiZhi.get("ZHK").toString());
			map.put("zhm",  weiZhi.get("ZHM").toString());
			list.add(map);
		}
		return list;
	}

	/**
	 *  根据路线id、位置类型id、位置名称查询包含的位置描述
	 * @param luXianId
	 * @param wzTypeId
	 * @param wzName
	 * @return
	 */
	public List<Map<String, String>> findWzMiaoShuByLxIdAndWzTypeAndName(String luXianId, String wzTypeId,
			String wzName) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		List<WeiZhi> weiZhiList = WeiZhi.dao.findWzMiaoShuByLxIdAndWzTypeAndName(luXianId, wzTypeId, wzName);
		for (WeiZhi weiZhi : weiZhiList) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("value", weiZhi.getStr("wzMiaoShu"));
			map.put("name", weiZhi.getStr("wzMiaoShu"));
			list.add(map);
		}
		return list;
	}

}
