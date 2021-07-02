package br.ufpb.dcx.aps.formbuilder.services;

import br.ufpb.dcx.aps.formbuilder.DTOs.CampoDTO;
import br.ufpb.dcx.aps.formbuilder.DTOs.FormularioDTO;
import br.ufpb.dcx.aps.formbuilder.exceptions.FormularioNaoEncontradoException;
import br.ufpb.dcx.aps.formbuilder.models.Campo;
import br.ufpb.dcx.aps.formbuilder.models.Formulario;
import br.ufpb.dcx.aps.formbuilder.repositories.CampoRepository;
import br.ufpb.dcx.aps.formbuilder.repositories.FormularioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class FormularioService {

    @Autowired
    private FormularioRepository formularioRepository;

    @Autowired
    private CampoRepository campoRepository;


    public Formulario buscaFormulario(long formularioId){
        if(ifExists(formularioId)) {
            return this.formularioRepository.findById(formularioId);
        }
        throw new FormularioNaoEncontradoException("Formulário não encontrado!");
    }

    public Formulario buscaFormulario(String titulo){
        if(ifExists(titulo)) {
            return this.formularioRepository.findByTitulo(titulo);
        }
        throw new FormularioNaoEncontradoException("Formulário não encontrado!");
    }

    public List<Formulario> listaFormularios(){
        return this.formularioRepository.findAll();
    }

    public List<Campo> listaCampos(long formularioId){
        return this.formularioRepository.findById(formularioId).getCampos();
    }

    public Campo salvarCampo(Campo campo){
        this.campoRepository.save(campo);
        return campo;
    }

    public Formulario salvarFormulario(FormularioDTO formularioDTO){
        Formulario novoFormulario = new Formulario(formularioDTO.getTitulo());
        this.campoRepository.saveAll(formularioDTO.getCampos());
        this.formularioRepository.save(novoFormulario);
        return novoFormulario;
    }


    public boolean ifExists(Long id){
        Optional<Formulario> form = this.formularioRepository.findById(id);
        return form.isPresent();
    }

    public boolean ifExists(String titulo){
        Formulario form = this.formularioRepository.findByTitulo(titulo);
        return (form != null);
    }

}
