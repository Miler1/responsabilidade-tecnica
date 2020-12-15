package com.gestaoresponsabilidadetecnica.responsavelTecnico.specifications;

import com.gestaoresponsabilidadetecnica.configuracao.utils.StringUtil;
import com.gestaoresponsabilidadetecnica.responsavelTecnico.models.ResponsavelTecnico;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.JoinType;

@AllArgsConstructor
public class ResponsavelTecnicoSpecification {

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

}
