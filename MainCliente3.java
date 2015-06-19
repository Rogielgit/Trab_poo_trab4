import sistemas.*;
import itens.*;
import mercado.*;
import java.util.*;

class MainCliente3
{
	public static void main(String[] args) throws Exception
	{
		SistemaCliente sist = new SistemaCliente();

		sist.conectarAoServidor("localHost", 5000);

		sist.logarNovoUsuario("Hold on", "rua dois", "333333", "@@@@comBR", "1990", "3 doors down");
	}	
}