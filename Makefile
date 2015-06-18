all: limpa serv client

serv: limpa compServ execServ
client: limpa compClient execClient
limpa:
	clear
compServ:
	javac SistemaServidor.java
execServ:
	java SistemaServidor
compClient:
	javac SistemaCliente.java
execClient:
	java SistemaCliente