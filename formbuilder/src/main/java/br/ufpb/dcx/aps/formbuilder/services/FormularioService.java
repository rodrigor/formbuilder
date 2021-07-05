package br.ufpb.dcx.aps.formbuilder.services;

import br.ufpb.dcx.aps.formbuilder.DTOs.FormularioDTO;
import br.ufpb.dcx.aps.formbuilder.exceptions.FormularioNaoEncontradoException;
import br.ufpb.dcx.aps.formbuilder.models.Campo;
import br.ufpb.dcx.aps.formbuilder.models.Formulario;
import br.ufpb.dcx.aps.formbuilder.repositories.CampoRepository;
import br.ufpb.dcx.aps.formbuilder.repositories.FormularioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.annotation.PostConstruct;
import java.util.LinkedList;


@Service
public class FormularioService {

    @Autowired
    private FormularioRepository formularioRepository;

    @Autowired
    private CampoRepository campoRepository;

    @PostConstruct
    private void initFormulario() {
        Formulario formulario = new Formulario();
        formulario.setTitulo("forms_1");
        List<Campo> campos = new LinkedList<>();

        Campo campo1 = new Campo();
        campo1.setLabel("campo 1");

        Campo campo2 = new Campo();
        campo2.setLabel("campo 2");

        Campo campo3 = new Campo();
        campo3.setLabel("campo 3");

        this.campoRepository.save(campo1);
        this.campoRepository.save(campo2);
        this.campoRepository.save(campo3);

        campos.add(campo1);
        campos.add(campo2);
        campos.add(campo3);

        formulario.setCampos(campos);
        this.formularioRepository.save(formulario);
    }

    public Formulario buscaFormulario(long formularioId) {
        if (this.existe(formularioId)) {
            return this.formularioRepository.findById(formularioId);
        }
        throw new FormularioNaoEncontradoException("Formulário não encontrado");
    }

    public Formulario buscaFormulario(String titulo) {
        if (this.existe(titulo)) {
            return this.formularioRepository.findByTitulo(titulo);
        }
        throw new FormularioNaoEncontradoException("Formulário não encontrado");
    }

    public List<Formulario> listaFormularios() {
        return this.formularioRepository.findAll();
    }

    public Formulario salvarFormulario(FormularioDTO formularioDTO) {
        if(formularioDTO == null)
            throw new IllegalArgumentException("formularioDTO não pode ser nulo");


        if (formularioDTO.getCampos()== null || formularioDTO.getTitulo() == null)
            throw new IllegalArgumentException("formularioDTO não pode possuir atributos nulos");

            Formulario novoFormulario = new Formulario();
            novoFormulario.setTitulo(formularioDTO.getTitulo());
            this.campoRepository.saveAll(formularioDTO.getCampos());
            this.formularioRepository.save(novoFormulario);
            return novoFormulario;
    }

    public boolean existe(Long id) {
        return this.pegarPorId(id) != null;
    }

    public boolean existe(String titulo) {
        return this.pegarPorTitulo(titulo) != null;
    }


    public Formulario pegarPorTitulo(String titulo) {
        return this.formularioRepository.findByTitulo(titulo);
    }

    // explicar criação
    public Formulario pegarPorId(long id){
        return this.formularioRepository.findById(id);
    }

    public List<Formulario> pegarTodos() {
        return this.formularioRepository.findAll();
    }

}
