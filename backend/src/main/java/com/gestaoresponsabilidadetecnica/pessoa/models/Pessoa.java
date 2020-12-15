package com.gestaoresponsabilidadetecnica.pessoa.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gestaoresponsabilidadetecnica.configuracao.utils.GlobalReferences;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(schema = GlobalReferences.ESQUEMA, name = "pessoa")
@Inheritance(strategy=InheritanceType.JOINED)
public class Pessoa implements Serializable {

//    Foi mapeado somente o id da classe Pessoa para que seja usada no responsável técnico.
//    Nos outros lugares está sendo utilizado a classe do OauthClient
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

}
