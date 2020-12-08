package br.com.fctecno.doacao.domain.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class Doador {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(max = 255)
	private String nome;	
	
	@Size(max = 14)
	private String cpf;
	
	@Size(max = 25)
	private String rg;
	
	@Column(name = "data_nasc", nullable = false, length = 10)
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dataNasc;
	
	@Size(max = 25)
	private String sexo;
	
	@Size(max = 255)
	private String mae;
	
	@Size(max = 255)
	private String pai;
	
	@Size(max = 100)
	@NotBlank(message = "Email é obrigatório")
	@Email
	private String email;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Endereco endereco;
	
	@Column(name = "telefone_fixo")
	@Size(max = 14)
	private String telefoneFixo;
	
	@Size(max = 15)
	private String celular;
	
	private BigDecimal altura;
	
	private BigDecimal peso;
	
	@NonNull
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_sanguineo")
	private TipoSanguineo tipoSanguineo;
	
	@JsonProperty(access = Access.READ_ONLY)
	private BigDecimal imc;
	
	@JsonProperty(access = Access.READ_ONLY)
	private boolean apto;
	
	@JsonProperty(access = Access.READ_ONLY)
	private Integer idade;
	

	public Integer getIdade() {
		return idade;
	}

	public void setIdade() {
		this.idade = Doador.calculaIdade(this.getDataNasc());
	}

	public boolean isApto() {
		return this.apto;
	}

	public void setApto() {
		if ((this.getIdade() < 16 || this.getIdade() > 69) && (this.getPeso().compareTo(new BigDecimal(50.0)) == 1))
			this.apto = false;		
		else
			this.apto = true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public Date getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getMae() {
		return mae;
	}

	public void setMae(String mae) {
		this.mae = mae;
	}

	public String getPai() {
		return pai;
	}

	public void setPai(String pai) {
		this.pai = pai;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getTelefoneFixo() {
		return telefoneFixo;
	}

	public void setTelefoneFixo(String telefoneFixo) {
		this.telefoneFixo = telefoneFixo;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public BigDecimal getAltura() {
		return altura;
	}

	public void setAltura(BigDecimal altura) {
		this.altura = altura;
	}

	public BigDecimal getPeso() {
		return peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	public TipoSanguineo getTipoSanguineo() {
		return tipoSanguineo;
	}

	public void setTipoSanguineo(TipoSanguineo tipoSanguineo) {
		this.tipoSanguineo = tipoSanguineo;
	}

	public BigDecimal getImc() {
		return imc;
	}

	public void setImc() {
		BigDecimal altura  = this.getAltura().multiply(this.getAltura());
		System.out.println(altura);
		this.imc = this.getPeso().divide(altura, 2, RoundingMode.HALF_UP).setScale(2, RoundingMode.HALF_EVEN);  
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Doador other = (Doador) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	private static int calculaIdade(Date dataNasc){
		Calendar dateOfBirth = new GregorianCalendar();

		dateOfBirth.setTime(dataNasc);

		Calendar today = Calendar.getInstance();

		int age = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);

		dateOfBirth.add(Calendar.YEAR, age);

		if (today.before(dateOfBirth)) {
			age--;
		}

		return age;
	}

	
	public void setData() {
		this.setIdade();
		this.setApto();
		this.setImc();
	}

	
}
