package com.agilliza.api.repository.lancamento;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.agilliza.api.model.entity.Categoria_;
import com.agilliza.api.model.entity.Lancamento;
import com.agilliza.api.model.entity.Lancamento_;
import com.agilliza.api.model.entity.Pessoa_;
import com.agilliza.api.repository.filter.LancamentoFilter;
import com.agilliza.api.repository.projection.LancamentoProjection;

public class LancamentoRepositoryImpl implements LancamentoRepositoryQuery {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Page<Lancamento> filter(LancamentoFilter filter, Pageable pageable) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Lancamento> criteria = builder.createQuery(Lancamento.class);
		Root<Lancamento> root = criteria.from(Lancamento.class);

		Predicate[] restrictions = criarRestricoes(filter, builder, root);
		criteria.where(restrictions);

		TypedQuery<Lancamento> query = em.createQuery(criteria);
		adicionarResticoesPaginacao(query, pageable);
		
		return new PageImpl<Lancamento>(query.getResultList(), pageable, total(filter));
	}
	
	@Override
	public Page<LancamentoProjection> resumir(LancamentoFilter filter, Pageable pageable) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<LancamentoProjection> criteria = builder.createQuery(LancamentoProjection.class);
		Root<Lancamento> root = criteria.from(Lancamento.class);
		
		criteria.select(builder.construct(LancamentoProjection.class,
				root.get(Lancamento_.id), root.get(Lancamento_.descricao),
				root.get(Lancamento_.dataVencimento), root.get(Lancamento_.dataPagamento),
				root.get(Lancamento_.valor), root.get(Lancamento_.tipo),
				root.get(Lancamento_.categoria).get(Categoria_.nome),
				root.get(Lancamento_.pessoa).get(Pessoa_.nome)));
		
		Predicate[] restrictions = criarRestricoes(filter, builder, root);
		criteria.where(restrictions);

		TypedQuery<LancamentoProjection> query = em.createQuery(criteria);
		adicionarResticoesPaginacao(query, pageable);
		
		return new PageImpl<LancamentoProjection>(query.getResultList(), pageable, total(filter));
	}
	
	private void adicionarResticoesPaginacao(TypedQuery<?> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistosPagina = pageable.getPageSize();
		int primeiroRegistroPagina = paginaAtual * totalRegistosPagina;
		
		query.setFirstResult(primeiroRegistroPagina);
		query.setMaxResults(totalRegistosPagina);
	}
	
	private Long total(LancamentoFilter filter) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Lancamento> root = criteria.from(Lancamento.class);
		
		Predicate[] restrictions = criarRestricoes(filter, builder, root);
		criteria.where(restrictions);
		
		criteria.select(builder.count(root));
		return em.createQuery(criteria).getSingleResult();
	}

	private Predicate[] criarRestricoes(LancamentoFilter filter, CriteriaBuilder builder, Root<Lancamento> root) {
		List<Predicate> predicates = new ArrayList<>();

		if (StringUtils.isNotEmpty(filter.getDescricao())) {
			predicates.add(builder.like(builder.lower(root.get(Lancamento_.descricao)),
					"%" + filter.getDescricao().toLowerCase() + "%"));
		}

		LocalDate inicio = filter.getDataVencimentoInicio();
		if (inicio != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get(Lancamento_.dataVencimento), inicio));
		}

		LocalDate fim = filter.getDataVencimentoFim();
		if (fim != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get(Lancamento_.dataVencimento), fim));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
