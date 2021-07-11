package br.ufpb.dcx.aps.formbuilder.models;

import br.ufpb.dcx.aps.formbuilder.validador.ValidadorCampo;
import br.ufpb.dcx.aps.formbuilder.validador.ValidadorEmail;
import br.ufpb.dcx.aps.formbuilder.validador.ValidadorInteiro;
import br.ufpb.dcx.aps.formbuilder.validador.ValidadorTextoSimples;
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

    private String valor;

    @Setter
    @Getter
    private boolean obrigatorio;

    @Setter
    @Getter
    private boolean preenchido;

    @Getter
    @Setter
    private OpcoesValidacao opcoesValidacao;


    @Transient
    private ValidadorCampo validador;


    public void setValidador(ValidadorCampo validador) {
        this.validador = validador;
    }

    public String getValor() {
        return valor;
    }

    @Override
    public void setValor(String valor) {
        this.preenchido = true;
        this.valor = valor;
    }

    private void atribuirValidador(){
        switch (this.opcoesValidacao){
            case TEXTO_SIMPLES:
                this.setValidador(new ValidadorTextoSimples(3, 55));
                break;
            case EMAIL:
                this.setValidador(new ValidadorEmail());
                break;
            case INTEIRO:
                this.setValidador(new ValidadorInteiro(3, 55));
                break;
            default:
                this.setValidador(null);
                break;
        }
    }

    @Override
    public Resultado validar() {
        if (this.obrigatorio && !this.preenchido)
            return new Resultado(true, this.getLabel() + " é obrigatório e não foi preenchido");
        if (this.preenchido){
            this.atribuirValidador();
            return this.validador.validarCampo(this.valor);
        }

        return new Resultado();
    }
}
