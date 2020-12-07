package br.com.fctecno.doacao.api.controller;

import org.springframework.http.HttpStatus;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fctecno.doacao.domain.model.Doador;
import br.com.fctecno.doacao.domain.repository.DoacaoRepository;

@RestController
@RequestMapping("/doacoes")
public class DoacaoController {

	@Autowired
	private DoacaoRepository repo;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Doador adicionar(@Valid @RequestBody Doador doacao) {
		return repo.save(doacao);
	}
}
