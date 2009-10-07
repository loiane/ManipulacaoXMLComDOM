package com.loiane.agenda;

/**
 * Classe que representa um elemento contato da agenda - XML
 * 
 * @author Loiane Groner
 *
 */
public class Contato {
	
	private int id;
	private String nome;
	private String endereco;
	private String telefone;
	private String email;	
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String toString(){
		StringBuffer st = new StringBuffer();
		st.append("Contato id: " + getId())
		.append("\n")
		.append("Nome: " + getNome())
		.append("\n")
		.append("Endereço: " + getEndereco())
		.append("\n")
		.append("Telefone: " + getTelefone())
		.append("\n")
		.append("Email: " + getEmail())
		.append("\n");
		return st.toString();
	}
	
}
