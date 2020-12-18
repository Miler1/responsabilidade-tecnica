package com.gestaoresponsabilidadetecnica.responsavelTecnico.specifications;

import com.gestaoresponsabilidadetecnica.configuracao.utils.StringUtil;
import com.gestaoresponsabilidadetecnica.responsavelTecnico.models.ResponsavelTecnico;
import com.gestaoresponsabilidadetecnica.responsavelTecnico.models.StatusCadastroResponsavelTecnico;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.JoinType;
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

    public static Specification<ResponsavelTecnico> baseJoin(String coluna, String referenced, String filter){
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(
                criteriaBuilder.function("unaccent", String.class, criteriaBuilder.lower(root.join(coluna, JoinType.INNER).get(referenced))),
                "%" + StringUtil.removeAccents(filter.toLowerCase()) + "%");
    }

    public static Specification<ResponsavelTecnico> nome(String nome) {
        return baseJoin("pessoa", "nome", nome);
    }

    public static Specification<ResponsavelTecnico> status(String status) {
        return baseJoin("status", "nome", status);
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
