package model;

public class Cheque extends FormaPagamento {
    private String numeroCheque;

    public String getNumeroCheque() { return numeroCheque; }
    public void setNumeroCheque(String numeroCheque) { this.numeroCheque = numeroCheque; }

    @Override
    public void processarPagamento() {
        System.out.println("Processando pagamento com cheque.");
    }
}
