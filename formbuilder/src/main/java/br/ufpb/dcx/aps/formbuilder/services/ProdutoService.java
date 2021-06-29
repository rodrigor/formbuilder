package br.ufpb.dcx.aps.formbuilder.services;

import br.ufpb.dcx.aps.formbuilder.models.Produto;
import br.ufpb.dcx.aps.formbuilder.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

    public Produto cadastrarNovoProduto(Produto produto) {
        this.produtoRepository.save(produto);
        return produto;
    }

}
