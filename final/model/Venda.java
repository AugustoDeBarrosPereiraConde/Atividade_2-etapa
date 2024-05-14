package model;

import java.util.ArrayList;
import java.util.List;

public class Venda {
    private List<Produto> produtos = new ArrayList<>();
    private Cliente cliente;
    private FormaPagamento formaPagamento;
    private double valorTotal;

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
        calcularValorTotal();
    }

    public void calcularValorTotal() {
        valorTotal = produtos.stream().mapToDouble(Produto::getPrecoVenda).sum();
    }

    public void aplicarDesconto(double percentual) {
        valorTotal -= valorTotal * (percentual / 100);
    }

    public void finalizarVenda() {
        formaPagamento.processarPagamento();
    }

    public List<Produto> getProdutos() { return produtos; }
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    public FormaPagamento getFormaPagamento() { return formaPagamento; }
    public void setFormaPagamento(FormaPagamento formaPagamento) { this.formaPagamento = formaPagamento; }
    public double getValorTotal() { return valorTotal; }
}
