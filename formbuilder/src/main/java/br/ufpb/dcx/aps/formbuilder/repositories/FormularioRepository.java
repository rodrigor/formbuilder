package br.ufpb.dcx.aps.formbuilder.repositories;

import br.ufpb.dcx.aps.formbuilder.models.Formulario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface FormularioRepository <T, ID extends Serializable> extends JpaRepository<Formulario, Long> {

    public Formulario findById(long id);
    public boolean existByTitulo(String titulo);
    public Formulario findByTitulo(String titulo);
    public boolean existById(long id);
}
