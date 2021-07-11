package br.ufpb.dcx.aps.formbuilder.models;

public enum OpcoesValidacao {
    TEXTO_SIMPLES(1), EMAIL(2), INTEIRO(3);

    int ACAO = 0;
    OpcoesValidacao(int valorAcao){
        ACAO = valorAcao;
    }

    public int getACAO() {
        return ACAO;
    }
}
