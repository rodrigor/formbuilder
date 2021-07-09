package br.ufpb.dcx.aps.formbuilder.validador;

import br.ufpb.dcx.aps.formbuilder.models.Resultado;

public class ValidadorInteiro extends ValidadorDecorator{

    private int maxValor;
    private int minValor;

    public ValidadorInteiro(int min, int max){
        if (min < 0 || max < 0)
            throw new IllegalArgumentException("invalido minValor="+min+", maxValor="+max);
        this.maxValor = max;
        this.minValor = min;
    }
    public ValidadorInteiro(){
        this.maxValor = Integer.MAX_VALUE;
        this.minValor = Integer.MIN_VALUE;
    }

    @Override
    public Resultado validarCampo(String valor){
        if(valor == null)
            return new Resultado(true,"valor: null");
        int intVal = 0;
        try{
            intVal = Integer.parseInt(valor);
        }catch (Exception e){
            return new Resultado(true,"valor não é um inteiro:'"+valor+"'");
        }

        if(intVal < this.minValor)
            return new Resultado(true,"valor menor que "+minValor+":"+intVal);

        if(intVal > this.maxValor)
            return new Resultado(true,"valor maior que "+maxValor+":"+intVal);

        return new Resultado();
    }
}