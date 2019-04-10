package br.com.cogerh.template.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cogerh.template.dao.SolucaoChamadoDAO;
import br.com.cogerh.template.model.SolucaoChamado;
import br.com.cogerh.template.service.SolucaoChamadoService;

@Service
public class SolucaoChamadoServiceImpl implements SolucaoChamadoService{
	
	private SolucaoChamadoDAO dao;
	
	@Autowired
	public SolucaoChamadoServiceImpl(SolucaoChamadoDAO dao) {
		this.dao = dao;
	}
	
	@Override
	public SolucaoChamado salvar(SolucaoChamado obj) throws Exception {
		Random gerador = new Random(19700621);
		Integer numAleatorio = gerador.nextInt(100000000);
		
		SolucaoChamado solu = dao.save(obj);
		
		solu.setHashUrl(convertStringToMd5(solu.getId() +""+numAleatorio));
		
		return this.alterar(solu);
	}

	@Override
	public SolucaoChamado alterar(SolucaoChamado obj) throws Exception {
		return dao.update(obj);
	}

	@Override
	public void remover(SolucaoChamado obj) throws Exception {
		dao.delete(obj);
	}

	@Override
	public SolucaoChamado buscarPorId(Integer id) throws Exception {
		return dao.buscarPorId(id);
	}

	@Override
	public List<SolucaoChamado> listar(String pesquisa) throws Exception {
		return dao.listar();
	}

	@Override
	public List<SolucaoChamado> listarByChamado(Integer idChamado)throws Exception {
		List<SolucaoChamado> lista = dao.listarByChamado(idChamado);
		
		if(lista == null){
			return new ArrayList<SolucaoChamado>();
		}
		
		return lista;
	}

	private String convertStringToMd5(String valor)
	{
		MessageDigest mDigest;

		try
		{
			// Instanciamos o nosso HASH MD5, poderíamos usar outro como
			// SHA, por exemplo, mas optamos por MD5.
			mDigest = MessageDigest.getInstance("MD5");

			// Convert a String valor para um array de bytes em MD5
			byte[] valorMD5 = mDigest.digest(valor.getBytes("UTF-8"));

			// Convertemos os bytes para hexadecimal, assim podemos salvar
			// no banco para posterior comparação se senhas
			StringBuffer sb = new StringBuffer();

			for (byte b : valorMD5)
				sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1, 3));

			return sb.toString();
		}
		catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();

			return null;
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();

			return null;
		}
	}

	@Override
	public SolucaoChamado pesquisarByURL(String url) throws Exception {
		return dao.pesquisarByURL(url);
	}
	
}
