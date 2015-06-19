package mercado;

import itens.*;
import java.util.*;
import java.io.*;
import java.util.stream.*;

public class Supermercado
{
	private List<Produto> listaProdutos;
	private List<Usuario> listaUsuarios;
	private List<Venda> listaVendas;
	private GregorianCalendar dataAtual; 

	public Supermercado()
	{
		this.listaProdutos = new LinkedList<Produto>();
		this.listaUsuarios = new LinkedList<Usuario>();
		this.dataAtual = new GregorianCalendar();
		this.listaVendas = new LinkedList<Venda>();
	}

	public void cadastraProduto(Produto p) throws Exception
	{
		String codigo = p.getCodigo();
		if(this.listaProdutos
			.stream()
			.anyMatch(x -> x.getCodigo().equals(p.getCodigo()))
			)
		{
			throw new Exception("Codigo de produto ja existente!");
		}

		this.listaProdutos.add(p);
	}

	synchronized public void cadastraUsuario(Usuario u) throws Exception
	{
		String id = u.getID();
		if(this.listaUsuarios
			.stream()
			.anyMatch(x -> x.getID().equals(u.getID()))
			)
		{
			throw new Exception("ID de usuario ja existente!");
		}
		
		this.listaUsuarios.add(u);
	}

	public LinkedList<Produto> listarProdEsgotados()
	{
		LinkedList<Produto> esgotados = this.listaProdutos
		.stream()
        .filter(prod -> (prod.getQuant() == 0))
        .collect(Collectors.toCollection(LinkedList::new));
        
        return esgotados;
	}

	public LinkedList<Produto> listarProdEstoque()
	{
		LinkedList<Produto> estoque = this.listaProdutos
		.stream()
        .filter(prod -> (prod.getQuant() > 0))
        .collect(Collectors.toCollection(LinkedList::new));

        return estoque;
	}

	//Parametros invalidos : null e negativo (não sao alterados)
	public void atualizaProduto(Produto prod, String nome, String codigo, double preco, String fornec, GregorianCalendar val, double quant)
		throws Exception
	{	
		if(prod == null)
			throw new Exception("Produto inexistente!");
		if(nome != null)
			prod.setNome(nome);
		if(codigo != null)
			prod.setCodigo(codigo);
		if(preco >= 0)
			prod.setPreco(preco);
		if(fornec != null)
			prod.setFornecedor(fornec);
		if(val != null)
			prod.setValidade(val);
		if(quant >=0)
			prod.setQuant(quant);
	}

	public Produto buscaProduto(String codigo) throws Exception
	{
		Optional<Produto> opt =  this.listaProdutos
			.stream()
			.filter( x -> x.getCodigo().equals(codigo))
			.findAny();
		Produto prod = opt.get();
		return prod;
	}

	public Usuario buscaUsuario(String id, String senha) throws Exception
	{
		Optional<Usuario> opt = this.listaUsuarios
			.stream()
			.filter( x -> x.getID().equals(id) && (x.getSenha().equals(senha)) )
			.findAny();
		try
		{
			Usuario usuario = opt.get();
			return usuario;
		}
		catch (Exception e)
		{
			throw new Exception("Erro: id ou senha inválidos!");
		}
	}

	synchronized public void realizarVenda(String codigo, String id, String senha, int quant) throws Exception
	{	
		Produto prod = buscaProduto(codigo); 		//lança exceção se nao achar o produto ou usuario
		Usuario usuario = buscaUsuario(id, senha);
		Venda v = new Venda(new GregorianCalendar(), codigo, id, quant);
		if(quant < 0)
			throw new Exception("Quantidade negativa!");
		if(prod.getQuant() - quant < 0)
			throw new Exception("Quantidade de produto indisponivel no momento!");
		this.listaVendas.add(v); 					 //adiciona venda na lista de vendas
		prod.setQuant(prod.getQuant()-1);
	}

	public void gerarRelatorioDia()
	{
		/*code*/
	}

	public void gerarRelatorioMes()
	{
		/*code*/
	}

	public LinkedList<Usuario> carregaUsuarios()
	{
		LinkedList<Usuario> list = new LinkedList<Usuario> ();
		
		/*Code*/
		
		return list;
	}

	public LinkedList<Produto> carregaProdutos()
	{
		LinkedList<Produto> list = new LinkedList<Produto>();
		
		/*Code*/
		
		return list;
	}

	public LinkedList<Venda> carregaVendas()
	{
		LinkedList<Venda> list = new LinkedList<Venda>();
		
		/*Code*/
		
		return list;
	}

	public List<Venda> getVendas()
	{
		return this.listaVendas;
	}

	public List<Usuario> getListaDeUsuarios()
	{
		return this.listaUsuarios;
	}
}




















