package br.com.cogerh.template.util;

import javax.faces.component.html.HtmlCommandButton;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

public class GerenciadorCoresButton {

	// VARIAVEIS PARA CONTROLAR OS BOTÕES DA TELA DE NOVO CHAMADO
	private HtmlCommandButton prioridadeBaixaButton;
	private HtmlCommandButton prioridadeMediaButton;
	private HtmlCommandButton prioridadeAltaButton;
	private HtmlCommandButton informadoNaoButton;
	private HtmlCommandButton informadoSimButton;
	private HtmlCommandButton retroativoNaoButton;
	private HtmlCommandButton retroativoSimButton;
	private HtmlCommandButton trabalhosParadosNaoButton;
	private HtmlCommandButton trabalhosParadosSimButton;
	private HtmlCommandButton recorrenteNaoButton;
	private HtmlCommandButton recorrenteSimButton;	
	private static final String GRUPO_PRIORIDADE = "prioridade";
	private static final String GRUPO_INFORMADO = "prioridade";
	private static final String GRUPO_RETROATIVO = "prioridade";
	private static final String GRUPO_TRABALHOS_PARADOS = "prioridade";
	private static final String GRUPO_RECORRENTE = "prioridade";
	private static final String CLASS_CSS_DEFAULT = "btn btn-default btn-xs";
	private static final String CLASS_CSS_DANGER = "btn btn-danger btn-xs";
	private static final String CLASS_CSS_PRIMARY = "btn btn-primary btn-xs";
	private static final String CLASS_CSS_SUCESS = "btn btn-success btn-xs";
	


	public void atualizarCoresPrioridade(Integer valor) {
			prioridadeBaixaButton.setStyleClass(CLASS_CSS_DEFAULT);
			prioridadeMediaButton.setStyleClass(CLASS_CSS_DEFAULT);
			prioridadeAltaButton.setStyleClass(CLASS_CSS_DEFAULT);
			
		if (valor.equals(1)) {
			prioridadeBaixaButton.setStyleClass(CLASS_CSS_SUCESS);
			prioridadeMediaButton.setStyleClass(CLASS_CSS_DEFAULT);
			prioridadeAltaButton.setStyleClass(CLASS_CSS_DEFAULT);

		} else if (valor.equals(2)) {
			prioridadeBaixaButton.setStyleClass(CLASS_CSS_DEFAULT);
			prioridadeMediaButton.setStyleClass(CLASS_CSS_SUCESS);
			prioridadeAltaButton.setStyleClass(CLASS_CSS_DEFAULT);
		} else if (valor.equals(3)) {
			prioridadeBaixaButton.setStyleClass(CLASS_CSS_DEFAULT);
			prioridadeMediaButton.setStyleClass(CLASS_CSS_DEFAULT);
			prioridadeAltaButton.setStyleClass(CLASS_CSS_SUCESS);

		}
	}
	
	public void atualizarCoresInformado(boolean valor){
		if(valor){
			informadoSimButton.setStyleClass(CLASS_CSS_SUCESS);
			informadoNaoButton.setStyleClass(CLASS_CSS_DEFAULT);

		}else{
			informadoSimButton.setStyleClass(CLASS_CSS_DEFAULT);
			informadoNaoButton.setStyleClass(CLASS_CSS_SUCESS);
		}
	}
	
	public void atualizarCoresRetroativo(boolean valor){
		
		if(valor){
			retroativoSimButton.setStyleClass(CLASS_CSS_SUCESS);
			retroativoNaoButton.setStyleClass(CLASS_CSS_DEFAULT);

		}else{
			retroativoSimButton.setStyleClass(CLASS_CSS_DEFAULT);
			retroativoNaoButton.setStyleClass(CLASS_CSS_SUCESS);			
		}		
	}
	
	public void atualizarCoresTrabalhosParados(boolean valor){
		if(valor){
			trabalhosParadosSimButton.setStyleClass(CLASS_CSS_SUCESS);
			trabalhosParadosNaoButton.setStyleClass(CLASS_CSS_DEFAULT);

		}else{
			trabalhosParadosSimButton.setStyleClass(CLASS_CSS_DEFAULT);
			trabalhosParadosNaoButton.setStyleClass(CLASS_CSS_SUCESS);
		}
	}
	
	public void atualizarCoresRecorrente(boolean valor){
		if(valor){
			recorrenteSimButton.setStyleClass(CLASS_CSS_SUCESS);
			recorrenteNaoButton.setStyleClass(CLASS_CSS_DEFAULT);

		}else{
			recorrenteSimButton.setStyleClass(CLASS_CSS_DEFAULT);
			recorrenteNaoButton.setStyleClass(CLASS_CSS_SUCESS);
		}
	}


	public HtmlCommandButton getPrioridadeBaixaButton() {
		return prioridadeBaixaButton;
	}



	public void setPrioridadeBaixaButton(HtmlCommandButton prioridadeBaixaButton) {
		this.prioridadeBaixaButton = prioridadeBaixaButton;
	}



	public HtmlCommandButton getPrioridadeMediaButton() {
		return prioridadeMediaButton;
	}



	public void setPrioridadeMediaButton(HtmlCommandButton prioridadeMediaButton) {
		this.prioridadeMediaButton = prioridadeMediaButton;
	}



	public HtmlCommandButton getPrioridadeAltaButton() {
		return prioridadeAltaButton;
	}



	public void setPrioridadeAltaButton(HtmlCommandButton prioridadeAltaButton) {
		this.prioridadeAltaButton = prioridadeAltaButton;
	}



	public HtmlCommandButton getInformadoNaoButton() {
		return informadoNaoButton;
	}



	public void setInformadoNaoButton(HtmlCommandButton informadoNaoButton) {
		this.informadoNaoButton = informadoNaoButton;
	}



	public HtmlCommandButton getInformadoSimButton() {
		return informadoSimButton;
	}



	public void setInformadoSimButton(HtmlCommandButton informadoSimButton) {
		this.informadoSimButton = informadoSimButton;
	}



	public HtmlCommandButton getRetroativoNaoButton() {
		return retroativoNaoButton;
	}



	public void setRetroativoNaoButton(HtmlCommandButton retroativoNaoButton) {
		this.retroativoNaoButton = retroativoNaoButton;
	}



	public HtmlCommandButton getRetroativoSimButton() {
		return retroativoSimButton;
	}



	public void setRetroativoSimButton(HtmlCommandButton retroativoSimButton) {
		this.retroativoSimButton = retroativoSimButton;
	}



	public HtmlCommandButton getTrabalhosParadosNaoButton() {
		return trabalhosParadosNaoButton;
	}



	public void setTrabalhosParadosNaoButton(HtmlCommandButton trabalhosParadosNaoButton) {
		this.trabalhosParadosNaoButton = trabalhosParadosNaoButton;
	}



	public HtmlCommandButton getTrabalhosParadosSimButton() {
		return trabalhosParadosSimButton;
	}



	public void setTrabalhosParadosSimButton(HtmlCommandButton trabalhosParadosSimButton) {
		this.trabalhosParadosSimButton = trabalhosParadosSimButton;
	}



	public HtmlCommandButton getRecorrenteNaoButton() {
		return recorrenteNaoButton;
	}



	public void setRecorrenteNaoButton(HtmlCommandButton recorrenteNaoButton) {
		this.recorrenteNaoButton = recorrenteNaoButton;
	}



	public HtmlCommandButton getRecorrenteSimButton() {
		return recorrenteSimButton;
	}



	public void setRecorrenteSimButton(HtmlCommandButton recorrenteSimButton) {
		this.recorrenteSimButton = recorrenteSimButton;
	}



	public static String getGrupoPrioridade() {
		return GRUPO_PRIORIDADE;
	}



	public static String getGrupoInformado() {
		return GRUPO_INFORMADO;
	}



	public static String getGrupoRetroativo() {
		return GRUPO_RETROATIVO;
	}



	public static String getGrupoTrabalhosParados() {
		return GRUPO_TRABALHOS_PARADOS;
	}



	public static String getGrupoRecorrente() {
		return GRUPO_RECORRENTE;
	}



	public static String getClassCssDefault() {
		return CLASS_CSS_DEFAULT;
	}



	public static String getClassCssDanger() {
		return CLASS_CSS_DANGER;
	}



	public static String getClassCssPrimary() {
		return CLASS_CSS_PRIMARY;
	}



	public static String getClassCssSucess() {
		return CLASS_CSS_SUCESS;
	}

	
	
}
