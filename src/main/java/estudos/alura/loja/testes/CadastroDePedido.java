package estudos.alura.loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import estudos.alura.loja.dao.CategoriaDao;
import estudos.alura.loja.dao.ClienteDao;
import estudos.alura.loja.dao.PedidoDao;
import estudos.alura.loja.dao.ProdutoDao;
import estudos.alura.loja.modelo.Categoria;
import estudos.alura.loja.modelo.Cliente;
import estudos.alura.loja.modelo.ItemPedido;
import estudos.alura.loja.modelo.Pedido;
import estudos.alura.loja.modelo.Produto;
import estudos.alura.loja.util.JPAUtil;

public class CadastroDePedido {

	public static void main(String[] args) {
		popularBanco();
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		ClienteDao clienteDao = new ClienteDao(em);
		
		Produto produto = produtoDao.buscarPorId(1l);
		Cliente cliente = clienteDao.buscarPorId(1l);
		
		em.getTransaction().begin();
		
		
		Pedido pedido = new Pedido(cliente);
		pedido.adicionarItem(new ItemPedido(10, pedido, produto));
		
		PedidoDao pedidoDao = new PedidoDao(em);
		pedidoDao.cadastrar(pedido);

		em.getTransaction().commit();
		
		BigDecimal totalVendido = pedidoDao.valorTotalVendido();
		System.out.println("Valor total vendido: " + totalVendido);
	}
	
	private static void popularBanco() {
		Categoria celulares = new Categoria("CELULARES");
		Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares);
		Cliente cliente = new Cliente("Rodrigo", "123456");
		
		//Conectando no banco usando a classe JPAUtil para auxiliar e evitar a repetição de codigo
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		ClienteDao clienteDao = new ClienteDao(em);
		
		//Iniciando uma transação manual pois estamos usando resource local
		em.getTransaction().begin();
		
		//Inserindo no banco
		categoriaDao.cadastrar(celulares);
		produtoDao.cadastrar(celular);
		clienteDao.cadastrar(cliente);
		
		//Fazendo um commit da transação
		em.getTransaction().commit();
		//Fechando o entity manager
		em.close();
	}
}
