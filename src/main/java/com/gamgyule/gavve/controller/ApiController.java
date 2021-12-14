package com.gamgyule.gavve.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gamgyule.gavve.service.UserService;

@RestController
@RequestMapping("/api")
public class ApiController {

	@Autowired
	ServletContext servletContext;

	@Autowired
	UserService userService;

	@RequestMapping("/get")
	public String Home() {
		return userService.getUserList().toString();
	}

	@PostMapping(value = "/banner-image", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public String BannerImageUpload(@RequestParam("file") MultipartFile file, HttpServletRequest req) {
		if (file.isEmpty())
			return "파일이 존재하지 않습니다.";

		OutputStream out = null;
		PrintWriter printWriter = null;

		System.out.println("파일업로드");
		String resultJson = "";
		try {

			String originFileName = file.getOriginalFilename();
			String fileName = System.currentTimeMillis()+"_"+originFileName;
			String uploadPath = "D:/Web/Gavve/Gavve/src/main/webapp/upload/temp/";
			String path = "webapp/upload/temp/";
			byte[] bytes = file.getBytes();

			File _file = new File(uploadPath + fileName);
			
			resultJson = "{"+"\"name\""+":\""+_file.getName()+"\","+"\"path\""+":\"/"+path+_file.getName()+"\"}";
			System.out.println(resultJson);
			

			out = new FileOutputStream(_file);
			out.write(bytes);
			System.out.println(System.currentTimeMillis());
			return resultJson;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (out != null)
					out.close();

				if (printWriter != null)
					printWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
		
	}

	@PostMapping("/banner-word")
	public String BannerWordUpload(MultipartFile file) {
		if (file.isEmpty())
			return "파일이 존재하지 않습니다.";

		return "";
	}

}
