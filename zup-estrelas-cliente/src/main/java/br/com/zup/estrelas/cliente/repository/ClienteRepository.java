package br.com.zup.estrelas.cliente.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.zup.estrelas.cliente.entity.ClienteEntity;

@Repository
public interface ClienteRepository extends CrudRepository<ClienteEntity, Long> {
		
	boolean existsByCpf(String cpf);
	
	boolean deleteByCpf(String cpf);
	
	Optional<ClienteEntity> findByCpf(String cpf);
}
