package com.packtpub.restapp;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.packtpub.service.FileUploadService;

@RestController
@RequestMapping("/file")
public class FileController {
	
	@Autowired
	FileUploadService fileUploadSevice;

	@ResponseBody
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public Map<String, Object> uploadFile(@RequestParam("file") MultipartFile file) {
		Map<String, Object> map = new LinkedHashMap<>();
		try {
			fileUploadSevice.uploadFile(file);
			
			map.put("result", "file uploaded");
		} catch (IOException e) {
			map.put("result", "error while uploading : "+e.getMessage());
		}
		
		return map;
	}
}