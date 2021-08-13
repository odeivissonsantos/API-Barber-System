package io.github.odeivissonsantos.models;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity(name = "servico_prestado")
public class ServicoPrestadoModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull(message = "Campo descricao é obrigatório")
	private String descricao;

	@NotNull(message = "Campo valor é obrigatório")
	private BigDecimal valor;

	@ManyToOne(cascade = CascadeType.ALL)
	private ClienteModel Cliente;

	@ManyToOne(cascade = CascadeType.ALL)
	private BarbeiroModel barbeiro;

	public ServicoPrestadoModel() {
		super();
	}

	public ServicoPrestadoModel(Integer id, @NotNull(message = "Campo descricao é obrigatório") String descricao,
			@NotNull(message = "Campo valor é obrigatório") BigDecimal valor, ClienteModel cliente,
			BarbeiroModel barbeiro) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.valor = valor;
		Cliente = cliente;
		this.barbeiro = barbeiro;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public ClienteModel getCliente() {
		return Cliente;
	}

	public void setCliente(ClienteModel cliente) {
		Cliente = cliente;
	}

	public BarbeiroModel getBarbeiro() {
		return barbeiro;
	}

	public void setBarbeiro(BarbeiroModel barbeiro) {
		this.barbeiro = barbeiro;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Cliente, barbeiro, descricao, id, valor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ServicoPrestadoModel other = (ServicoPrestadoModel) obj;
		return Objects.equals(Cliente, other.Cliente) && Objects.equals(barbeiro, other.barbeiro)
				&& Objects.equals(descricao, other.descricao) && Objects.equals(id, other.id)
				&& Objects.equals(valor, other.valor);
	}

	@Override
	public String toString() {
		return "ServicoPrestadoModel [id=" + id + ", descricao=" + descricao + ", valor=" + valor + ", Cliente="
				+ Cliente + ", barbeiro=" + barbeiro + "]";
	}

}
