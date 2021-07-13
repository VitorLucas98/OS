package com.vitorlucas.os.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue(value = "CLI")
public class Cliente extends Pessoa{
	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy = "cliente")
	private List<OrdemServico> os = new ArrayList<>();
	
	public Cliente() {
	}

	public Cliente(Long id, String nome, String cpf, String telefone) {
		super(id, nome, cpf, telefone);
	}

	public List<OrdemServico> getOs() {
		return os;
	}
}
