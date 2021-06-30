package br.ufpb.dcx.aps.formbuilder.controllers;

import br.ufpb.dcx.aps.formbuilder.models.Formulario;
import br.ufpb.dcx.aps.formbuilder.repositories.FormularioRepository;
import br.ufpb.dcx.aps.formbuilder.services.FormularioService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/formularios")
public class FormularioController {

    @Autowired
    private FormularioService formularioService;

    @GetMapping
    public List<Formulario> listar(){
        return this.formularioService.listaFormularios();
    }

    @GetMapping("{formularioId}")
    public Formulario buscar(@PathVariable long formularioId){
        return this.formularioService.buscaFormulario(formularioId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Formulario salvar(@RequestBody Formulario formulario){
        return this.formularioService.salvaFormulario(formulario);
    }
}
