package br.com.fctecno.doacao.domain.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Response {
	private String informacao;
	private BigDecimal valor;
	
	public String getItem() {
		return informacao;
	}
	public void setItem(String informacao) {
		this.informacao = informacao;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	public Response() {
	}
	
	public Response(String informacao, long valor) {
		this.informacao = informacao;
		this.valor = new BigDecimal(valor).setScale(2, RoundingMode.HALF_EVEN);
	}
	
	public Response(TipoSanguineo informacao, Double valor) {
		this.informacao = informacao.getSangue();
		this.valor = new BigDecimal(valor).setScale(2, RoundingMode.HALF_EVEN);
	}
	
	public Response(TipoSanguineo informacao, long valor) {
		this.informacao = informacao.getSangue();
		this.valor = new BigDecimal(valor).setScale(2, RoundingMode.HALF_EVEN);
	}
	
	public Response(String informacao, String valor) {
		this.informacao = informacao;
		this.valor = new BigDecimal(valor).setScale(2, RoundingMode.HALF_EVEN);
	}
	
//	public Response(Object informacao, Object valor) {
//		this.informacao = informacao.toString();
//		this.valor = new BigDecimal(valor.toString()).setScale(2, RoundingMode.HALF_EVEN);
//	}
	
}
