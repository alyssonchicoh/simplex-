package br.com.cogerh.template.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.cogerh.template.model.Operacao;
import br.com.cogerh.template.model.Restricao;
import br.com.cogerh.template.model.Tablo;
import br.com.cogerh.template.model.Variavel;

@Component
@Scope("view")
public class ControladorBean  extends AbstractBean{
	
	private Integer qtdVariaveis;
	private Integer qtdRestricoes;
	private List<Variavel> variaveis;
	private List<Restricao> restricoes;
	private List<String> head;
	private List<String> preHead;
	private List<String> linhaZ;
	private List<String> linhaZC;
	private List<List<String>> content;
	private List<List<String>> contentAnterior;
	private boolean mostraPainel;
	private boolean mostraPainelInservaoVariaveis;
	private String valorMenorQue;
	private Integer numero;
	private List<Variavel> baseList;
	private Variavel variavelSai;
	private Variavel variavelEntra;
	private Integer colunaPivo = 0;
	private Integer linhaPivo = 0;
	private Integer tipo;
	private boolean solucaoOtima;
	
	@PostConstruct
	public void init() {
		tipo = 1;

		variaveis = new ArrayList<Variavel>();
		restricoes = new ArrayList<Restricao>();
		head = new ArrayList<String>();
		content = new ArrayList<List<String>>();
		mostraPainel = false;
		mostraPainelInservaoVariaveis = false;
		valorMenorQue = "<=";
	}
	
	public void exibirPainel() {
		mostraPainel = true;
	}
	
	public void exibirPainelVariaveis() {
		mostraPainelInservaoVariaveis = true;
		this.criarListaVariaveis();
		this.criarListaRestricoes();
	}
	
	public void definirMax() {
		this.tipo = 1;
	}
	
	public void definirMin() {
		this.tipo = 0;
	}
	
	private void criarListaVariaveis() {
		Operacao o = new Operacao();
		o.setTipo("+");
		for (int i = 1; i <=qtdVariaveis; i++) {
			Variavel v = new Variavel("X"+i,true);
			v.setOperacao(o);
			variaveis.add(v);
		}
		for (int i = 1; i <= qtdRestricoes; i++) {
			Variavel v = new Variavel("F"+i,false);
			v.setValor(0.0);
			variaveis.add(v);
		}
		
	}
	
	private void criarListaRestricoes() {
		int xFolga =1;
		Operacao o = new Operacao();
		o.setTipo("+");
		for (int i = 1; i <= qtdRestricoes; i++) {
			List<Variavel> lista = new ArrayList<Variavel>();
			Restricao r = new Restricao();
				for (int j = 1; j <=qtdVariaveis; j++) {
					Variavel v = new Variavel("X"+j,true);
					v.setOperacao(o);
					lista.add(v);	
				}
				
				
			r.setFolga("F"+xFolga);
			r.setVariaveis(lista);
			restricoes.add(r);
			xFolga++;
		}
	}
	
	public void gerar() {
		mostraPainelInservaoVariaveis = false;
		exibirPainel();

		gerarTablo();
		
	}
	public void gerarSimplex() {
		for (Variavel v : variaveis) {
			System.out.println(v.getNome() + "-"+v.getValor());
		}
		this.gerarTablo();
	}
	
	public void gerarTablo() {
		baseList = new ArrayList<Variavel>();
		for (Variavel v : variaveis) {
			if(!v.getVisivel()) {
				baseList.add(v);
			}
			
		}
		Tablo tablo = new Tablo(variaveis,restricoes,baseList,qtdRestricoes,1,tipo,null,null);

		this.head = tablo.getHead();
		this.preHead = tablo.getPreHead();
		this.content = tablo.getLinhaContent();
		this.linhaZ = tablo.getLinhaZ();
		this.linhaZC = tablo.getLinhaZC();
		this.numero = tablo.getNumero();
		this.variavelEntra = definirVariavelEntra();
		this.variavelSai = definirVariavelSai();
		this.solucaoOtima = tablo.isSolucaoOtima();

	}
	
	public void proximoTablo() {
		
		realizarPivoteamento();
		atualizarContentVariavelEntraAndSai(variavelEntra, variavelSai);
		
		
		Tablo tablo = new Tablo(variaveis,restricoes,baseList,qtdRestricoes,numero +1,tipo,content,linhaZ);
		
		this.head = tablo.getHead();
		this.preHead = tablo.getPreHead();
		this.linhaZ = tablo.getLinhaZ();
		this.linhaZC = tablo.getLinhaZC();
		this.numero = tablo.getNumero();
		
		this.solucaoOtima = tablo.isSolucaoOtima();
		this.variavelEntra = definirVariavelEntra();
		this.variavelSai = definirVariavelSai();

	}
	
	
	
		
	
	public void atualizarContentVariavelEntraAndSai(Variavel variavelEntra,Variavel variavelSai) {
		List<List<String>> valores = content;
		
		int y = 0;
		for (int i = 0; i < valores.size(); i++) {
			String nomeVariavel = valores.get(i).get(1);
				if(nomeVariavel.equals(variavelSai.getNome())) {
			
					Integer casaEntra = obterPosicaoHead(variavelEntra.getNome());
					String valorEntra = preHead.get(casaEntra);
					valores.get(i).set(0, valorEntra);
					valores.get(i).set(1, variavelEntra.getNome());
					baseList.set(y, variavelEntra);
			}
				y++;
		}
		
		content = valores;
		
	}
	
	public int obterPosicaoHead(String nomeVariavel) {
		Integer tamanho = head.size();
		
		for (int i = 0; i < tamanho; i++) {
			if(head.get(i).equals(nomeVariavel)) {
				return i;
			}
		}
		return 0;
	}
	
	public void definirBaseProximoTablo() {
		
	}
	
	public Variavel definirVariavelEntra() {
		
		Double valorComparacao = Double.valueOf(linhaZC.get(2));
		Variavel v = variaveis.get(0);
		for (int i = 2; i <= (linhaZC.size()-1); i++) {
			Double valor = Double.valueOf(this.linhaZC.get(i));
			if(valor > 0) {
				if(valor > valorComparacao) {
					v = variaveis.get(i-2);
					valorComparacao = valor;
				}	
			}
		
			
		}
		this.colunaPivo = definirColunaPivo(v);
		return v;
	}
	
	public Variavel definirVariavelSai() {
		Integer indiceBase = 0;
		Integer repeticoes = content.size()-1;
		Double valorComparacao = null;
		Variavel v = null;
		for (int i = 0; i <= repeticoes; i++) {
			String valorColuna = content.get(i).get(colunaPivo+2);
			Integer indiceB = content.get(i).size()-1;
			String valorB = content.get(i).get(indiceB);
			
			if(Double.valueOf(valorColuna)> 0.) {
				Double total = Double.valueOf(valorB) / Double.valueOf(valorColuna);
				
				if(valorComparacao == null) {
					v = baseList.get(i);
					valorComparacao = total;
					linhaPivo = i;

				}else {
					if(total < valorComparacao) {
						v = baseList.get(i);
						valorComparacao = total;
						linhaPivo = i;
					}
				}
			}
		}
		return v;
	}
	
	public Integer definirColunaPivo(Variavel v) {
		Integer valor = 0;
		Integer i = 0;
		for (Variavel variavel : variaveis) {
			if(v.getNome().equals(variavel.getNome())) {
				valor = i;
			}
			i++;
		}
		return valor;
	}
	public void addValorVariavelFO(Variavel variavel,Double valor) {
		for (Variavel v : variaveis) {
			if(v.equals(variavel)) {
				v.setValor(valor);
			}
		}
	}
	
	public void realizarPivoteamento() {
		transformarEm1Pivoteamento();
		transformarEm0Pivoteamento();
	}
	
	private void transformarEm1Pivoteamento() {
		Double valorColuna = Double.valueOf(content.get(linhaPivo).get(colunaPivo+2));
		List<String> valores = content.get(linhaPivo);
		List<String> novosValores = new ArrayList<String>();
		novosValores.add(valores.get(0));
		novosValores.add(valores.get(1));
		int i = 0;
		for (String v : valores) {
			if(i >1) {
				Double valor = Double.valueOf(v);
				Double total = valor/valorColuna;
				novosValores.add(total.toString());

			}
			
			i++;
		}
		
		List<List<String>> contNovo = new ArrayList<List<String>>();
		Integer tamanho = content.size();
		
		for (int j = 0; j < tamanho; j++) {
			if(linhaPivo.equals(j)) {
				contNovo.add(novosValores);
			}else {
				contNovo.add(content.get(j));
			}
			
		}
		contentAnterior = content;
		content = contNovo;
		
	}
	
	private void transformarEm0Pivoteamento() {
		Integer tamanhoBase = content.size();

		List<List<String>> novoContent = new ArrayList<List<String>>();
		for (int i = 0; i < tamanhoBase; i++) {
			if(!linhaPivo.equals(i)) {
				List<String> valoresEscolha = contentAnterior.get(i);
				List<String> valoresReferencia = content.get(linhaPivo);
				if(! (Double.valueOf(valoresEscolha.get(colunaPivo+2)).equals(0.0))) {
					
				
				Integer qtd = valoresEscolha.size();
				int x = 0;
				
				List<String> var = new ArrayList<String>();
				var.add(content.get(i).get(0));
				var.add(content.get(i).get(1));
				for (int j = 0; j < qtd; j++) {
					if(x >1) {
						Double novaLinha = Double.valueOf(valoresReferencia.get(j));
						
						Double valorQueQuerZerar = Double.valueOf(contentAnterior.get(i).get(colunaPivo+2));
						valorQueQuerZerar = valorQueQuerZerar * (-1);
						
						Double res = novaLinha * valorQueQuerZerar;
						Double soma = res + Double.valueOf(valoresEscolha.get(j));

						
						var.add(soma.toString());

					}
					x++;
				}
				novoContent.add(var);
				}else {
					novoContent.add(valoresEscolha);
				}
				
			}else {
				novoContent.add(content.get(i));
			}
		}
		
		
		content = novoContent;
	}
	
	
	public Integer getQtdVariaveis() {
		return qtdVariaveis;
	}
	public void setQtdVariaveis(Integer qtdVariaveis) {
		this.qtdVariaveis = qtdVariaveis;
	}
	public Integer getQtdRestricoes() {
		return qtdRestricoes;
	}
	public void setQtdRestricoes(Integer qtdRestricoes) {
		this.qtdRestricoes = qtdRestricoes;
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

	public List<List<String>> getContent() {
		return content;
	}

	public void setContent(List<List<String>> content) {
		this.content = content;
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

	public boolean isMostraPainel() {
		return mostraPainel;
	}

	public void setMostraPainel(boolean mostraPainel) {
		this.mostraPainel = mostraPainel;
	}

	public boolean isMostraPainelInservaoVariaveis() {
		return mostraPainelInservaoVariaveis;
	}

	public void setMostraPainelInservaoVariaveis(boolean mostraPainelInservaoVariaveis) {
		this.mostraPainelInservaoVariaveis = mostraPainelInservaoVariaveis;
	}

	public String getValorMenorQue() {
		return valorMenorQue;
	}

	public void setValorMenorQue(String valorMenorQue) {
		this.valorMenorQue = valorMenorQue;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public List<Variavel> getBaseList() {
		return baseList;
	}

	public void setBaseList(List<Variavel> baseList) {
		this.baseList = baseList;
	}

	public Variavel getVariavelSai() {
		return variavelSai;
	}

	public void setVariavelSai(Variavel variavelSai) {
		this.variavelSai = variavelSai;
	}

	public Variavel getVariavelEntra() {
		return variavelEntra;
	}

	public void setVariavelEntra(Variavel variavelEntra) {
		this.variavelEntra = variavelEntra;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public List<List<String>> getContentAnterior() {
		return contentAnterior;
	}

	public void setContentAnterior(List<List<String>> contentAnterior) {
		this.contentAnterior = contentAnterior;
	}

	public Integer getColunaPivo() {
		return colunaPivo;
	}

	public void setColunaPivo(Integer colunaPivo) {
		this.colunaPivo = colunaPivo;
	}

	public Integer getLinhaPivo() {
		return linhaPivo;
	}

	public void setLinhaPivo(Integer linhaPivo) {
		this.linhaPivo = linhaPivo;
	}

	public boolean isSolucaoOtima() {
		return solucaoOtima;
	}

	public void setSolucaoOtima(boolean solucaoOtima) {
		this.solucaoOtima = solucaoOtima;
	}
	
	
	

}
