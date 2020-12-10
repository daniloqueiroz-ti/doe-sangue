package br.com.fctecno.doacao.domain.model;

import br.com.fctecno.doacao.api.model.DataResponse;

public class Receptores {
	private TipoSanguineo tipo;
	private DataResponse dataResponse;
	
	public TipoSanguineo getTipo() {
		return tipo;
	}
	public void setTipo(TipoSanguineo tipo) {
		this.tipo = tipo;
	}
	public DataResponse getDataResponse() {
		return dataResponse;
	}
	public void setDataResponse(DataResponse dataResponse) {
		this.dataResponse = dataResponse;
	}
	
	
}
