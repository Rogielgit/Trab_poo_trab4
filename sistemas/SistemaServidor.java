package sistemas;

import mercado.*;
import itens.*;
import java.util.*;
import java.io.*;
import java.net.*;

public class SistemaServidor
{
	public LinkedList<Socket> clientesConectados; //lista de clientes conectados
	private ServerSocket servidor;
	private Supermercado mercado;	

	public SistemaServidor(int porta) throws Exception //construtor
	{
		this.mercado = new Supermercado();
		this.clientesConectados = new LinkedList<Socket>();
		this.servidor = new ServerSocket(porta);
	}

	public void conectarComClientes() throws Exception //estabelece conexão com um cliente
	{
		while(true)
		{
			System.out.println("Aguardando conexao...");
			Socket socket = this.servidor.accept(); //aguarda conexão
			new Thread( () -> 
			{
				try
				{
					//this.clientesConectados.add(socket);  //add cliente na lista ===== ?
					this.comunicacarComCliente(socket);
				}
				catch(Exception e)
				{
					System.out.println(e.getMessage());
					this.listarUsuarios();
				}	
			}).start();
		}
	}

	public void comunicacarComCliente(Socket socket) throws Exception
	{
		ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());
		Boolean flagNovoUsuario = (Boolean)inStream.readObject();
		System.out.println("lido: " + flagNovoUsuario.toString());
		
		//Login/senha/novo cadastro:
		if(flagNovoUsuario.booleanValue()) //se usuario nao esta cadastrado ainda
		{
			Usuario novoUsuario = (Usuario)inStream.readObject();
			System.out.println("Usuarios:");
			this.mercado.cadastraUsuario(novoUsuario);
			this.listarUsuarios();
		}
		else
		{
			String id = (String)inStream.readObject();
			String senha = (String)inStream.readObject();
			try
			{
				Usuario usuario = this.mercado.buscaUsuario(id, senha);
				ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
				outStream.writeObject(usuario);
				System.out.println("user ja existente logado!!!");
			}
			catch (Exception e)
			{
				ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
				System.out.println("login invalido!!");
				outStream.writeObject(null);
			}
		}
	}

	public void cadastrarNovoUsuario(Usuario user) throws Exception
	{
		this.mercado.cadastraUsuario(user);
	}

	public Usuario buscarUsuario(String id, String senha) throws Exception
	{
		return this.mercado.buscaUsuario(id, senha);
	}

	public void listarUsuarios()
	{
		for(Usuario u :  this.mercado.getListaDeUsuarios())
		{
			u.print();
		}
		System.out.println("(tam: " + this.mercado.getListaDeUsuarios().size() + ")");
	}

}

/*if(flagNovoUsuario.booleanValue())
		{
			Usuario novoUsuario = (Usuario)inStream.readObject();
			this.cadastrarNovoUsuario(novoUsuario);
			System.out.println("Novo usuario!");
			novoUsuario.print();
		}
		else
		{
			System.out.println("Caiu no else");
			
			String id = (String)inStream.readObject(); //se der algum erro de exceção no cliente, deve ser aqui o problema ===============
			String senha = (String)inStream.readObject();
			try
			{
				Usuario user = this.buscarUsuario(id, senha);
				outStream.writeObject(user);
				System.out.println("Usuario ja cadastrado");
				
			}
			catch (Exception e)
			{
				Usuario user = null;
				outStream.writeObject(user);
			}
		}*/
