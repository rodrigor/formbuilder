package br.ufpb.dcx.aps.formbuilder.validacaoDecorator;

import br.ufpb.dcx.aps.formbuilder.models.Resultado;

public class ValidadorTextoSimples extends ValidadorDecorator {

    private int maxTam;
    private int minTam;

    public ValidadorTextoSimples(int min, int max){
        if (min < 0 || max < 0)
            throw new IllegalArgumentException("invalido min="+min+", max="+max);
        this.maxTam = max;
        this.minTam = min;
    }
    public ValidadorTextoSimples(){
        this.maxTam = Integer.MIN_VALUE;
        this.minTam = Integer.MIN_VALUE;
    }

    @Override
    public Resultado validarCampo(String valor){
        if(valor == null)
            return new Resultado(true,"valor: null");
        if(minTam == Integer.MIN_VALUE && maxTam == Integer.MIN_VALUE)
            return new Resultado();

        Resultado resultado = new Resultado();

        int tam = valor.length();
        if(tam > maxTam) {
            resultado.setErro(true);
            resultado.addMensagem("ERRO: tamanho do valor > "+maxTam+": '"+valor+"'");
        }
        if(tam < minTam){
            resultado.setErro(true);
            resultado.addMensagem("ERRO: tamanho do valor < "+minTam+": '"+valor+"'");
        }


        return resultado;
    }

    public int getMaxTam(){
        return this.maxTam;
    }
}