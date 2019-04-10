package br.com.cogerh.template.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.text.MaskFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cogerh.template.dao.ChamadoDAO;
import br.com.cogerh.template.model.Chamado;
import br.com.cogerh.template.model.Tecnico;
import br.com.cogerh.template.model.TecnicoChamado;
import br.com.cogerh.template.service.ChamadoService;
import br.com.cogerh.template.service.EmailService;
import br.com.cogerh.template.util.FiltroChamadoDadosGerais;
import br.com.cogerh.template.util.StatusChamado;

@Service
public class ChamadoServiceImpl implements ChamadoService {

	private ChamadoDAO dao;


	@Autowired
	public ChamadoServiceImpl(ChamadoDAO dao) {
		this.dao = dao;
	}
	
	private String aplicarMascaraNumeroChamado(Integer idChamado){
		//MASCARA SERÁ IIIII/YYYY-MM ONDE OS I´S SERA O ID DO CHAMADO COM 0 A ESQUERDA, Y O ANO DA ABERTURA DO CHAMADO E M O MÊS DA ABERTURA
		
		String primeiraParte = "000";
		if(idChamado.toString().length() == 1){
			primeiraParte = "00"+idChamado;
		}else if(idChamado.toString().length() == 2){
			primeiraParte = "0"+idChamado;

		}else if(idChamado.toString().length() == 3){
			primeiraParte = ""+idChamado;

		}
		
		Calendar data = Calendar.getInstance();
		primeiraParte = primeiraParte + "/"+String.valueOf(data.get(Calendar.YEAR)) +"-" +String.valueOf(data.get(Calendar.MONTH) + 1); 
		
		return primeiraParte;
	}
	


	@Transactional
	public Chamado salvar(Chamado chamado) throws Exception {
		chamado.setStatus(StatusChamado.AGUARDANDO_TECNICO);
		Chamado save = dao.save(chamado);

		save.setNumero(aplicarMascaraNumeroChamado(save.getId()));
		alterar(save);
		
		return save;
	}

	@Transactional
	public Chamado alterar(Chamado chamado) throws Exception {
		return dao.update(chamado);
	}

	@Transactional
	public void remover(Chamado chamado) throws Exception {
		dao.delete(chamado);
	}

	@Transactional
	public Chamado buscarPorId(Integer id) throws Exception {
		
		Chamado chamado = dao.buscarPorId(id);
		return  chamado;
	}

	@Transactional
	public List<Chamado> listar(String pesquisa) throws Exception {
		return dao.listar(pesquisa);
	}

	@Transactional
	public Chamado buscarByTitutlo(String titulo) throws Exception {
		return dao.buscarChamadoByNome(titulo);
	}

	@Override
	public List<Chamado> listarSemTecnicoAlocado() throws Exception {
		return dao.listarSemTecnicoAlocado();
	}

	@Transactional
	public List<Chamado> listarByTecnico(Integer id) throws Exception {
		return dao.listarByTecnico(id);
	}

	@Override
	public List<Chamado> listarChamadosSolicitadoByUsuario(Integer idUsuario) throws Exception {
		return dao.listarChamadosSolicitadoByUsuario(idUsuario);
	}

	@Override
	public List<Chamado> listarChamadoOrdenadoPorPosicaoFilaByTecnico(Integer idTecnico) throws Exception {
		return dao.listarChamadoOrdenadoPorPosicaoFilaByTecnico(idTecnico);
	}

	@Override
	public void atualizarPosicaoFilaChamados(Integer posicao, Integer idUsuarioTecnico) throws Exception {
		List<Chamado> chamados = this.listarChamadoOrdenadoPorPosicaoFilaByTecnico(idUsuarioTecnico);
		List<Chamado> chamadosAtualizados = new ArrayList<Chamado>();
		for (Chamado chamado : chamados) {
			if(posicao <= chamado.getPosicaoFila()){
				chamado.setPosicaoFila(chamado.getPosicaoFila() +1);
				chamadosAtualizados.add(chamado);
			}
		}
		for (Chamado chamado : chamadosAtualizados) {
			this.alterar(chamado);
		}
	}

	@Override
	public List<Chamado> listarChamadoBySolicitanteAndStatusSolucionado(Integer idUsuario) throws Exception {
		return dao.listarChamadoBySolicitanteAndStatus(idUsuario, StatusChamado.SOLUCIONADO);
	}

	@Override
	public boolean verificaTecnicoAlocadoChamado(Chamado chamado,Tecnico tecnico) {
		
		for (TecnicoChamado tec : chamado.getTecnicosChamados()) {
			if(tec.getTecnico().getId().equals(tecnico.getId())){
				return true;
			}
		}
		
		return false;
	}

	@Override
	public List<Chamado> listarByFiltros(FiltroChamadoDadosGerais filtroChamadoDadosGerais) throws Exception {
		return dao.listarByFiltros(filtroChamadoDadosGerais);
	}

}
