package br.com.cogerh.template.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="permissao")
public class Permissao extends PersistentEntityImpl
{
	@Id
	@Column(name = "per_cod_id")
	@SequenceGenerator(name = "seq_permissao", sequenceName = "seq_permissao", allocationSize = 1, initialValue = 1)
	@GeneratedValue(generator = "seq_permissao", strategy = GenerationType.SEQUENCE)
    private Integer id;

	@Column(name = "per_nome")
	private String nome;

	@Column(name = "per_descricao")
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name = "tpe_cod_id")
	private TelaPermissao telaPermissao;
	
	@Transient
	private boolean check;
	
	public Permissao(){
		
	}
	
	
	
	
	public Permissao(String nome,String descricao){
		this.nome = nome;
		this.descricao = descricao;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getNome() 
	{
		return nome;
	}

	public void setNome(String nome) 
	{
		this.nome = nome;
	}

	public String getDescricao()
	{
		return descricao;
	}

	public void setDescricao(String descricao)
	{
		this.descricao = descricao;
	}


	public boolean isCheck() {
		return check;
	}


	public void setCheck(boolean check) {
		this.check = check;
	}
	
}