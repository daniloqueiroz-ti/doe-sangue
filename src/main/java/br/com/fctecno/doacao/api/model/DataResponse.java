package br.com.fctecno.doacao.api.model;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Tuple;

import br.com.fctecno.doacao.domain.model.Response;

public class DataResponse {
	
	public String item;
	public List<Response> informacoes;
	
	public DataResponse(String item, List<Response> informacoes) {
		super();
		this.item = item;
		this.informacoes = informacoes;
	}
	
	public DataResponse(List<Tuple> informacoes, String item) {
		this.item = item;
		this.informacoes = this.toCollectionResult(informacoes);
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public List<Response> getInformacoes() {
		return informacoes;
	}
	public void setInformacoes(List<Response> informacoes) {
		this.informacoes = informacoes;
	}
	
	private Response toModel(Tuple item) {
		return new Response(item.get("informacao").toString(), item.get("valor").toString());
	}
	
	private List<Response> toCollectionResult(List<Tuple> lista) {
		return lista.stream()
				.map(doador -> toModel(doador))
				.collect(Collectors.toList());
		
	}
	
	
	
}
