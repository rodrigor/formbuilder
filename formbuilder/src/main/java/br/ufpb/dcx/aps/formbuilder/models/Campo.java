package br.ufpb.dcx.aps.formbuilder.models;

import br.ufpb.dcx.aps.formbuilder.validador.ValidadorCampo;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@NoArgsConstructor
@Entity
public class Campo implements ItemFormulario{

    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Setter
    private String label;

    @Setter
    @Getter
    private String valor;

    @Setter
    @Getter
    private boolean obrigatorio;

    @Setter
    @Getter
    private boolean preenchido;


    @Transient
    private ValidadorCampo validador;


    public ValidadorCampo getValidador() {
        return validador;
    }

    public void setValidador(ValidadorCampo validador) {
        this.validador = validador;
    }

    @Override
    public Resultado validar() {
        if (this.obrigatorio && !this.preenchido)
            return new Resultado(true, this.getId() + " é obrigatório e não foi preenchido");
        if (this.preenchido)
            return this.validador.validarCampo(this.valor);
        return new Resultado();
    }
}
