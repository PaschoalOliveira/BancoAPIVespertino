package com.example.demo.specification;

import java.util.ArrayList;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.example.demo.models.Empregado;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmpregadoSpecification implements Specification<Empregado>{
	
	Integer cpf;
	
	String nome;
	
	String nomeAgencia;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Predicate toPredicate(Root<Empregado> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		ArrayList<Predicate> predicates = new ArrayList<Predicate>();
		
		if(this.cpf != null) {
			Predicate p = criteriaBuilder.equal(root.get("cpf"), cpf);
			predicates.add(p);
		}
		if(this.nome != null) {
			Predicate p = criteriaBuilder.equal(root.get("nome"), nome);
			predicates.add(p);
		}
		if(this.nomeAgencia != null) {
			Predicate p = criteriaBuilder.equal(root.get("agencia").get("nome"), nomeAgencia);
			predicates.add(p);
		}
		Predicate[] arrayPredicates = predicates.toArray(new Predicate[predicates.size()]);
		
		Predicate pAnd = criteriaBuilder.and(arrayPredicates);
		
		return pAnd;
	}

}
