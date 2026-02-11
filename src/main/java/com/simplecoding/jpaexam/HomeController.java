/**
 * 
 */
package com.simplecoding.jpaexam;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author user
 * 메인화면: http://localhost:8080
 */
@Controller
public class HomeController {
	@GetMapping("/")
	public String home() {
		return "home";
	}
}
