package br.com.cogerh.template.util;

public class StatusChamado {
	
	/**
	 * STATUS DE QUANDO O CHAMADO � ABERTO 
	 */
	public final static Integer AGUARDANDO_TECNICO = 1;
	
	/**
	 * STATUS DE QUANDO O CHAMANDO ENCONTRA-SE ALOCADO A ALGUM T�CNICO E EST� NA FILA DO MESMO 
	 */
	public final static Integer NA_FILA = 2;
	
	/**
	 * STATUS DE QUANDO O T�CNICO SELECIONA PARA REALIZAR O ATENDIMENTO DO CHAMADO
	 */
	public final static Integer EM_ATENDIMENTO = 3;
	
	/**
	 * STATUS QUANDO O T�CNICO REALIZA A SOLU��O DO CHAMADO
	 */
	public final static Integer SOLUCIONADO = 4;
	
	/**
	 * STATUS DE QUANDO O USU�RIO ACEITA A SOLU��O DO T�CNICO
	 */
	public final static Integer FINALIZADO = 5;
	
	/**
	 * STATUS DE QUANDO O TECNICO INFORMA QUE O CHAMADO ENCONTRA-SE IMPEDIDO POR ALGUM MOTIVO
	 */
	public final static Integer IMPEDIDO = 6;
	
	/**
	 * STATUS DE QUANDO O T�CNICO NECESSITA DE UMA APROVA��O NA �REA DE N�GOCIO PARA ATENDER O CHAMADO
	 */
	public final static Integer AGUARDANDO_VIABILIDADE = 7;
	
	/**
	 * STATUS DE QUANDO O CHAMADO � REJEITADO POR ALGUM MOTIVO
	 */
	public final static Integer REJEITADO = 8;
	
	/**
	 * STATUS DE QUANDO O CHAMADO ENCONTRA-SE AGUARDANDO ALGUMA RESPOSTA DO USU�RIO 
	 */
	public final static Integer AGUARDANDO_USUARIO = 9;
}
