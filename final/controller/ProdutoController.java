package controller;

import model.Produto;
import dao.ProdutoDAO;

import java.util.List;

public class ProdutoController {
    private ProdutoDAO produtoDAO;

    public ProdutoController() {
        this.produtoDAO = new ProdutoDAO();
    }

    public void adicionarProduto(Produto produto) {
        produtoDAO.adicionarProduto(produto);
    }

    public List<Produto> listarProdutos() {
        return produtoDAO.listarProdutos();
    }

}
