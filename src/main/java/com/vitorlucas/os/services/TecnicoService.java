package com.vitorlucas.os.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vitorlucas.os.domain.Tecnico;
import com.vitorlucas.os.dto.TecnicoDTO;
import com.vitorlucas.os.repositories.TecnicoRepository;
import com.vitorlucas.os.services.exceptions.DatabaseException;
import com.vitorlucas.os.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {
	
	@Autowired
	private TecnicoRepository repository;

	@Transactional(readOnly = true)
	public TecnicoDTO findById(Long id) {
		Optional<Tecnico> cli = repository.findById(id);
		Tecnico obj = cli.orElseThrow(() -> new ObjectNotFoundException("Tecnico não encontrado"));
		return new TecnicoDTO(obj);
	}
	
	@Transactional(readOnly = true)
	public Page<TecnicoDTO> findAllPaged(Pageable pageble){
		Page<Tecnico> list = repository.findAll(pageble);
		return list.map(x -> new TecnicoDTO(x));
	}
	
	@Transactional
	public TecnicoDTO insert(TecnicoDTO dto) {
		Tecnico entity = new Tecnico();
		entity.setCpf(dto.getCpf());
		entity.setNome(dto.getNome());
		entity.setTelefone(dto.getTelefone());
		entity = repository.save(entity);
		return new TecnicoDTO(entity);
	}
	
	@Transactional
	public TecnicoDTO update(Long id, TecnicoDTO dto) {
		Tecnico entity =  repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Técnico não encontrado"));
		entity.setCpf(dto.getCpf());
		entity.setNome(dto.getNome());
		entity.setTelefone(dto.getTelefone());
		entity = repository.save(entity);
		return new TecnicoDTO(entity);
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}catch (EntityNotFoundException e) {
			throw new ObjectNotFoundException("Técnico não encontrado");
		}catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Erro ao deletar técnico");
		}
	}

}
