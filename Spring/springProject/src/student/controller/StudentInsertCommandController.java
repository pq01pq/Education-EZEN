package student.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

import student.dao.StudentDAO;
import student.dto.StudentDTO;

public class StudentInsertCommandController extends AbstractCommandController {
	
	private StudentDAO studentDAO;

	public void setStudentDAO(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}

	@Override
	protected ModelAndView handle(HttpServletRequest req, HttpServletResponse resp,
			Object dto, BindException e) throws Exception {
		StudentDTO student = (StudentDTO)dto;
		studentDAO.insertStudent(student);
		return new ModelAndView("redirect:student_list.do");
	}
	// redirect : request, response는 초기화됨
	// forward : request, response값 유지
}

/*
ModelAndView mv = new ModelAndView();
		mv.setViewName("경로");
		mv.addObject("파라미터명", 변수);
		ModelAndView mv = new ModelAndView("경로");
		ModelAndView mv = new ModelAndView("경로", "파라미터명", 변수)
*/