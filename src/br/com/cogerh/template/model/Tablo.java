package br.com.cogerh.template.model;

import java.util.ArrayList;
import java.util.List;

/**
 * CLASSE RESPONSAVEL POR REPRESENTAR O TABLO
 * @author alysson
 * @since 01/06/2019
 *
 */
public class Tablo {

	private Integer numero;
	
	private List<String> head;
	
	private List<String> preHead;

	private List<String> content;
	
	private List<List<String>> linhaContent;
	
	private List<String> linhaZ;
	
	private List<String> linhaZC;
	
	private List<Variavel> variaveis;
	
	private List<Restricao> restricoes;
	
	private List<String> folgas;
	
	private List<Variavel> base;
	
	private Integer qdtFolga;
	
	private Integer tipo;
	
	private boolean solucaoOtima;

	public Tablo(List<Variavel> variaveis,List<Restricao> restricaos,
				List<Variavel> base,Integer qtdFolga,Integer numero,Integer tipo,List<List<String>> content,List<String> lz) {
		this.tipo = tipo;

		init();
		this.numero = numero;
		this.variaveis = variaveis;
		this.restricoes = restricaos;
		this.base = base;
		this.qdtFolga = qtdFolga;
		populaFolgas();
		
		initHead();
		initPreHead();
		if(content == null) {
			initContent();	
		}else {
			this.linhaContent = content;
		}
		
		
			initLinhaZ();

		
		initLinhaZC();
		solucaoOtima = verificaSolucaoOtima();
	}
	
	

	



	private void init() {
		head = new ArrayList<String>();
		preHead = new ArrayList<String>();
		variaveis = new ArrayList<Variavel>();
		folgas = new ArrayList<String>();
		linhaZ = new ArrayList<String>();
		linhaZC = new ArrayList<String>();
	}
	
	private void populaFolgas() {
		for (Restricao r : restricoes) {
			folgas.add(r.getFolga());
		}
	}
	
	private void initHead() {
		head.add("Cb");
		head.add("Xb");
		for (Variavel vari : variaveis) {
			head.add(vari.getNome());
		}
		head.add("B");
		
	}
	
	private void initPreHead() {
		preHead.add("");
		preHead.add("Cj");
		for (Variavel vari : variaveis) {
				preHead.add(obterValorFOByVariavel(vari.getNome()).toString());
			
		}
		preHead.add("");
	}
	
	private void initContent() {
		linhaContent = new ArrayList<List<String>>();
		int i = 0;
		for (Restricao r : restricoes) {
			List<String> content = new ArrayList<String>();
			content.add(obterValorFOByVariavel(base.get(i).getNome()).toString());
			content.add(base.get(i).getNome());
			for (Variavel v : r.getVariaveis()) {
				if(v.getValor() != null) {
					content.add(v.getValor().toString());

				}
				
			}
			
			for (String f : folgas) {
				if(r.getFolga().equals(f)) {
					content.add("1");
				}else {
					content.add("0");
				}
			}
			
			if(r.getValorDireto() != null) {
				content.add(r.getValorDireto().toString());

			}
			
			
			linhaContent.add(content);
			i++;
			
		}
	}
	
	private void initContenteExecucao() {
		linhaContent = new ArrayList<List<String>>();
		int i = 0;
		for (Restricao r : restricoes) {
			List<String> content = new ArrayList<String>();
			content.add(obterValorFOByVariavel(base.get(i).getNome()).toString());
			content.add(base.get(i).getNome());
			for (Variavel v : r.getVariaveis()) {
				if(v.getValor() != null) {
					content.add(v.getValor().toString());

				}
				
			}
			
			for (String f : folgas) {
				if(r.getFolga().equals(f)) {
					content.add("1");
				}else {
					content.add("0");
				}
			}
			
			if(r.getValorDireto() != null) {
				content.add(r.getValorDireto().toString());

			}
			
			
			linhaContent.add(content);
			i++;
			
		}
	}
	
	private void initLinhaZ() {
		
		linhaZ.add("");
		linhaZ.add("Z");
	
		Integer tamanhoContent = this.linhaContent.size();
		Integer indiceHorizontal = 2;
		//USAMOS ISSO PARA COLOCAR O VALOR DE B (QUE ENCONTRA-SE FORA DAS VARIAVEIS)
		Integer tamanhoLoop = variaveis.size() +1;
		for (int x = 0; x < tamanhoLoop; x++) {
			Double total = 0.0;
			for (int i = 0; i < tamanhoContent; i++) {
				//OBTENDO O VALOR DO CB
				Double Cb = Double.valueOf(linhaContent.get(i).get(0));
				
				//OBTENDO O VALOR DA VARIAVEL
				Double valorVariavel = Double.valueOf(linhaContent.get(i).get(indiceHorizontal));
			
				Double res = Cb * valorVariavel;
				
				total += res;
			}	
			indiceHorizontal++;

			linhaZ.add(total.toString());
		}
		
		
		
		
		/**
		 
		linhaZ.add("");
		linhaZ.add("Z");
	
		for (Variavel variavel : variaveis) {
			Double somaTotal = 0.0;
			
			for (Variavel v : base) {
				Double valorBase = obterValorFOByVariavel(v.getNome());
				Double valorVariavel = variavel.getValor();

				somaTotal = somaTotal + (valorBase * valorVariavel);
			}
			linhaZ.add(somaTotal.toString());
		}
		
		int y = 0;
		Double soma = 0.0;
		for (Restricao r : restricoes) {
			Double valorB = r.getValorDireto();
			Double valorCb = base.get(y).getValor();
			soma += (valorB * valorCb);
			y++;
		}
		linhaZ.add(soma.toString());
	
		 * 
		 */
	}
	
	private void initLinhaZC() {
		this.linhaZC.add("");
		if(tipo.equals(0)) {
			this.linhaZC.add("Z-C");
		}
		
		if(tipo.equals(1)) {
			this.linhaZC.add("C-Z");
		}
		
		for (int i = 2; i < (linhaZ.size()-1); i++) {
			Double valorZ = Double.valueOf(linhaZ.get(i));
			Double valorC = Double.valueOf(preHead.get(i));
			if(tipo.equals(0)) {
				Double total = valorZ - valorC;
				this.linhaZC.add(total.toString());	
			}
			
			if(tipo.equals(1)) {
				Double total = valorC - valorZ ;
				this.linhaZC.add(total.toString());
			}
			
			
		}
		
	}
	
	/**
	 * OBTEM O VALOR DA VARIAVEL NA FUNCAO OBJETIVO
	 */
	private Double obterValorFOByVariavel(String variavel) {
		for (Variavel v : variaveis) {
			if(v.getNome().equals(variavel)) {
				return v.getValor();
			}
		}
		return 0.0;
	}

	private boolean verificaSolucaoOtima() {
		boolean verificacao = true;
		int i = 0;
		for (String valor : linhaZC) {
			if(i >1) {
				
			
			Double vd = Double.valueOf(valor);
			if(vd > 0) {
				return false;
			}
			}
			i++;
		}
		
		return verificacao;
	}
	
	
	
	
	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public List<String> getHead() {
		return head;
	}

	public void setHead(List<String> head) {
		this.head = head;
	}

	public List<String> getPreHead() {
		return preHead;
	}

	public void setPreHead(List<String> preHead) {
		this.preHead = preHead;
	}

	public List<Variavel> getVariaveis() {
		return variaveis;
	}

	public void setVariaveis(List<Variavel> variaveis) {
		this.variaveis = variaveis;
	}

	public List<Restricao> getRestricoes() {
		return restricoes;
	}

	public void setRestricoes(List<Restricao> restricoes) {
		this.restricoes = restricoes;
	}

	public List<String> getContent() {
		return content;
	}

	public void setContent(List<String> content) {
		this.content = content;
	}

	public List<Variavel> getBase() {
		return base;
	}

	public void setBase(List<Variavel> base) {
		this.base = base;
	}

	public List<List<String>> getLinhaContent() {
		return linhaContent;
	}

	public void setLinhaContent(List<List<String>> linhaContent) {
		this.linhaContent = linhaContent;
	}



	public List<String> getLinhaZ() {
		return linhaZ;
	}



	public void setLinhaZ(List<String> linhaZ) {
		this.linhaZ = linhaZ;
	}



	public List<String> getLinhaZC() {
		return linhaZC;
	}



	public void setLinhaZC(List<String> linhaZC) {
		this.linhaZC = linhaZC;
	}



	public List<String> getFolgas() {
		return folgas;
	}



	public void setFolgas(List<String> folgas) {
		this.folgas = folgas;
	}



	public Integer getQdtFolga() {
		return qdtFolga;
	}



	public void setQdtFolga(Integer qdtFolga) {
		this.qdtFolga = qdtFolga;
	}







	public boolean isSolucaoOtima() {
		return solucaoOtima;
	}







	public void setSolucaoOtima(boolean solucaoOtima) {
		this.solucaoOtima = solucaoOtima;
	}

	
	
	
}
