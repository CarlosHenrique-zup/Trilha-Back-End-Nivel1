package br.com.zup.estrelas.cliente.mvc.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.zup.estrelas.cliente.mvc.models.Cliente;
import br.com.zup.estrelas.cliente.mvc.repository.ClienteRepository;

@Controller
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;

	@RequestMapping(value = "/cadastrarCliente", method = RequestMethod.GET)
	public String form() {
		return "cliente/formCliente";
	}

	@RequestMapping(value = "/cadastrarCliente", method = RequestMethod.POST)
	public String form(@Valid Cliente cliente, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/cadastrarCliente";
		}

		clienteRepository.save(cliente);
		attributes.addFlashAttribute("mensagem", "Cliente cadastrado com sucesso!");

		return "redirect:/cadastrarCliente";
	}

	@RequestMapping(value = "/clientes")
	public ModelAndView listaClientes() {
		ModelAndView mv = new ModelAndView("index");
		Iterable<Cliente> clientes = clienteRepository.findAll();
		mv.addObject("clientes", clientes);
		return mv;
	}

	@RequestMapping(value = "/{codigo}", method = RequestMethod.GET)
	public ModelAndView detalhesCliente(@PathVariable("codigo") long codigo) {
		Cliente cliente = clienteRepository.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("cliente/detalhesCliente");
		mv.addObject("cliente", cliente);
		return mv;
	}

	@RequestMapping(value = "/editarCliente", method = RequestMethod.GET)
	public ModelAndView editarCliente(long codigo) {
		Cliente cliente = clienteRepository.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("cliente/atualizaCliente");
		mv.addObject("cliente", cliente);
		return mv;
	}

	@RequestMapping(value = "/editarCliente", method= RequestMethod.POST)
	public String editarClientePost(@Valid Cliente cliente, BindingResult result, RedirectAttributes attributes) {
		clienteRepository.save(cliente);
		attributes.addFlashAttribute("sucess", "Cliente alteado com sucesso!");
		return "redirect:/";
	}

	@RequestMapping("/deletarCliente")
	public String deletarCliente(long codigo) {
		Cliente cliente = clienteRepository.findByCodigo(codigo);
		clienteRepository.delete(cliente);
		return "redirect:/";
	}
}
