package br.ufpb.dcx.aps.formbuilder.models;

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
    private List<Campo> campos;

    public Formulario() {
        this.campos = new LinkedList<>();
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
}
