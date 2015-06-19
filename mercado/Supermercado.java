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

	public void cadastraUsuario(Usuario u) throws Exception
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

	Produto buscaProduto(String codigo) throws Exception
	{
		Optional<Produto> opt =  this.listaProdutos
			.stream()
			.filter( x -> x.getCodigo().equals(codigo))
			.findAny();
		Produto prod = opt.get();
		return prod;
	}

	Usuario buscaUsuario(String id) throws Exception
	{
		Optional<Usuario> opt = this.listaUsuarios
			.stream()
			.filter( x -> x.getID().equals(id))
			.findAny();
		Usuario usuario = opt.get();
		return usuario;
	}

	synchronized public void realizarVenda(String codigo, String id, int quant) throws Exception
	{	
		Produto prod = buscaProduto(codigo); 		//lança exceção se nao achar o produto ou usuario
		Usuario usuario = buscaUsuario(id);
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
		LinkedList list = new LinkedList();		//???????
		String arquivocsv = "Arquivos/Dados_usuarios.csv";
		BufferedReader br = null;
		String linha = "";
		String separatorcsv = ",";		
 
		try
		{ 
			br = new BufferedReader(new FileReader(arquivocsv));
			while ((linha = br.readLine()) != null)
			{	
	        	String[] dadosUsuarios = linha.split(separatorcsv); // use comma as separator
				this.cadastraUsuario(new Usuario(dadosUsuarios[0], dadosUsuarios[1],dadosUsuarios[2],dadosUsuarios[3],dadosUsuarios[4],dadosUsuarios[5]));

			} 
		}
		catch(Exception ex)
		{
			System.out.println("Erro: "+ ex.getMessage());
		}
		finally
		{
			if (br != null)
			{
				try
				{
					br.close();
				}
				catch (IOException ex)
				{
					ex.printStackTrace();
				}
			}
		}
	
		return list;
	}

	public LinkedList<Produto> carregaProdutos()
	{
		LinkedList list = new LinkedList();
		
		String arquivocsv = "Arquivos/Dados_produtos.csv";
		BufferedReader br = null;
		String linha = "";
		String separatorcsv = ",";

		try
		{ 
			br = new BufferedReader(new FileReader(arquivocsv));
			while ((linha = br.readLine()) != null)
			{
	        	String[] dadosProdutos = linha.split(separatorcsv); // use comma as separator
    	    	//Verifica de que tipo e o usuario
				this.cadastraProduto (new Produto(dadosProdutos[0], dadosProdutos[1],Double.parseDouble(dadosProdutos[2]),dadosProdutos[3],new GregorianCalendar (
										Integer.parseInt(dadosProdutos[4]),Integer.parseInt(dadosProdutos[6]),Integer.parseInt(dadosProdutos[7])),
										Double.parseDouble(dadosProdutos[8])));
			} 
		}
		catch(Exception ex)
		{
			System.out.println("Erro: "+ ex.getMessage());
		}
		finally
		{
			if (br != null)
			{
				try
				{
					br.close();
				}
				catch (IOException ex)
				{
					ex.printStackTrace();
				}
			}
		}
			
		return list;
	}

	public LinkedList<Venda> carregaVendas()
	{
		LinkedList list = new LinkedList();
		
		/*Code*/
		
		return list;
	}

	public List<Venda> getVendas()
	{
		return this.listaVendas;
	}
}




















