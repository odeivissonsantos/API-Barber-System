package io.github.odeivissonsantos.enums;

public enum TipoConta {

    SALARIO("salario"),
    CORRENTE("corrente"),
    POUPANCA("poupanca");

    private final String descricao;

    TipoConta(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
