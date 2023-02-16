package estudos.alura.loja.testes;

import java.math.BigDecimal;
import java.time.LocalDate;

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

public class TesteCriteria {
    public static void main(String[] args) {
        popularBanco();
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        produtoDao.buscarProParametros2(null, null, LocalDate.now());
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
        //PEDIDOS
        Pedido pedido = new Pedido(rodrigo);
		pedido.adicionarItem(new ItemPedido(10, pedido, celular));
		pedido.adicionarItem(new ItemPedido(40, pedido, videogame));
		Pedido pedido2 = new Pedido(rodrigo);
		pedido2.adicionarItem(new ItemPedido(2, pedido2, macbook));
		
		//Conectando no banco usando a classe JPAUtil para auxiliar e evitar a repetição de codigo
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		ClienteDao clienteDao = new ClienteDao(em);
        PedidoDao pedidoDao = new PedidoDao(em);
		
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
		
        pedidoDao.cadastrar(pedido);
        pedidoDao.cadastrar(pedido2);
		//Fazendo um commit da transação
		em.getTransaction().commit();
		//Fechando o entity manager
		em.close();
	}
}
