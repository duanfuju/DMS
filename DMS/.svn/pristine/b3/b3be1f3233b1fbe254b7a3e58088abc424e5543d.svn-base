package com.platform.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.PropKit;
import com.platform.plugin.ParamInitPlugin;

/**
 * poi工具类
 * @author 董华健  dongcb678@163.com
 * 描述：建议导出规则是，先定义好excel模板，然后填充数据，这样避免编写很多不必要的样式代码
 */
public abstract class ToolPoi {

	private static Logger log = Logger.getLogger(ParamInitPlugin.class);

	/**
	 * excel导出
	 * @param templatePath 模板路径
	 */
	@SuppressWarnings("unused")
	public static String export(String templatePath) {
	    // 导出文件存放目录
	    String filePath = PathKit.getWebRootPath() + File.separator + "exportFile";
        File fileDir = new File(filePath);
        if(!fileDir.exists()){
        	fileDir.mkdir();
        }
        
	    // 导出文件路径
        String path = filePath + File.separator + ToolDateTime.format(new Date(), "yyyyMMddHHmmssSSS") + ".xlsx";
        
        XSSFWorkbook wb = null;
	    SXSSFWorkbook swb = null;
        FileOutputStream os = null;
		try {
			// 1.载入模板
	 		wb = new XSSFWorkbook(new File(templatePath));//初始化HSSFWorkbook对象    
	 		wb.setSheetName(0, "用户信息导出");
		    Sheet sheet = wb.getSheetAt(0);// wb.createSheet("监控点资源状态"); 
		    
		    // 2.读取模板处理好样式
		    
		    
		    // 3.转换成大数据读取模式
		    swb = new SXSSFWorkbook(wb, 1000);//用于大文件导出
			sheet = swb.getSheetAt(0);
			
			// 4.大批量写入数据

		    
	        // 5.保存到本地文件夹
			os = new FileOutputStream(new File(path));
			swb.write(os);  
			
			return path;
		} catch (IOException | InvalidFormatException e) {
			log.error("导出失败：" + e.getMessage());
			e.printStackTrace();
			return null;
		} finally {
			if(null != os){
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if(null != wb){
				try {
					wb.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if(null != swb){
				try {
					swb.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	
	public static JSONArray ReadExcel(String filename, String proName) throws Exception {
		String[] titles = PropKit.get(proName).split(",");
		JSONArray jsonArr = new JSONArray();
		try {
			if(filename.endsWith("xls"))
			{
				@SuppressWarnings("resource")
				HSSFWorkbook wookbook = new HSSFWorkbook(new FileInputStream(filename));
				HSSFSheet sheet = wookbook.getSheetAt(0);
				jsonArr = readRows(titles, sheet, 1);
				wookbook = null;
			}
			else
			{
				XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new FileInputStream(filename));
				XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
				jsonArr = readRows(titles, sheet, 1);
				xssfWorkbook = null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonArr;
	}
	
	/**
	 * 读取多行
	 * @param excelDefinition
	 * @param titles
	 * @param sheet
	 * @param titleIndex
	 * @return
	 * @throws Exception
	 */
	protected static JSONArray readRows(String[] titles, Sheet sheet,int titleIndex)throws Exception {
		int rowNum = sheet.getLastRowNum();
		JSONArray jsonArr = new JSONArray();
		for (int i = titleIndex; i < rowNum; i++) {
			Row row = sheet.getRow(i);
			JSONObject jsonObj = readRow(row,titles,i);
			if(jsonObj !=null){
				jsonArr.add(jsonObj);
			}
		}
		return jsonArr;
	}
	
	/**
	 * 获取标题
	 * @param sheet
	 * @param titleIndex
	 * @return
	 */
	protected String[] readTitle(Sheet sheet,int titleIndex) {
		// 获取Excel标题数据
		Row hssfRowTitle = sheet.getRow(titleIndex);
		int cellNum = hssfRowTitle.getLastCellNum();
		StringBuffer titles = new StringBuffer();
		// 获取标题数据
		for (int i = 0; i < cellNum; i++) {
			Cell cell = hssfRowTitle.getCell(i);
			Object value = getCellValue(cell);
			titles.append(value);
			titles.append(",");
		}
		return titles.toString().substring(0, titles.toString().length()-1).split(",");
	}
	
	/**
	 * 读取1行
	 * @param excelDefinition
	 * @param row
	 * @param titles
	 * @param rowNum 第几行
	 * @return
	 * @throws Exception
	 */
	protected static JSONObject readRow(Row row, String[] titles,int rowNum) throws Exception {
		JSONObject jsonObj = new JSONObject();
		for (int j = 0; j < titles.length; j++) {
			Cell cell = row.getCell(j);
			//获取Excel原生value值
			//if(HSSFDateUtil.isCellDateFormatted(cell))
			
			Object value = getCellValue(cell);
			if(value != null){
				if(value instanceof String){
					//去除前后空格
					value = value.toString().trim();
				}
			}else{
				value = "";
			}
			if(j == 0 && value ==""){ //第一项为空则结束读取
				return null;
			}
			jsonObj.put(titles[j], value);
		}
		return jsonObj;
	}
	
	
	
	
	/**
	 * 获取cell值
	 * 
	 * @param cell
	 * @return
	 */
	protected static Object getCellValue(Cell cell) {
		Object value = null;
		if (null != cell) {
			switch (cell.getCellType()) {
			// 空白
			case Cell.CELL_TYPE_BLANK:
				break;
			// Boolean
			case Cell.CELL_TYPE_BOOLEAN:
				value = cell.getBooleanCellValue();
				break;
			// 错误格式
			case Cell.CELL_TYPE_ERROR:
				break;
			// 公式
			case Cell.CELL_TYPE_FORMULA:
				Workbook wb = cell.getSheet().getWorkbook();
				CreationHelper crateHelper = wb.getCreationHelper();
				FormulaEvaluator evaluator = crateHelper.createFormulaEvaluator();
				value = getCellValue(evaluator.evaluateInCell(cell));
				break;
			// 数值
			case Cell.CELL_TYPE_NUMERIC:
				// 处理日期格式
				if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)) {
//					value = cell.getDateCellValue().getTime()
//					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
//					String v = sdf.format(value);
//					System.out.println(v);
//					try {
//						value = Timestamp.valueOf(v);
//						System.out.println(value);
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
					value = new Timestamp(cell.getDateCellValue().getTime());
				} else {
					value = NumberToTextConverter.toText(cell.getNumericCellValue());
				}
				break;
			case Cell.CELL_TYPE_STRING:
				value = cell.getRichStringCellValue().getString();
				break;
			default:
				value = null;
			}
		}
		return value;
	}
	
	/**
	 * 数据有效性校验
	 * @param fieldValue
	 * @param value
	 * @param rowNum
	 */
//	private void validate(FieldValue fieldValue,Object value,int rowNum){
//		if(value == null || StringUtils.isBlank(value.toString())){
//			//空校验
//			if(!fieldValue.isNull()){
//				String err = getErrorMsg(fieldValue, "不能为空", rowNum);
//				throw new ExcelException(err);
//			}
//		}else{
//			//正则校验
//			String regex = fieldValue.getRegex();
//			if(StringUtils.isNotBlank(regex)){
//				String val = value.toString().trim();
//				if(!val.matches(regex)){
//					String errMsg = fieldValue.getRegexErrMsg()==null?"格式错误":fieldValue.getRegexErrMsg();
//					String err = getErrorMsg(fieldValue, errMsg, rowNum);
//					throw new ExcelException(err);
//				}
//			}
//		}
//	}
	
	/**
	 * 获取错误消息
	 * @param fieldValue
	 * @param errMsg 消息提示内容
	 * @param rowNum
	 * @return
	 */
	protected String getErrorMsg(FieldValue fieldValue,String errMsg,int rowNum){
		StringBuilder err = new StringBuilder();
		err.append("第[").append(rowNum).append("行],[")
		.append(fieldValue.getTitle()).append("]").append(errMsg);
		return err.toString();
	}
	
}
