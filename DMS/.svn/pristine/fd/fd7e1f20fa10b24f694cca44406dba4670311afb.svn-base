package com.junl.dms.tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class WordUtil {
	
	/**freemark word 工具类,生成word
	 * @param params 数据map
	 * @param templateName 模板
	 * @param fileName 保存路径
	 * @throws IOException 
	 * @throws ParseException 
	 * @throws MalformedTemplateNameException 
	 * @throws TemplateNotFoundException 
	 */
	public static void create(Map<String, Object> params, String templateName, String fileName) throws Exception{
			Configuration configuration = new Configuration();
			configuration.setDefaultEncoding("UTF-8");
			//String path = Demo.class.getClassLoader().getResource("").getPath();
			configuration.setClassForTemplateLoading(WordUtil.class, "/com/junl/template");
			Template temp = configuration.getTemplate(templateName);
			Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(fileName )),"UTF-8")); ;
			temp.process(params, out);
	}
	
}
