package com.example.demo.specification;

import java.util.ArrayList;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.example.demo.models.Agencia;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//Classe que implemnta Specification e conterá os atributos de minha classe Agencia
// Dentro tela teremos o método toPredicate responsável por criar as minhas restrições
public class AgenciaSpecification implements Specification<Agencia>{

	private Integer id;
	
	private String nome;
	
	@Override
	//Método responsável por criar as minhas restrições. 
	//É chamado automticamente pelo findAll. 
	//NECESSÁRIO QUE O RESPOSITORY EXTENDS JPASPECIFICATIONEXECUTOR
	public Predicate toPredicate(Root<Agencia> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		ArrayList<Predicate> predicates = new ArrayList<Predicate>();
		
		if(id != null) {
			//Criando uma restrição que compara o campo Id de agência e o valor id
			//Ex: agencia_.id = 5
			Predicate p = criteriaBuilder.equal(root.get("id"), id);
			predicates.add(p);
		}
		if(nome != null) {
			//Criando uma restrição que compara o campo nome de agência e o valor nome
			//Ex: agencia.nome = 'Boca do Rio'
			Predicate p = criteriaBuilder.equal(root.get("nome"), nome);
			predicates.add(p);
		}
		//Transforma um arrayList em um array
		Predicate[] arrayPredicates = predicates.toArray(new Predicate[predicates.size()]);
		
		//Cria uma restrição AND entre todos os predicados do array
		//Ex : agencia_.id = 5 and  agencia.nome = 'Boca do Rio'
		Predicate pAnd = criteriaBuilder.and(arrayPredicates);
		
		return pAnd;
	}

}
