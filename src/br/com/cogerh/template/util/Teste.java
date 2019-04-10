package br.com.cogerh.template.util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class Teste {
	
	private List<String> labels;
	private List<ObjetosGraficos> objetos;
	
	public Teste(){
		labels = new ArrayList<String>();
		objetos = new ArrayList<ObjetosGraficos>();
	}
	
	public void adicionar(String label){
		labels.add(label);
	}
	public void adicionarObjeto(String label,List<String> dados,String cor,String corBorda){
		ObjetosGraficos obj = new ObjetosGraficos();
		obj.setLabel(label);
		obj.setDados(dados);
		obj.setCor(cor);
		obj.setCorBorda(corBorda);
		objetos.add(obj);
	}

	public static void main(String[] args) {
		double numero= 77601136.45;
		DecimalFormat df = new DecimalFormat("#,###.00");
		double subtota=326624.4700000137;
				String sub=df.format( subtota);
				System.out.println(sub);
	}
	
}
