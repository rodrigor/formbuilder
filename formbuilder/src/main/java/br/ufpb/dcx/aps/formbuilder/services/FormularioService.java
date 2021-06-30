package br.ufpb.dcx.aps.formbuilder.services;

import br.ufpb.dcx.aps.formbuilder.exceptions.FormularioNaoEncontradoException;
import br.ufpb.dcx.aps.formbuilder.models.Formulario;
import br.ufpb.dcx.aps.formbuilder.repositories.CampoRepository;
import br.ufpb.dcx.aps.formbuilder.repositories.FormularioRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormularioService {

    @Autowired
    FormularioRepository formularioRepository;


    public Formulario salvaFormulario(Formulario formulario){
        this.formularioRepository.save(formulario);
        return formulario;
    }

    public Formulario buscaFormulario(long formularioId){
        if(!this.formularioRepository.existsById(formularioId)){
            throw new FormularioNaoEncontradoException("Formulario não encontrado");
        }
        return this.formularioRepository.findById(formularioId);
    }

    public List<Formulario> listaFormularios(){
        return this.formularioRepository.findAll();
    }
}
