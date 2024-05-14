package dao;

import model.Venda;
import model.Produto;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VendaDAO {
    private Connection connection;

    public VendaDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    public void adicionarVenda(Venda venda) {
        String sql = "INSERT INTO venda (cliente_id, forma_pagamento, valor_total) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, venda.getCliente().getId());
            stmt.setString(2, venda.getFormaPagamento().getTipo());
            stmt.setDouble(3, venda.getValorTotal());
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int vendaId = generatedKeys.getInt(1);
                adicionarProdutosVenda(vendaId, venda.getProdutos());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void adicionarProdutosVenda(int vendaId, List<Produto> produtos) {
        String sql = "INSERT INTO venda_produto (venda_id, produto_id, quantidade) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            for (Produto produto : produtos) {
                stmt.setInt(1, vendaId);
                stmt.setInt(2, produto.getCodigo());
                stmt.setInt(3, produto.getQuantidadeEstoque());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Venda> listarVendas() {
        List<Venda> vendas = new ArrayList<>();
        String sql = "SELECT * FROM venda";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Venda venda = new Venda();
                venda.setId(rs.getInt("id"));
                venda.setCliente(clienteDAO.buscarClientePorId(rs.getInt("cliente_id")));
                venda.setValorTotal(rs.getDouble("valor_total"));
                vendas.add(venda);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vendas;
    }
    
}
