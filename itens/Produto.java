package itens;
import java.util.*;
import java.io.*;

public class Produto
{
	private String nome;
	private String codigo;
	private double preco;
	private String fornecedor;
	private GregorianCalendar validade;
	private double quantidade;

	public Produto(String nome, String codigo, double preco, String fornec, GregorianCalendar val, double quant)
	{
		this.nome = nome;
		this.preco = preco;
		this.fornecedor = fornec;
		this.validade = val;
		this.quantidade = quant;
		this.codigo = codigo.replace(" ", "");;
	}

	//Sets:
	public void setNome(String value)
	{
		this.nome = value;
	}
	public void setCodigo(String value)
	{
		this.codigo = value;
	}
	public void setPreco(double value)
	{
		this.preco = value;
	}
	public void setFornecedor(String value)
	{
		this.fornecedor = value;
	}
	public void setValidade(GregorianCalendar value)
	{
		this.validade = value;
	}
	public void setQuant(double value)
	{
		this.quantidade = value;
	}

	//Gets:
	public String getNome()
	{
		return this.nome;
	}
	public double getPreco()
	{
		return this.preco;
	}
	public String getFornecedor()
	{
		return this.fornecedor;
	}
	public GregorianCalendar getValidade()
	{
		return this.validade;
	}
	public double getQuant()
	{
		return this.quantidade;
	}
	public String getCodigo()
	{
		return this.codigo;
	}

	public void print()
	{
		System.out.println("\nNome: " + this.getNome() + 
						   "\nPreco: " + this.getPreco() +
						   "\nFornecedor: "+ this.getFornecedor() + 
						   "\nValidade: " + this.getValidade().getTime().toString() +
						   "\nQuantidade: " + this.getQuant() + "\n");

	}

}
