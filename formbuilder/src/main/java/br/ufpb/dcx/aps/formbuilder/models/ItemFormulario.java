package br.ufpb.dcx.aps.formbuilder.models;

import br.ufpb.dcx.aps.formbuilder.models.Resultado;

public interface ItemFormulario {
    Long getId();
    String getLabel();
    void setValor(String valor);
    Resultado validar();
    void setObrigatorio(boolean obrigatorio);
    boolean isPreenchido();
    boolean isObrigatorio();
}
