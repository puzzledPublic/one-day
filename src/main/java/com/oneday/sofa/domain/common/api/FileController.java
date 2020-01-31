package com.oneday.sofa.domain.common.api;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.oneday.sofa.domain.common.FileInfo;
import com.oneday.sofa.domain.common.validation.UploadFile;
import com.oneday.sofa.global.util.FileHelper;

@RestController
@RequestMapping("/file")
public class FileController {
	
	@PostMapping("/upload")
	public List<FileInfo> uploadFile(List<@UploadFile MultipartFile> files) {
		return FileHelper.saveMultipartFiles(files);
	}
}
