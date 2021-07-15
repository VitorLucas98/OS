package com.vitorlucas.os.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vitorlucas.os.dto.OrdemServicoDTO;
import com.vitorlucas.os.services.OrdemServicoService;

@RestController
@RequestMapping(value = "/os")
public class OrdemServicoResource {
	
	@Autowired
	private OrdemServicoService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<OrdemServicoDTO> findById(@PathVariable Long id){
		OrdemServicoDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping
	public ResponseEntity<Page<OrdemServicoDTO>> findAllPaged(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "5") Integer size, 
			@RequestParam(value = "direction", defaultValue = "ASC") String  direction,
			@RequestParam(value = "orderBy", defaultValue = "dataAbertura") String orderBy){
		
		PageRequest request = PageRequest.of(page, size, Direction.valueOf(direction), orderBy);
		Page<OrdemServicoDTO> pag = service.findAllPaged(request);
		
		return ResponseEntity.ok(pag);
	}
	
	@PostMapping
	public ResponseEntity<OrdemServicoDTO> insert(@RequestBody @Valid OrdemServicoDTO dto){
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<OrdemServicoDTO> update(@PathVariable Long id, @RequestBody @Valid OrdemServicoDTO dto){
		dto = service.update(id, dto);
		return ResponseEntity.ok(dto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
