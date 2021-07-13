package com.vitorlucas.os.domain;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Pessoa{
	private static final long serialVersionUID = 1L;

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
