package br.ufpb.dcx.aps.formbuilder.DTOs;

import br.ufpb.dcx.aps.formbuilder.models.Campo;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class FormularioDTO {

    @NotBlank
    private String titulo;
    @NotBlank
    private List<Campo> campos = new ArrayList<>();


    public FormularioDTO(String titulo){
        this.titulo = titulo;
    }
}
