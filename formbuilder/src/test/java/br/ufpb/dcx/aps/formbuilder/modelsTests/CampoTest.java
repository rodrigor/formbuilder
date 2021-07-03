package br.ufpb.dcx.aps.formbuilder.modelsTests;

import br.ufpb.dcx.aps.formbuilder.models.Campo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class CampoTest {

    Campo campoteste = new Campo();

    @BeforeEach
    void setUp(){

        campoteste.setValor("jackson.anderson@dcx.ufpb.br");
        campoteste.setLabel("E-mail teste");
    }


    @Test
    void testeCampoSimples(){

        assertEquals("jackson.anderson@dcx.ufpb.br", campoteste.getValor());
        assertEquals("E-mail teste", campoteste.getLabel());
    }

    @Test
    void testeIgualdadeCampos(){
        // Dois campos são iguiais se possuírem o mesmo ID

        Campo campo2 = new Campo();
        assertNotEquals(campo2.getLabel(), campoteste.getLabel());

        campo2.setLabel("E-mail teste");
        assertEquals(campo2.getLabel(), campoteste.getLabel());

        // Como ambos os IDs são null, o teste deve passar
        assertEquals(campo2, campoteste);

        campoteste.setLabel("teste teste");
        assertEquals("teste teste", campoteste.getLabel());
    }
}
