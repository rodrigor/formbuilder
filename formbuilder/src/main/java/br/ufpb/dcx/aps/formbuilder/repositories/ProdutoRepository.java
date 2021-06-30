package br.ufpb.dcx.aps.formbuilder.repositories;

import br.ufpb.dcx.aps.formbuilder.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}

