package br.ufpb.dcx.aps.formbuilder.repositories;

import br.ufpb.dcx.aps.formbuilder.models.Campo;
import br.ufpb.dcx.aps.formbuilder.models.Formulario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

public interface CampoRepository <T, ID extends Serializable> extends JpaRepository<Campo, Long> {

    public Campo findById(long id);

    public List<Campo> findAllByFormulario(Formulario formulario);
}
