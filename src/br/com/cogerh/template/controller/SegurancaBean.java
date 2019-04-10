package br.com.cogerh.template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.cogerh.template.model.Grupo;
import br.com.cogerh.template.model.Usuario;
import br.com.cogerh.template.service.GrupoService;

/**
 * Bean utilizado para realizar o gerenciamento de segurança das telas do sistema.
 * @since  11/01/2019
 * @author alyssonnascimento
 *
 */
@Controller
@Scope("view")
public class SegurancaBean extends AbstractBean{
	
	private static final String NOME_GRUPO_OBSERVADOR = "observadores";
	
	
	@Autowired
	private GrupoService grupoService;
	
	@Autowired
	private UsuarioWeb usuarioWeb;
	
	/**
	 * Método responsavel para verificar se o usuário logado pertece ao grupo de observador
	 * @return boolean com a resposta da pergunta
	 */
	public boolean verificaIsObservador(){
		Usuario usuario = usuarioWeb.getUsuario();
		Grupo grupo = usuario.getGrupo();
		if(grupo != null){
			if(grupo.getNome().equals(NOME_GRUPO_OBSERVADOR)){
				return true;
			}
		}
		return false;
	}
	

}
