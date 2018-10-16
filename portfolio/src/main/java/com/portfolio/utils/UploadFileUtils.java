package com.portfolio.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.util.FileCopyUtils;



public class UploadFileUtils {

	//기본 이미지 사이즈로 업로드
	public static String noneChangeNameUploadFile(String uploadPath, String originalName, byte[] fileData) throws Exception{
		
		String savedName = originalName;
		String savedPath = calcPath(uploadPath);
		
		File target = new File(uploadPath + savedPath, savedName);
		FileCopyUtils.copy(fileData, target);
		
		String formatName = originalName.substring(originalName.lastIndexOf(".") + 1);
		String uploadedFileName = null;
		
		if(MimeMediaUtil.getMediaType(formatName) != null){
			String temp = uploadPath + savedPath + File.separator + savedName;
			uploadedFileName = temp.substring(uploadPath.length()).replace(File.separator, "/");
		}else{
			String temp = uploadPath + savedPath + File.separator + savedName;
			new File(uploadPath + temp.substring(uploadPath.length()).replace("/", File.separator)).delete();
		}
		
		return uploadedFileName;
	}

	//기본 이미지 사이즈로 업로드 | 고유 아이디로 이름이 추가 수정된다.
	public static String defaultUploadFile(String uploadPath, String originalName, byte[] fileData) throws Exception{
		
		UUID uid = UUID.randomUUID();
		String savedName = uid.toString() + originalName.substring(originalName.lastIndexOf("."));
		String savedPath = calcPath(uploadPath);
		
		File target = new File(uploadPath + savedPath, savedName);
		FileCopyUtils.copy(fileData, target);
		
		String formatName = originalName.substring(originalName.lastIndexOf(".") + 1);
		String uploadedFileName = null;
		
		if(MimeMediaUtil.getMediaType(formatName) != null){
			String temp = uploadPath + savedPath + File.separator + savedName;
			uploadedFileName = temp.substring(uploadPath.length()).replace(File.separator, "/");
		}else{
			String temp = uploadPath + savedPath + File.separator + savedName;
			new File(uploadPath + temp.substring(uploadPath.length()).replace("/", File.separator)).delete();
		}
		
		return uploadedFileName;
	}
	
	//이미지 사이즈 조정 - 썸네일
	public static String thumbnailUploadFile(String uploadPath, String originalName, byte[] fileData) throws Exception{
		
		UUID uid = UUID.randomUUID();
		String savedName = uid.toString() + originalName.substring(originalName.lastIndexOf("."));
		String savedPath = calcPath(uploadPath);
		
		File target = new File(uploadPath + savedPath, savedName);
		FileCopyUtils.copy(fileData, target);
		
		String formatName = originalName.substring(originalName.lastIndexOf(".") + 1);
		String uploadedFileName = null;
		
		if(MimeMediaUtil.getMediaType(formatName) != null){
			uploadedFileName = makeThumbnail(uploadPath, savedPath, savedName);
		}else{
			String temp = uploadPath + savedPath + File.separator + savedName;
			new File(uploadPath + temp.substring(uploadPath.length()).replace("/", File.separator)).delete();
		}
		
		return uploadedFileName;
	}
	
	public static String fileAllUpload(String uploadPath, String originalName, byte[] fileData) throws Exception{
		
		UUID uid = UUID.randomUUID();
		String savedName = uid.toString() + originalName.substring(originalName.lastIndexOf("."));
		String savedPath = calcPath(uploadPath);
		
		File target = new File(uploadPath + savedPath, savedName);
		FileCopyUtils.copy(fileData, target);
		
		String uploadedFileName = null;
		
		String temp = uploadPath + savedPath + File.separator + savedName;
		uploadedFileName = temp.substring(uploadPath.length()).replace(File.separator, "/");
		
		return uploadedFileName;
	}
	
	/* 디렉토리명을 위한 날짜 확인 코드 */
	private static String calcPath(String uploadPath){
		Calendar cal = Calendar.getInstance();
		String yearPath = File.separator + cal.get(Calendar.YEAR);
		String monthpath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH)+1);
		String datePath = monthpath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));
		makeDir(uploadPath, yearPath, monthpath, datePath);
		return datePath;
	}
	
	/* 디렉토리 생성 코드 */
	private static void makeDir(String uploadPath, String... paths){
		
		if(new File(paths[paths.length-1]).exists()){ return; }
		
		for(String path: paths){
			File dirPath = new File(uploadPath + path);
			
			if(! dirPath.exists()){
				dirPath.mkdir();
			}
		}
	}
	
	private static String makeThumbnail(String uploadPath, String path, String fileName) throws Exception{
		BufferedImage sourceImg = ImageIO.read(new File(uploadPath + path, fileName));
		BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 300);
		String thumbnailName = uploadPath + path + File.separator + fileName;
		File newFile = new File(thumbnailName);
		String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
		ImageIO.write(destImg, formatName.toUpperCase(), newFile);
		return thumbnailName.substring(uploadPath.length()).replace(File.separator, "/");
	}
	
}
