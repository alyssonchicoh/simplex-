package br.com.cogerh.template.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * @author alysson(alysson.chicoh@gmail.com)
 * @sice 19/10/2018
 *
 */
@Entity
@Table(name = "categoria")
public class Categoria extends PersistentEntityImpl{
	
	@Id
	@Column(name = "cat_cod_id")
	@SequenceGenerator(name = "seq_categoria", sequenceName = "seq_categoria", allocationSize = 1, initialValue = 1)
	@GeneratedValue(generator = "seq_categoria", strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@Column(name = "cat_nome")
	private String nome;
	
	@Column(name = "cat_descricao")
	private String descricao;
	
	public Categoria(){
		
	}
	
	public Categoria(String nome,String descricao){
		this.nome = nome;
		this.descricao = descricao;
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	

}
