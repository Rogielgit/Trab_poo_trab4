import sistemas.*;
import itens.*;
import mercado.*;
import java.util.*;

class MainServidor
{
	public static void main(String[] args) throws Exception
	{
		SistemaServidor sist = new SistemaServidor(5000);
		sist.conectarComClientes();
	}	
}


