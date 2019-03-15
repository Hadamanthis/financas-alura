package br.com.caelum.financas.teste;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.TipoMovimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class TesteFuncoesJPQL {
	
	public static void main(String[] args) {
		
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		
		Conta conta = new Conta();
		conta.setId(1);
		
		String jpql = "select sum(m.valor) as s from Movimentacao m where m.id = :pConta "
				+ "and m.tipoMovimentacao = :pTipoMovimentacao group by s"
				+ "order by m.valor desc";
		Query query = em.createQuery(jpql);
		query.setParameter("pConta", conta.getId());
		query.setParameter("pTipoMovimentacao", TipoMovimentacao.ENTRADA);
		
		List<BigDecimal> somas = query.getResultList();
		
		for (BigDecimal soma : somas) {
			System.out.println(soma);
		}
		
		em.getTransaction().commit();
		
		em.close();
	}
	
}
