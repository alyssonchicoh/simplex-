package br.com.cogerh.template.graphics;

import java.util.ArrayList;
import java.util.List;

import org.primefaces.component.chart.renderer.PieRenderer;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

public class PieChart {
	
	private PieChartModel pieChartModel;
	private List<BarModel> barModelList;
	private Integer ano;
	private Double faturado;
	private Double arrecadado;
	
	public PieChart(Double faturado,Double arrecadado,Integer ano){
		pieChartModel = new PieChartModel();
		this.barModelList = barModelList;
		this.faturado = faturado;
		this.arrecadado = arrecadado;
		this.ano = ano;
	}
	
	public PieChartModel create(){
		Double arrecadadoTemp = calculaPorcentagem(faturado, arrecadado);
		Double faturadoTemp = 100 - arrecadadoTemp ;
		
		pieChartModel.set("ARRECADADO",arrecadadoTemp);
		pieChartModel.set("FATURADO SEM ARRECADADO",faturadoTemp);
		pieChartModel.setSeriesColors("548B54,6CA6CD");

		pieChartModel.setShowDataLabels(true);
			
		
		
		pieChartModel.setTitle(ano.toString());
		pieChartModel.setLegendPosition("T");
		
		return pieChartModel;
	}

	
	public Double calculaPorcentagem(Double total,Double parcial){
		Double m = parcial * 100;
		Double res = (double) (m/total);
		return res;
	}
	

	
}
