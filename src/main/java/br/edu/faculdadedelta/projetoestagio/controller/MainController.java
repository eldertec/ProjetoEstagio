package br.edu.faculdadedelta.projetoestagio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "index.xhtml";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String logar() {
		return "/logout";
	}
}
