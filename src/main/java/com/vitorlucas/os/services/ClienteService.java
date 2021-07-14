package com.vitorlucas.os.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.swing.text.MaskFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vitorlucas.os.domain.Cliente;
import com.vitorlucas.os.dto.ClienteDTO;
import com.vitorlucas.os.repositories.ClienteRepository;
import com.vitorlucas.os.services.exceptions.DatabaseException;
import com.vitorlucas.os.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;

	@Transactional(readOnly = true)
	public ClienteDTO findByCpf(String cpf) {
		cpf = formataCpf(cpf);
		Optional<Cliente> cli = repository.findByCpf(cpf);
		Cliente obj = cli.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado"));
		return new ClienteDTO(obj);
	}
	
	@Transactional(readOnly = true)
	public Page<ClienteDTO> findAllPaged(Pageable pageble){
		Page<Cliente> list = repository.findAll(pageble);
		return list.map(x -> new ClienteDTO(x));
	}
	
	@Transactional
	public ClienteDTO insert(ClienteDTO dto) {
		Cliente entity = new Cliente();
		entity.setCpf(formataCpf(dto.getCpf()));
		entity.setNome(dto.getNome());
		entity.setTelefone(dto.getTelefone());
		entity = repository.save(entity);
		return new ClienteDTO(entity);
	}
	
	@Transactional
	public ClienteDTO update(String cpf, ClienteDTO dto) {
		Optional<Cliente> cli = repository.findByCpf(cpf);
		Cliente entity = cli.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado"));
		entity.setNome(dto.getNome());
		entity.setTelefone(dto.getTelefone());
		entity = repository.save(entity);
		return new ClienteDTO(entity);
	}
	
	public void delete(String cpf) {
		try {
			Cliente entity = repository.findByCpf(cpf).get();
			repository.delete(entity);
		}catch (EntityNotFoundException e) {
			throw new ObjectNotFoundException("Cliente não encontrado");
		}catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Erro ao deletar cliente");
		}
	}
	
	private String formataCpf(String cpf) {
		if (!cpf.contains(".")) {
			try {
				MaskFormatter mask = new MaskFormatter("###.###.###-##");
				mask.setValueContainsLiteralCharacters(false);
				cpf = mask.valueToString(cpf);
			} catch (Exception e) {
				throw new RuntimeException();
			}
		}
		return cpf;
	}
}
