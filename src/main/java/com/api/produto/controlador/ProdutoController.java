package com.api.produto.controlador;

import com.api.produto.model.Produto;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private static List<Produto> produtos = new ArrayList<>();

    @GetMapping
    public List<Produto> listarProdutos() {
        return produtos;
    }

    @GetMapping("/{id}")
    public Produto buscarProduto(@PathVariable Integer id) {

        for (Produto produto : produtos) {

            if (produto.getId().equals(id)) {
                return produto;
            }
        }
          return null;
    }


    @PostMapping
    public Produto adicionarProduto(@RequestBody Produto produto) {

        produtos.add(produto);

        return produto;
    }


    @PatchMapping("/{id}")
    public Produto atualizarProduto(
            @PathVariable Integer id,
            @RequestBody Produto dadosAtualizados) {

        for (Produto produto : produtos) {

             if (produto.getId().equals(id)) {

                if (dadosAtualizados.getNome() != null) {
                    produto.setNome(dadosAtualizados.getNome());
                }

                 if (dadosAtualizados.getPreco() != null) {
                    produto.setPreco(dadosAtualizados.getPreco());
                }

                  return produto;
            }
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public String excluirProduto(@PathVariable Integer id) {

        boolean removido = produtos.removeIf(
                produto -> produto.getId().equals(id));

        if (removido) {
            return "Produto removido com sucesso!";
        }

        return "Produto não encontrado!";
    }

}
