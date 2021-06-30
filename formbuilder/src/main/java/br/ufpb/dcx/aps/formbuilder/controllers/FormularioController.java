package br.ufpb.dcx.aps.formbuilder.controllers;

import br.ufpb.dcx.aps.formbuilder.DTOs.CampoDTO;
import br.ufpb.dcx.aps.formbuilder.DTOs.FormularioDTO;
import br.ufpb.dcx.aps.formbuilder.models.Campo;
import br.ufpb.dcx.aps.formbuilder.models.Formulario;
import br.ufpb.dcx.aps.formbuilder.services.FormularioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/formularios/")
public class FormularioController {

    @Autowired
    private FormularioService formularioService;

    @GetMapping
    public List<Formulario> listar(){
        return this.formularioService.listaFormularios();
    }

    @GetMapping("{formularioId}/")
    public Formulario buscar(@PathVariable long formularioId){
        return this.formularioService.buscaFormulario(formularioId);
    }

    @GetMapping("criar/")
    public Formulario salvarNovoFormulario(String titulo, List<String> labels){

        return this.criarESalvar(this.formularioService.criarNovoFormularioDTO(titulo,labels));
    }

    @GetMapping("{formularioId}")
    public List<Campo> listaCampos(@PathVariable long formularioId){
        return this.formularioService.listaCampos(formularioId);
    }

    public Formulario criarESalvar(FormularioDTO formularioDTO){
        Formulario novoFormulario = new Formulario();
        novoFormulario.setTitulo(formularioDTO.getTitulo());

        List<CampoDTO> camposDTO = formularioDTO.getCampos();
        List<Campo> campos = new ArrayList<>();

        for(CampoDTO campoDTO: camposDTO){
            Campo novoCampo = new Campo();
            novoCampo.setFormulario(novoFormulario);
            novoCampo.setLabel(campoDTO.getLabel());
            campos.add(novoCampo);
            this.formularioService.salvarCampo(novoCampo);
        }

        novoFormulario.setCampos(campos);
        this.formularioService.salvarFormulario(novoFormulario);
        return novoFormulario;
    }
}
