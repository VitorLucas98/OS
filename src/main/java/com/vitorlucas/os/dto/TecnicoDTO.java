package com.vitorlucas.os.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.br.CPF;

import com.vitorlucas.os.domain.Tecnico;
import com.vitorlucas.os.services.validation.TecnicoValid;

@TecnicoValid
public class TecnicoDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotBlank(message = "Campo obrigatorio")
	private String nome;
	
	@CPF(message = "Informe um CPF valido")
	private String cpf;	
	
	@NotBlank(message = "Campo obrigatorio")
	private String telefone;

	
	public TecnicoDTO() {
	}

	public TecnicoDTO(String nome, String cpf, String telefone) {
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
	}
	
	public TecnicoDTO(Tecnico entity) {
		id = entity.getId();
		nome = entity.getNome();
		cpf = entity.getCpf();
		telefone = entity.getTelefone();
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
}
