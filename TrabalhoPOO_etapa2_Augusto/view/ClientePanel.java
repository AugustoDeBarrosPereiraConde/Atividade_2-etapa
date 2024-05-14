package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import controller.ClienteController;
import model.Cliente;

public class ClientePanel extends JPanel {
    private ClienteController clienteController;
    private JTextField nomeField, cpfField, dataNascimentoField, emailField;
    private JTable clientesTable;
    private DefaultTableModel tableModel;

    public ClientePanel() {
        this.clienteController = new ClienteController();
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(5, 2));

        formPanel.add(new JLabel("Nome:"));
        nomeField = new JTextField();
        formPanel.add(nomeField);

        formPanel.add(new JLabel("CPF:"));
        cpfField = new JTextField();
        formPanel.add(cpfField);

        formPanel.add(new JLabel("Data de Nascimento (yyyy-mm-dd):"));
        dataNascimentoField = new JTextField();
        formPanel.add(dataNascimentoField);

        formPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        formPanel.add(emailField);

        JButton addButton = new JButton("Adicionar Cliente");
        addButton.addActionListener(e -> adicionarCliente());
        formPanel.add(addButton);

        JButton updateButton = new JButton("Atualizar Cliente");
        updateButton.addActionListener(e -> atualizarCliente());
        formPanel.add(updateButton);

        JButton removeButton = new JButton("Remover Cliente");
        removeButton.addActionListener(e -> removerCliente());
        formPanel.add(removeButton);

        add(formPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new Object[]{"ID", "Nome", "CPF", "Data de Nascimento", "Email"}, 0);
        clientesTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(clientesTable);
        add(scrollPane, BorderLayout.CENTER);

        listarClientes();
    }

    private void adicionarCliente() {
        Cliente cliente = new Cliente();
        cliente.setNome(nomeField.getText());
        cliente.setCpf(cpfField.getText());
        cliente.setDataNascimento(java.sql.Date.valueOf(dataNascimentoField.getText()));
        cliente.setEmail(emailField.getText());
        clienteController.adicionarCliente(cliente);
        listarClientes();
    }

    private void atualizarCliente() {
        int selectedRow = clientesTable.getSelectedRow();
        if (selectedRow != -1) {
            int id = (int) tableModel.getValueAt(selectedRow, 0);
            Cliente cliente = new Cliente();
            cliente.setId(id);
            cliente.setNome(nomeField.getText());
            cliente.setCpf(cpfField.getText());
            cliente.setDataNascimento(java.sql.Date.valueOf(dataNascimentoField.getText()));
            cliente.setEmail(emailField.getText());
            clienteController.atualizarCliente(cliente);
            listarClientes();
        }
    }

    private void removerCliente() {
        int selectedRow = clientesTable.getSelectedRow();
        if (selectedRow != -1) {
            int id = (int) tableModel.getValueAt(selectedRow, 0);
            clienteController.removerCliente(id);
            listarClientes();
        }
    }

    private void listarClientes() {
        List<Cliente> clientes = clienteController.listarClientes();
        tableModel.setRowCount(0);
        for (Cliente cliente : clientes) {
            tableModel.addRow(new Object[]{cliente.getId(), cliente.getNome(), cliente.getCpf(), cliente.getDataNascimento(), cliente.getEmail()});
        }
    }
}
