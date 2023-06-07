package org.edupoll.controller;

import org.edupoll.repository.UserRepository;
import org.edupoll.service.AuthService;
import org.edupoll.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {
	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	AuthService authService;
	@Autowired
	UserService userService;

	@GetMapping("/auth")
	public String gotoLoginView(Model model) {

		return "auth";
	}

	@PostMapping("/auth-task")
	public String handleLogin(@RequestParam String id, @RequestParam String pass, HttpSession session) {
		boolean result = authService.isValidate(id, pass);
		if (result) {
			session.setAttribute("logonId", id);
			return "redirect:/todos";
		} else {
			return "auth";
		}

	}

	@GetMapping("/auth/user/join")
	public String join() {
		return "user/join";
	}
	
	@PostMapping("/auth/user/join-task")
	public String joinTask(String id, String password) {
		System.out.println("id="+id);
		System.out.println("password="+password);
		
		boolean rst = userService.joinService(id, password);
		if (rst) {

			return "redirect:/auth";
		} else {
			return "error";
		}
	}
}
