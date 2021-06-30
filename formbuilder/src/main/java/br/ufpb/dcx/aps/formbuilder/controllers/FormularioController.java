package br.ufpb.dcx.aps.formbuilder.controllers;

import br.ufpb.dcx.aps.formbuilder.models.Formulario;
import br.ufpb.dcx.aps.formbuilder.services.FormularioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/formularios/")
public class FormularioController {

    @Autowired
    FormularioService formularioService;

    @GetMapping(value = "", produces = "text/html")
    public ModelAndView pegarFormulario() {
        ModelAndView mv = new ModelAndView("formulario");
        Formulario formulario = this.formularioService.pegarPorTitulo("forms_1");
        mv.addObject("formDinamico", formulario);
        return mv;
    }


}
