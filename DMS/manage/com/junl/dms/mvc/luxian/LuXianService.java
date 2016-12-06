package com.junl.dms.mvc.luxian;

import java.util.List;

import org.apache.log4j.Logger;

import com.platform.mvc.base.BaseService;

public class LuXianService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(LuXianService.class);

	public static final LuXianService service = new LuXianService();

	public boolean save(LuXian luXian) {
		return luXian.save();
	}

	public boolean delete(String ids) {
		return LuXian.dao.deleteById(ids);
	}

	public LuXian findById(String ids) {
		LuXian luXian = LuXian.dao.findById(ids);
		return luXian;
	}
	
	public boolean update(LuXian luXian){
		return luXian.update();
	}
	
	public List<LuXian> findList(){
		List<LuXian> list = LuXian.dao.findList();
		return list;
	}

}
