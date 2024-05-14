package model;

public class Dinheiro extends FormaPagamento {
    @Override
    public void processarPagamento() {
        System.out.println("Processando pagamento em dinheiro.");
    }
}
