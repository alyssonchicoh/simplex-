package br.com.cogerh.template.graphics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

public class ModeloGraficoChartJS {
	
	private List<String> labels;
	private List<ObjetoModeloGraficoChartJS> objetos;
	
	
	public ModeloGraficoChartJS(){
		this.labels = new ArrayList<String>();
		this.objetos = new ArrayList<ObjetoModeloGraficoChartJS>();
	}
	
	public void addLabels(String label){
		labels.add(label);
	}
	
	public void addObjetos(ObjetoModeloGraficoChartJS obj){
		objetos.add(obj);
	}
	
	public String gerar(){
		Gson gson = new Gson();
		return gson.toJson(this);
	}
}
