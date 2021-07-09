package br.ufpb.dcx.aps.formbuilder.services;

import br.ufpb.dcx.aps.formbuilder.DTOs.FormularioDTO;
import br.ufpb.dcx.aps.formbuilder.exceptions.FormularioNaoEncontradoException;
import br.ufpb.dcx.aps.formbuilder.models.Campo;
import br.ufpb.dcx.aps.formbuilder.models.Formulario;
import br.ufpb.dcx.aps.formbuilder.models.Resultado;
import br.ufpb.dcx.aps.formbuilder.repositories.FormularioRepository;
import br.ufpb.dcx.aps.formbuilder.validador.ValidadorEmail;
import br.ufpb.dcx.aps.formbuilder.validador.ValidadorInteiro;
import br.ufpb.dcx.aps.formbuilder.validador.ValidadorTextoSimples;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.Optional;


@Service
public class FormularioService {

    @Autowired
    private FormularioRepository formularioRepository;

    @Autowired
    private CampoService campoService;

    @PostConstruct
    private void initFormulario() {
        Formulario formulario = new Formulario();
        formulario.setTitulo("forms_1");
        List<Campo> campos = new LinkedList<>();

        Campo campo1 = new Campo();
        campo1.setLabel("campo email");
        campo1.setValidador(new ValidadorEmail());

        Campo campo2 = new Campo();
        campo2.setLabel("campo nome");
        campo2.setValidador(new ValidadorTextoSimples(3, 256));

        Campo campo3 = new Campo();
        campo3.setLabel("campo inteiro");
        campo3.setValidador(new ValidadorInteiro(1, 10));


        this.campoService.salvarCampo(campo1);
        this.campoService.salvarCampo(campo2);
        this.campoService.salvarCampo(campo3);

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
        if (formularioDTO == null)
            throw new IllegalArgumentException("formularioDTO não pode ser nulo");


        if (formularioDTO.getCampos() == null || formularioDTO.getTitulo() == null)
            throw new IllegalArgumentException("formularioDTO não pode possuir atributos nulos");

        Formulario novoFormulario = new Formulario();
        novoFormulario.setTitulo(formularioDTO.getTitulo());
        this.campoService.salvarTodos(formularioDTO.getCampos());
        this.formularioRepository.save(novoFormulario);
        return novoFormulario;
    }

    public Formulario atualizarFormulario(long formularioId, FormularioDTO formulario) {
        if (formulario == null)
            throw new IllegalArgumentException("Parâmetro formulario não pode ser nulo");

        if (existe(formularioId)) {
            Formulario formAtualizado = formularioRepository.findById(formularioId);
            formAtualizado.setTitulo(formulario.getTitulo());
            formAtualizado.setCampos(formulario.getCampos());
            this.campoService.salvarTodos(formAtualizado.getCampos());
            this.formularioRepository.save(formAtualizado);
            return formAtualizado;
        }
        throw new FormularioNaoEncontradoException("Formulário não encontrado");
    }

    public Formulario deletarFormulario(long formularioId) {
        if (existe(formularioId)) {
            Formulario formularioDeletado = formularioRepository.findById(formularioId);
            formularioRepository.delete(formularioRepository.findById(formularioId));
            return formularioDeletado;
        }
        throw new FormularioNaoEncontradoException("Formulário não encontrado");
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

    public Formulario pegarPorId(long id) {
        return this.formularioRepository.findById(id);
    }

    public Resultado validar(){
       return new Resultado();
    }

    public List<Campo> getAllByCampos(Long id){
        Optional<Formulario> optForm = this.formularioRepository.findById(id);
        if(optForm.isPresent())
            return optForm.get().getCampos();
        throw new FormularioNaoEncontradoException("formulario não encontrado");
    }

    public Resultado validar(Long id){
        Resultado r = new Resultado();
        for (Campo c : this.getAllByCampos(id))
            if (c.validar().isErro())
                r.addMensagem(c.getId() + ": " + c.validar().getMensagem(0));
        return r;
    }
}
