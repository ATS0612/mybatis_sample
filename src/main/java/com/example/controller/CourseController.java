package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entity.Course;
import com.example.form.CourseForm;
import com.example.service.CourseService;

@Controller
@RequestMapping("/course")
public class CourseController {
	private final CourseService courseService;

	@Autowired
	public CourseController(CourseService courseService) {
		this.courseService = courseService;
	}

	//lesson02 メソッドの第2引数に@ModelAttribute追加
//	@GetMapping("/list")
//	public String index(Model model, @ModelAttribute CourseForm courseForm) {
//		List<Course> courses = this.courseService.findAll();
//		model.addAttribute("courses", courses);
//		return "index";
//	}

	// 新規登録
	@PostMapping("/create")
	public String create(@ModelAttribute CourseForm courseForm) {
		this.courseService.insert(courseForm.getName());
		return "redirect:/course/list";
	}

// lesson02	
	// 更新
	@GetMapping("/edit/{id}")
	public String showEdit(@PathVariable Integer id, Model model, @ModelAttribute CourseForm courseForm) {
		Course course = this.courseService.findById(id);
		courseForm.setName(course.getName());
		model.addAttribute("id", id);
		return "edit";
	}

	@PostMapping("/edit/{id}")
	public String edit(@PathVariable Integer id, @ModelAttribute CourseForm courseForm) {
		this.courseService.update(id, courseForm.getName());
		return "redirect:/course/list";
	}

	// 削除
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {
		this.courseService.deleteById(id);
		return "redirect:/course/list";
	}

	// 複数の引数で取得
	@GetMapping("/list")
	public String index(Model model,
			@ModelAttribute CourseForm courseForm,
			@RequestParam(value = "courseId", required = false) Integer courseId,
			@RequestParam(value = "courseName", required = false) String courseName) { // ここまでが引数
		List<Course> courses = this.courseService.findAll(courseId, courseName);
		model.addAttribute("courses", courses);
		return "index";
	}
}