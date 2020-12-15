package com.gestaoresponsabilidadetecnica.responsavelTecnico.specifications;

import com.gestaoresponsabilidadetecnica.responsavelTecnico.models.ResponsavelTecnico;
import com.gestaoresponsabilidadetecnica.responsavelTecnico.models.StatusCadastroResponsavelTecnico;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;

@AllArgsConstructor
public class ResponsavelTecnicoSpecification implements Specification<ResponsavelTecnico>{

    public static Specification<ResponsavelTecnico> padrao() {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.isNotNull(root.get("id"));
    }

    public static Specification<ResponsavelTecnico> vencidoPorData(Date data) {

        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThan(
                root.<Date>get("validade"),
                data);

    }

    public static Specification<ResponsavelTecnico> status(StatusCadastroResponsavelTecnico status){

        return(root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(
                root.get("status"),
                status);
    }

    @Override
    public Predicate toPredicate(Root<ResponsavelTecnico> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return null;
    }
}
