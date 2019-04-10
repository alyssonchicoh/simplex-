package br.com.cogerh.template.util;

public class StatusChamado {
	
	/**
	 * STATUS DE QUANDO O CHAMADO É ABERTO 
	 */
	public final static Integer AGUARDANDO_TECNICO = 1;
	
	/**
	 * STATUS DE QUANDO O CHAMANDO ENCONTRA-SE ALOCADO A ALGUM TÉCNICO E ESTÁ NA FILA DO MESMO 
	 */
	public final static Integer NA_FILA = 2;
	
	/**
	 * STATUS DE QUANDO O TÉCNICO SELECIONA PARA REALIZAR O ATENDIMENTO DO CHAMADO
	 */
	public final static Integer EM_ATENDIMENTO = 3;
	
	/**
	 * STATUS QUANDO O TÉCNICO REALIZA A SOLUÇÃO DO CHAMADO
	 */
	public final static Integer SOLUCIONADO = 4;
	
	/**
	 * STATUS DE QUANDO O USUÁRIO ACEITA A SOLUÇÃO DO TÉCNICO
	 */
	public final static Integer FINALIZADO = 5;
	
	/**
	 * STATUS DE QUANDO O TECNICO INFORMA QUE O CHAMADO ENCONTRA-SE IMPEDIDO POR ALGUM MOTIVO
	 */
	public final static Integer IMPEDIDO = 6;
	
	/**
	 * STATUS DE QUANDO O TÉCNICO NECESSITA DE UMA APROVAÇÃO NA ÁREA DE NÉGOCIO PARA ATENDER O CHAMADO
	 */
	public final static Integer AGUARDANDO_VIABILIDADE = 7;
	
	/**
	 * STATUS DE QUANDO O CHAMADO É REJEITADO POR ALGUM MOTIVO
	 */
	public final static Integer REJEITADO = 8;
	
	/**
	 * STATUS DE QUANDO O CHAMADO ENCONTRA-SE AGUARDANDO ALGUMA RESPOSTA DO USUÁRIO 
	 */
	public final static Integer AGUARDANDO_USUARIO = 9;
}
