package br.ufpb.dcx.aps.formbuilder.controllers;

import br.ufpb.dcx.aps.formbuilder.models.Campo;
import br.ufpb.dcx.aps.formbuilder.models.Formulario;
import br.ufpb.dcx.aps.formbuilder.repositories.FormularioRepository;
import br.ufpb.dcx.aps.formbuilder.services.FormularioService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
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
    public Formulario salvar(String titulo, List<String> labels){
        return this.formularioService.criarNovoFormulario(titulo,labels);
    }

    @GetMapping("{formularioId}")
    public List<Campo> listaCampos(@PathVariable long formularioId){
        return this.formularioService.listaCampos(formularioId);
    }

}
