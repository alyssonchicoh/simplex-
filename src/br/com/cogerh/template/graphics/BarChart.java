package br.com.cogerh.template.graphics;

import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

public class BarChart {
	
	private BarChartModel barModel;
	private List<BarModel> barModelList;
	
	public BarChart(List<BarModel> barModelList){
		barModel = new BarChartModel();
		this.barModelList = barModelList;
	}
	
	public BarChartModel create(){
		for(BarModel bar : barModelList){
			ChartSeries charts = new ChartSeries();
			charts.setLabel(bar.getLabel());
			
			for (String chave : bar.getValores().keySet()) {
				if(chave != null){
					
					
					charts.set(chave, Double.valueOf(bar.getValores().get(chave)));
					
				}
			}
			
			barModel.addSeries(charts);
			
		}
		barModel.setSeriesColors("6CA6CD,548B54");

		barModel.setLegendPosition("e");
		
		return barModel;
	}

	public void toJson() {
		// TODO Auto-generated method stub
		
	}

	
	
}
