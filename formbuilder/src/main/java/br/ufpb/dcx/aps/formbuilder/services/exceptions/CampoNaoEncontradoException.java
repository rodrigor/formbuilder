package br.ufpb.dcx.aps.formbuilder.services.exceptions;

public class CampoNaoEncontradoException extends RuntimeException{

    public CampoNaoEncontradoException(String mensagem){
        super(mensagem);
    }
}
