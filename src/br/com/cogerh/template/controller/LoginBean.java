package br.com.cogerh.template.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.cogerh.template.enumeration.EnumAtivo;
import br.com.cogerh.template.model.Grupo;
import br.com.cogerh.template.model.GrupoPermissao;
import br.com.cogerh.template.model.Permissao;
import br.com.cogerh.template.model.Usuario;
import br.com.cogerh.template.service.Autenticador;
import br.com.cogerh.template.service.GrupoService;
import br.com.cogerh.template.service.PermissaoService;
import br.com.cogerh.template.service.UsuarioService;
import br.com.cogerh.template.util.FacesUtil;
import br.com.cogerh.template.util.GeradorPermissao;

@Controller
@Scope("request")
public class LoginBean extends AbstractBean
{
	private String login;
	private String senha;
	
	private UsuarioWeb usuarioWeb;
	private Autenticador autenticador;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private GrupoService grupoService;
	@Autowired
	private PermissaoService permisaoService;
	
	@Autowired
	private FacesUtil faces;
	
	private boolean exibirRecuperarSenha;
	
	@Autowired
	public LoginBean(Autenticador aut, UsuarioWeb usuWeb)
	{
		this.autenticador = aut;
		this.usuarioWeb = usuWeb;
	}
	
	public String logar()
	{
		criarUsuarios();
		Usuario usuario = autenticador.autentica(login, faces.convertStringToMd5(senha));
		
		System.out.println(faces.convertStringToMd5(senha));
		
		if (usuario != null)
		{
			if (usuario.getAtivo().equals(EnumAtivo.SIM))
			{
				usuarioWeb.loga(usuario);
				return "principal?faces-redirect=true";
			}
			else
			{
				this.adicionaMensagemDeErro("O Usuário informado está inativo");

				return null;
			}
		}

		this.adicionaMensagemDeErro("Login e/ou Senha inválidos");

		return null;
	}
	
	public String deslogar()
	{
		usuarioWeb.desloga();

		return "/login?faces-redirect=true";
	}

	
	public String lerArquivo(){
		File dir = new File("E:\\Arquivo");
	    File arq = new File(dir, "oi.txt");
	    String retorno = "";
	    try {
	        //Indicamos o arquivo que será lido
	        FileReader fileReader = new FileReader(arq);

	        //Criamos o objeto bufferReader que nos
	        // oferece o método de leitura readLine()
	        BufferedReader bufferedReader = new BufferedReader(fileReader);

	        //String que irá receber cada linha do arquivo
	        String linha = "";

	        //Fazemos um loop linha a linha no arquivo,
	        // enquanto ele seja diferente de null.
	        //O método readLine() devolve a linha na
	        // posicao do loop para a variavel linha.
	        while ( ( linha = bufferedReader.readLine() ) != null) {
	        //Aqui imprimimos a linha
	        	retorno = retorno + linha +"\n";
	        
	    }
			//liberamos o fluxo dos objetos ou fechamos o arquivo
	        fileReader.close();
	        bufferedReader.close();
		} catch (IOException e) {
	    	e.printStackTrace();
	    }
	    return retorno;
	}
	
	public String lerScript(){
		File dir = new File("E:\\Arquivo");
	    File arq = new File(dir, "script.txt");
	    String retorno = "";
	    try {
	        //Indicamos o arquivo que será lido
	        FileReader fileReader = new FileReader(arq);

	        //Criamos o objeto bufferReader que nos
	        // oferece o método de leitura readLine()
	        BufferedReader bufferedReader = new BufferedReader(fileReader);

	        //String que irá receber cada linha do arquivo
	        String linha = "";

	        //Fazemos um loop linha a linha no arquivo,
	        // enquanto ele seja diferente de null.
	        //O método readLine() devolve a linha na
	        // posicao do loop para a variavel linha.
	        while ( ( linha = bufferedReader.readLine() ) != null) {
	        //Aqui imprimimos a linha
	        	retorno = retorno + linha +"\n";
	        
	    }
			//liberamos o fluxo dos objetos ou fechamos o arquivo
	        fileReader.close();
	        bufferedReader.close();
		} catch (IOException e) {
	    	e.printStackTrace();
	    }
	    return retorno;
	}

	
	
	public String montarJS(String arquivo){
		StringBuilder js = new StringBuilder();
	//	js.append("window.diagramUrl = '"+arquivo+"';" +"/n");
		
	//	js.append(lerScript());
		
		js.append("winwos.diagramUrl = 'diagram.bpmn'");


		
	
		return js.toString();
	}
	
	public void criarUsuarios(){
		try {
			if( usuarioService.listarUsuarios(null).size() == 0){
			//	GeradorPermissao geradorPermisaso = new GeradorPermissao(permisaoService);
			//	geradorPermisaso.salvar();
				Usuario usuario = new Usuario();
				usuario.setAtivo(EnumAtivo.SIM);
				usuario.setLogin("606.440.843-08");
				usuario.setNome("Alysson Nascimento");
				usuario.setEmail("alysson.nascimento@cogerh.com.br");
				usuario.setSenha(faces.convertStringToMd5("123456"));
				
				
				Grupo grupoObservador = new Grupo();
				grupoObservador.setNome("observadores");
				grupoService.salvarGrupo(grupoObservador);
				
				Grupo grupoUsuario = new Grupo();
				grupoUsuario.setNome("usuarios");
				grupoService.salvarGrupo(grupoUsuario);
				usuario.setGrupo(grupoUsuario);
				
				Usuario observador = new Usuario();
				observador.setAtivo(EnumAtivo.SIM);
				observador.setLogin("observador");
				observador.setNome("Observador");
				observador.setSenha(faces.convertStringToMd5("123456"));
				observador.setGrupo(grupoObservador);
				observador.setEmail("alysson.nascimento@cogerh.com.br");
				usuarioService.salvarUsuario(usuario);
				usuarioService.salvarUsuario(observador);

				
				Usuario tecnico = new Usuario();
				tecnico.setAtivo(EnumAtivo.SIM);
				tecnico.setLogin("tecnico");
				tecnico.setNome("Tecnico");
				tecnico.setSenha(faces.convertStringToMd5("123456"));
				tecnico.setEmail("alysson.nascimento@cogerh.com.br");
				usuarioService.salvarUsuario(tecnico);

				Usuario admin = new Usuario();
				admin.setAtivo(EnumAtivo.SIM);
				admin.setLogin("admin");
				admin.setNome("Admin");
				admin.setSenha(faces.convertStringToMd5("123456"));
				
				
				
				/**
				 
				
				Grupo tecnicos = new Grupo();
				grupo.setDescricao("Tecnicos");
				
				Grupo usuarios = new Grupo();
				grupo.setDescricao("Usuarios");
				
				//PERMISSAO PARA INCLUIR UM TECNICO
				Permissao incluir_tecnico = new Permissao();
				incluir_tecnico.setDescricao("[OBSERVADOR] Incluir_Tecnicos_Chamado");
				
				Permissao all_observador = new Permissao();
				incluir_tecnico.setDescricao("[OBSERVADOR] all");
				
			
				
				GrupoPermissao  gp = new GrupoPermissao();
				gp.setGrupo(grupo);
				gp.setPermissao(incluir_tecnico);
				
				GrupoPermissao  gp2 = new GrupoPermissao();
				gp.setGrupo(grupo);
				gp.setPermissao(all_observador);
				
				List<GrupoPermissao> lista = new ArrayList<GrupoPermissao>();
				lista.add(gp);
				lista.add(gp2);

				grupo.setGrupoPermissaoList(lista);
				incluir_tecnico = permisaoService.salvarPermissao(incluir_tecnico);
				all_observador = permisaoService.salvarPermissao(all_observador);

				grupo = grupoService.salvarGrupo(grupo);
				usuario.setGrupo(grupo);
				observador.setGrupo(grupo);
				usuarioService.salvarUsuario(usuario);
				usuarioService.salvarUsuario(observador);
				* 
				 */

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public String getLogin() {return login;}
	public void setLogin(String login) {this.login = login;}

	public String getSenha() {return senha;}
	public void setSenha(String senha) {this.senha = senha;}

	public UsuarioWeb getUsuarioWeb() {return usuarioWeb;}
	public void setUsuarioWeb(UsuarioWeb usuarioWeb) {this.usuarioWeb = usuarioWeb;}

	public Autenticador getAutenticador() {return autenticador;}
	public void setAutenticador(Autenticador autenticador) {this.autenticador = autenticador;}

	public boolean isExibirRecuperarSenha() {
		return exibirRecuperarSenha;
	}

	public void setExibirRecuperarSenha(boolean exibirRecuperarSenha) {
		this.exibirRecuperarSenha = exibirRecuperarSenha;
	}
	
	
}