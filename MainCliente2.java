import sistemas.*;
import itens.*;
import mercado.*;
import java.util.*;

class MainCliente2
{
	public static void main(String[] args) throws Exception
	{
		SistemaCliente sist = new SistemaCliente();
		sist.conectarAoServidor("localHost", 5000);
		sist.logar("1995", "AAKKA");
		Usuario u = sist.getUsuario();
		if(u == null)
			System.out.println("NULL");
		else
			u.print();
	}	
}