package br.com.cogerh.template.service.impl;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;



import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import br.com.cogerh.template.model.Acompanhamento;
import br.com.cogerh.template.model.Chamado;
import br.com.cogerh.template.model.Observador;
import br.com.cogerh.template.model.SolucaoChamado;
import br.com.cogerh.template.model.TecnicoChamado;
import br.com.cogerh.template.model.Usuario;
import br.com.cogerh.template.service.EmailService;
import br.com.cogerh.template.service.ObservadorService;
import br.com.cogerh.template.util.CaminhoAplicacaoUtil;


@Service
public class EmailServiceImpl implements EmailService{
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private ObservadorService observadorService;
	
	
	private List<Observador> observadoresList;
	
	private final String REMETENTE = "sysdiarias@cogerh.com.br";
	
	

	@Override
	public void enviarEmailObservadores(Chamado chamado) {
		try {
			listarObservadores();
		    for (Observador observador : observadoresList) {
			String titulo = "[ATENÇÃO] Novo Chamado inserido";
			
			String mensagemEmail = lerArquivoTemplateEmail("template_chamado_aguarda_tecnico.txt");
			
			SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy"); // HH:mm:ss
			
			String textoFinal = mensagemEmail.toString().replace("%NUMERO_CHAMADO%",chamado.getId().toString());
				   textoFinal =  textoFinal.toString().replace("%NOME_USUARIO%",observador.getUsuario().getNome().toString());
				   textoFinal = textoFinal.toString().replace("%DESCRICAO_CHAMADO%",chamado.getTitulo());
			
			
			
				enviarEmail(observador.getUsuario().getEmail(), null, titulo, textoFinal);

			} 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void enviarEmail(String destinatario, String destinatarioCc,String assunto, String texto) {
		MimeMessage message = mailSender.createMimeMessage();
			try {
				MimeMessageHelper helper = new MimeMessageHelper(message, true);
								
				helper.setFrom(REMETENTE);
				helper.setTo(destinatario);
				
					if (destinatarioCc != null) {
						helper.setCc(destinatarioCc);
					}
				
				helper.setSubject(assunto);
				helper.setText(texto, true);
				
				mailSender.send(message);
			
			} catch (MessagingException e) {
				e.printStackTrace();
			}
	
	}
	
	private String lerArquivoTemplateEmail(String arquivoTemplate) {
		String retorno = null;
		
		StringBuilder mensagemEmail = new StringBuilder();
		
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext(); 
		String diretorio = ec.getRealPath("/");
		String path = diretorio + "\\templatesEmail\\";
        
        FileReader arq;
		try {
			arq = new FileReader(path + "\\" +arquivoTemplate);
			
			BufferedReader lerArq = new BufferedReader(arq);
			String linha = lerArq.readLine(); 
	        mensagemEmail.append(linha);
	        
	        while (linha != null) {
	        	mensagemEmail.append(linha);
		        linha = lerArq.readLine();
			}
	        
	        lerArq.close();
	        retorno = mensagemEmail.toString();
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		return retorno;
		
	}

	@Override
	public void enviarEmailSolicitante(Chamado chamado) {
		try {
			String titulo = "Novo Chamado inserido";

			String mensagemEmail = lerArquivoTemplateEmail("template_solicitacao_chamado.txt");

			SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy"); // HH:mm:ss

			
			String textoFinal = mensagemEmail.toString().replace("%NUMEROCHAMADO%",chamado.getId().toString());
			textoFinal = textoFinal.toString().replace("%NOME_USUARIO%",chamado.getSolicitante().getNome().toString());
			textoFinal = textoFinal.toString().replace("%DATA_SOLICITACAO%",dataFormatada.format(new Date()));
			enviarEmail(chamado.getSolicitante().getEmail(), null, titulo, textoFinal);

		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	

	@Override
	public void enviarEmailTecnicosChamado(Chamado chamado) {
		for (TecnicoChamado tecnico : chamado.getTecnicosChamados()) {
			String titulo = "[ATENÇÃO] Novo Chamado Atribuído";

			String mensagemEmail = lerArquivoTemplateEmail("template_chamado_atribuido.txt");
			
			SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy"); // HH:mm:ss
			
			String textoFinal = mensagemEmail.toString().replace("%NUMERO_CHAMADO%",chamado.getId().toString());
				   textoFinal = textoFinal.toString().replace("%NOME_USUARIO%",tecnico.getTecnico().getUsuario().getNome());
			       textoFinal = textoFinal.toString().replace("%DESCRICAO_CHAMADO%",chamado.getDescricao());
			
			 enviarEmail(tecnico.getTecnico().getUsuario().getEmail(), null, titulo, textoFinal);
		}	
	}
	

	@Override
	public void enviarEmailChamadoEncerrado(SolucaoChamado solucaoChamado) {
		try {
			String titulo = "Chamado Encerrado";
			
			String urlAceitar = CaminhoAplicacaoUtil.obterCaminho() + "/aprovacao_chamado.xhtml?faces-redirect=true&hashURL="+solucaoChamado.getHashUrl();
			String urlrecusar = CaminhoAplicacaoUtil.obterCaminho() + "/aprovacao_chamado.xhtml?faces-redirect=true&hashURL="+solucaoChamado.getHashUrl();


			String mensagemEmail = lerArquivoTemplateEmail("template_chamado_encerrado.txt");
			
			SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy"); // HH:mm:ss
			
			String textoFinal = mensagemEmail.toString().replace("%NUMERO_CHAMADO%",solucaoChamado.getChamado().getNumero().toString());
				   textoFinal = textoFinal.toString().replace("%NOME_USUARIO%",solucaoChamado.getChamado().getSolicitante().getNome().toString());
			       textoFinal = textoFinal.toString().replace("%DESCRICAO_SOLUCAO%",solucaoChamado.getSolucaoUsuario());
			       textoFinal = textoFinal.toString().replace("%LINK_ACEITAR%",urlAceitar);
			       textoFinal = textoFinal.toString().replace("%LINK_RECUSAR%",urlrecusar);


			
			    enviarEmail(solucaoChamado.getChamado().getSolicitante().getEmail(), null, titulo, textoFinal);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	@Override
	public void enviaEmailAcompanhamento(Acompanhamento acompanhamento,Usuario usuario) {
		try {
			String titulo = "Acompanhamento de Chamado";

			String mensagemEmail = lerArquivoTemplateEmail("template_novo_acompanhamento.txt");
			
			SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy"); // HH:mm:ss
			
			String textoFinal = mensagemEmail.toString().replace("%NUMERO_CHAMADO%",acompanhamento.getChamado().getId().toString());
				   textoFinal = textoFinal.toString().replace("%NOME_USUARIO%",usuario.getNome());
			       textoFinal = textoFinal.toString().replace("%DESCRICAO_ACOMPANHAMENTO%",acompanhamento.getTexto());
			       textoFinal = textoFinal.toString().replace("%NOME_USUARIO_SOLICITANTE%",acompanhamento.getUsuario().getNome());
			       textoFinal = textoFinal.toString().replace("%DATA_SOLICITAÇÃO%",dataFormatada.format(new Date()));

			
			    enviarEmail(usuario.getEmail(), null, titulo, textoFinal);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	private void listarObservadores() throws Exception{
		observadoresList = new ArrayList<Observador>();
		observadoresList = observadorService.listar(null);
	}
	
	public JavaMailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public String getREMETENTE() {
		return REMETENTE;
	}

	@Override
	public void enviarEmailChamadoEmEspera(Chamado chamado) {
		try {
			String titulo = "[ATENÇÃO] Chamado Bloqueado";

			String mensagemEmail = lerArquivoTemplateEmail("template_email_chamado_bloqueado.txt");
			
			String textoFinal = mensagemEmail.toString().replace("%NUMERO_CHAMADO%",chamado.getNumero());
			textoFinal = textoFinal.toString().replace("%NOME_USUARIO%",chamado.getSolicitante().getNome().toString());
			enviarEmail(chamado.getSolicitante().getEmail(), null, titulo, textoFinal);

		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void enviarEmailViabilidadeRespondida(Chamado chamado) {
		try {
			List<TecnicoChamado> tecnicos = chamado.getTecnicosChamados();
			
			for (TecnicoChamado tecnicoChamado : tecnicos) {
				String titulo = "Viabilidade do Chamado Analisada";

				String mensagemEmail = lerArquivoTemplateEmail("template_email_viabilidade_analisada.txt");
				
				String textoFinal = mensagemEmail.toString().replace("%NUMERO_CHAMADO%",chamado.getNumero());
				textoFinal = textoFinal.toString().replace("%NOME_USUARIO%",tecnicoChamado.getTecnico().getUsuario().getNome());
				enviarEmail(tecnicoChamado.getTecnico().getUsuario().getEmail(), null, titulo, textoFinal);
			}
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}				
	}

	

	
}
