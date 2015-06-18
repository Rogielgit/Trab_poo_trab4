import mercado.*;
import itens.*;
import java.util.*;
import java.io.*;
import java.net.*;

public class SistemaCliente
{
	private Socket cliente;
	private Usuario usuario;

	//construtor para novo usuario
	private SistemaCliente(String nome, String endereco, String telefone, String email, String id, String senha)
	{
		this.usuario = new Usuario(nome, endereco, telefone, email, id, senha);
	}

	//construtor para usuario ja existente
	private SistemaCliente(String id, String senha)
	{

	}

	public void conectarAoServidor(String ip, int porta) throws Exception
	{
		this.cliente = new Socket(ip, porta);
	}
	

	public static void main(String[] args)
	{
			
	}
}