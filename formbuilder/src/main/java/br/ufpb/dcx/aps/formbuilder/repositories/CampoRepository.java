package br.ufpb.dcx.aps.formbuilder.repositories;

import br.ufpb.dcx.aps.formbuilder.models.Campo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface CampoRepository extends JpaRepository<Campo, Long> {
}
