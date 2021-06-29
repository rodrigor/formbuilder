package br.ufpb.dcx.aps.formbuilder.controllers;

import br.ufpb.dcx.aps.formbuilder.models.Produto;
import br.ufpb.dcx.aps.formbuilder.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/produtos")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;


    @GetMapping(value = "", produces = "application/json")
    public ModelAndView formCadastrarProduto(@RequestBody Produto produto) {
        return new ModelAndView("criarNovoProduto");
    }

    @PostMapping(value = "/criar", produces = "application/json")
    public ModelAndView createNewUser(@RequestBody Produto produto) {
        ModelAndView mv = new ModelAndView("criarNovoProduto");
        mv.addObject("produto", new Produto());
        produtoService.cadastrarNovoProduto(produto);
        return new ModelAndView("criarNovoProduto");
    }


}
