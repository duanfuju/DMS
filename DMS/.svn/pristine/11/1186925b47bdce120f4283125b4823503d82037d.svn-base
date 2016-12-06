package com.junl.dms.mvc.yangHuLog;


import org.apache.log4j.Logger;

import com.platform.mvc.base.BaseService;

public class YangHuLogService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(YangHuLogService.class);

	public static final YangHuLogService service = new YangHuLogService();

	public boolean save(YangHuLog yangHuLog) {
		return yangHuLog.save();
	}

	public boolean delete(String ids) {
		return YangHuLog.dao.deleteById(ids);
	}

	public YangHuLog findById(String ids) {
		YangHuLog yangHuLog = YangHuLog.dao.findById(ids);
		return yangHuLog;
	}
	
	public boolean update(YangHuLog yangHuLog){
		return yangHuLog.update();
	}
	
}
