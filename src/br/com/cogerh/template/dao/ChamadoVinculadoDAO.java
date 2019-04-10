package br.com.cogerh.template.dao;

import java.util.List;

import br.com.cogerh.template.model.ChamadoVinculado;
import br.com.cogerh.template.model.Permissao;

public interface ChamadoVinculadoDAO  extends GenericDAO<ChamadoVinculado, Integer>{
	
	public List<ChamadoVinculado> listar(String pesquisa);

	public List<ChamadoVinculado> listarByChamadoOrigem(Integer idChamado);
}
