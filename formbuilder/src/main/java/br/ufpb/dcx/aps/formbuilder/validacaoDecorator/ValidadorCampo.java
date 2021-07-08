package br.ufpb.dcx.aps.formbuilder.validacaoDecorator;

import br.ufpb.dcx.aps.formbuilder.models.Resultado;

public interface ValidadorCampo {

    public Resultado validarCampo(String valor);
}

