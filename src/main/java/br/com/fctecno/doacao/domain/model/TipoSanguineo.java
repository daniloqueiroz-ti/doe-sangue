package br.com.fctecno.doacao.domain.model;

import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoSanguineo {
	A_POSITIVO("A+"), 
	A_NEGATIVO("A-"), 
	B_POSITIVO("B+"), 
	B_NEGATIVO("B-"), 
	AB_POSITIVO("AB+"), 
	AB_NEGATIVO("AB-"), 
	O_POSITIVO("O+"), 
	O_NEGATIVO("O-");
	
	private String sangue;
	
	private TipoSanguineo(String tipoSanguineo) {
		this.sangue = tipoSanguineo;
	}
	
	@JsonCreator
	public static TipoSanguineo decode(final String tipo)
	{
		return Stream.of(TipoSanguineo.values()).filter(tarEnum -> tarEnum.sangue.equals(tipo)).findFirst().orElse(null);
	}
	
	@JsonValue
	public String getSangue()
	{
		return sangue;
	}
	
}
