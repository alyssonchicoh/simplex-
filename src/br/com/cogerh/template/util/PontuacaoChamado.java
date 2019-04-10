package br.com.cogerh.template.util;

/**
 * Classe responsável por calcular a pontuação do chamado, baseado nas regras definidas
 * @author Alysson Nascimento
 * @since 29/12/2018
 *
 */
public class PontuacaoChamado {
	
	private boolean prioridadeBaixa;
	private boolean prioridadeMedia;
	private boolean prioridadeAlta;
	
	private boolean trabalhosParados;
	private boolean recorrente;
	
	private Integer notaFinal;

	//O TOTAL DO CHAMADO É 10 PONTOS
	//PRIORIDADE ALTA = 3
	//PRIORIDADE MEDIA = 2
	//PRIORIDADE BAIXA = 1
	//PRIORIDADE RECORRENTE = 2
	//PRIORIDADE TRABALHOS_PARADOS = 5
	
	public PontuacaoChamado(boolean prioridadeBaixa,boolean prioridadeMedia,boolean prioridadeAlta,boolean trabalhosParados,boolean recorrente){
		this.prioridadeAlta = prioridadeAlta;
		this.prioridadeMedia = prioridadeMedia;
		this.prioridadeBaixa = prioridadeBaixa;
		this.trabalhosParados = trabalhosParados;
		this.recorrente = recorrente;
	}

	public void calcular(){
		if(prioridadeAlta){
			notaFinal = notaFinal + 3;
		}else if(prioridadeMedia){
			notaFinal = notaFinal + 2;
		}else if(prioridadeBaixa){
			notaFinal = notaFinal + 1;
		}
		
		if(trabalhosParados){
			notaFinal = notaFinal + 5;
		}
		
		if(recorrente){
			notaFinal = notaFinal + 2;
		}
	}

	public Integer getNotaFinal() {
		return notaFinal;
	}



	
	
	

}
