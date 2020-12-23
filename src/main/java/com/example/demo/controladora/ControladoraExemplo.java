package com.example.demo.controladora;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ControladoraExemplo {

	@GetMapping("/exemplo")
	public @ResponseBody String exemplo() {
		return "Exemplo!";
	}
	
	@GetMapping("/admin/principal")
	public @ResponseBody String principal() {
		return "Esta página somente o administrador tem acesso liberado!";
	}
}
