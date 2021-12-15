package com.gamgyule.gavve.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gamgyule.gavve.dto.BannerDTO;
import com.gamgyule.gavve.service.BannerService;
import com.gamgyule.gavve.service.UserService;

@RestController
@RequestMapping("/api")
public class ApiController {

	@Autowired
	ServletContext servletContext;

	@Autowired
	UserService userService;
	
	@Autowired
	BannerService bannerService;

	@PostMapping(value = "/banner-image", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public String BannerImageUpload(@RequestParam("file") MultipartFile file, HttpServletRequest req) {
		if (file.isEmpty())
			return "파일이 존재하지 않습니다.";

		OutputStream out = null;
		PrintWriter printWriter = null;

		String resultJson = "";
		try {

			String originFileName = file.getOriginalFilename();
			String fileName = System.currentTimeMillis() + "_" + originFileName;
			System.out.println("파일업로드 : "+fileName);
			String uploadPath = "D:/Web/Gavve/Gavve/src/main/webapp/upload/temp/";
			String path = "webapp/upload/temp/";
			byte[] bytes = file.getBytes();

			File _file = new File(uploadPath + fileName);

			resultJson = "{" + "\"name\"" + ":\"" + _file.getName() + "\"," + "\"path\"" + ":\"/" + path
					+ _file.getName() + "\"}";

			out = new FileOutputStream(_file);
			out.write(bytes);
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

	@GetMapping("/banner-list")
	public List<BannerDTO> GetBannerList() {
		List<BannerDTO> bannerlist = bannerService.getBannerList();
		return bannerlist;
	}
	
	@DeleteMapping("/banner-image-temp/{fileName}")
	public String DeleteBannerImageTemp(@PathVariable String fileName) {
		String realPath = servletContext.getRealPath("upload/temp/");
		DeleteFile(realPath+fileName);
		return fileName;
	}
	
	private boolean DeleteFile(String path) {
		File file = new File(path);
		if(file.exists()) {
			System.out.println("파일 확인 : "+path);
			if(file.isDirectory()) {
				System.out.println("파일이 폴더입니다. 삭제 취소 : "+path);
				return false;
			}
			if(file.delete()) {
				System.out.println("파일 삭제 성공 : "+path);
				return true;
			}else {
				System.out.println("파일 삭제 실패 : "+path);
				return false;
			}
		}else {
			System.out.println("파일 없음 : "+path);
			return false;
		}
	}

}
