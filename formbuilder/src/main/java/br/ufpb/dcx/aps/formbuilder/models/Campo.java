package br.ufpb.dcx.aps.formbuilder.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Campo {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    private String label;
    private String valor;


    public Campo(String label, String valor) {
        this.label = label;
        this.valor = valor;
    }

    public Campo() {
        this("", "");
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
