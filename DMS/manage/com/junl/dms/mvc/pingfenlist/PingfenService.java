package com.junl.dms.mvc.pingfenlist;


import java.sql.Timestamp;

import org.apache.log4j.Logger;

import com.platform.mvc.base.BaseService;

public class PingfenService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(PingfenService.class);

	public static final PingfenService service = new PingfenService();

	public boolean save(Pingfen dayplan) {
//		dayplan.set("state", 0);
		dayplan.set("createTime", new Timestamp(System.currentTimeMillis()));
		return dayplan.save();
	}

	public boolean delete(String ids) {
		return Pingfen.dao.deleteById(ids);
	}

	public Pingfen findById(String ids) {
		Pingfen dayplan = Pingfen.dao.findById(ids);
		return dayplan;
	}
	
	public boolean update(Pingfen dayplan){
		return dayplan.update();
	}
/*	public List<Shenqingzhifubiao> findByDate(){
		List<Shenqingzhifubiao> list = Shenqingzhifubiao.dao.findByDate();
		return list;
	}

	public List<Shenqingzhifubiao> findByMonthIds(String ids) {
		List<Shenqingzhifubiao> list = Shenqingzhifubiao.dao.findByMonthIds(ids);
		return list;
	}
*/
	
}
