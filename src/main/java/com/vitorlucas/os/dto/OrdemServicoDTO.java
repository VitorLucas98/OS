package com.vitorlucas.os.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import com.vitorlucas.os.domain.OrdemServico;

public class OrdemServicoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private LocalDateTime dataAbertura;
	private LocalDateTime dataFechamento;
	private Integer prioridade;
	
	@NotBlank(message = "Campo obrigatorio")
	private String observacao;
	
	private Integer status;
	private Long idTecnico;
	private Long idCliente;
	
	public OrdemServicoDTO() {
	}

	public OrdemServicoDTO(Long id, LocalDateTime dataAbertura, LocalDateTime dataFechamento, Integer prioridade,
			String observacao, Integer status, Long idTecnico, Long idCliente) {
		super();
		this.id = id;
		this.dataAbertura = dataAbertura;
		this.dataFechamento = dataFechamento;
		this.prioridade = prioridade;
		this.observacao = observacao;
		this.status = status;
		this.idTecnico = idTecnico;
		this.idCliente = idCliente;
	}
	
	public OrdemServicoDTO(OrdemServico entity) {
		id = entity.getId();
		dataAbertura = entity.getDataAbertura();
		dataFechamento = entity.getDataFechamento();
		prioridade = entity.getPrioridade().getCod();
		observacao = entity.getObservacao();
		status = entity.getStatus().getCod();
		idTecnico = entity.getTecnico().getId();
		idCliente = entity.getCliente().getId();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(LocalDateTime dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public LocalDateTime getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(LocalDateTime dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public Integer getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(Integer prioridade) {
		this.prioridade = prioridade;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getIdTecnico() {
		return idTecnico;
	}

	public void setIdTecnico(Long idTecnico) {
		this.idTecnico = idTecnico;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
}
