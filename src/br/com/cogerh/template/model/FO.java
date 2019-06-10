package br.com.cogerh.template.model;

import java.util.ArrayList;
import java.util.List;

public class FO {
	
	private TipoFO tipo;
	
	private List<Variavel> variaveis;
	
	public FO(TipoFO tipo) {
		this.tipo = tipo;
		variaveis = new ArrayList<Variavel>();
	}
	
	public void addVariavel(Variavel variavel) {
		this.variaveis.add(variavel);
	}
	
	
	class TipoFO{
		public static final String MAX = "MAX";
		public static final String MIN = "MIN";
	}

}
