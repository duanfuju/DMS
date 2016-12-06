package com.junl.dms.mvc.yhluduan;

import org.apache.log4j.Logger;

import com.platform.mvc.base.BaseService;

public class YhLuDuanService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(YhLuDuanService.class);

	public static final YhLuDuanService service = new YhLuDuanService();

	public boolean save(YhLuDuan yhLuDuan) {
		return yhLuDuan.save();
	}

	public boolean delete(String id) {
		return YhLuDuan.dao.deleteById(id);
	}

	public YhLuDuan findById(String id) {
		return YhLuDuan.dao.findById(id);
	}
	
	public boolean update(YhLuDuan yhLuDuan) {
		return yhLuDuan.update();
	}

}
