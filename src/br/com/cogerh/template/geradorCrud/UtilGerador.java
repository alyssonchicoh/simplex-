package br.com.cogerh.template.geradorCrud;

/**
 * CLASSE RESPONSAVEL POR DETER DE M�TODOS UTILIS DE TODOS OS GERADORES
 * @author alyssonnascimento
 *
 */
public class UtilGerador {

	protected String transformaStringMinisculo(String valor){
		return valor.toLowerCase();
	}
	
	protected String transformaStringMaisculo(String valor){
		return valor.toUpperCase();
	}
	
 
}
