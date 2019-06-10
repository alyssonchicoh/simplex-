package br.com.cogerh.template.graphics;

import java.util.HashMap;
import java.util.Map;

/**
 * Modelo para Grafico do tipo barra
 * @author alyssonnascimento
 *
 */
public class BarModel {

	
	private String label;
	private Map<String,String> valores;

	public BarModel(String label){
		this.label = label;
		valores = new HashMap<String,String>();
	}
	
	public void adicionarItem(String chave,String valor){
		valores.put(chave,valor);
	}
	
	

	public String getLabel() {
		return label;
	}

	public Map<String, String> getValores() {
		return valores;
	}
	
	
	
}
