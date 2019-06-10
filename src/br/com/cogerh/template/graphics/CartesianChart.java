package br.com.cogerh.template.graphics;

import java.util.List;

import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.BarChartSeries;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

public class CartesianChart {

	private CartesianChartModel cartesianChartModel;
	private List<BarModel> barModelList;
	
	public CartesianChartModel create(){
		for(BarModel bar : barModelList){
			ChartSeries charts = new ChartSeries();
			charts.setLabel(bar.getLabel());
			
			for (String chave : bar.getValores().keySet()) {
				if(chave != null){
					
					
					charts.set(chave, Double.valueOf(bar.getValores().get(chave)));
					
				}
			}
			
			cartesianChartModel.addSeries(charts);
			
		}
		cartesianChartModel.setSeriesColors("6CA6CD,548B54");

		
		cartesianChartModel.setLegendPosition("e");
		
		return cartesianChartModel;
	}
	
	public CartesianChart(List<BarModel> barModelList){
		cartesianChartModel = new CartesianChartModel();
		this.barModelList = barModelList;
		

		
       
		
	}
	
	
	
}
