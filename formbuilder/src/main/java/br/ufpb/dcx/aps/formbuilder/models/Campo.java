package br.ufpb.dcx.aps.formbuilder.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Campo {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    private String label;

    private String valor;


    @ManyToOne
    private Formulario formulario;

}
