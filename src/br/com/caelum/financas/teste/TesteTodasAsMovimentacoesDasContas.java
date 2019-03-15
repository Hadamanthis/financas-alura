package br.com.caelum.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.caelum.financas.modelo.Conta;

public class TesteTodasAsMovimentacoesDasContas {

	public static void main(String[] args) {
		
		EntityManager em = Persistence.createEntityManagerFactory("financas").createEntityManager();
		
		em.getTransaction().begin();
		
		String jpql = "select distinct c from Conta c join fetch c.movimentacoes";
		Query query = em.createQuery(jpql);
		
		List<Conta> contas = query.getResultList();
		
		for (Conta conta : contas) {
			System.out.println("Titular: " + conta.getTitular());
			System.out.println("Movimentações:");
			System.out.println(conta.getMovimentacoes());
		}
		
		em.getTransaction().commit();
		em.close();
		
	}
	
}
