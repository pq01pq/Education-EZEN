package student.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import student.dao.StudentDAO;
import student.dto.StudentDTO;
import student.mybatis.StudentMapper;

@Controller
public class StudentController {
	
//	@Autowired
//	private StudentDAO studentDAO;
	
	// 리턴타입 : ModelAndView, Model, Map, String, String, View객체, void
	
	@RequestMapping("student_index.do")
	public String index() {
		return "student/student";
	}
	
	@RequestMapping("student_input.do")
	public String input() {
		return "student/input";
	}
	
	@RequestMapping("student_insert.do")
	public String insert(@ModelAttribute StudentDTO student, BindingResult result) {
//		if(result.hasErrors()) {
//			student.setId("쏼라쏼라");
//		}
		int res = StudentMapper.insertStudent(student);
		return "redirect:student_list.do";
	}
	
	@RequestMapping("student_list.do")
	public String list(HttpServletRequest req) {
		List<StudentDTO> students = StudentMapper.listStudent();
		req.setAttribute("students", students);
		return "student/list";
	}
	
	@RequestMapping("student_delete_input.do")
	public String deleteInput() {
		return "student/delete";
	}
	
	@RequestMapping("student_delete.do")
	public String delete(@RequestParam String id) {
		StudentMapper.deleteStudent(id);
		return "redirect:student_list.do";
	}
	
	@RequestMapping("student_find.do")
	public String find(HttpServletRequest req, @RequestParam String key) {
		key = "%" + key + "%";
		List<StudentDTO> students = StudentMapper.findStudent(key);
		req.setAttribute("students", students);
		req.setAttribute("key", key);
		return "student/list";
	}
	
}
