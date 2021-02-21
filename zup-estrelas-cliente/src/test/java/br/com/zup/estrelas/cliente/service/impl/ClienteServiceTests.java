package br.com.zup.estrelas.cliente.service.impl;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;

import br.com.zup.estrelas.cliente.dto.ClienteDTO;
import br.com.zup.estrelas.cliente.dto.MensagemDTO;
import br.com.zup.estrelas.cliente.entity.ClienteEntity;
import br.com.zup.estrelas.cliente.repository.ClienteRepository;

@RunWith(MockitoJUnitRunner.class)
public class ClienteServiceTests {

	private static final String CLIENTE_CADASTRADO_COM_SUCESSO = "Cliente cadastrado com sucesso!";
	private static final String CLIENTE_INEXISTENTE = "Cliente inexistente!";
	private static final String CLIENTE_REMOVIDO_COM_SUCESSO = "Cliente removido com sucesso!";
	private static final String CLIENTE_JA_EXISTENTE = "Cliente já existente!";
	private static final String CLIENTE_ALTERADO_COM_SUCESSO = "Cliente alterado com sucesso!";

	@Mock
	ClienteRepository clienteRepository;

	@InjectMocks
	ClienteServiceImpl clienteService;

	private ClienteDTO implementarCliente() {
		ClienteDTO cliente = new ClienteDTO();
		cliente.setCpf("34234234244");
		cliente.setNome("Fernando");
		cliente.setIdade(10);
		cliente.setEmail("Fernando2020@gmail.com.br");
		cliente.setEndereco("Rua Moreira Pires");
		cliente.setTelefone("22223333");

		return cliente;
	}

	@Test
	public void deveAdicionarClienteComSucesso() {
		ClienteDTO cliente = implementarCliente();

		Mockito.when(clienteRepository.existsByCpf(cliente.getCpf())).thenReturn(false);

		MensagemDTO mensagemRetornada = this.clienteService.adicionarCliente(cliente);
		MensagemDTO mensagemEsperada = new MensagemDTO(CLIENTE_CADASTRADO_COM_SUCESSO);

		Assert.assertEquals("Deve criar uma cliente com sucesso", mensagemEsperada, mensagemRetornada);
	}

	@Test
	public void naoDeveAdicionarClienteJaExistente() {
		ClienteDTO cliente = implementarCliente();

		Mockito.when(clienteRepository.existsByCpf(cliente.getCpf())).thenReturn(true);

		MensagemDTO mensagemRetornada = this.clienteService.adicionarCliente(cliente);
		MensagemDTO mensagemEsperada = new MensagemDTO(CLIENTE_JA_EXISTENTE);

		Assert.assertEquals("Não deve adicionar um cliente já existente", mensagemEsperada, mensagemRetornada);
	}

	@Test
	public void deveAlterarUmClienteComSucesso() {
		ClienteDTO clienteDTO = implementarCliente();
		ClienteEntity cliente = new ClienteEntity();
		cliente.setCpf("321312314");
		BeanUtils.copyProperties(clienteDTO, cliente);

		Optional<ClienteEntity> clienteBD = Optional.of(cliente);

		Mockito.when(clienteRepository.findByCpf("34234234244")).thenReturn(clienteBD);

		ClienteDTO clienteAlterado = implementarCliente();
		clienteAlterado.setIdade(13);
		clienteAlterado.setTelefone("22223333");

		MensagemDTO mensagemRetornada = this.clienteService.atualizarCliente(1L, clienteAlterado);
		MensagemDTO mensagemEsperada = new MensagemDTO(CLIENTE_ALTERADO_COM_SUCESSO);

		Assert.assertEquals("Deve alterar um cliente com sucesso", mensagemEsperada, mensagemRetornada);
	}

	@Test
	public void naoDeveAlterarUmClienteInexistente() {
		ClienteDTO clienteDTO = implementarCliente();

		Optional<ClienteEntity> clienteBD = Optional.empty();

		Mockito.when(clienteRepository.findByCpf("321312314")).thenReturn(clienteBD);

		MensagemDTO mensagemRetornada = this.clienteService.atualizarCliente(1L, clienteDTO);
		MensagemDTO mensagemEsperada = new MensagemDTO(CLIENTE_INEXISTENTE);

		Assert.assertEquals("Não deve alterar um cliente inexistente", mensagemEsperada, mensagemRetornada);
	}

	@Test
	public void deveRemoverClienteComSucesso() {

		Mockito.when(clienteRepository.existsByCpf("321312314")).thenReturn(false);

		MensagemDTO mensagemRetornada = this.clienteService.removerCliente(1L);
		MensagemDTO mensagemEsperada = new MensagemDTO(CLIENTE_REMOVIDO_COM_SUCESSO);

		Assert.assertEquals("Deve remover um cliente com sucesso", mensagemEsperada, mensagemRetornada);
	}

	@Test
	public void naoDeveRemoverClienteInexistente() {

		Mockito.when(clienteRepository.existsByCpf("3423432423")).thenReturn(true);

		MensagemDTO mensagemRetornada = this.clienteService.removerCliente(1L);
		MensagemDTO mensagemEsperada = new MensagemDTO(CLIENTE_INEXISTENTE);

		Assert.assertEquals("Não deve remover um cliente inexistente", mensagemEsperada, mensagemRetornada);
	}
}
