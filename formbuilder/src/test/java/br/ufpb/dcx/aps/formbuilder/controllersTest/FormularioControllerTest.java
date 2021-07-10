package br.ufpb.dcx.aps.formbuilder.controllersTest;

import br.ufpb.dcx.aps.formbuilder.DTOs.FormularioDTO;
import br.ufpb.dcx.aps.formbuilder.controllers.FormularioController;
import br.ufpb.dcx.aps.formbuilder.models.Formulario;
import br.ufpb.dcx.aps.formbuilder.services.FormularioService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.jupiter.api.Assertions.*;


import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FormularioControllerTest {

    @InjectMocks
    FormularioController formularioController;
    @Mock
    FormularioService formularioService;

    @Test
    public void atualizarTest(){
        Formulario formularioAtualizado = new Formulario();
        formularioAtualizado.setTitulo("formulario atualizado");

        FormularioDTO formularioDTO = new FormularioDTO("teste");

        when(formularioService.atualizarFormulario(10L, formularioDTO)).thenReturn(formularioAtualizado);

        assertEquals("formulario atualizado", formularioController.atualizarFormulario(10L, formularioDTO).getTitulo());
    }


}
