package com.vitorlucas.os.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vitorlucas.os.domain.Cliente;

@Repository
public interface TecnicoRepository extends JpaRepository<Cliente, Long>{

}
