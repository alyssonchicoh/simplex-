package br.com.cogerh.template.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TableColorUtil {
	
	private static List<Integer> indices = new ArrayList<Integer>();
	
	private static final String AZUL = "#116EC0";
	private static final String VERDE = "#228B22";
	private static final String OURO = "#DAA520";
	private static final String AMARELO = "#FFFF00";
	private static final String CINZA = "#E8E8E8";
	private static final String VERMELHO = "#CD2626";
	private static final String SALMAO = "#FA8072";
	private static final String ROSA = "#FFB6C1";
	private static final String LARANJA = "#FF8C00";
	private static final String PRETO = "#000000";
	
	public static String sortearCor(){
		Random gerador = new Random();
		Integer numero = gerador.nextInt(10);
		boolean repete = verificaExistenciaLista(numero);
		
		while (repete){
			 numero = gerador.nextInt(10);
			 repete = verificaExistenciaLista(numero);
		}
		
		
		if(numero.equals(0)){
			return AZUL;
		}else if(numero.equals(1)){
			return VERDE;
		}else if(numero.equals(2)){
			return OURO;
		}else if(numero.equals(3)){
			return AMARELO;
		}else if(numero.equals(4)){
			return CINZA;
		}else if(numero.equals(5)){
			return VERMELHO;
		}else if(numero.equals(6)){
			return SALMAO;
		}else if(numero.equals(7)){
			return ROSA;
		}else if(numero.equals(8)){
			return LARANJA;
		}else if(numero.equals(9)){
			return PRETO;
		}
		
		return "";
	}
	
	public static boolean verificaExistenciaLista(Integer indice){
		for (Integer i : indices) {
			if(i.equals(indice)){
				return true;
			}
		}
		indices.add(indice);
		return false;
	}
	
	public static void clean(){
		indices.clear();
	}
	
	public static List<String> obterPack(Integer tamanho){
		List<String> listaCores = new ArrayList<String>();
		listaCores.add(AZUL);
		listaCores.add(VERMELHO);
		listaCores.add(VERDE);
		listaCores.add(OURO);
		listaCores.add(PRETO);
		listaCores.add(AMARELO);
		listaCores.add(CINZA);
		listaCores.add(SALMAO);
		listaCores.add(ROSA);
		listaCores.add(LARANJA);

		Collections.shuffle(listaCores);

		
		List<String> listaMisturados = new ArrayList<String>();
		for (int i = 0; i < tamanho; i++) {
			listaMisturados.add(listaCores.get(i));
		}
		
		return listaMisturados;
	}
	
	



}
