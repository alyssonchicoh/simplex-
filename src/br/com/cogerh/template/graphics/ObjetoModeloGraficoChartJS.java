package br.com.cogerh.template.graphics;

import java.util.List;

public class ObjetoModeloGraficoChartJS {

	private String label;
	private List<Double> data;
	private String backgroundColor;
	private String borderColor;
	private String fill;
	
	
	public ObjetoModeloGraficoChartJS(String label, List<Double> data,String backgroundColor, String borderColor) {
		this.label = label;
		this.data = data;
		this.backgroundColor = backgroundColor;
		this.borderColor = borderColor;
		fill = "false";
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	public String getBackgroundColor() {
		return backgroundColor;
	}
	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	public String getBorderColor() {
		return borderColor;
	}
	public void setBorderColor(String borderColor) {
		this.borderColor = borderColor;
	}
	public List<Double> getData() {
		return data;
	}
	public void setData(List<Double> data) {
		this.data = data;
	}
	
	
	
	
}
