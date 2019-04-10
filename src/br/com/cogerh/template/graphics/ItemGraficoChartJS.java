package br.com.cogerh.template.graphics;

import java.util.ArrayList;
import java.util.List;

public class ItemGraficoChartJS {

	private String nome;
	private List<String> chaves;
	private List<Double> valores;
	private String backgroundColor;
	private String borderColor;
	
	public ItemGraficoChartJS(String nome, String backgroundColor,String borderColor){
		this.nome = nome;
		this.chaves = new ArrayList<String>();
		this.valores = new ArrayList<Double>();
		this.backgroundColor = backgroundColor;
		this.borderColor = borderColor;
	}
	
	public void add(String chave,Double valor){
		chaves.add(chave);
		valores.add(valor);
	}

	
	public String getNome() {
		return nome;
	}

	public List<String> getChaves() {
		return chaves;
	}



	public String getBackgroundColor() {
		return backgroundColor;
	}

	

	public String getBorderColor() {
		return borderColor;
	}

	public List<Double> getValores() {
		return valores;
	}

	public void setValores(List<Double> valores) {
		this.valores = valores;
	}

	
	
	
	
}
