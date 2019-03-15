package br.com.caelum.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.TipoMovimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class TesteJPQL {

	
	public static void main(String[] args) {
		
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		
		Conta conta = new Conta();
		conta.setId(1);
		
		String jpql = "select m from Movimentacao m where m.conta = :pConta "
				+ "and m.tipoMovimentacao = :pTipoMovimentacao "
				+ "order by m.valor desc";
		Query query = em.createQuery(jpql);
		query.setParameter("pConta", conta.getId());
		query.setParameter("pTipoMovimentacao", TipoMovimentacao.ENTRADA);
		
		List<Movimentacao> movimentacoes = query.getResultList();
		
		for (Movimentacao movimentacao : movimentacoes) {
			System.out.println("Descrição: " + movimentacao.getDescricao());
			System.out.println("Valor: R$" + movimentacao.getValor());
			System.out.println("Conta.id: " + movimentacao.getConta().getId());
		}
		
		em.getTransaction().commit();
		
		em.close();
	}
	
}
