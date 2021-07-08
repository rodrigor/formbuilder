package br.ufpb.dcx.aps.formbuilder.controllers;


import br.ufpb.dcx.aps.formbuilder.models.Campo;
import br.ufpb.dcx.aps.formbuilder.DTOs.FormularioDTO;
import br.ufpb.dcx.aps.formbuilder.models.Formulario;
import br.ufpb.dcx.aps.formbuilder.services.FormularioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping(value = "/formularios/")
public class FormularioController {

    @Autowired
    private FormularioService formularioService;

    @GetMapping(value = "", produces = "text/html")
    public ModelAndView  listar(){
        ModelAndView mv = new ModelAndView("listarFormularios");
        List<Formulario> formularios = this.formularioService.listaFormularios();
        mv.addObject("formularios", formularios);
        return mv;
    }

    @GetMapping(value = "/details", produces = "text/html")
    public ModelAndView visualizarFormulario(@RequestParam("titulo") String titulo) {
        ModelAndView mv = new ModelAndView("visualizarFormulario");
        Formulario formulario = this.formularioService.buscaFormulario(titulo);
        mv.addObject("formDinamico", formulario);
        return mv;
    }

    @GetMapping(value = "/criar", produces = "text/html")
    public ModelAndView criarFormulario() {
        ModelAndView mv = new ModelAndView("criarFormulario");
        mv.addObject("formulario", new Formulario());
        mv.addObject("campos", new LinkedList<Campo>());
        return mv;
    }
    @GetMapping("{formularioId}")
    public ModelAndView buscar(@PathVariable long formularioId){
        ModelAndView mv = new ModelAndView("visualizarFormulario");
        Formulario formulario = this.formularioService.buscaFormulario(formularioId);
        mv.addObject("formDinamico", formulario);
        return mv;
    }

    @PostMapping("/criar")
    public Formulario criarFormulario(FormularioDTO formulario){
        return this.formularioService.salvarFormulario(formulario);
    }

    @PutMapping("/atualizar/{formularioId}")
    public Formulario atualizarFormulario(@PathVariable long formularioId, FormularioDTO formlarioDTO){
        return this.formularioService.atualizarFormulario(formularioId,formlarioDTO);
    }

    @DeleteMapping("/deletar/{formularioId}")
    public Formulario deletarFormulario(@PathVariable long formularioId){
        return this.formularioService.deletarFormulario(formularioId);
    }

}
