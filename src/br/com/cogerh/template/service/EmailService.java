package br.com.cogerh.template.service;

import br.com.cogerh.template.model.Acompanhamento;
import br.com.cogerh.template.model.Chamado;
import br.com.cogerh.template.model.SolucaoChamado;
import br.com.cogerh.template.model.Usuario;

public interface EmailService {

	/**
	 * M�TODO UTILIZADO PARA ENVIAR UM EMAIL PARA OS OBSERVADORES DO SISTEMA
	 * @param chamado
	 */
	public void enviarEmailObservadores(Chamado chamado);
	
	/**
	 * M�TODO UTILIZADO PARA ENVIAR UM EMAIL PARA O SOLICITANTE DO CHAMADO
	 * @param chamado
	 */
	public void enviarEmailSolicitante(Chamado chamado);
	
	/**
	 * M�TODO UTILIZADO PARA ENVIAR UM EMAIL PARA TODOS OS TECNICOS ATRIBUIDOS AO CHAMADO
	 * @param chamado
	 */
	public void enviarEmailTecnicosChamado(Chamado chamado);
	
	/**
	 * M�TODO UTILIZADO PARA ENVIAR UM EMAIL PARA O USU�RIO QUANDO SEU CHAMADO FOR ENCERRADO POR UM T�CNICO 
	 * @param chamado
	 */
	public void enviarEmailChamadoEncerrado(SolucaoChamado solucaoChamado);
	
	/**
	 * M�TODO UTILIZADO PARA ENVIAR EMIALS DE ACOMPANHAMENTO PARA O USU�RIO, T�CNICO E OBSERVADORES
	 * @param chamado
	 */
	public void enviaEmailAcompanhamento(Acompanhamento acompanhamento,Usuario usuario);
	
	/**
	 * M�TODO UTILIZADO PARA ENVIAR UM EMAIL INFORMANDO QUE O CHAMADO ENCONTRA-SE EM ESPERA
	 */
	public void enviarEmailChamadoEmEspera(Chamado chamado);
	
	/**
	 * ESSE M�TODO ENVIA UM EMAIL QUANDO A VIABILIDADE SOLICITADA FOI RESPONDIDA
	 * @param CHAMADO A QUAL SE DESEJA OBTER OS TECNICOS PARA ENVIO DO EMAIL
	 */
	public void enviarEmailViabilidadeRespondida(Chamado chamado);

}
