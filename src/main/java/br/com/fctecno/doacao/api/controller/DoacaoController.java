package br.com.fctecno.doacao.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fctecno.doacao.api.model.DoacaoInput;
import br.com.fctecno.doacao.domain.model.Doador;
import br.com.fctecno.doacao.domain.repository.DoadorRepository;
import br.com.fctecno.doacao.domain.service.DoacaoService;

@RestController
@RequestMapping("/doacoes")
public class DoacaoController {

	@Autowired
	private DoadorRepository repo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private DoacaoService service;
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(
		method = RequestMethod.POST,
		value = "/all",
		produces = "application/json; charset=utf-8" 
	)
	public void adicionarTodos(@Valid @RequestBody List<DoacaoInput> doacoes) {
		for(Doador doador : toCollectionModel(doacoes)) {			
			service.salvar(doador);
		}

	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(
		method = RequestMethod.POST,
		produces = "application/json; charset=utf-8" 
	)
	public Doador adicionar(@Valid @RequestBody Doador doacao) {
		return repo.save(doacao);
	}
	
	private Doador toModel(DoacaoInput doador) {
		//modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		return modelMapper.map(doador, Doador.class);
	}
	
	private List<Doador> toCollectionModel(List<DoacaoInput> doadores) {
		return doadores.stream()
				.map(doador -> toModel(doador))
				.collect(Collectors.toList());
	}
	
}
