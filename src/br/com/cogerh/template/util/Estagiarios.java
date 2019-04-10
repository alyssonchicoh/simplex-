package br.com.cogerh.template.util;

public class Estagiarios {
	public static void main(String[] args) {
		Integer[] valores = new Integer[6];
		valores[0] = 3;
		valores[1] = 2;
		valores[2] = 25;
		valores[3] = 25;
		valores[4] = 12;
		valores[5] = 1;
		Integer[] aux = new Integer[6];
		Integer[] novo = new Integer[6];

		
		
	for (int i = 0; i < 5; i++) {
		for (int d = 0; d < 5; d++) {
			if(valores[i] > valores[d]){
				aux[i] = valores[d];
				valores[i] = aux[i];
			}
			novo[i] = valores[i];
		}
	}
	
	for (int i = 0; i < novo.length; i++) {
		System.out.println(novo[i]);
	}
	
	}
	
}
