package br.ufpb.dcx.aps.formbuilder.repositories;

import br.ufpb.dcx.aps.formbuilder.models.Campo;
import br.ufpb.dcx.aps.formbuilder.models.Resultado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampoRepository extends JpaRepository<Campo, Long> {
}
