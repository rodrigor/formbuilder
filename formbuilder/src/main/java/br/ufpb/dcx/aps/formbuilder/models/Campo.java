package br.ufpb.dcx.aps.formbuilder.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Campo {

    @NotBlank
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    @NotBlank
    private String label;

    @NotNull
    private String valor;

    @NotBlank
    @ManyToOne
    private Formulario formulario;
}
