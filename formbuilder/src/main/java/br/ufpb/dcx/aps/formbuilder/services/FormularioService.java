package br.ufpb.dcx.aps.formbuilder.services;

import br.ufpb.dcx.aps.formbuilder.models.Campo;
import br.ufpb.dcx.aps.formbuilder.models.Formulario;
import br.ufpb.dcx.aps.formbuilder.repositories.CampoRepository;
import br.ufpb.dcx.aps.formbuilder.repositories.FormularioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class FormularioService {

    @Autowired
    FormularioRepository formularioRepository;

    @Autowired
    CampoRepository campoRepository;


    @PostConstruct
    private void initFormulario() {
        Formulario formulario = new Formulario("forms_1");
        List<Campo> campos = new LinkedList<>();

        Campo campo1 = new Campo("campo 1");
        Campo campo2 = new Campo("campo 2");
        Campo campo3 = new Campo("campo 3");

        this.campoRepository.save(campo1);
        this.campoRepository.save(campo2);
        this.campoRepository.save(campo3);

        campos.add(campo1);
        campos.add(campo2);
        campos.add(campo3);

        formulario.setCampos(campos);
        this.formularioRepository.save(formulario);
    }

    public Formulario criarFormulario(Formulario formulario){
        this.formularioRepository.save(formulario);
        return formulario;
    }

    public Formulario pegarPorTitulo(String titulo){
        return this.formularioRepository.getByTitulo(titulo);
    }

}
