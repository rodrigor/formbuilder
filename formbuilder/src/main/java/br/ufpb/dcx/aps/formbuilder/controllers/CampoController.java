package br.ufpb.dcx.aps.formbuilder.controllers;

import br.ufpb.dcx.aps.formbuilder.models.Campo;
import br.ufpb.dcx.aps.formbuilder.models.Formulario;
import br.ufpb.dcx.aps.formbuilder.services.CampoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CampoController {

    @Autowired
    private CampoService campoService;

    @RequestMapping("/formularios/{formularioId}")
    public List<Campo> lista(Formulario formulario, @PathVariable long formularioId){
        return this.campoService.listaCampos(formulario);
    }
}
