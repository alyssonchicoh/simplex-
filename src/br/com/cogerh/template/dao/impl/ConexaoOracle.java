package br.com.cogerh.template.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Classe que armazena uma conexão com o banco. A conexão é aberta no construtor da
 * classe.
 * 
 * 
 *
 */
public class ConexaoOracle {
	private Connection conexao;

	public ConexaoOracle() throws ClassNotFoundException, SQLException {
		criaConexao("COGERH");
	}

	public ConexaoOracle(String servico) throws ClassNotFoundException, SQLException {
		criaConexao(servico);
	}
	
	public void criaConexao(String service) throws ClassNotFoundException, SQLException {
		
		try{
			final String url = "jdbc:oracle:thin:@172.31.136.22:1521:" + service;
	
			Properties connectProperties = new Properties();
			
			if (service.trim().equals("totvs10")) {
				connectProperties.put("user", "totvs10_consulta");
				connectProperties.put("password", "markus");
			} else {
				connectProperties.put("user", "adm_outorga");
				connectProperties.put("password", "outorga");
			}
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			conexao = DriverManager.getConnection(url, connectProperties);

		} catch (SQLException sqle) {
			
			System.out.println("SQLException em Conecta.java "+ sqle.getMessage());
			throw sqle;
		}

	}

	public void fechaConexao() throws SQLException {
		conexao.close();
		conexao = null;
	}

	public boolean isFechada() {
		try {
			return conexao.isClosed();
		} catch (SQLException ex) {
			return false;
		}
	}

	public Connection getConexao() {
		return conexao;
	}

}
