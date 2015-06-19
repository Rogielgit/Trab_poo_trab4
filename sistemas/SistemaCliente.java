package sistemas;

import mercado.*;
import itens.*;
import java.util.*;
import java.io.*;
import java.net.*;

public class SistemaCliente
{
	private static Socket cliente;
	private Usuario usuario;
	private ObjectInputStream inStream;
    private ObjectOutputStream outStream;

	public void conectarAoServidor(String ip, int porta) throws Exception
	{
		this.cliente = new Socket(ip, porta);
		System.out.println("Conectou!!!");
	}

	public void logar(String id, String senha) throws Exception
	{
		this.outStream = new ObjectOutputStream(this.cliente.getOutputStream());
		outStream.writeObject(id);
		outStream.writeObject(senha);
		this.inStream = new ObjectInputStream(this.cliente.getInputStream());
		Usuario u = (Usuario)inStream.readObject();
		this.usuario = u;
	}
	public void logarNovoUsuario(String nome, String endereco, String telefone, String email, String id, String senha) throws Exception
	{
		this.usuario = new Usuario(nome, endereco, telefone, email, id, senha);
		this.outStream = new ObjectOutputStream(this.cliente.getOutputStream());
		outStream.writeObject(new Boolean(true));
		outStream.writeObject(this.usuario);
	}
}




















