package estudos.alura.loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import estudos.alura.loja.dao.ProdutoDao;
import estudos.alura.loja.modelo.Produto;
import estudos.alura.loja.util.JPAUtil;

public class CadastroDeProduto {

	public static void main(String[] args) {
		Produto celular = new Produto();
		celular.setNome("Xiaomi Redmi");
		celular.setDescricao("Muito legal");
		celular.setPreco(new BigDecimal("800"));
		
		//Conectando no banco usando a classe JPAUtil para auxiliar e evitar a repetição de codigo
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao dao = new ProdutoDao(em);
		
		//Iniciando uma transação manual pois estamos usando resource local
		em.getTransaction().begin();
		//Inserindo no banco
		dao.cadastrar(celular);
		//Fazendo um commit da transação
		em.getTransaction().commit();
		//Fechando o entity manager
		em.close();
	}

}
