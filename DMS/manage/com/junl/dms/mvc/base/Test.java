package com.junl.dms.mvc.base;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.imageio.ImageIO;

import com.alibaba.fastjson.JSONObject;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

public class Test {
	  public static void main(String []args)throws Exception{   
	        String text = "http://blog.csdn.net/feiyu84/article/details/9089497";   
	        int width = 100;   
	        int height = 100;   
	        String format = "png";   
	        Hashtable<EncodeHintType, String> hints= new Hashtable<EncodeHintType, String>();   
	        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");   
	        BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height,hints);   
	        File outputFile = new File("d:/new.png");   
	        QrCodeToImageWriter.writeToFile(bitMatrix, format, outputFile);   
	            
	    }   
	  

	    /** 
	     * 解析二维码图像 
	     */  
	    public void testDecode() {  
	        String filePath = "D://zxing.png";  
	        BufferedImage image;  
	        try {  
	            image = ImageIO.read(new File(filePath));  
	            LuminanceSource source = new BufferedImageLuminanceSource(image);  
	            Binarizer binarizer = new HybridBinarizer(source);  
	            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);  
	            Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();  
	            hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");  
	            Result result = new MultiFormatReader().decode(binaryBitmap, hints);// 对图像进行解码  
	            JSONObject content = JSONObject.parseObject(result.getText());  
	            System.out.println("图片中内容：  ");  
	            System.out.println("author： " + content.getString("author"));  
	            System.out.println("zxing：  " + content.getString("zxing"));  
	            System.out.println("图片中格式：  ");  
	            System.out.println("encode： " + result.getBarcodeFormat());  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        } catch (NotFoundException e) {  
	            e.printStackTrace();  
	        }  
	    }  
	    
	    
	    /**
	     * 
	     * 生成二维码图片
	     * 
	     * @throws WriterException
	     * @throws IOException
	     */
	    public void testEncode() throws WriterException, IOException {  
	        JSONObject json = new JSONObject();  
	        json.put(  
	                "zxing",  
	                "https://github.com/zxing/zxing/tree/zxing-3.0.0/javase/src/main/java/com/google/zxing");  
	        json.put("author", "shihy");  
	        String content = json.toJSONString();// 内容  
	        int width = 200; // 图像宽度  
	        int height = 200; // 图像高度  
	        String format = "png";// 图像类型  
	        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();  
	        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");  
	        BitMatrix bitMatrix = new MultiFormatWriter().encode(content,  
	                BarcodeFormat.QR_CODE, width, height, hints);// 生成矩阵  
	        File outputFile = new File("D://zxing.png");   
	        QrCodeToImageWriter.writeToFile(bitMatrix, format, outputFile);   
//	        Path path = FileSystems.getDefault().getPath(filePath, fileName);  
//	        MatrixToImageWriter.writeToPath(bitMatrix, format, path);// 输出图像  
	        System.out.println("输出成功.");  
	    }  
	    
	    
	    
	    
	    /**
		 * 生成二维码图片
		 * @param imagePath   	        文件位置
		 * @param context		        二维码内容（可以是一个url，也可以是json）
		 * @param imageName		        文件名  带后缀的
		 * @param width	                            图片宽度
		 * @param height	                  图片高度
		 * @throws IOException
		 * @throws WriterException 
		 */
		public static void generatorQRCodeImage(File file,String context,int width,int height) throws IOException, WriterException
		{
			//判断目录是否存在 
			if(!file.getParentFile().isDirectory())
			{
				//不存在则创建该目录  
				file.mkdirs();
				file.createNewFile();
			}
			Hashtable<EncodeHintType, String> hints= new Hashtable<EncodeHintType, String>();   
		    hints.put(EncodeHintType.CHARACTER_SET, "utf-8");   
		    BitMatrix bitMatrix = new MultiFormatWriter().encode(context, BarcodeFormat.QR_CODE, width, height,hints);
		    String suffix = file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf("."));
		    QrCodeToImageWriter.writeToFile(bitMatrix,suffix , file); 
		}
	  
}
