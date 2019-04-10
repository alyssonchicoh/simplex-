package br.com.cogerh.template.util;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

import br.com.cogerh.template.model.Chamado;

/**
 * CLASSE RESPONSAVEL POR GERAR OS IDS DOS CHAMADOS COM BASE DA SEGUINTE REGRA
 * ID SEQUENCIAL +  "/" +  MES COM 2 DIGITOS + ANO COM 4 DIGITOS
 * @author alyssonnascimento
 *
 */
public class GeradorIDChamado implements IdentifierGenerator{
	
	@Override
	public Serializable generate(SessionImplementor arg0, Object object) throws HibernateException {
		 final Chamado entity = (Chamado) object;
	        return entity.getId() + '-' + "2018";
	}
}
