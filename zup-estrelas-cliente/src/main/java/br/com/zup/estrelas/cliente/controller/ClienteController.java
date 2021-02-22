package br.com.zup.estrelas.cliente.controller;

import java.util.List;
import org.springframework.http.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.estrelas.cliente.dto.ClienteDTO;
import br.com.zup.estrelas.cliente.dto.MensagemDTO;
import br.com.zup.estrelas.cliente.entity.ClienteEntity;
import br.com.zup.estrelas.cliente.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	ClienteService clienteService;
	
	@PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public MensagemDTO adicionarCliente(@RequestBody ClienteDTO clienteDTO) {
		return clienteService.adicionarCliente(clienteDTO);
	}

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<ClienteEntity> listarCliente() {
		return clienteService.listarCliente();
	}

	@PutMapping(path = "/{idCliente}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public MensagemDTO alterarCliente(@PathVariable Long idCliente, @RequestBody ClienteDTO clienteDTO) {
		return clienteService.atualizarCliente(idCliente, clienteDTO);
	}

	@DeleteMapping(path = "/{idCliente}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public MensagemDTO removerCliente(@PathVariable Long idCliente) {
		return clienteService.removerCliente(idCliente);
	
	}
}
