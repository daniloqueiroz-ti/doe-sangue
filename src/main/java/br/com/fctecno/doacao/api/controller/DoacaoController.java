package br.com.fctecno.doacao.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Tuple;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fctecno.doacao.api.model.DoacaoInput;
import br.com.fctecno.doacao.domain.model.Doador;
import br.com.fctecno.doacao.domain.model.Response;
import br.com.fctecno.doacao.domain.repository.DoadorRepository;
import br.com.fctecno.doacao.domain.service.DoacaoService;

@RestController
@RequestMapping("/api/doacoes")
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
	
	@RequestMapping(
		method = RequestMethod.GET
	)
	public List<List<Response>> resultados()
	{
		List<List<Response>> result = new ArrayList<>();
		
		
		List<Response> lista = repo.qtdeDoadorByEstado();
		List<Response> lista2  = repo.percentObesosBySexo();
		List<Response> lista3  = repo.avgIdadeByTipoSanguineo();
		List<Response> lista4 =  service.qtdePossiveisdoadores();
		List<Response> lista5  = toCollectionResult(repo.avgImcByFaixaIdade());
		result = List.of(lista, lista2, lista3, lista4, lista5);
		
		return result;
	}
	private Doador toModel(DoacaoInput doador) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		return modelMapper.map(doador, Doador.class);
	}
	
	private Response toModel(Tuple item) {
		return new Response(item.get("informacao").toString(), item.get("valor").toString());
	}
	
	private List<Doador> toCollectionModel(List<DoacaoInput> lista) {
		return lista.stream()
				.map(doador -> toModel(doador))
				.collect(Collectors.toList());
	}
	
	private List<Response> toCollectionResult(List<Tuple> lista) {
		return lista.stream()
				.map(doador -> toModel(doador))
				.collect(Collectors.toList());
		
	}
	
}
