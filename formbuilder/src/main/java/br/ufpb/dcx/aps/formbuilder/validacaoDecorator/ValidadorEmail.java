package br.ufpb.dcx.aps.formbuilder.validacaoDecorator;

import br.ufpb.dcx.aps.formbuilder.models.Resultado;
import org.apache.commons.validator.routines.EmailValidator;

public class ValidadorEmail extends ValidadorDecorator {

    private String dominio;

    public ValidadorEmail() {
        super();
        this.dominio = null;
    }

    public ValidadorEmail(String dominio){
        this.dominio = dominio;
    }

    @Override
    public Resultado validarCampo(String valor) {
        if(valor == null)
            return new Resultado(true,"Valor nulo!");


        if(!EmailValidator.getInstance().isValid(valor)) {
            return new Resultado(true, "email inválido: '" + valor + "'");
        }

       if(dominio != null && !dominioValido(valor)){

      }


        return new Resultado();
    }

    boolean dominioValido(String email){
        String dominioStr = email.substring(email.indexOf("@"));
        return dominioStr.equals(this.dominio);
    }

}