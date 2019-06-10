package br.com.cogerh.template.graphics;

import java.util.ArrayList;
import java.util.List;

public class GeradorGraficoChartJS {

	private List<ItemGraficoChartJS> itens;
	
	
	public GeradorGraficoChartJS(List<ItemGraficoChartJS> itens){
		this.itens = itens;
	}
	
	public String gerar(){
		List<String> labels = new ArrayList<String>();
		ModeloGraficoChartJS modelo = new ModeloGraficoChartJS();
		
		for (ItemGraficoChartJS itemGraficoChartJS : itens) {
			
			for (String i : itemGraficoChartJS.getChaves()) {
			
				if(!existeValorLista(labels, i)){
					modelo.addLabels(i);
					labels.add(i);
				}
			
			
			}
			ObjetoModeloGraficoChartJS obj = new ObjetoModeloGraficoChartJS(itemGraficoChartJS.getNome(),itemGraficoChartJS.getValores(),itemGraficoChartJS.getBackgroundColor(),itemGraficoChartJS.getBorderColor());
			modelo.addObjetos(obj);
			
			
		}
		return modelo.gerar() ;
	}
	
	
	private boolean existeValorLista(List<String> lista,String valor){
		boolean retorno = false;
		for (String l : lista) {
			if(l.equals(valor)){
				retorno = true;
				break;
			}
		}
		return retorno;
	}
}
