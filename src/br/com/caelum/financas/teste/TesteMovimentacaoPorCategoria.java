package br.com.caelum.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.financas.modelo.Categoria;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class TesteMovimentacaoPorCategoria {

	public static void main(String[] args) {
		
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		
		Categoria categoria = new Categoria();
		categoria.setId(1);
		
		String jpql = "select m from Movimentacao m join m.categoria c where c = :pCategoria";
		Query query = em.createQuery(jpql);
		query.setParameter("pCategoria", categoria);
		
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
