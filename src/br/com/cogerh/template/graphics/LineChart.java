package br.com.cogerh.template.graphics;

import java.util.List;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;

public class LineChart {
	
	private LineChartModel lineChartModel;
	private List<BarModel> barModelList;
	
	public LineChartModel create(){
		for(BarModel bar : barModelList){
			ChartSeries charts = new ChartSeries();
			charts.setLabel(bar.getLabel());
			
			for (String chave : bar.getValores().keySet()) {
				if(chave != null){
					
					
					charts.set(chave, Double.valueOf(bar.getValores().get(chave)));
					
				}
			}
			
			lineChartModel.addSeries(charts);
			
		}
		lineChartModel.setSeriesColors("6CA6CD,548B54");

		lineChartModel.setLegendPosition("e");
		
		return lineChartModel;
	}
	
	public LineChart(List<BarModel> barModelList){
		lineChartModel = new LineChartModel();
		this.barModelList = barModelList;
		
		
       
		
	}
	
	

}
