package br.edu.faculdadedelta.projetoestagio.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.annotation.SessionScope;

import br.edu.faculdadedelta.projetoestagio.domain.Instrutor;
import br.edu.faculdadedelta.projetoestagio.repositories.InstrutorRepository;
import br.edu.faculdadedelta.projetoestagio.util.FacesUtil;

@Controller
@SessionScope
@RequestMapping("/cadastroInstrutor.xhtml")
public class InstrutorController {

	@Autowired
	private InstrutorRepository repo;

	private Instrutor instrutor = new Instrutor();

	public Instrutor getInstrutor() {
		return instrutor;
	}

	public void setInstrutor(Instrutor instrutor) {
		this.instrutor = instrutor;
	}
	
	public String limpar() {
		instrutor = new Instrutor();
		return "cadastroInstrutor.xhtml";
	}
	

	public String salvar() {
		if (instrutor.getId() == null) {
			repo.save(instrutor);
			FacesUtil.exibirMsg("Instrutor cadastrado com sucesso!");
			limpar();
		} else {
			repo.save(instrutor);
			FacesUtil.exibirMsg("Instrutor atualizado com sucesso!");
			limpar();
		}
		return "cadastroInstrutor.xhtml";
	}

	public String atualizar() {
		return "cadastroInstrutor.xhtml";
	}

	public String remover() {
		repo.delete(instrutor);
		FacesUtil.exibirMsg("Instrutor removido com sucesso!");
		limpar();
		return "cadastroInstrutor.xhtml";
	}

	public List<Instrutor> getListar() {
		List<Instrutor> instrutores = new ArrayList<>();
		instrutores = repo.findAll();
		return instrutores;
	}
}
