package com.CollageAdmissionForm;

import java.io.File;
import java.io.FileOutputStream;

import org.springframework.web.multipart.MultipartFile;

public class FileUpload {
	
	public static String uploadFile(MultipartFile ufile ) {
		
		String downloadPath=System.getProperty("user.home")+File.separator+"Downloads";
		String folderName="uploadPhoto";
		String uploadPath= downloadPath+File.separator+folderName;
		
		//create directory if does not exist
		File dir=new File(uploadPath);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		//Define file path
		String filePath=uploadPath+File.separator+ufile.getOriginalFilename();
		File newFile=new File(filePath);
		
		//write the file
		try (FileOutputStream fos=new FileOutputStream(newFile)) {
			fos.write(ufile.getBytes());
			return "File upload successfully to :"+filePath;
		} catch (Exception e) {
			e.printStackTrace();
			return "File upload failed : "+e.getMessage();
		}
	}

}
