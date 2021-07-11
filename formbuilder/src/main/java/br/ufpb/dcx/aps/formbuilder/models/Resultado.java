package br.ufpb.dcx.aps.formbuilder.models;

import java.util.LinkedList;
import java.util.List;

public class Resultado {

    private boolean temErro;
    private List<String> mensagens = new LinkedList<>();


    public Resultado() {
        temErro = false;
    }

    public Resultado(boolean temErro, String mensagem) {
        this.temErro = temErro;
        this.addMensagem(mensagem);
    }

    public void addMensagem(String mensagem) {
        this.mensagens.add(mensagem);
    }

    public void setErro(boolean erro) {
        this.temErro = erro;
    }

    public boolean isErro() {
        return this.temErro;
    }

    public String getMensagem(int id) {
        return this.mensagens.get(id);
    }
}