package br.ufpb.dcx.aps.formbuilder.services;

import br.ufpb.dcx.aps.formbuilder.models.Formulario;
import br.ufpb.dcx.aps.formbuilder.repositories.CampoRepository;
import br.ufpb.dcx.aps.formbuilder.repositories.FormularioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormularioService {

    @Autowired
    FormularioRepository formularioRepository;

    @Autowired
    CampoRepository campoRepository;

    public Formulario criarFormulario(Formulario formulario){
        this.formularioRepository.save(formulario);
        return formulario;
    }

}
