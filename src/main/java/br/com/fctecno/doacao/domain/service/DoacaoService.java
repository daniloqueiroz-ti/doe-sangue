package br.com.fctecno.doacao.domain.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fctecno.doacao.domain.model.Doador;
import br.com.fctecno.doacao.domain.model.Response;
import br.com.fctecno.doacao.domain.model.TipoSanguineo;
import br.com.fctecno.doacao.domain.repository.DoadorRepository;
import br.com.fctecno.doacao.domain.util.Utils;

@Service
public class DoacaoService {
	
	@Autowired
	private DoadorRepository doadorRespository;
	
	public Doador salvar(Doador doador) {
		doador.setData();
		return doadorRespository.save(doador);
	}
	
	public List<Response> qtdePossiveisdoadores() {
		
		List<Response> response = new ArrayList<>();
		
		for (TipoSanguineo tipo : TipoSanguineo.values()) {
			List<TipoSanguineo> doadores = Utils.listaDoadores(tipo);
			Response data = new Response();
			data.setItem(tipo.toString());
			data.setValor(doadorRespository.qtdeDoadoresByTiposSanguineos(doadores).stream().map(x -> x.getValor()).reduce(new BigDecimal(0), BigDecimal::add));
			response.add(data);
		}
		
		return response;
		
	}
	
	
}
