package controller;

import model.Venda;
import dao.VendaDAO;

import java.util.List;

public class VendaController {
    private VendaDAO vendaDAO;

    public VendaController() {
        this.vendaDAO = new VendaDAO();
    }

    public void adicionarVenda(Venda venda) {
        vendaDAO.adicionarVenda(venda);
    }
    
    public List<Venda> listarVendas() {
        return vendaDAO.listarVendas();
    }
    
}
