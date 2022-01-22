package com.ezen.bigdata3;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezen.bigdata3.model.StudentDTO;
import com.ezen.bigdata3.service.StudentMapper;

@Controller
@RequestMapping("student")
public class StudentController {
	
	@Autowired
	private StudentMapper studentMapper;
	// property로 넣어주지 않아도 
	// @Service로 등록된 클래스를 @Controller에서 @Autowired를 할 수 있음
	
	// 리턴타입 : ModelAndView, Model, Map, String, String, View객체, void
	
	@RequestMapping("index")
	public String index() {
		return "student/student";
	}
	
	@RequestMapping("input.do")
	public String input() {
		return "student/input";
	}
	
	@RequestMapping("insert.do")
	public String insert(@ModelAttribute StudentDTO student, BindingResult result) {
//		if(result.hasErrors()) {
//			student.setId("쏼라쏼라");
//		}
		int res = studentMapper.insertStudent(student);
		return "redirect:student_list.do";
	}
	
	@RequestMapping("list.do")
	public String list(HttpServletRequest req) {
		List<StudentDTO> students = studentMapper.listStudent();
		req.setAttribute("students", students);
		return "student/list";
	}
	
	@RequestMapping("delete_input.do")
	public String deleteInput() {
		return "student/delete";
	}
	
	@RequestMapping("delete.do")
	public String delete(@RequestParam String id) {
		studentMapper.deleteStudent(id);
		return "redirect:student_list.do";
	}
	
	@RequestMapping("find.do")
	public String find(HttpServletRequest req, @RequestParam String key) {
		key = "%" + key + "%";
		List<StudentDTO> students = studentMapper.findStudent(key);
		req.setAttribute("students", students);
		req.setAttribute("key", key);
		return "student/list";
	}
	
}
