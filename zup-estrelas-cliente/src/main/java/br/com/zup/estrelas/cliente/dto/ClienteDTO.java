package br.com.zup.estrelas.cliente.dto;

import javax.validation.constraints.NotNull;

public class ClienteDTO {
	
	@NotNull(message = "O campo não pode ser vazio.")
	private String nome;
	
	@NotNull(message = "O campo não pode ser vazio.")
	private Integer idade;
	
	@NotNull(message = "O campo não pode ser vazio.")
	private String cpf;
	
	@NotNull(message = "O campo não pode ser vazio.")
	private String email;
	
	@NotNull(message = "O campo não pode ser vazio.")
	private String telefone;
	
	@NotNull(message = "O campo não pode ser vazio.")
	private String endereco;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

}
