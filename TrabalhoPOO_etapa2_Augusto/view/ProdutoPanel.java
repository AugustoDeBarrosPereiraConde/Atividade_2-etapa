package view;

import javax.swing.*;
import java.awt.*;
import controller.ProdutoController;
import model.Produto;
import model.ProdutoEletronico;

public class ProdutoPanel extends JPanel {
    private ProdutoController produtoController;
    private JTextField nomeField, descricaoField, precoCustoField, precoVendaField, quantidadeField, voltagemField;

    public ProdutoPanel() {
        this.produtoController = new ProdutoController();
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(7, 2));

        formPanel.add(new JLabel("Nome:"));
        nomeField = new JTextField();
        formPanel.add(nomeField);

        formPanel.add(new JLabel("Descrição:"));
        descricaoField = new JTextField();
        formPanel.add(descricaoField);

        formPanel.add(new JLabel("Preço de Custo:"));
        precoCustoField = new JTextField();
        formPanel.add(precoCustoField);

        formPanel.add(new JLabel("Preço de Venda:"));
        precoVendaField = new JTextField();
        formPanel.add(precoVendaField);

        formPanel.add(new JLabel("Quantidade em Estoque:"));
        quantidadeField = new JTextField();
        formPanel.add(quantidadeField);

        formPanel.add(new JLabel("Voltagem:"));
        voltagemField = new JTextField();
        formPanel.add(voltagemField);

        JButton addButton = new JButton("Adicionar Produto");
        addButton.addActionListener(e -> adicionarProduto());
        formPanel.add(addButton);

        add(formPanel, BorderLayout.CENTER);
    }

    private void adicionarProduto() {
        ProdutoEletronico produto = new ProdutoEletronico();
        produto.setNome(nomeField.getText());
        produto.setDescricao(descricaoField.getText());
        produto.setPrecoCusto(Double.parseDouble(precoCustoField.getText()));
        produto.setPrecoVenda(Double.parseDouble(precoVendaField.getText()));
        produto.setQuantidadeEstoque(Integer.parseInt(quantidadeField.getText()));
        produto.setVoltagem(voltagemField.getText());
        produtoController.adicionarProduto(produto);
    }

}
