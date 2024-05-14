package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import controller.VendaController;
import model.Venda;
import model.Produto;
import model.Cliente;
import model.FormaPagamento;
import model.Dinheiro;
import model.Cartao;
import model.Cheque;

public class VendaPanel extends JPanel {
    private VendaController vendaController;
    private JTextField clienteField, produtoField, quantidadeField, descontoField;
    private JComboBox<String> formaPagamentoComboBox;
    private JTable vendasTable;
    private DefaultTableModel tableModel;

    public VendaPanel() {
        this.vendaController = new VendaController();
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(6, 2));

        formPanel.add(new JLabel("Cliente ID:"));
        clienteField = new JTextField();
        formPanel.add(clienteField);

        formPanel.add(new JLabel("Produto ID:"));
        produtoField = new JTextField();
        formPanel.add(produtoField);

        formPanel.add(new JLabel("Quantidade:"));
        quantidadeField = new JTextField();
        formPanel.add(quantidadeField);

        formPanel.add(new JLabel("Forma de Pagamento:"));
        formaPagamentoComboBox = new JComboBox<>(new String[]{"Dinheiro", "Cartão", "Cheque"});
        formPanel.add(formaPagamentoComboBox);

        formPanel.add(new JLabel("Desconto (%):"));
        descontoField = new JTextField();
        formPanel.add(descontoField);

        JButton addButton = new JButton("Realizar Venda");
        addButton.addActionListener(e -> realizarVenda());
        formPanel.add(addButton);

        add(formPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new Object[]{"Venda ID", "Cliente", "Produtos", "Valor Total", "Forma de Pagamento"}, 0);
        vendasTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(vendasTable);
        add(scrollPane, BorderLayout.CENTER);

        listarVendas();
    }

    private void realizarVenda() {
        Venda venda = new Venda();

        Cliente cliente = new Cliente();
        cliente.setId(Integer.parseInt(clienteField.getText()));
        venda.setCliente(cliente);

        Produto produto = new Produto();
        produto.setCodigo(Integer.parseInt(produtoField.getText()));
        produto.setQuantidadeEstoque(Integer.parseInt(quantidadeField.getText()));
        venda.adicionarProduto(produto);

        String formaPagamento = (String) formaPagamentoComboBox.getSelectedItem();
        switch (formaPagamento) {
            case "Dinheiro":
                venda.setFormaPagamento(new Dinheiro());
                break;
            case "Cartão":
                venda.setFormaPagamento(new Cartao());
                break;
            case "Cheque":
                venda.setFormaPagamento(new Cheque());
                break;
        }

        double desconto = Double.parseDouble(descontoField.getText());
        venda.aplicarDesconto(desconto);

        venda.finalizarVenda();
        vendaController.adicionarVenda(venda);

        listarVendas();
    }

    private void listarVendas() {
        List<Venda> vendas = vendaController.listarVendas();
        tableModel.setRowCount(0);
        for (Venda venda : vendas) {
            tableModel.addRow(new Object[]{venda.getId(), venda.getCliente().getNome(), venda.getProdutos().size(), venda.getValorTotal(), venda.getFormaPagamento().getTipo()});
        }
    }
}
