package estudos.alura.loja.testes;

import java.math.BigDecimal;
import java.util.List;

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
import estudos.alura.loja.vo.RelatorioVendasVO;

public class CadastroDePedido {

	public static void main(String[] args) {
		popularBanco();
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		ClienteDao clienteDao = new ClienteDao(em);
		
		Produto produto = produtoDao.buscarPorId(1l);
		Produto produto2 = produtoDao.buscarPorId(2l);
		Produto produto3 = produtoDao.buscarPorId(3l);
		Cliente rodrigo = clienteDao.buscarPorId(1l);
		
		em.getTransaction().begin();
		
		
		Pedido pedido1 = new Pedido(rodrigo);
		pedido1.adicionarItem(new ItemPedido(10, pedido1, produto));
		pedido1.adicionarItem(new ItemPedido(40, pedido1, produto2));
		Pedido pedido2 = new Pedido(rodrigo);
		pedido1.adicionarItem(new ItemPedido(2, pedido1, produto3));
		
		PedidoDao pedidoDao = new PedidoDao(em);
		pedidoDao.cadastrar(pedido1);

		em.getTransaction().commit();
		
		BigDecimal totalVendido = pedidoDao.valorTotalVendido();
		System.out.println("Valor total vendido: " + totalVendido);
		
		
		List<RelatorioVendasVO> relatorio = pedidoDao.relatorioDeVendas();
		relatorio.forEach(System.out::println);
	}
	
	private static void popularBanco() {
		//CATEGORIAS
		Categoria celulares = new Categoria("CELULARES");
		Categoria videogames = new Categoria("VIDEOGAMES");
		Categoria informatica = new Categoria("INFORMATICA");
		//PRODUTOS
		Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares);
		Produto videogame = new Produto("PS5", "Playstation 5", new BigDecimal("5000"), videogames);
		Produto macbook = new Produto("Macbook", "Macbook Pro", new BigDecimal("2000"), informatica);
		//CLIENTES
		Cliente rodrigo = new Cliente("Rodrigo", "123456");
		Cliente jessica = new Cliente("Jessica", "654321");
		
		
		//Conectando no banco usando a classe JPAUtil para auxiliar e evitar a repetição de codigo
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		ClienteDao clienteDao = new ClienteDao(em);
		
		//Iniciando uma transação manual pois estamos usando resource local
		em.getTransaction().begin();
		
		//Inserindo no banco
		categoriaDao.cadastrar(celulares);
		categoriaDao.cadastrar(videogames);
		categoriaDao.cadastrar(informatica);
		
		produtoDao.cadastrar(celular);
		produtoDao.cadastrar(videogame);
		produtoDao.cadastrar(macbook);
		
		clienteDao.cadastrar(rodrigo);
		clienteDao.cadastrar(jessica);
		
		//Fazendo um commit da transação
		em.getTransaction().commit();
		//Fechando o entity manager
		em.close();
	}
}
