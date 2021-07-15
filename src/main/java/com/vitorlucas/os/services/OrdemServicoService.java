package com.vitorlucas.os.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vitorlucas.os.domain.Cliente;
import com.vitorlucas.os.domain.OrdemServico;
import com.vitorlucas.os.domain.Tecnico;
import com.vitorlucas.os.domain.enums.Prioridade;
import com.vitorlucas.os.domain.enums.Status;
import com.vitorlucas.os.dto.OrdemServicoDTO;
import com.vitorlucas.os.repositories.ClienteRepository;
import com.vitorlucas.os.repositories.OrdemServicoRepository;
import com.vitorlucas.os.repositories.TecnicoRepository;
import com.vitorlucas.os.services.exceptions.DatabaseException;
import com.vitorlucas.os.services.exceptions.ObjectNotFoundException;

@Service
public class OrdemServicoService {

	@Autowired
	private OrdemServicoRepository repository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private TecnicoRepository tecnicoRepository;

	@Transactional(readOnly = true)
	public OrdemServicoDTO findById(Long id) {
		Optional<OrdemServico> os = repository.findById(id);
		OrdemServico entity = os.orElseThrow(() -> new ObjectNotFoundException("OS não encontrada"));
		return new OrdemServicoDTO(entity);
	}

	@Transactional(readOnly = true)
	public Page<OrdemServicoDTO> findAllPaged(Pageable pageable){
		Page<OrdemServico> pags = repository.findAll(pageable);
		return pags.map(x -> new OrdemServicoDTO(x));
	}

	@Transactional
	public OrdemServicoDTO insert(OrdemServicoDTO dto) {
		OrdemServico entity = new OrdemServico();
		entity.setDataAbertura(LocalDateTime.now());
		copiarDtoParaEntity(entity, dto);
		return new OrdemServicoDTO(entity);
	}
	
	@Transactional
	public OrdemServicoDTO update(Long id, OrdemServicoDTO dto) {
		OrdemServico entity = repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("OS não encontrada"));
		copiarDtoParaEntity(entity, dto);
		return new OrdemServicoDTO(entity);
	}
	
	public void delete (Long id) {
		try {
			repository.deleteById(id);
		}catch (EmptyResultDataAccessException e) {
			throw new ObjectNotFoundException("OS não encontrada !");
		}catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Erro de violação de integridade");
		}
	}
	
	private void copiarDtoParaEntity(OrdemServico entity, OrdemServicoDTO dto) {
		
		entity.setDataFechamento(dto.getDataFechamento());
		entity.setObservacao(dto.getObservacao());
		entity.setPrioridade(Prioridade.toEnum(dto.getPrioridade()));
		entity.setStatus(Status.toEnum(dto.getStatus()));
		
		Cliente cli = clienteRepository.getOne(dto.getIdCliente());
		Tecnico tec = tecnicoRepository.getOne(dto.getIdTecnico());
	
		if (dto.getStatus().equals(2)) {
			entity.setDataFechamento(LocalDateTime.now());
		}
		
		entity.setCliente(cli);
		entity.setTecnico(tec);
		
		entity = repository.save(entity);
	}
}
