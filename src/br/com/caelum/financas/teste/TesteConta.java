package br.com.caelum.financas.teste;

import javax.persistence.EntityManager;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.util.JPAUtil;

public class TesteConta {

	public static void main(String[] args) {
		
		EntityManager em = new JPAUtil().getEntityManager();
		
		em.getTransaction().begin();
		Conta conta = em.find(Conta.class, 1);
		System.out.println(conta.getTitular());
		em.getTransaction().commit();
		
		em.close();
		
	}
	
}
