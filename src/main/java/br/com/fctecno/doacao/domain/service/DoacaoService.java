package br.com.fctecno.doacao.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fctecno.doacao.domain.model.Doador;
import br.com.fctecno.doacao.domain.repository.DoadorRepository;

@Service
public class DoacaoService {
	
	@Autowired
	private DoadorRepository doadorRespository;
	
	public Doador salvar(Doador doador) {
		doador.setData();
		return doadorRespository.save(doador);
		
	}
}
