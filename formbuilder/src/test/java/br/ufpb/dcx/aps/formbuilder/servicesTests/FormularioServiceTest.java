package br.ufpb.dcx.aps.formbuilder.servicesTests;
import br.ufpb.dcx.aps.formbuilder.DTOs.FormularioDTO;
import br.ufpb.dcx.aps.formbuilder.services.exceptions.FormularioNaoEncontradoException;
import br.ufpb.dcx.aps.formbuilder.models.Campo;
import br.ufpb.dcx.aps.formbuilder.models.Formulario;
import br.ufpb.dcx.aps.formbuilder.repositories.CampoRepository;
import br.ufpb.dcx.aps.formbuilder.repositories.FormularioRepository;
import br.ufpb.dcx.aps.formbuilder.services.FormularioService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class FormularioServiceTest {

    @InjectMocks
    private FormularioService formularioService;
    @Mock
    private FormularioRepository formularioRepository;
    @Mock
    private CampoRepository campoRepository;

    @Test
    public void salvarNovoFormularioTeste(){

        FormularioDTO novoFormulario = new FormularioDTO("teste mock");
        List<Campo> campos = new ArrayList<>();
        Campo campo1 = new Campo();
        Campo campo2 = new Campo();
        campos.add(campo1);
        campos.add(campo2);
        novoFormulario.setCampos(campos);

        assertEquals("teste mock",formularioService.salvarFormulario(novoFormulario).getTitulo());

        Formulario formularioReal = new Formulario();
        formularioReal.setTitulo(novoFormulario.getTitulo());
        formularioReal.setCampos(novoFormulario.getCampos());

        verify(formularioRepository, times(1)).save(formularioReal);
        verify(campoRepository, times(1)).saveAll(formularioReal.getCampos());

        assertEquals(formularioReal, formularioService.salvarFormulario(novoFormulario));


    }

    @Test
    public void testeParametroNulo (){

        Exception e = assertThrows(IllegalArgumentException.class, () ->
                formularioService.salvarFormulario(null));
        assertEquals("formularioDTO não pode ser nulo", e.getMessage());

        FormularioDTO nulos = new FormularioDTO(null);
        Exception f = assertThrows(IllegalArgumentException.class, () ->
                formularioService.salvarFormulario(nulos));
        assertEquals("formularioDTO não pode possuir atributos nulos", f.getMessage());
    }


    @Test
    public void existeTeste(){
        Formulario formulario = new Formulario();
        formulario.setTitulo("este formulario");

        when(formularioRepository.findById(42L)).thenReturn(formulario);
        when(formularioRepository.findByTitulo("titulo certo")).thenReturn(formulario);

        assertTrue(formularioService.existe("titulo certo"));
        assertTrue(formularioService.existe(42L));

        assertFalse(formularioService.existe("qualquer titulo"));
        assertFalse(formularioService.existe(1L));
    }

    @Test
    public void buscadoresTeste(){

        Formulario formulario = new Formulario();
        formulario.setTitulo("buscado com sucesso");

        when(formularioRepository.findById(333L)).thenReturn(formulario);
        when(formularioRepository.findByTitulo("titulo certo")).thenReturn(formulario);

        assertEquals("buscado com sucesso", formularioService.buscaFormulario("titulo certo").getTitulo());
        assertNotNull(formularioService.buscaFormulario(333L));

        Exception e = assertThrows(FormularioNaoEncontradoException.class, () ->
                formularioService.buscaFormulario(4L));
        assertEquals("Formulário não encontrado", e.getMessage());
    }

    @Test
    public void atualizarFormularioTest(){

        Formulario formulario = new Formulario();
        formulario.setTitulo("desatualizado");

        List<Campo> campos  = new ArrayList<>();
        campos.add(new Campo());
        formulario.setCampos(campos);

        when(formularioRepository.findById(89L)).thenReturn(formulario);

        FormularioDTO formularioAtualizado = new FormularioDTO("atualizado");

        assertEquals("atualizado", formularioService.atualizarFormulario(89L,formularioAtualizado).getTitulo());
        assertEquals(formulario.getCampos(), formularioService.atualizarFormulario(89L, formularioAtualizado).getCampos());

        verify(formularioRepository, times(2)).save(formulario);
        verify(formularioRepository, times(4)).findById(89L);
    }

    @Test
    public void deletarFormularioTest(){

        Formulario formularioSalvo = new Formulario();
        when(formularioRepository.findById(4L)).thenReturn(formularioSalvo);

        assertEquals(formularioSalvo, formularioService.deletarFormulario(4L));

        Exception e = assertThrows(FormularioNaoEncontradoException.class, () ->
                formularioService.deletarFormulario(10L));
        assertEquals("Formulário não encontrado", e.getMessage());

    }
}
