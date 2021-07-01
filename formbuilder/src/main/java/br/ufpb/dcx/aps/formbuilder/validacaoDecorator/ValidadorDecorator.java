package br.ufpb.dcx.aps.formbuilder.validacaoDecorator;

import br.ufpb.dcx.aps.formbuilder.models.Resultado;

public class ValidadorDecorator implements ValidadorCampo {

    protected ValidadorCampo decorated;

    public ValidadorDecorator(){}

    public ValidadorDecorator(ValidadorCampo decorated){
        this.decorated = decorated;
    }

    @Override
    public Resultado validarCampo(String valor) {
        if(decorated == null) return new Resultado();
        return decorated.validarCampo(valor);
    }

    public void setDecorated(ValidadorCampo decorated){
        this.decorated = decorated;
    }
}
