package com.junl.dms.mvc.systemhelp.mobileClientDownload;

import org.apache.log4j.Logger;

import com.platform.annotation.Table;
import com.platform.mvc.base.BaseModel;


/**
 * 
 * 手机客户端下载
 * 
 * @author MENGJIANJUN
 *
 */

@Table(tableName = "DMS_APP_downloadApp")
public class DownloadApp extends BaseModel<DownloadApp>  {

	private static final long serialVersionUID = -4339920556727267696L;
	
	private static Logger log = Logger.getLogger(DownloadApp.class);
	
	public static final DownloadApp dao = new DownloadApp();
	
	public static final String sqlId_splitPageSelect = "manage.downloadApp.splitPageSelect";
	
	public static final String sqlId_splitPageFrom = "manage.downloadApp.splitPageFrom";

}
