package br.com.cogerh.template.geradorCrud;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * CLASSE RESPONSAVEL POR FAZER O GERADOR DE CLASSE MODELO
 * @author alyssonnascimento
 *
 */
public class GeradorClasseModel extends UtilGerador{

	private String nomeClasse;
	private String nomeClasseminisculo;
	private List<Atributo> atributos;
	
	public GeradorClasseModel(){
		nomeClasseminisculo = transformaStringMinisculo(nomeClasse);
	}
	
	
	public void gerar(){
		StringBuilder codigo = new StringBuilder();
		codigo.append("@Entity");
		codigo.append("@Table(name=\""+nomeClasseminisculo +"\")");
		codigo.append("@Table(name=\""+nomeClasseminisculo +"\")");


	}
	
}
