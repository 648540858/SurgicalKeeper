package com.rjkx.sk.system.img;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.rjkx.sk.system.utils.SpringBeanLoader;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 图片压缩工具类
 * 可以正常实现bmp转jpg、png转jpg、gif转jpg，
 * @author Rally_
 *
 */
public class ImageHelper {
	
	private static Log log = LogFactory.getLog(ImageHelper.class);
	
	/**
	 * 需要压缩的图片
	 */
	private File inputFile;
	/**
	 * 压缩后输出的图片流
	 */
	private File outputFile;
	/**
	 * 是否为等比例缩放:默认等比例缩放
	 */
	 private boolean proportion = true;
	 /**
	  * 输出图片宽度
	  */
	 private int outImageWidth = 110;
	 /**
	  * 暑促图片高度
	  */
	 private int outImageHeight = 140;
	
	 /**
	  * 构造方法(用于非Spring环境中)
	  */
	 public ImageHelper()
	 {
		 this.inputFile= null;
		 
		 this.outputFile = null;
		 
		 log.info("构造ImageHelper(无输入/输出路径)成功!");
	 }
	 /**
	  * 构造方法(用于非Spring环境中)
	  * @param inputStream 图片文件输入流
	  * @param outputStream 输出流
	  */
	 public  ImageHelper(File input,File output)
	 {
		 this.setInputFile(input);
		 
		 this.setOutputFile(output);
		 
		 log.info("构造ImageHelper(有输入/输出路径)成功!");
	 }
	 
	/**
	 * 获取实例
	 * @return
	 */
	public static ImageHelper getInstance()
	{
		ImageHelper imageHelper = (ImageHelper) SpringBeanLoader.getSpringBean("imageHelper");
		
		imageHelper.setInputFile(null);
		
		imageHelper.setOutputFile(null);
		
		return imageHelper;
	}
	/**
	 * 获取实例
	 * @param inputStream
	 * @param outputStream
	 * @return
	 */
	public static ImageHelper getInstance(File input,File output)
	{
		ImageHelper imageHelper = (ImageHelper) SpringBeanLoader.getSpringBean("imageHelper");
		
		imageHelper.setInputFile(input);
		 
		imageHelper.setOutputFile(output);
		
		return imageHelper;
	}
	/**
	 * 压缩 图片
	 * @return
	 */
	 public boolean compressPic() 
	 {  
		 if(this.getInputFile() == null || this.getOutputFile() == null)
		 {
			 log.error("输入或输出文件路径为空!请设置后重新操作!");
			 
			 return false;
		 }
		 else
		 {
			 try {
				Image img = ImageIO.read(inputFile);
				
				if(img.getWidth(null) == -1)
				{
					log.error("图片压缩文件类型出错!");
					
					return false;
				}
				else
				{
					int newWidth , newHeight;   
	                
	                 if (this.proportion == true) 
	                 {  
	                	 double rate1 , rate2; 
	                	 
	                	 if(this.getOutImageWidth() > img.getWidth(null) && this.getOutImageHeight() > img.getHeight(null))
	                	 {
	                		 rate1 = 1; 
		                     
		                     rate2 = 1;   
	                	 }
	                	 else
	                	 {
	                		 rate1 = ((double) img.getWidth(null)) / (double) this.getOutImageWidth() + 0.1; 
	                		 
	                		 rate2 = ((double) img.getHeight(null)) / (double) this.getOutImageHeight() + 0.1;   
	                	 }
	                     
	                     double rate = rate1 > rate2 ? rate1 : rate2;  
	                     
	                     newWidth = (int) (((double) img.getWidth(null)) / rate); 
	                     
	                     newHeight = (int) (((double) img.getHeight(null)) / rate); 
	                     
	                 } 
	                 else
	                 {   
	                	 if(this.getOutImageWidth() > img.getWidth(null) && this.getOutImageHeight() > img.getHeight(null))
	                	 {
	                		 newWidth = img.getWidth(null); 
		                     
		                     newHeight = img.getHeight(null);
	                	 }
	                	 else
	                	 {
	                		 newWidth = this.getOutImageWidth(); 
	                		 
	                		 newHeight = this.getOutImageHeight();   	                		 
	                	 }
	                 }
	                 
	                 BufferedImage tag = new BufferedImage((int) newWidth, (int) newHeight, BufferedImage.TYPE_INT_RGB);
	                
	                 tag.getGraphics().drawImage(img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null); 
	                 
	                 FileOutputStream out = new FileOutputStream(outputFile);  
	                   
	                 JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out); 
	                 
	                 encoder.encode(tag); 
	                 
	                 out.close();
	                 
	                 log.info(inputFile.getPath() + ":图片压缩完成!输出路径为:" + outputFile.getPath());
	                 
	                 return true;
				}
				
			} 
			 catch (IOException e) 
			{
				log.error("图片压缩出错!");
				
				e.printStackTrace();
				
				return false;
			}
			 
		 }
	 }
	 /**
	  * 使用默认输出大小等比例压缩
	  * @param inputFileDir 图片路径
	  * @param outputFileDir 输出路径
	  * @return
	  */
	 public boolean compressPic(String inputFilePath,String outputFilePath)
	 {
		 this.setInputFile(new File(inputFilePath));
		 
		 this.setOutputFile(new File(outputFilePath));
		 
		 return this.compressPic();
	 }
	/**
	 * 压缩图片
	 * @param inputFileDir 图片路径
	  * @param outputFileDir 输出路径
	 * @param outWidth 输出图片宽度
	 * @param outHeight 输出图片高度
	 * @param isPg 是否等比例压缩
	 * @return
	 */
	 public boolean compressPic(String inputFilePath,String outputFilePath,int outWidth,int outHeight,boolean isPg)
	 {
		 this.setInputFile(new File(inputFilePath));
		 
		 this.setOutputFile(new File(outputFilePath));
		 
		 this.setOutImageWidth(outWidth);
		 
		 this.setOutImageHeight(outHeight);
		 
		 this.setProportion(isPg);
		 
		 return this.compressPic();
	 }
	 /**
	  * 裁剪
	  * @param top
	  * @param left
	  * @param width
	  * @param height
	  * @return
	  */
	 public boolean cutPic( int top,int left,int width,int height)
	 {
		 if(this.getInputFile() == null || this.getOutputFile() == null)
		 {
			 log.error("输入或输出文件路径为空!请设置后重新操作!");
			 
			 return false;
		 }
		 else
		 {
			 try {
				BufferedImage bi = (BufferedImage)ImageIO.read(this.getInputFile());
				
				BufferedImage out = null;
				
				if (top > 0 && left >0 && bi.getWidth() >= width && bi.getHeight() >= width) 
				{
				           out = bi.getSubimage(top, left, width, height);
				} 
				else 
				{
				           out = bi.getSubimage(0, 0, bi.getWidth(), bi.getHeight());
				}
				
				return ImageIO.write(out, "jpeg", this.getOutputFile());
			} 
			catch (IOException e) 
			{
				log.error("图片剪切出错!");
				
				e.printStackTrace();
				
				return false;
			}
		 }
	 }
	 
	 public boolean cutPic(String inputFilePath,String outputFilePath, int top,int left,int width,int height)
	 {
		 this.setInputFile(new File(inputFilePath));
		 
		 this.setOutputFile(new File(outputFilePath));
		 
		 return this.cutPic(top, left, width, height);
	 }
	 
	 
	public File getInputFile() {
		return inputFile;
	}
	public void setInputFile(File inputFile) {
		this.inputFile = inputFile;
	}
	public File getOutputFile() {
		return outputFile;
	}
	public void setOutputFile(File outputFile) {
		this.outputFile = outputFile;
	}
	public boolean isProportion() {
		return proportion;
	}
	public void setProportion(boolean proportion) {
		this.proportion = proportion;
	}
	public int getOutImageWidth() {
		return outImageWidth;
	}
	public void setOutImageWidth(int outImageWidth) {
		this.outImageWidth = outImageWidth;
	}
	public int getOutImageHeight() {
		return outImageHeight;
	}
	public void setOutImageHeight(int outImageHeight) {
		this.outImageHeight = outImageHeight;
	}
}
