package com.junl.dms.mvc.xunchainfofzhlrelate;

import org.apache.log4j.Logger;

import com.platform.mvc.base.BaseService;

public class XunChaInfoFzhlRelateService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(XunChaInfoFzhlRelate.class);

	public static final XunChaInfoFzhlRelateService service = new XunChaInfoFzhlRelateService();

	public boolean save(XunChaInfoFzhlRelate xunChaInfoFzhlRelate) {
		
		return xunChaInfoFzhlRelate.save();
	}

	public boolean delete(String ids) {
		return XunChaInfoFzhlRelate.dao.deleteById(ids);
	}

	public XunChaInfoFzhlRelate findById(String ids) {
		XunChaInfoFzhlRelate xunChaInfoFzhlRelate = XunChaInfoFzhlRelate.dao.findById(ids);
		return xunChaInfoFzhlRelate;
	}
	
	public boolean update(XunChaInfoFzhlRelate xunChaInfoFzhlRelate){
		return xunChaInfoFzhlRelate.update();
	}

}
