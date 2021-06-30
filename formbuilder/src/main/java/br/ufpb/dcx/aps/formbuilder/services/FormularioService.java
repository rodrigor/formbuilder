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

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.LinkedList;
import java.util.List;

@Service
public class FormularioService {

    @Autowired
    private FormularioRepository formularioRepository;

    @Autowired
    private CampoRepository campoRepository;


    public Formulario buscaFormulario(long formularioId){
        if(!this.formularioRepository.existById(formularioId)){
            throw new FormularioNaoEncontradoException("Formulário não encontrado");
        }
        return this.formularioRepository.findById(formularioId);
    }

    public Formulario buscaFormulario(String titulo){
        if(!this.formularioRepository.existByTitulo(titulo)){
            throw new FormularioNaoEncontradoException("Formulário não encontrado");
        }
        return this.formularioRepository.findByTitulo(titulo);
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

    public FormularioDTO criarNovoFormularioDTO(@NotBlank String titulo, @NotNull List<String> labels){

        FormularioDTO novoFormularioDTO = new FormularioDTO(titulo);

        List<CampoDTO> camposDTO = new LinkedList<>();

        for(String label: labels){
            CampoDTO novoCampoDTO = new CampoDTO(label, novoFormularioDTO);
            camposDTO.add(novoCampoDTO);
        }

        novoFormularioDTO.setCampos(camposDTO);
        return novoFormularioDTO;
    }

}
