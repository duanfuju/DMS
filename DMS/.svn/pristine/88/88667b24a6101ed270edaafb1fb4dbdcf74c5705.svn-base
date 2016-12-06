package com.junl.dms.tools;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import com.jfinal.kit.PropKit;
import com.platform.tools.ToolDateTime;

public class ToolsFile {

	
	/**
	 * 
	 * @method getPathUrl
	 * @author tongzhenghan
	 * @date 2016年7月18日 下午3:37:57
	 * @description
	 *		获取图片文件绝对路径
	 *
	 * @param request
	 * @return
	 */
	public static String getPathUrl(HttpServletRequest request){
		String serverpath = request.getServletContext().getRealPath("");
		StringBuffer path = new StringBuffer(serverpath.substring(0,serverpath.indexOf("webapps")));
//		path.append(File.separator);
//		path.append("E:/workInstall/www");
		path.append(PropKit.get("tomcat.imgFolderName"));
//		path.append(PropertiesPlugin.getParamMapValue("resource.img.path"));
		path.append(File.separator);
		path.append("file");
		path.append(File.separator);
		path.append(getPathDate());
		path.append(File.separator);
		return path.toString();
	}
	
	public static String getPathDate(){
		String format = ToolDateTime.format(ToolDateTime.getDate(), ToolDateTime.pattern_ymd);
		return format;
	}
}
