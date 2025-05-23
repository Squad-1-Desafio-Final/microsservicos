package br.com.acabouMony_conta.tipos;

public enum TipoPagamento {

    CREDITO("credito"),
    DEBITO("debito");

    private String tipo;

    TipoPagamento(String tipo) {
        this.tipo = tipo;
    }

    public static TipoPagamento fromString(String valor) {
        for (TipoPagamento tipo : TipoPagamento.values()) {
            if (tipo.tipo.equalsIgnoreCase(valor)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Nenhum categoria encontrado");
    }
}
