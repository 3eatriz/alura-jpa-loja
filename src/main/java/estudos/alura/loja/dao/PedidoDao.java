package estudos.alura.loja.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import estudos.alura.loja.modelo.Pedido;
import estudos.alura.loja.vo.RelatorioVendasVO;

public class PedidoDao {
	private EntityManager em;

	public PedidoDao(EntityManager em) {
		super();
		this.em = em;
	}
	
	public void cadastrar(Pedido pedido) {
		this.em.persist(pedido);
	}
	
	public void atualizar(Pedido pedido) {
		this.em.merge(pedido);
	}
	
	public void remover(Pedido pedido) {
		pedido = em.merge(pedido);
		this.em.remove(pedido);
	}
	
	public Pedido buscarPorId(Long id) {
		return em.find(Pedido.class, id);
	}
	
	public BigDecimal valorTotalVendido() {
		String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
		return em.createQuery(jpql, BigDecimal.class)
				.getSingleResult();
	}
	
	public List<RelatorioVendasVO> relatorioDeVendas(){
		String jpql = "SELECT new estudos.alura.loja.vo.RelatorioVendasVO("
				+ "produto.nome, "
				+ "SUM(item.quantidade), "
				+ "MAX(pedido.data)) "
				+ "FROM Pedido pedido "
				+ "JOIN pedido.itens item "
				+ "JOIN item.produto produto "
				+ "GROUP BY produto.nome "
				+ "ORDER BY item.quantidade DESC";
		return em.createQuery(jpql, RelatorioVendasVO.class)
				.getResultList();
	}
	
//	public List<Pedido> buscarTodos(){
//		String jpql = "SELECT p FROM Produto p";
//		return em.createQuery(jpql, Pedido.class).getResultList();
//	}
//	
//	public List<Pedido> buscarPorNome(String nome){
//		String jpql = "SELECT p FROM Produto p WHERE p.nome = :nome";
//		return em.createQuery(jpql, Pedido.class)
//				.setParameter("nome", nome)
//				.getResultList();
//	}
//	
//	public List<Pedido> buscarPorDescricao(String descricao){
//		String jpql = "SELECT p FROM Produto p WHERE p.nome = ?1";
//		return em.createQuery(jpql, Pedido.class)
//				.setParameter(1, descricao)
//				.getResultList();
//	}
//	
//	public List<Pedido> buscarPorNomeDaCategoria(String cat){
//		String jpql = "SELECT p FROM Produto p WHERE p.categoria.nome = :cat";
//		return em.createQuery(jpql, Pedido.class)
//				.setParameter("cat", cat)
//				.getResultList();
//	}
//	
//	public BigDecimal buscarPrecoPeloNome(String nome) {
//		String jpql = "SELECT p.preco FROM Produto p WHERE p.nome = ?1";
//		return em.createQuery(jpql, BigDecimal.class)
//				.setParameter(1, nome)
//				.getSingleResult();
//	}
}
