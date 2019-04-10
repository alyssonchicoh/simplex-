package br.com.cogerh.template.util;

import java.util.Date;
import java.util.List;

import br.com.cogerh.template.model.Chamado;

/**
 * Método utilizado para calcular a posição do chamado de forma automatica.
 * @author alyssonnascimento
 */
public class CalculadoraPosicaoChamado {
	
	public static final String TIPO_PRIORIDADE_BAIXA = "PRIORIDADE_BAIXA"; 
	public static final String TIPO_PRIORIDADE_MEDIA = "PRIORIDADE_MEDIA"; 
	public static final String TIPO_PRIORIDADE_ALTA  = "PRIORIDADE_ALTA"; 
	
	private static final Integer SCORE_PRIORIDADE_BAIXA = 10;
	private static final Integer SCORE_PRIORIDADE_MEDIA = 20;
	private static final Integer SCORE_PRIORIDADE_ALTA = 30;
	private static final Integer SCORE_RECORRENTE = 30;
	private static final Integer SCORE_TRABALHOS_PARADOS = 30;
	
	/**
	 * LISTA COM OS CHAMADOS DO TÉCNICO
	 */
	private List<Chamado> chamados;
	
	public CalculadoraPosicaoChamado(){
		
	}
	
	public CalculadoraPosicaoChamado(List<Chamado> chamados){
		this.chamados = chamados;
	}
	
	public Integer calcularScoresChamado(String prioridade, boolean isRecorrente, boolean isTrabalhoParado){
		Integer score = 0;

		switch (prioridade) {
			case TIPO_PRIORIDADE_BAIXA:
				score = score + SCORE_PRIORIDADE_BAIXA;
			break;
			
			case TIPO_PRIORIDADE_MEDIA:
				score = score + SCORE_PRIORIDADE_MEDIA;
			break;
			
			case TIPO_PRIORIDADE_ALTA:
				score = score + SCORE_PRIORIDADE_ALTA;
			break;
		}
		
		if(isRecorrente){
			score = score + SCORE_RECORRENTE;
		}
		
		if(isTrabalhoParado){
			score = score + SCORE_TRABALHOS_PARADOS;
		}
			
		return score;		
	}
	
	public Integer calcularPosicao(Integer score){
		Integer posicao;
		
		//INICIALMENTE, NOSSA POSIÇÃO SERÁ A ULTIMO DA FILA
		posicao = chamados.size() + 1;
		
		int auxPosicaoFila = 1;
		boolean verificaoSemIgualdade = true;
		
		for (Chamado chamado : chamados) {
			
			if(verificaoSemIgualdade){
				if(score > chamado.getScoreFila()){
					posicao = auxPosicaoFila;
					System.out.println("DIFERENTE, POSIÇÃO:" + posicao);
					break;
					
				}
			}
			
			if(score.equals(chamado.getScoreFila())){
				posicao = auxPosicaoFila + 1;
				System.out.println("IGUAL, POSIÇÃO:" + posicao);
				verificaoSemIgualdade = false;
			}
			
			auxPosicaoFila++;
		}
		
		return posicao;
	}
	

	
	
}
