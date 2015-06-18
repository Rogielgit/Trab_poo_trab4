package itens;
import java.util.*;
import java.io.*;

public class Usuario
{
	private String nome;
	private String endereco;
	private String telefone;
	private String email;
	private String id;
	private String senha; 

	public Usuario(String nome, String endereco, String telefone, String email, String id, String senha)
	{
		this.nome = nome;
		this.endereco = endereco;
		this.telefone = telefone;
		this.email = email.replace(" ", ""); //elimina espacos em branco
		this.id = id.replace(" ", "");
		this.senha = senha;
	}

	public String getID()
	{
		return this.id;
	}


}