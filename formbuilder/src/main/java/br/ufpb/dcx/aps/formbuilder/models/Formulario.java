package br.ufpb.dcx.aps.formbuilder.models;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Formulario {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @OneToMany
    @Cascade(org.hibernate.annotations.CascadeType.REMOVE)
    private List<Campo> campos;
    private String titulo;

    public Formulario(String titulo) {
        this.titulo = titulo;
    }

    public Formulario() {
        this("");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Formulario that = (Formulario) o;
        return id.equals(that.id);
    }
}
