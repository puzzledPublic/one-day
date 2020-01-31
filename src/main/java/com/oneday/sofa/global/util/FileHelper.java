package com.oneday.sofa.global.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.oneday.sofa.domain.common.FileInfo;

public class FileHelper {
	
	public static FileInfo saveMultipartFile(MultipartFile multipartFile) {
		String originName = multipartFile.getOriginalFilename();
		String originExtension = originName.substring(originName.lastIndexOf("."));
		String newName = UUID.randomUUID().toString() + originExtension;
		
		try {
			File file = new File("C:\\Users\\KHM\\Pictures\\uploads\\" + newName);
			multipartFile.transferTo(file);
		}
		catch(IOException | IllegalStateException ex) {
			throw new RuntimeException("upload file save fail!!!", ex);
		}
		
		return new FileInfo(newName, originName);
	}
	
	public static List<FileInfo> saveMultipartFiles(List<MultipartFile> multipartFiles) {
		List<FileInfo> fileNames = new ArrayList<>();
		
		for(MultipartFile multipartFile : multipartFiles) {
			fileNames.add(saveMultipartFile(multipartFile));
		}
		
		return fileNames;
	}
}
