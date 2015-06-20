import sistemas.*;
import itens.*;
import mercado.*;
import java.util.*;

class MainCliente
{
	public static void main(String[] args) throws Exception
	{
		SistemaCliente sist = new SistemaCliente();
		sist.conectarAoServidor("localHost", 5000);
		sist.logarNovoUsuario("BBB", "rua dois", "333333", "@@@@comBR", "1995", "AAAA");
		Usuario u = sist.getUsuario();
		if(u == null)
			System.out.println("NULL");
		else
			u.print();
	}	
}
