package estudos.alura.loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import estudos.alura.loja.modelo.Produto;

public class CadastroDeProduto {

	public static void main(String[] args) {
		Produto celular = new Produto();
		celular.setNome("Xiaomi Redmi");
		celular.setDescricao("Muito legal");
		celular.setPreco(new BigDecimal("800"));
		
		//Conectando no banco
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("loja");
		EntityManager em = factory.createEntityManager();
		
		//Iniciando uma transação manual pois estamos usando resource local
		em.getTransaction().begin();
		//Inserindo no banco
		em.persist(celular);
		//Fazendo um commit da transação
		em.getTransaction().commit();
		//Fechando o entity manager
		em.close();
	}

}
