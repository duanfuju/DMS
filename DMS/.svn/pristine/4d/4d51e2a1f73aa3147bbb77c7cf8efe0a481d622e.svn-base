package com.junl.dms.tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;


/**
 * 
 * @author hank
 * @date 2016年7月21日 下午1:37:57
 * @description
 *	freemark word 工具类
 */
public class WordTools {
	
	@SuppressWarnings({ "rawtypes", "deprecation" })
	public static void createWord(Map dataMap, String templateName,
			String filePath, String fileName) {
		try {
			Configuration configuration = new Configuration();
			configuration.setDefaultEncoding("UTF-8");
			configuration.setClassForTemplateLoading(WordTools.class,
					"/com/junl/template/");
			Template template = configuration.getTemplate(templateName);
			File outFile = new File(filePath + File.separator + fileName);
			if (!outFile.getParentFile().exists()) {
				outFile.getParentFile().mkdirs();
			}
			Writer out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(outFile), "UTF-8"));
			template.process(dataMap, out);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		
		 Map<String, Object> dataMap = new HashMap<String, Object>();
		 List<Map<String, Object>> mapList = getDate();
		 dataMap.put("fangxiang", "方向信息");
		 dataMap.put("youwuList", mapList);
        String filePath="/Users/apple/Downloads/wwwDoc/";
       
        String fileOnlyName = "youwuchulijilu.doc";
       
        String fileName="youwuchulijilu.ftl";
       
        /** 生成word */
        WordTools.createWord(dataMap, fileName, filePath, fileOnlyName);
       
	}
	
	
	public static List<Map<String, Object>> getDate(){
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
		for (int i = 0; i < 20; i++) {
//			DemoBean db = new DemoBean();
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("chedao","车道"+i);
			dataMap.put("chulidate","2017/07/"+i);
			dataMap.put("fangxiang","方向信息"+i);
			dataMap.put("faxiandate","2017/06/"+i);
			dataMap.put("mianji", "10"+i);
			dataMap.put("zhuanghao","桩号信息"+i);
			mapList.add(dataMap);
		}
		return mapList;
	}
	
	
}
