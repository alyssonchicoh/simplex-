package br.com.cogerh.template.service;

import br.com.cogerh.template.model.Acompanhamento;
import br.com.cogerh.template.model.Chamado;
import br.com.cogerh.template.model.SolucaoChamado;
import br.com.cogerh.template.model.Usuario;

public interface EmailService {

	/**
	 * MÉTODO UTILIZADO PARA ENVIAR UM EMAIL PARA OS OBSERVADORES DO SISTEMA
	 * @param chamado
	 */
	public void enviarEmailObservadores(Chamado chamado);
	
	/**
	 * MÉTODO UTILIZADO PARA ENVIAR UM EMAIL PARA O SOLICITANTE DO CHAMADO
	 * @param chamado
	 */
	public void enviarEmailSolicitante(Chamado chamado);
	
	/**
	 * MÉTODO UTILIZADO PARA ENVIAR UM EMAIL PARA TODOS OS TECNICOS ATRIBUIDOS AO CHAMADO
	 * @param chamado
	 */
	public void enviarEmailTecnicosChamado(Chamado chamado);
	
	/**
	 * MÉTODO UTILIZADO PARA ENVIAR UM EMAIL PARA O USUÁRIO QUANDO SEU CHAMADO FOR ENCERRADO POR UM TÉCNICO 
	 * @param chamado
	 */
	public void enviarEmailChamadoEncerrado(SolucaoChamado solucaoChamado);
	
	/**
	 * MÉTODO UTILIZADO PARA ENVIAR EMIALS DE ACOMPANHAMENTO PARA O USUÁRIO, TÉCNICO E OBSERVADORES
	 * @param chamado
	 */
	public void enviaEmailAcompanhamento(Acompanhamento acompanhamento,Usuario usuario);
	
	/**
	 * MÉTODO UTILIZADO PARA ENVIAR UM EMAIL INFORMANDO QUE O CHAMADO ENCONTRA-SE EM ESPERA
	 */
	public void enviarEmailChamadoEmEspera(Chamado chamado);
	
	/**
	 * ESSE MÉTODO ENVIA UM EMAIL QUANDO A VIABILIDADE SOLICITADA FOI RESPONDIDA
	 * @param CHAMADO A QUAL SE DESEJA OBTER OS TECNICOS PARA ENVIO DO EMAIL
	 */
	public void enviarEmailViabilidadeRespondida(Chamado chamado);

}
