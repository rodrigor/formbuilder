package br.ufpb.dcx.aps.formbuilder.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.*;

@Data
@Entity
public class Formulario {

    @NotBlank
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @NotBlank
    @OneToMany
    private List<Campo> campos;

    @NotBlank
    private String titulo;

}
