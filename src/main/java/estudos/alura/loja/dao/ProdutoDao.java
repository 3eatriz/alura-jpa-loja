package estudos.alura.loja.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import estudos.alura.loja.modelo.Produto;

public class ProdutoDao {
	private EntityManager em;

	public ProdutoDao(EntityManager em) {
		super();
		this.em = em;
	}
	
	public void cadastrar(Produto produto) {
		this.em.persist(produto);
	}
	
	public void atualizar(Produto produto) {
		this.em.merge(produto);
	}
	
	public void remover(Produto produto) {
		produto = em.merge(produto);
		this.em.remove(produto);
	}
	
	public Produto buscarPorId(Long id) {
		return em.find(Produto.class, id);
	}
	
	public List<Produto> buscarTodos(){
		String jpql = "SELECT p FROM Produto p";
		return em.createQuery(jpql, Produto.class).getResultList();
	}
	
	public List<Produto> buscarPorNome(String nome){
		String jpql = "SELECT p FROM Produto p WHERE p.nome = :nome";
		return em.createQuery(jpql, Produto.class)
				.setParameter("nome", nome)
				.getResultList();
	}
	
	public List<Produto> buscarPorDescricao(String descricao){
		String jpql = "SELECT p FROM Produto p WHERE p.nome = ?1";
		return em.createQuery(jpql, Produto.class)
				.setParameter(1, descricao)
				.getResultList();
	}
	
	public List<Produto> buscarPorNomeDaCategoria(String cat){
		return em.createNamedQuery("Produto.buscarPorNomeDaCategoria", Produto.class)
				.setParameter("cat", cat)
				.getResultList();
	}
	
	public BigDecimal buscarPrecoPeloNome(String nome) {
		String jpql = "SELECT p.preco FROM Produto p WHERE p.nome = ?1";
		return em.createQuery(jpql, BigDecimal.class)
				.setParameter(1, nome)
				.getSingleResult();
	}
}
