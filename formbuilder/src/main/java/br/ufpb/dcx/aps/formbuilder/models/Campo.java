package br.ufpb.dcx.aps.formbuilder.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
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

    public Campo(String label) {
        this.label = label;
    }

    public Campo() {
        this("", "");
    }


}
