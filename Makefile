all: limpa merc MainServidor MainCliente
serv: compServ
client: compClient
limpa:
	clear
compServ:
	javac sistemas//SistemaServidor.java
execServ:
	java MainServidor
compClient:
	javac sistemas//SistemaCliente.java
execClient:
	java SistemaCliente
MainServidor:
	javac MainServidor.java
MainCliente:
	javac MainCliente.java
merc:
	javac mercado//Supermercado.java