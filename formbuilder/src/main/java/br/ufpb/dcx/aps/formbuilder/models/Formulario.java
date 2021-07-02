package br.ufpb.dcx.aps.formbuilder.models;

import org.hibernate.annotations.Cascade;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.*;

@Entity
public class Formulario {

    @GeneratedValue
    @Id
    private Long id;
    @OneToMany
    @Cascade(org.hibernate.annotations.CascadeType.REMOVE)
    private List<Campo> campos;
    private String titulo;

    public Formulario() {
        this.campos = new LinkedList<>();
    }

    public Formulario(String titulo) {
        this();
        this.titulo = titulo;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public List<Campo> getCampos() {
        return campos;
    }

    public void setCampos(List<Campo> campos) {
        this.campos = campos;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
