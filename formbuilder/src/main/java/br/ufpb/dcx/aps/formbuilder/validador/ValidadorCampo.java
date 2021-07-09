package br.ufpb.dcx.aps.formbuilder.validador;

import br.ufpb.dcx.aps.formbuilder.models.Resultado;

public interface ValidadorCampo {

    public Resultado validarCampo(String valor);
}

