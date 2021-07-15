package com.vitorlucas.os.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.vitorlucas.os.domain.Pessoa;
import com.vitorlucas.os.dto.TecnicoDTO;
import com.vitorlucas.os.repositories.PessoaRepository;
import com.vitorlucas.os.resources.exceptions.FieldMessage;


public class TecnicoValidator implements ConstraintValidator<TecnicoValid, TecnicoDTO>{
	@Autowired
	private PessoaRepository repository;
	
	@Override
	public void initialize(TecnicoValid ann) {
	}

	@Override
	public boolean isValid(TecnicoDTO dto, ConstraintValidatorContext context) {

		
		List<FieldMessage> lista = new ArrayList<>();
		
		Pessoa pessoa = repository.findByCpf(dto.getCpf());
		
		if (pessoa != null) {
			lista.add(new FieldMessage("cpf", "cpf já cadastrado"));			
		}
		
		for (FieldMessage e : lista) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return lista.isEmpty();
	}
}
