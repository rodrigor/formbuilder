package br.ufpb.dcx.aps.formbuilder.exceptions;

public class FormularioNaoEncontradoException extends RuntimeException{

    public FormularioNaoEncontradoException(String mensagem){
        super(mensagem);
    }
}
