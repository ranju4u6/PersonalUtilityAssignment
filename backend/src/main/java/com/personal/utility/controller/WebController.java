package com.personal.utility.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

	@RequestMapping("/**/{[path:[^\\.]*}")
	public String forward() {
		return "forward:/";
	}
}
