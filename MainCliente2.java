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

		sist.logarNovoUsuario("Jap", "rua dois", "333333", "@@@@comBR", "1992", "JAMIRO");
	}	
}