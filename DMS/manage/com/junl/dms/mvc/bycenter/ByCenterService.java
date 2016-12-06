package com.junl.dms.mvc.bycenter;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.platform.mvc.base.BaseService;

public class ByCenterService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(ByCenterService.class);

	public static final ByCenterService service = new ByCenterService();

	public boolean save(ByCenter bycenter) {
		return bycenter.save();
	}

	public boolean delete(String ids) {
		return ByCenter.dao.deleteById(ids);
	}

	public ByCenter findById(String ids) {
		ByCenter luXian = ByCenter.dao.findById(ids);
		return luXian;
	}
	
	public boolean update(ByCenter bycenter){
		return bycenter.update();
	}
	public List<Map<String, String>> findList(){
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		List<ByCenter> clist = ByCenter.dao.findList();
		for (ByCenter c : clist) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("name", c.getStr("name"));
			list.add(map);
		}
		return list;
	}

}
