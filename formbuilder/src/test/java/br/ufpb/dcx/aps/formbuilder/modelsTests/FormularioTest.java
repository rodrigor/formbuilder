package br.ufpb.dcx.aps.formbuilder.modelsTests;

import br.ufpb.dcx.aps.formbuilder.models.Campo;
import br.ufpb.dcx.aps.formbuilder.models.Formulario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;

public class FormularioTest {

    Formulario formularioDoTeste;

    @BeforeEach
    void setUp(){
        formularioDoTeste = new Formulario();
        formularioDoTeste.setTitulo("formulario teste");

        List<Campo> camposDoFormulario = new LinkedList<>();

        Campo campo1 = new Campo();
        campo1.setLabel("teste campo tipo e-mail");
        campo1.setValor("jackson.gomes@dcx.ufpb.br");
        camposDoFormulario.add(campo1);

        Campo campo2 = new Campo();
        campo2.setLabel("teste campo tipo inteiro");
        campo2.setValor("20");
        camposDoFormulario.add(campo2);

        Campo campo3 = new Campo();
        campo3.setLabel("teste campo tipo texto simples");
        campo3.setValor("texto simples testando 1 2 3 ");
        camposDoFormulario.add(campo3);

        formularioDoTeste.setCampos(camposDoFormulario);
    }

    @Test
    void testeFormularioSimples(){

        assertEquals("formulario teste", formularioDoTeste.getTitulo());
        assertEquals(3, formularioDoTeste.getCampos().size());

        Campo campo3 = new Campo();
        campo3.setLabel("teste campo tipo texto simples");
        campo3.setValor("texto simples testando 1 2 3 ");
        assertTrue(formularioDoTeste.getCampos().contains(campo3));

    }

    @Test
    void testeIgualdade(){
        // Formulários só são iguais se possuírem o mesmo ID

        Formulario formulario2 = new Formulario();
        assertNotEquals(formulario2.getTitulo(), formularioDoTeste.getTitulo());


        formulario2.setTitulo("formulario teste");
        assertEquals(formulario2.getTitulo(), formularioDoTeste.getTitulo());


        List<Campo> camposDoFormulario = new LinkedList<>();

        Campo campo1 = new Campo();
        campo1.setLabel("teste campo tipo e-mail");
        campo1.setValor("jackson.gomes@dcx.ufpb.br");
        camposDoFormulario.add(campo1);

        formulario2.setCampos(camposDoFormulario);
        assertNotEquals(formulario2.getCampos(), formularioDoTeste.getCampos());


        Campo campo2 = new Campo();
        campo2.setLabel("teste campo tipo inteiro");
        campo2.setValor("20");
        camposDoFormulario.add(campo2);

        Campo campo3 = new Campo();
        campo3.setLabel("teste campo tipo texto simples");
        campo3.setValor("texto simples testando 1 2 3 ");
        camposDoFormulario.add(campo3);
        formulario2.setCampos(camposDoFormulario);

        assertEquals(formulario2.getCampos(), formularioDoTeste.getCampos());

        // Mesmo não tendo nenhum atributo igual, esse teste terá que ser verdadeiro, já que ambos os Id são null
        Formulario formularioIgual = new Formulario();
        assertEquals(formularioIgual, formularioDoTeste);
    }
}
