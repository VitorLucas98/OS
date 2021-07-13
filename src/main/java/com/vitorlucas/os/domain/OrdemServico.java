package com.vitorlucas.os.domain;

import java.io.Serializable;
import java.time.LocalDate;

import com.vitorlucas.os.domain.enums.Prioridade;
import com.vitorlucas.os.domain.enums.Status;

public class OrdemServico implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private LocalDate dataAbertura;
	private LocalDate dataFechamento;
	private Integer prioridade;
	private String observação;
	private Integer status;
	private Tecnico tecnico;
	private Cliente cliente;
	
	
	public OrdemServico() {
		dataAbertura = LocalDate.now();
		prioridade = Prioridade.BAIXA.getCod();
		status = Status.ABERTO.getCod();
	}

	public OrdemServico(Long id, LocalDate dataFechamento, Prioridade prioridade,
			String observação, Status status, Tecnico tecnico, Cliente cliente) {
		super();
		this.id = id;
		dataAbertura = LocalDate.now();
		this.dataFechamento = dataFechamento;
		this.prioridade = (prioridade == null) ? 0 : prioridade.getCod();
		this.observação = observação;
		this.status = (status == null) ? 0 : status.getCod();
		this.tecnico = tecnico;
		this.cliente = cliente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(LocalDate dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public LocalDate getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(LocalDate dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public Prioridade getPrioridade() {
		return Prioridade.toEnum(prioridade);
	}

	public void setPrioridade(Prioridade prioridade) {
		this.prioridade = prioridade.getCod();
	}

	public String getObservação() {
		return observação;
	}

	public void setObservação(String observação) {
		this.observação = observação;
	}

	public Status getStatus() {
		return Status.toEnum(status);
	}

	public void setStatus(Status status) {
		this.status = status.getCod();
	}

	public Tecnico getTecnico() {
		return tecnico;
	}

	public void setTecnico(Tecnico tecnico) {
		this.tecnico = tecnico;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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
		OrdemServico other = (OrdemServico) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
