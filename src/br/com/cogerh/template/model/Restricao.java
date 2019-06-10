package br.com.cogerh.template.model;

import java.util.List;

public class Restricao {

	private List<Variavel> variaveis;
	
	private Double valorDireto;
	
	private String tipoRelacao;

	private String folga;
	
	
	public List<Variavel> getVariaveis() {
		return variaveis;
	}

	public void setVariaveis(List<Variavel> variaveis) {
		this.variaveis = variaveis;
	}

	public Double getValorDireto() {
		return valorDireto;
	}

	public void setValorDireto(Double valorDireto) {
		this.valorDireto = valorDireto;
	}

	public String getTipoRelacao() {
		return tipoRelacao;
	}

	public void setTipoRelacao(String tipoRelacao) {
		this.tipoRelacao = tipoRelacao;
	}

	public String getFolga() {
		return folga;
	}

	public void setFolga(String folga) {
		this.folga = folga;
	}
	
	
}
