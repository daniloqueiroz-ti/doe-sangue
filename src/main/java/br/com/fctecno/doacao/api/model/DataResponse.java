package br.com.fctecno.doacao.api.model;

import java.util.List;

import br.com.fctecno.doacao.domain.model.Doador;

public class DataResponse {
	
	public String file;
	
	public List<Doador> doadores;

	public List<Doador> getDoadores() {
		return doadores;
	}

	public void setDoadores(List<Doador> doadores) {
		this.doadores = doadores;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
	
	
	
	
}
