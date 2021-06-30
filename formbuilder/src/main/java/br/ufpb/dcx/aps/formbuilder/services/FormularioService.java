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
        Formulario formulario = this.formularioRepository.findById(formularioId);
        return this.campoRepository.findAllByFormulario(formulario);
    }

    public Campo salvarCampo(Campo campo){
        this.campoRepository.save(campo);
        return campo;
    }

    public Formulario salvarFormulario(Formulario formulario){
        this.formularioRepository.save(formulario);
        return formulario;
    }

    public FormularioDTO criarNovoFormularioDTO( String titulo,  List<String> labels){

        FormularioDTO novoFormularioDTO = new FormularioDTO(titulo);

        List<CampoDTO> camposDTO = new LinkedList<>();

        for(String label: labels){
            CampoDTO novoCampoDTO = new CampoDTO(label, novoFormularioDTO);
            camposDTO.add(novoCampoDTO);
        }

        novoFormularioDTO.setCampos(camposDTO);
        return novoFormularioDTO;
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
