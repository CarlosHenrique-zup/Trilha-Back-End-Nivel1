package br.com.zup.estrelas.cliente.mvc.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.zup.estrelas.cliente.mvc.models.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {
	Cliente findByCodigo(long codigo);
}
