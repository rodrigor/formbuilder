package br.ufpb.dcx.aps.formbuilder.controllers;

import br.ufpb.dcx.aps.formbuilder.models.Produto;
import br.ufpb.dcx.aps.formbuilder.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/produtos/")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @GetMapping(value = "/", produces = "text/html")
    public ModelAndView formCadastrarProduto() {
        ModelAndView mv = new ModelAndView("criarNovoProduto");
        mv.addObject("produtoForm", new Produto());
        return mv;
    }

    @PostMapping(value = "/criar", produces = "text/html")
    public ModelAndView createNewUser(@ModelAttribute Produto produto) {
        produtoService.cadastrarNovoProduto(produto);
        return new ModelAndView("index");
    }

}
