package student.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import student.dao.StudentDAO;

public class StudentDeleteController implements Controller {
	
	StudentDAO studentDAO;

	public void setStudentDAO(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String id = req.getParameter("id");
		studentDAO.deleteStudent(id);
		return new ModelAndView("redirect:student_list.do");
	}

}
