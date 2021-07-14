package com.vitorlucas.os.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity(name = "tb_tecnico")
@DiscriminatorValue(value = "TECNICO")
public class Tecnico extends Pessoa {
	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy = "tecnico")
	private List<OrdemServico> os = new ArrayList<>();
	
	public Tecnico() {
	}

	public Tecnico(Long id, String nome, String cpf, String telefone) {
		super(id, nome, cpf, telefone);
	}

	public List<OrdemServico> getOs() {
		return os;
	}
	
	
}
