package itens;
import java.util.*;
import java.io.*;

public class Venda implements Serializable
{
	private GregorianCalendar dataDaVenda;
	private String codigo; 	//identifica o produto
	private String id;  		//identifica o cliente
	private int quantidade; 	//quantidade do produto vendido

	public Venda(GregorianCalendar data, String codigoProduto, String idCliente, int quantidade)
	{
		this.dataDaVenda = data;
		this.codigo = codigoProduto;
		this.id = idCliente;
		this.quantidade = quantidade;
	}

	public String toString()
	{
		String s = " Data: " + this.dataDaVenda.getTime().toString() +
				   "\n Cliente: " + this.id + 
				   "\n Produto: " + this.codigo ;
		return s;
	}
}