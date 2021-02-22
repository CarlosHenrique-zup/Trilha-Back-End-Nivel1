package br.com.zup.estrelas.cliente.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zup.estrelas.cliente.dto.ClienteDTO;
import br.com.zup.estrelas.cliente.dto.MensagemDTO;
import br.com.zup.estrelas.cliente.entity.ClienteEntity;
import br.com.zup.estrelas.cliente.repository.ClienteRepository;
import br.com.zup.estrelas.cliente.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

	private final Logger log = LoggerFactory.getLogger(ClienteServiceImpl.class);
	
	private static final String CLIENTE_ADICIONADO_COM_SUCESSO = "Cliente cadastrado com sucesso!";
	private static final String CLIENTE_INEXISTENTE = "Cliente inexistente!";
	private static final String CLIENTE_JA_EXISTENTE = "Cliente já existente!";
	private static final String CLIENTE_ALTERADO_COM_SUCESSO = "Cliente alterado com sucesso!";
	private static final String CLIENTE_REMOVIDO_COM_SUCESSO = "Cliente removido com sucesso!";

	@Autowired
	ClienteRepository clienteRepository;

	@Override
	public MensagemDTO adicionarCliente(ClienteDTO clienteDTO) {
		log.info("Entrando no metodo adicionar cliente na classe Service: " + clienteDTO);
		ClienteEntity cliente = new ClienteEntity();

		if (clienteRepository.existsByCpf(cliente.getCpf())) {
			return new MensagemDTO(CLIENTE_JA_EXISTENTE);
		}

		BeanUtils.copyProperties(clienteDTO, cliente);
		clienteRepository.save(cliente);

		return new MensagemDTO(CLIENTE_ADICIONADO_COM_SUCESSO);
	}

	@Override
	public List<ClienteEntity> listarCliente() {
		log.info("Entrando no metodo listar cliente na classe Service: ");
		return (List<ClienteEntity>) clienteRepository.findAll();
	}
	
	@Override
	public MensagemDTO atualizarCliente(Long idCliente, ClienteDTO clienteDTO) {
		log.info("Entrando no metodo alterar cliente na classe Service: " + clienteDTO);
		Optional<ClienteEntity> clienteConsultado = clienteRepository.findById(idCliente);

		if (clienteConsultado.isEmpty()) {
			return new MensagemDTO(CLIENTE_INEXISTENTE);
		}

		ClienteEntity clienteAlterado = clienteConsultado.get();
		BeanUtils.copyProperties(clienteDTO, clienteAlterado);
		clienteRepository.save(clienteAlterado);
		return new MensagemDTO(CLIENTE_ALTERADO_COM_SUCESSO);
	}

	@Override
	public MensagemDTO removerCliente(Long idCliente) {
		log.info("Entrando no metodo remover cliente na classe Service: " + idCliente);
		if(clienteRepository.existsById(idCliente)){
			clienteRepository.deleteById(idCliente);
			return new MensagemDTO(CLIENTE_REMOVIDO_COM_SUCESSO);
		}

		return new MensagemDTO(CLIENTE_INEXISTENTE);
	}
}
