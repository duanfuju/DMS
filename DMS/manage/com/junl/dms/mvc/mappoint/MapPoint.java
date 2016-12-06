package com.junl.dms.mvc.mappoint;

import org.apache.log4j.Logger;

import com.platform.annotation.Table;
import com.platform.mvc.base.BaseModel;


/** 
 * @author  hank E-mail: hankun@junl.cn 
 * @date 创建时间：2016年8月30日 下午4:52:45 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 */
@Table(tableName = "DMS_APP_PointInfo")
public class MapPoint extends BaseModel<MapPoint> {

	private static final long serialVersionUID = 5979434062234466436L;

	private static Logger log = Logger.getLogger(MapPoint.class);
	
	public static final MapPoint dao = new MapPoint();
}
