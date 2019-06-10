package br.com.cogerh.template.model;

import java.util.List;

public class Base {

	private Integer qtdRestricoes;
	
	public Base(Integer qtdRestricoes) {
		this.qtdRestricoes = qtdRestricoes;
	}
	
	
	
	

	public boolean verificarBase100(List<String> valores) {
		boolean existe = false;
		if(valores != null) {
			if(valores.get(0).equals("1")) {
				existe = true;
			}
			
			int i = 0;
			for (String v : valores) {
				if(i != 0) {
					if(v.equals("0")) {
						existe = true;
					}else {
						return false;
					}
				}
				i++;
			}
		}
		return existe;
	}
	
	public void verificarBase010() {
		
	}
	
	public void verificarBase001() {
		
	}
	
}
