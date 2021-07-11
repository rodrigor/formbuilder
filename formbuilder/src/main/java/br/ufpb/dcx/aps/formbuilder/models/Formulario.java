package br.ufpb.dcx.aps.formbuilder.models;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
public class Formulario {

    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Setter
    @OneToMany
    @Cascade(org.hibernate.annotations.CascadeType.REMOVE)
    private List<Campo> campos;

    @Setter
    @EqualsAndHashCode.Include
    private String titulo;


}
