import mercado.*;
import itens.*;
import java.util.*;
import java.io.*;
import java.net.*;

public class SistemaServidor
{
	private LinkedList<Socket> clientesConectados; //lista de clientes conectados
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
		new Thread( () -> 
		{
			while(true)
			{
				try
				{
					Socket socket = this.servidor.accept(); //aguarda conexão
					this.clientesConectados.add(socket);  //add cliente na lista ===== ?
					this.comunicacarComCliente(socket);
				}
				catch(Exception e)
				{
					continue;
				}
				
			}
		});
	}

	public void comunicacarComCliente(Socket socket)
	{

	}

	public static void main(String[] args)
	{
		
	}

}
