package br.ufpb.dcx.aps.formbuilder.DTOs;


import br.ufpb.dcx.aps.formbuilder.models.Formulario;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CampoDTO {

    @NotBlank
    private String label;
    @NotBlank
    private FormularioDTO formulario;

    public CampoDTO(String label, FormularioDTO formulario){
        this.label = label;
        this.formulario = formulario;
    }

}
