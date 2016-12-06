package com.junl.dms.mvc.xunchainfofzhlrelate;

import java.util.List;

import org.apache.log4j.Logger;

import com.junl.dms.mvc.chooseinfo.ChooseInfo;
import com.platform.annotation.Table;
import com.platform.mvc.base.BaseModel;


@SuppressWarnings("unused")
@Table(tableName = "DMS_XC_xunChaInfo_fzhl_relate")
public class XunChaInfoFzhlRelate extends BaseModel<XunChaInfoFzhlRelate> {
	
	private static final long serialVersionUID = 5979434062234466436L;

	private static Logger log = Logger.getLogger(XunChaInfoFzhlRelate.class);
	
	public static final XunChaInfoFzhlRelate dao = new XunChaInfoFzhlRelate();
	
	public static final String sqlId_splitPageSelect = "manage.xunchainfofzhlrelate.splitPageSelect";
	public static final String sqlId_splitPageFrom = "manage.xunchainfofzhlrelate.splitPageFrom";
	
	
	public List<XunChaInfoFzhlRelate> findListByfindListByxunChaInfoId(String xunChaInfoId) {
		String sql = getSql("manage.xunchainfofzhlrelate.findListByxunChaInfoId");
		List<XunChaInfoFzhlRelate> list = XunChaInfoFzhlRelate.dao.find(sql, xunChaInfoId);
		return list;
	}
}