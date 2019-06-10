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
	private boolean mostraPainel;
	private boolean mostraPainelInservaoVariaveis;
	private String valorMenorQue;
	private Integer numero;
	private List<Variavel> baseList;
	private Variavel variavelSai;
	private Variavel variavelEntra;
	private Integer colunaPivo = 0;
	private Integer linhaPivo = 0;
	@PostConstruct
	public void init() {
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
		Tablo tablo = new Tablo(variaveis,restricoes,baseList,qtdRestricoes,1);

		this.head = tablo.getHead();
		this.preHead = tablo.getPreHead();
		this.content = tablo.getLinhaContent();
		this.linhaZ = tablo.getLinhaZ();
		this.linhaZC = tablo.getLinhaZC();
		this.numero = tablo.getNumero();
		this.variavelEntra = definirVariavelEntra();
		this.variavelSai = definirVariavelSai();

	}
	
	public void proximoTablo() {
		realizarPivoteamento();

		baseList.remove(variavelSai);
		baseList.add(variavelEntra);
		
		
		Tablo tablo = new Tablo(variaveis,restricoes,baseList,qtdRestricoes,numero +1);

		this.head = tablo.getHead();
		this.preHead = tablo.getPreHead();
		this.content = tablo.getLinhaContent();
		this.linhaZ = tablo.getLinhaZ();
		this.linhaZC = tablo.getLinhaZC();
		this.numero = tablo.getNumero();
		this.variavelEntra = definirVariavelEntra();
		this.variavelSai = definirVariavelSai();
	}
	
	public void definirBaseProximoTablo() {
		
	}
	
	public Variavel definirVariavelEntra() {
		Double valorComparacao = Double.valueOf(this.linhaZC.get(2));
		Variavel v = variaveis.get(0);
		for (int i = 2; i < (linhaZC.size()-1); i++) {
			Double valor = Double.valueOf(this.linhaZC.get(i));
			if(valor != 0) {
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
			
			if(!Double.valueOf(valorColuna).equals(0.0)) {
				Double total = Double.valueOf(valorB) / Double.valueOf(valorColuna);
				
				if(valorComparacao == null) {
					v = baseList.get(i);
					valorComparacao = total;

				}else {
					if(total < valorComparacao) {
						v = baseList.get(i);
						valorComparacao = total;
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
		
		int indice = 0;
		for (int j = 0; j < content.size(); j++) {
			if(j == linhaPivo) {
				content.remove(j);
				break;
			}else {
				j++;	
			}
			
		}
		content.add(indice, novosValores);
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
	
	

}
