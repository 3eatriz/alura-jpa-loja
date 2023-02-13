package estudos.alura.loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import estudos.alura.loja.dao.CategoriaDao;
import estudos.alura.loja.dao.ProdutoDao;
import estudos.alura.loja.modelo.Categoria;
import estudos.alura.loja.modelo.Produto;
import estudos.alura.loja.util.JPAUtil;

public class CadastroDeProduto {

	public static void main(String[] args) {
		Categoria celulares = new Categoria("CELULARES");
		
		Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares);
		
		//Conectando no banco usando a classe JPAUtil para auxiliar e evitar a repetição de codigo
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		
		//Iniciando uma transação manual pois estamos usando resource local
		em.getTransaction().begin();
		
		//Inserindo no banco
		categoriaDao.cadastrar(celulares);
		produtoDao.cadastrar(celular);
		
		//Fazendo um commit da transação
		em.getTransaction().commit();
		//Fechando o entity manager
		em.close();
	}

}
