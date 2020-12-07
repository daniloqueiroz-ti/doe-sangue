package br.com.fctecno.doacao.domain.model;

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
	
	public String getSangue()
	{
		return sangue;
	}
	
}
