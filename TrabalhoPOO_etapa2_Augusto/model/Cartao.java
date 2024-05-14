package model;

public class Cartao extends FormaPagamento {
    private String numeroCartao;

    public String getNumeroCartao() { return numeroCartao; }
    public void setNumeroCartao(String numeroCartao) { this.numeroCartao = numeroCartao; }

    @Override
    public void processarPagamento() {
        System.out.println("Processando pagamento com cartão.");
    }
}
