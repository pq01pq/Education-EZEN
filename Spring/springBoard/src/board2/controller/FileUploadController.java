package board2.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class FileUploadController {
	@RequestMapping("/fileUpload_ok.board2")
	public void fileUpload(HttpServletRequest req) throws IOException {
		// 파일 받기
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest)req;
		MultipartFile mf = mr.getFile("filename");
		String filename = mf.getOriginalFilename();
		System.out.println("전송된 파일명 : " + filename);
		if(filename == null || filename.trim().equals("")) {
			System.out.println("선택된 파일이 없음");
			return;
		}
		
		HttpSession session = req.getSession();
		String saveDirectory = session.getServletContext().getRealPath("file");
		
		File file = new File(saveDirectory, filename);
		mf.transferTo(file);
	}
}
