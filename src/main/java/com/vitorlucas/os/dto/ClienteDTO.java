package com.vitorlucas.os.dto;

import java.io.Serializable;


import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.br.CPF;

import com.vitorlucas.os.domain.Cliente;
import com.vitorlucas.os.services.validation.ClienteValid;

@ClienteValid
public class ClienteDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotBlank(message = "Campo obrigatorio")
	private String nome;
	
	@CPF(message = "Informe um CPF valido")
	private String cpf;	
	
	@NotBlank(message = "Campo obrigatorio")
	private String telefone;

	
	public ClienteDTO() {
	}

	public ClienteDTO(String nome, String cpf, String telefone) {
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
	}
	
	public ClienteDTO(Cliente entity) {
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
