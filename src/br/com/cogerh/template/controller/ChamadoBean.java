package br.com.cogerh.template.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.EmitUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;

import com.sun.java_cup.internal.runtime.Scanner;

import br.com.cogerh.template.model.Acompanhamento;
import br.com.cogerh.template.model.ArquivoAnexado;
import br.com.cogerh.template.model.Categoria;
import br.com.cogerh.template.model.Chamado;
import br.com.cogerh.template.model.DesaprovacaoSolucaoChamado;
import br.com.cogerh.template.model.Observador;
import br.com.cogerh.template.model.SolucaoChamado;
import br.com.cogerh.template.model.Tecnico;
import br.com.cogerh.template.model.TecnicoChamado;
import br.com.cogerh.template.model.Usuario;
import br.com.cogerh.template.service.AcompanhamentoService;
import br.com.cogerh.template.service.ArquivoAnexadoService;
import br.com.cogerh.template.service.CategoriaService;
import br.com.cogerh.template.service.ChamadoService;
import br.com.cogerh.template.service.DesaprovacaoSolucaoChamadoService;
import br.com.cogerh.template.service.EmailService;
import br.com.cogerh.template.service.NotificacaoService;
import br.com.cogerh.template.service.ObservadorService;
import br.com.cogerh.template.service.SolucaoChamadoService;
import br.com.cogerh.template.service.TecnicoService;
import br.com.cogerh.template.service.impl.EmailServiceImpl;
import br.com.cogerh.template.util.CalculadoraPosicaoChamado;
import br.com.cogerh.template.util.GerenciadorCoresButton;
import br.com.cogerh.template.util.StatusChamado;

@Controller
@Scope("view")
public class ChamadoBean extends AbstractBean {

	// VARIAVEIS PARA A ENTIDADE CHAMADO
	@Autowired
	private ChamadoService chamadoService;
	private Chamado chamado;
	private GerenciadorCoresButton gerenciadorCoresButton;
	private List<Chamado> chamadoList;
	private Integer idChamadoSelecionado;

	// VARIAVEIS PARA A ENTIDADE USUÁRIO WEB
	@Autowired
	private UsuarioWeb usuarioWeb;

	// VARIAVEIS PARA A ENTIDADE CATEGORIA
	@Autowired
	private CategoriaService categoriaService;
	private List<Categoria> categoriaList;

	// VARIAVEIS PARA A ENTIDADE TECNICO
	@Autowired
	private TecnicoService tecnicoService;
	private List<Tecnico> tecnicoList;
	private List<Tecnico> tecnicoByChamadoList;

	// VARIAVEIS PARA OS ACOMPANHAMENTOS DE CHAMADO
	@Autowired
	private AcompanhamentoService acompanhamentoService;
	private Acompanhamento acompanhamento;
	private List<Acompanhamento> acompanhamentoList;

	@Autowired
	private NotificacaoService notificacaoService;
	
	@Autowired
	private ArquivoAnexadoService arquivoAnexadoService;
	
	@Autowired
	private ObservadorService observadorService;
	// INICIO DA DECLARAÇÃO DOS MÉTODOS DO BEAN
	
	private Part uploadedFile;
	
	private List<ArquivoAnexado> arquivoAnexado;
	
	private UploadedFile file;
	
	private StreamedContent fileStreamed;
	
	//VARIAVEL PARA CONTROLAR O MODAL QUE APARECE NA TELA AO CADASTRAR UM NOVO CHAMADO
	private boolean newChamado;
	
	//VARIAVEL PARA CONTROLAR O MODAL QUE APARECE NA TELA AO FECHAR UM CHAMADO
	private boolean newChamadoFechado = false;
	
	private boolean chamadoComSolucao = false;
	
	private List<SolucaoChamado> solucaoChamado;
	
	private Integer votoUsuario;
	
	@Autowired
	private SolucaoChamadoService solucaoChamadoService;

	@Autowired
	private EmailService email;
	
	private boolean exibeSolucaoChamado = false;
	
	private List<SolucaoChamado> solucaoChamadoList;
	
	private DesaprovacaoSolucaoChamado desaprovacaoSolucaoChamado;
	
	@Autowired

	private DesaprovacaoSolucaoChamadoService desaprovacaoSolucaoChamadoService;
	
	private SolucaoChamado ultimaSolucaoChamado;
	
	private String qtdAnexos;
	
	private String qtdAcompanhamentos;


	
	
	@PostConstruct
	public void init() {
		this.inicializaValores();
		this.inicializaListas();
        RequestContext.getCurrentInstance().execute("PF('dgl1').show();");
        arquivoAnexado = new ArrayList<ArquivoAnexado>();
        solucaoChamadoList = new ArrayList<SolucaoChamado>();
        desaprovacaoSolucaoChamado = new DesaprovacaoSolucaoChamado();
        initAreaUsuario();
	
	}
	
	public void initAreaUsuario(){
		this.listarChamadosSolicitadoByUsuarioLogado();
	}

	public void initFormDetalhamento(Integer chamadoId,boolean newChamado,boolean newChamadoFechado) {
		this.idChamadoSelecionado = chamadoId;
		chamado = consultarChamadoByID(chamadoId);
		listarAcompanhamentos();
		consultarArquivosAnexados();
		this.newChamado = newChamado;
		this.newChamadoFechado = newChamadoFechado; 
		consultarSolucaoChamado();
		qtdAnexos = String.valueOf(arquivoAnexado.size());
		qtdAcompanhamentos = String.valueOf(acompanhamentoList.size());
		listarTecnicosByChamado();
	}
	
	public void initAreaTecnico(){
		consultarChamadosAtribuidosByTecnico();
	}
	
	/**
	 * MÉTODO UTILIZADO PARA VERIFICAR SE O CHAMADO EM QUESTÃO TEM SOLUÇÃO REGISTRADA NO SISTEMA
	 */
	private void consultarSolucaoChamado() {
		try {
			solucaoChamadoList = solucaoChamadoService.listarByChamado(chamado.getId());
			
			if(solucaoChamadoList.size() > 0){
				ultimaSolucaoChamado = solucaoChamadoList.get(0);
				chamadoComSolucao = true;
			}
		} catch (Exception e) {
			super.adicionaMensagemDeErro(e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void consultarChamadosAtribuidosByTecnico(){
		try {
			chamadoList = chamadoService.listarChamadoOrdenadoPorPosicaoFilaByTecnico(consultarIDTecnicoLogado());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private Integer consultarIDTecnicoLogado(){
		Tecnico tecnico;
		try {
			tecnico = tecnicoService.buscarPorIdUsuario(usuarioWeb.getUsuario().getId());
			if(tecnico != null){
				return tecnico.getId();
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	private Integer consultarIDTecnico(Integer idUsuario){
		Tecnico tecnico;
		try {
			tecnico = tecnicoService.buscarPorIdUsuario(idUsuario);
			if(tecnico != null){
				return tecnico.getId();
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public void initFormEncerramento(Integer chamadoId){
		chamado = consultarChamadoByID(chamadoId);

	}
	
	 public void execute() {
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Executed", "Using RemoteCommand."));
	        System.out.println("OLÁ");
	    }
	
	/**
	 * Método chamado quando a tela de consulta de chamados by Observador é executada
	 */
	public void initMenuByObservador(){
		listarSemTecnico();
	}
	
	
	/**
	 * Método utilizado para inicializar os valores das variaveis
	 */
	public void inicializaValores() {
		chamado = new Chamado();
		gerenciadorCoresButton = new GerenciadorCoresButton();

		inicializarChamado();

		categoriaList = new ArrayList<Categoria>();

		acompanhamento = new Acompanhamento();

	}

	public void inicializarChamado() {
		chamado.setPrioridade(1);
		chamado.setDataAbertura(new Date());
		chamado.setSolicitante(usuarioWeb.getUsuario());
	}

	/**
	 * Método utilizado para inicialiar todas as listas do Bean
	 */
	public void inicializaListas() {
		listarCategorias();
		listarTecnicos();
		listarTecnicosByChamado();
	}

	/**
	 * Método utilizado para salvar um novo chamado
	 */
	public String salvar() {
		try {
			if(verificarCamposObrigatorios()){
				chamado.setStatus(StatusChamado.AGUARDANDO_TECNICO);
				chamado = chamadoService.salvar(chamado);
				salvarArquivos();
				adicionaMensagemDeSucesso("O seu chamado foi adicionado com Sucesso, em breve um você receberá mais informações sobre ele.");
				enviarNotificacaoObservadores();
				enviarEmails();
			}else{
				return "";
			}
		} catch (Exception e) {
			adicionaMensagemDeErro("Ops, aconteceu algo de errado. Contate a GETIN");
			e.printStackTrace();
		}
		return "detalhamento.xhtml?faces-redirect=true&chamadoId="+ chamado.getId() +"&newChamado=true&newChamadoFechado=false";
	}

	private void enviarEmails() {
		//NOTIFICA TODOS OS OBSERVADORES DO SISTEMA
		email.enviarEmailObservadores(chamado);
		
		//NOTIFICA O SOLICITANTE
		email.enviarEmailSolicitante(chamado);
	}

	private void enviarNotificacaoObservadores() {
		try {
			List<Observador> observadores = observadorService.listar(null);
			
			for (Observador observador : observadores) {
				notificacaoService.adicionarNotificacaoNovoChamado(observador.getUsuario());
				System.out.println("NOTIFICAÇÃO ENVIADA");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void listar() {
		try {
			chamadoList = chamadoService.listar(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void listarSemTecnico(){
		try{
			chamadoList = chamadoService.listarSemTecnicoAlocado();
		} catch (Exception e){
			
		}
	}
	
	/**
	 * Método utilizado para listar os chamados atribuidos ao usuário logado no sistema
	 * A principal utilidade desse método é na hora de consulta de chamados atribuidos no menu de Técnicos
	 */
	public void listarChamadosAtribuidosByUsuarioLogado(){
		Integer idUsuario = usuarioWeb.getUsuario().getId();
		try {
			chamadoList = chamadoService.listarByTecnico(idUsuario);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * MÉTODO UTILIZADO PARA LISTAR TODOS OS CHAMADOS ABERTOS PELO USUÁRIO LOGADO
	 */
	public void listarChamadosSolicitadoByUsuarioLogado(){
		Integer idUsuario = usuarioWeb.getUsuario().getId();

		try {
			chamadoList = chamadoService.listarChamadosSolicitadoByUsuario(idUsuario);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void solucionarChamado(){
		chamado.setStatus(StatusChamado.SOLUCIONADO);
		try {
			chamadoService.alterar(chamado);
			votarTecnico();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void votarTecnico(){
		SolucaoChamado solucao = solucaoChamado.get(0);
		solucao.setConfirmacao(true);
		solucao.setNotaSolucao(votoUsuario);
		try {
			solucaoChamadoService.alterar(solucao);
			System.out.println("OK!");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * MÉTODO UTILIZADO PARA VERIFICAR SE O CHAMADO TEM SOLUÇÃO
	 */
	private void verificaSolucaoChamado(){
		try {
			List<SolucaoChamado> list = solucaoChamadoService.listarByChamado(chamado.getId());
			
			if(list != null){
				if(list.size() >0){
					exibeSolucaoChamado = true;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void visualizarDetalhado(Integer id){
		System.out.println("TESTE "+id);
		

		ExternalContext externalContext = FacesContext.getCurrentInstance()
                .getExternalContext();
	    try {
	          externalContext.redirect(externalContext.getRequestContextPath() + "/pages/chamado/detalhamento.xhtml?chamadoId="+id+"&newChamado=false&&newChamadoFechado=false");
	    } catch (IOException e) {
	          e.printStackTrace();
	    }
		
	}

	/**
	 * Método utilizado para definir a prioridade do chamado, obtido na tela
	 * pelos botões de prioridades
	 * @param valor
	 */
	public void definirPrioridade(Integer valor) {
		this.chamado.setPrioridade(valor);
		gerenciadorCoresButton.atualizarCoresPrioridade(valor);
	}

	/**
	 * Método utilizado para definir se o chamado já foi informado ao tecnico
	 * 
	 * @param valor
	 */
	public void definirChamadoJaInformado(boolean valor) {
		this.chamado.setTecnicoInformado(valor);
		gerenciadorCoresButton.atualizarCoresInformado(valor);
	}

	/**
	 * Método utilizado para definir se o chamado é retroativo
	 * 
	 * @param valor
	 */
	public void definirChamadoRetroativo(boolean valor) {
		this.chamado.setRetroativo(valor);
		gerenciadorCoresButton.atualizarCoresRetroativo(valor);

	}

	/**
	 * Método utilizado para definir se os trabalhos estão parados por conta do
	 * chamado
	 * 
	 * @param valor
	 */
	public void definirTrabalhosParados(boolean valor) {
		this.chamado.setTrabalhosParados(valor);
		gerenciadorCoresButton.atualizarCoresTrabalhosParados(valor);
	}

	/**
	 * Método utilizado para definir se o chamado é recorrente
	 * 
	 * @param valor
	 */
	public void definirChamadoRecorrente(boolean valor) {
		this.chamado.setRecorrente(valor);
		gerenciadorCoresButton.atualizarCoresRecorrente(valor);
	}
	
	/**
	 * Método utilizado para atualizar o status do chamado quando o tecnico começa a trablhar no mesmo
	 */
	public void trabalharChamado(){
		chamado.setStatus(StatusChamado.EM_ATENDIMENTO);
		chamado.setTrabalhando(true);
		try {
			chamado = chamadoService.alterar(chamado);
			System.out.println("ATUALIZADO");
			super.adicionaMensagemDeSucesso("Chamado Atualizado");
		} catch (Exception e) {
			super.adicionaMensagemDeSucesso("Aconteceu algo estranho...");

			e.printStackTrace();
		}
	}
	
	public String finalizarChamado(){
		return "encerramento_chamado.xhtml?faces-redirect=true&chamadoId="+ chamado.getId();
	}
	
	public void rejeitarSolucaoChamado(){
		try {
			desaprovacaoSolucaoChamadoService.salvar(desaprovacaoSolucaoChamado);
			ultimaSolucaoChamado.setRejeicaoSolucaoChamado(desaprovacaoSolucaoChamado);
			solucaoChamadoService.alterar(ultimaSolucaoChamado);
			super.adicionaMensagemDeSucesso("Chamado rejeitado com sucesso!");
		} catch (Exception e) {
			super.adicionaMensagemDeErro("Erro ao rejeitar o chamado!");
			e.printStackTrace();
		}
	}
	
	
	public void anexarArquivos(UploadedFile file){
		
		
		try (InputStream input =  file.getInputstream()) {
			String fileName = file.getFileName();
			Files.copy(input, new File("E:////documentosChamado\\", fileName).toPath());
			ArquivoAnexado ar = new  ArquivoAnexado();
			ar.setNome(fileName);
			ar.setTamanho(Double.valueOf(file.getSize()));
			ar.setFormato(getFileExtension(file.getFileName()).toUpperCase());
			ar.setCaminho("E:////documentosChamado\\"+fileName);
			System.out.println(extractExtension(fileName));
			arquivoAnexado.add(ar);
		} catch (IOException e) {
			 e.printStackTrace();
			     }
	}

	private String extractFileName(Part part) {
	    String contentDisp = part.getHeader("content-disposition");
	    String[] items = contentDisp.split(";");
	    for (String s : items) {
	        if (s.trim().startsWith("filename")) {
	            return s.substring(s.indexOf("=") + 2, s.length()-1);
	        }
	    }
	    return "";
	}
	
	
	
	
	private String extractExtension(String fileName){
		String extension = "";

		int i = fileName.lastIndexOf('.');
		if (i > 0) {
		    extension = fileName.substring(i+1);
		}
		return extension;
	}
	
	private String getFileExtension(String file) {
	    String name = file;
	    int lastIndexOf = name.lastIndexOf(".");
	    if (lastIndexOf == -1) {
	        return ""; // empty extension
	    }
	    return name.substring(lastIndexOf);
	}
	
	private void salvarArquivos() throws Exception {
		for (ArquivoAnexado arquivo : arquivoAnexado) {
			arquivo.setChamado(chamado);
			arquivoAnexadoService.salvar(arquivo);
		}

	}
	
	public void selecionaArquivo(Integer idArquivo) throws FileNotFoundException{
		for (ArquivoAnexado arquivo : arquivoAnexado) {
			if(arquivo.getId().equals(idArquivo)){
				File ar = new File(arquivo.getCaminho());
				InputStream targetStream = new FileInputStream(ar);	
				fileStreamed = new DefaultStreamedContent(targetStream, "image/jpg", arquivo.getNome());
			}
		}
	}
	
	/**
	 * 
	 * @return boolean se todos os campos obrigatorios estão preenchidos True -> SIM, False -> Não
	 */
	public boolean verificarCamposObrigatorios(){
		 
		if(chamado != null){
			if(chamado.getTitulo().equals("")){
				super.adicionaMensagemDeErro("O Título é Obrigatório!");
				return false;
			}
			
			if(chamado.getDescricao().equals("")){
				super.adicionaMensagemDeErro("A Descrição é Obrigatória!");
				return false;
			}
			
			if(chamado.getCategoria() == null){
				super.adicionaMensagemDeErro("A Categoria é Obrigatória!");
				return false;
			}
			
			return true;
		
		}else{
			return false;
		}
	}

	// MÉTODOS PARA CATEGORIA DE CHAMADO
	public void listarCategorias() {
		try {
			categoriaList = categoriaService.listar(null);
		} catch (Exception e) {
			adicionaMensagemDeErro("Ops, Aconteceu algo estranho... entre em contato com a GETIN");
			e.printStackTrace();
		}
	}

	public void atribuirCategoria(Categoria categoria) {
		System.out.println("CHAMOU!");
		try {
			chamado.setCategoria(categoria);
		} catch (Exception e) {
			adicionaMensagemDeErro("Ops, Aconteceu algo estranho... entre em contato com a GETIN");
		}
	}

	public Chamado consultarChamadoByID(Integer id) {
		Chamado chamado = null;
		try {
			chamado = chamadoService.buscarPorId(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return chamado;
	}

	// MÉTODOS PARA TECNICOS
	public void listarTecnicos() {
		try {
			tecnicoList = tecnicoService.listar(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Adiciona um tecnico na lista de tecnicos relativos aos chamados
	 * @param tecnico a qual deseja-se adicionar na lista
	 */
	public void adicionarTecnicos(Tecnico tecnico) {
		tecnicoByChamadoList.add(tecnico);
	}
	
	/**
	 * Remove um tecnico da lista de tecnicos relativos ao chamado
	 * @param tecnico
	 */
	public void removerTecnico(Tecnico tecnico){
		tecnicoByChamadoList.remove(tecnico);
	}
	
	/**
	 * Salva os tenicos que estão na lista de TecnicosByChamadoList
	 */
	public void salvarTecnicos(){
		List<TecnicoChamado> tecnicos = new ArrayList<>();
		
		for (Tecnico tecnico : tecnicoByChamadoList) {
			TecnicoChamado tc = new TecnicoChamado();
			tc.setChamado(chamado);
			tc.setTecnico(tecnico);
			tecnicos.add(tc);
		}
		
		this.chamado.setTecnicosChamados(tecnicos);
		
		try {
			CalculadoraPosicaoChamado calculadoraPosicaoChamado = new CalculadoraPosicaoChamado();
			chamado.setStatus(StatusChamado.NA_FILA);
			chamado.setScoreFila(this.calcularScore(chamado.getPrioridade(), chamado.isRecorrente(),chamado.isTrabalhosParados()));
			chamado.setPosicaoFila(this.calcularPosicaoFila(chamado.getScoreFila(),tecnicoByChamadoList.get(0).getUsuario().getId()));
			chamadoService.atualizarPosicaoFilaChamados(chamado.getPosicaoFila(), tecnicoByChamadoList.get(0).getId());
			this.chamado = chamadoService.alterar(chamado);
			super.adicionaMensagemDeSucesso("Técnicos Adicionados");
			notificarTecnicos();

		} catch (Exception e) {
			super.adicionaMensagemDeSucesso("Erro ao adicionar Técnicos");
			e.printStackTrace();
		}
	}
	
	public void bloquearChamado(){
		chamado.setStatus(StatusChamado.AGUARDANDO_USUARIO);
		email.enviarEmailChamadoEmEspera(chamado);
		try {
			chamadoService.alterar(chamado);
			super.adicionaMensagemDeSucesso("Chamado Atualizado com Sucesso!");
		} catch (Exception e) {
			super.adicionaMensagemDeErro(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * Método utilizado para notificar todos os técnico adicionados no chamado
	 */
	private void notificarTecnicos() {
		email.enviarEmailTecnicosChamado(chamado);
	}
	
	private Integer calcularScore(Integer prioridade,boolean isRecorrente,boolean isTrabalhosParados){
		CalculadoraPosicaoChamado calculadoraPosicaoChamado = new CalculadoraPosicaoChamado();

		String tipoPrioridade = "";
		if(prioridade.equals(1)){
			tipoPrioridade = CalculadoraPosicaoChamado.TIPO_PRIORIDADE_BAIXA;
		}else if(prioridade.equals(2)){
			tipoPrioridade = CalculadoraPosicaoChamado.TIPO_PRIORIDADE_MEDIA;
		}else if(prioridade.equals(3)){
			tipoPrioridade = CalculadoraPosicaoChamado.TIPO_PRIORIDADE_ALTA;
		}

		Integer score = calculadoraPosicaoChamado.calcularScoresChamado(tipoPrioridade, isRecorrente, isTrabalhosParados);
		
		return score;
	}
	
	private Integer calcularPosicaoFila(Integer score,Integer idUsuario){
		try {
			Integer idTecnico = consultarIDTecnico(idUsuario);
			List<Chamado> chamadosOrdenadosByTecnico = chamadoService.listarChamadoOrdenadoPorPosicaoFilaByTecnico(idTecnico);
			CalculadoraPosicaoChamado calculadoraPosicaoChamado = new CalculadoraPosicaoChamado(chamadosOrdenadosByTecnico);
			Integer posicao = calculadoraPosicaoChamado.calcularPosicao(score);
			return posicao;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	

	/**
	 * Método utilizado para verificar se existe um tecnico na lista de tecnicos selecionados
	 * Utilizamos esse método para pintar os tecnicos selecionados na pagina de detalhamento de chamado
	 * @param idTecnico do técnico que deseja verificar se existe na lista de tecnicos adicionados
	 * @author Alysson Nascimento
	 * @return boolean se existe ou não o tecnico na lista. true -> Existe; false -> Não existe;
	 */
	public boolean verificaExistenciaTecnicoByList(Integer idTecnico){
		for (Tecnico tecnico : tecnicoByChamadoList) {
			if(tecnico.getId().equals(idTecnico)){
				return true;
			}
			
			
		}
		return false;
	}
	
	
	// MÉTODOS PARA ACOMPANHAMENTOS
	public void listarAcompanhamentos() {
		acompanhamentoList = new ArrayList<Acompanhamento>();
		try {
			acompanhamentoList = acompanhamentoService.listarByChamado(chamado.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void salvarAcompanhamento() {
		try {
			acompanhamento.setChamado(chamado);
			acompanhamento.setUsuario(usuarioWeb.getUsuario());
			acompanhamento.setData(new Date());
			acompanhamentoService.salvar(acompanhamento);
			acompanhamentoList.add(acompanhamento);
			System.out.println("ACOMPANHAMENTO SALVO!");
			email.enviaEmailAcompanhamento(acompanhamento, chamado.getSolicitante());
			acompanhamento = new Acompanhamento();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean verificarTipoUsuarioAcompanhamento(Usuario usuario) {

		if (usuario.getId().equals(this.usuarioWeb.getUsuario().getId())) {
			return true;
		} else {
			return false;
		}
	}

	public void listarTecnicosByChamado() {
		tecnicoByChamadoList = new ArrayList<Tecnico>();

		if (chamado != null) {
			List<TecnicoChamado> tecnicoChamados = chamado
					.getTecnicosChamados();
			if (tecnicoChamados != null) {
				for (TecnicoChamado tecnicoChamado : tecnicoChamados) {
					tecnicoByChamadoList.add(tecnicoChamado.getTecnico());
				}
			}
		}
	}

	/**
	 * MÉTODO UTILIZADO PARA ABRIR A PAGINA DE SOLICITAÇÃO DE VIABILIDADE DO CHAMADO
	 */
	public void showFormViabilidade(){
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	    try {
	          externalContext.redirect(externalContext.getRequestContextPath() + "/pages/viabilidade/novo.xhtml?chamadoId="+idChamadoSelecionado);
	    } catch (IOException e) {
	          e.printStackTrace();
	    }
	}
	
	
	public String transformarDataEmString(Date data) {
		String dataFormatada = "";
		
		if (data != null) {

			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(data);

			int dia = calendar.get(GregorianCalendar.DAY_OF_MONTH);
			int mes = calendar.get(GregorianCalendar.MONTH);

			String[] meses = { "Jan", "Fev", "Mar", "Abr", "Mai", "Jun", "Jul",
					"Ago", "Set", "Out", "Nov", "Dez"};

			dataFormatada = dia + " de " + meses[mes];
		}
		
		return dataFormatada;

	}
	
	public void consultarArquivosAnexados(){
		try {
			arquivoAnexado = arquivoAnexadoService.buscarByChamado(idChamadoSelecionado);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Chamado getChamado() {
		return chamado;
	}

	public void setChamado(Chamado chamado) {
		this.chamado = chamado;
	}

	public ChamadoService getChamadoService() {
		return chamadoService;
	}

	public void setChamadoService(ChamadoService chamadoService) {
		this.chamadoService = chamadoService;
	}

	public List<Categoria> getCategoriaList() {
		return categoriaList;
	}

	public void setCategoriaList(List<Categoria> categoriaList) {
		this.categoriaList = categoriaList;
	}

	public UsuarioWeb getUsuarioWeb() {
		return usuarioWeb;
	}

	public void setUsuarioWeb(UsuarioWeb usuarioWeb) {
		this.usuarioWeb = usuarioWeb;
	}

	public CategoriaService getCategoriaService() {
		return categoriaService;
	}

	public void setCategoriaService(CategoriaService categoriaService) {
		this.categoriaService = categoriaService;
	}

	public GerenciadorCoresButton getGerenciadorCoresButton() {
		return gerenciadorCoresButton;
	}

	public void setGerenciadorCoresButton(
			GerenciadorCoresButton gerenciadorCoresButton) {
		this.gerenciadorCoresButton = gerenciadorCoresButton;
	}

	public List<Chamado> getChamadoList() {
		return chamadoList;
	}

	public void setChamadoList(List<Chamado> chamadoList) {
		this.chamadoList = chamadoList;
	}

	public TecnicoService getTecnicoService() {
		return tecnicoService;
	}

	public void setTecnicoService(TecnicoService tecnicoService) {
		this.tecnicoService = tecnicoService;
	}

	public List<Tecnico> getTecnicoList() {
		return tecnicoList;
	}

	public void setTecnicoList(List<Tecnico> tecnicoList) {
		this.tecnicoList = tecnicoList;
	}

	public List<Tecnico> getTecnicoByChamadoList() {
		return tecnicoByChamadoList;
	}

	public void setTecnicoByChamadoList(List<Tecnico> tecnicoByChamadoList) {
		this.tecnicoByChamadoList = tecnicoByChamadoList;
	}

	public AcompanhamentoService getAcompanhamentoService() {
		return acompanhamentoService;
	}

	public void setAcompanhamentoService(
			AcompanhamentoService acompanhamentoService) {
		this.acompanhamentoService = acompanhamentoService;
	}

	public Acompanhamento getAcompanhamento() {
		return acompanhamento;
	}

	public void setAcompanhamento(Acompanhamento acompanhamento) {
		this.acompanhamento = acompanhamento;
	}

	public List<Acompanhamento> getAcompanhamentoList() {
		return acompanhamentoList;
	}

	public void setAcompanhamentoList(List<Acompanhamento> acompanhamentoList) {
		this.acompanhamentoList = acompanhamentoList;
	}

	public Part getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(Part uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	public List<ArquivoAnexado> getArquivoAnexado() {
		return arquivoAnexado;
	}

	public void setArquivoAnexado(List<ArquivoAnexado> arquivoAnexado) {
		this.arquivoAnexado = arquivoAnexado;
	}
	
	
	public UploadedFile getFile() {
        return file;
    }
 
    public void setFile(UploadedFile file) {
    	System.out.println("CHAMDOU");
        this.file = file;
    }
     
    public void upload() {
        if(file != null) {
            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
     
    public void handleFileUpload(FileUploadEvent event) {
    	anexarArquivos(event.getFile());
    	System.out.println("ok!!!");
    
    }

	public StreamedContent getFileStreamed() {
		return fileStreamed;
	}

	public void setFileStreamed(StreamedContent fileStreamed) {
		this.fileStreamed = fileStreamed;
	}

	public boolean isNewChamado() {
		return newChamado;
	}

	public void setNewChamado(boolean newChamado) {
		this.newChamado = newChamado;
	}

	public Integer getIdChamadoSelecionado() {
		return idChamadoSelecionado;
	}

	public void setIdChamadoSelecionado(Integer idChamadoSelecionado) {
		this.idChamadoSelecionado = idChamadoSelecionado;
	}

	public NotificacaoService getNotificacaoService() {
		return notificacaoService;
	}

	public void setNotificacaoService(NotificacaoService notificacaoService) {
		this.notificacaoService = notificacaoService;
	}

	public ArquivoAnexadoService getArquivoAnexadoService() {
		return arquivoAnexadoService;
	}

	public void setArquivoAnexadoService(ArquivoAnexadoService arquivoAnexadoService) {
		this.arquivoAnexadoService = arquivoAnexadoService;
	}

	public ObservadorService getObservadorService() {
		return observadorService;
	}

	public void setObservadorService(ObservadorService observadorService) {
		this.observadorService = observadorService;
	}

	public boolean isChamadoComSolucao() {
		return chamadoComSolucao;
	}

	public void setChamadoComSolucao(boolean chamadoComSolucao) {
		this.chamadoComSolucao = chamadoComSolucao;
	}

	public List<SolucaoChamado> getSolucaoChamado() {
		return solucaoChamado;
	}

	public void setSolucaoChamado(List<SolucaoChamado> solucaoChamado) {
		this.solucaoChamado = solucaoChamado;
	}

	public SolucaoChamadoService getSolucaoChamadoService() {
		return solucaoChamadoService;
	}

	public void setSolucaoChamadoService(SolucaoChamadoService solucaoChamadoService) {
		this.solucaoChamadoService = solucaoChamadoService;
	}

	public boolean isNewChamadoFechado() {
		return newChamadoFechado;
	}

	public void setNewChamadoFechado(boolean newChamadoFechado) {
		this.newChamadoFechado = newChamadoFechado;
	}

	public Integer getVotoUsuario() {
		return votoUsuario;
	}

	public void setVotoUsuario(Integer votoUsuario) {
		this.votoUsuario = votoUsuario;
	}

	public boolean isExibeSolucaoChamado() {
		return exibeSolucaoChamado;
	}

	public void setExibeSolucaoChamado(boolean exibeSolucaoChamado) {
		this.exibeSolucaoChamado = exibeSolucaoChamado;
	}

	public List<SolucaoChamado> getSolucaoChamadoList() {
		return solucaoChamadoList;
	}

	public void setSolucaoChamadoList(List<SolucaoChamado> solucaoChamadoList) {
		this.solucaoChamadoList = solucaoChamadoList;
	}

	public EmailService getEmail() {
		return email;
	}

	public void setEmail(EmailService email) {
		this.email = email;
	}

	public DesaprovacaoSolucaoChamado getDesaprovacaoSolucaoChamado() {
		return desaprovacaoSolucaoChamado;
	}

	public void setDesaprovacaoSolucaoChamado(
			DesaprovacaoSolucaoChamado desaprovacaoSolucaoChamado) {
		this.desaprovacaoSolucaoChamado = desaprovacaoSolucaoChamado;
	}

	public DesaprovacaoSolucaoChamadoService getDesaprovacaoSolucaoChamadoService() {
		return desaprovacaoSolucaoChamadoService;
	}

	public void setDesaprovacaoSolucaoChamadoService(
			DesaprovacaoSolucaoChamadoService desaprovacaoSolucaoChamadoService) {
		this.desaprovacaoSolucaoChamadoService = desaprovacaoSolucaoChamadoService;
	}

	public SolucaoChamado getUltimaSolucaoChamado() {
		return ultimaSolucaoChamado;
	}

	public void setUltimaSolucaoChamado(SolucaoChamado ultimaSolucaoChamado) {
		this.ultimaSolucaoChamado = ultimaSolucaoChamado;
	}

	public String getQtdAnexos() {
		return qtdAnexos;
	}

	public void setQtdAnexos(String qtdAnexos) {
		this.qtdAnexos = qtdAnexos;
	}

	public String getQtdAcompanhamentos() {
		return qtdAcompanhamentos;
	}

	public void setQtdAcompanhamentos(String qtdAcompanhamentos) {
		this.qtdAcompanhamentos = qtdAcompanhamentos;
	}
	
	
}

