package br.ufpb.dcx.aps.formbuilder.repositories;

import br.ufpb.dcx.aps.formbuilder.models.Campo;
import br.ufpb.dcx.aps.formbuilder.models.Formulario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormularioRepository extends JpaRepository<Formulario, Long> {

    Formulario findByTitulo(String titulo);

    public Formulario findById(long id);

    @Query("SELECT c FROM Campo c, Formulario f WHERE c.id = f.id")
    List<Campo> findCampos();

}
