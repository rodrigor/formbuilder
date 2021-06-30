package br.ufpb.dcx.aps.formbuilder.services;

import br.ufpb.dcx.aps.formbuilder.exceptions.FormularioNaoEncontradoException;
import br.ufpb.dcx.aps.formbuilder.models.Campo;
import br.ufpb.dcx.aps.formbuilder.models.Formulario;
import br.ufpb.dcx.aps.formbuilder.repositories.CampoRepository;
import br.ufpb.dcx.aps.formbuilder.repositories.FormularioRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;

@Service
public class FormularioService {

    @Autowired
    private FormularioRepository formularioRepository;

    @Autowired
    private CampoRepository campoRepository;

    public Formulario buscaFormulario(long formularioId){
        if(!this.formularioRepository.existsById(formularioId)){
            throw new FormularioNaoEncontradoException("Formulario não encontrado");
        }
        return this.formularioRepository.findById(formularioId);
    }

    public List<Formulario> listaFormularios(){
        return this.formularioRepository.findAll();
    }

    public List<Campo> listaCampos(long formularioId){
        Formulario formulario = this.formularioRepository.findById(formularioId);
        return this.campoRepository.findAllByFormulario(formulario);
    }

    @PostConstruct
    public Formulario criarNovoFormulario(String titulo, List<String> labels){

        Formulario novoFormulario = new Formulario(titulo);

        List<Campo> campos = new LinkedList<>();

        for(String label: labels){
            Campo novoCampo = new Campo(label, novoFormulario);
            campos.add(novoCampo);
            this.formularioRepository.save(novoCampo);
        }

        novoFormulario.setCampos(campos);
        this.formularioRepository.save(novoFormulario);
        return novoFormulario;
    }


}
