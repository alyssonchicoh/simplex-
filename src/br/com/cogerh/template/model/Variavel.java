package br.com.cogerh.template.model;

public class Variavel {

	private Operacao operacao;
	
	private Double valor;
	
	private String nome;
	
	private Boolean visivel;

	
	public Variavel(Operacao operacao,Double valor,String nome,boolean isVisible) {
		this.operacao = operacao;
		this.valor = valor;
		this.nome = nome;
		this.visivel = isVisible;
	}
	
	public Variavel(String nome,boolean isVisible) {
		this.nome = nome;
		this.visivel = isVisible;
	}
	
	public Operacao getOperacao() {
		return operacao;
	}

	public void setOperacao(Operacao operacao) {
		this.operacao = operacao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String obterValorLiteral() {
		Double v = this.valor == null ? 0.0 : this.valor;
		return operacao.getTipo() + v + this.nome;

	}

	public Boolean getVisivel() {
		return visivel;
	}

	public void setVisivel(Boolean visivel) {
		this.visivel = visivel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((operacao == null) ? 0 : operacao.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
		result = prime * result + ((visivel == null) ? 0 : visivel.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		Variavel v = (Variavel) obj;
		if(this.nome.equals(v.getNome())){
			return true;
		}else {
			return false;
		}
	}
	
	
	
}
