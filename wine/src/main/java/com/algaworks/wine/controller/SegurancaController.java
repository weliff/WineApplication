package com.algaworks.wine.controller;

import java.security.Principal;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
 
@Controller
public class SegurancaController {
	
	@RequestMapping("/login")
	public String login(@AuthenticationPrincipal Principal user) {
		if (user != null) {
			return "redirect:/vinhos/novo";
		}
		return "Login";
	}
}
