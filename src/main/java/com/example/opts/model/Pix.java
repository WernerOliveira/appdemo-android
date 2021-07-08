package com.example.opts.model;

public class Pix {

    private String chave;
    private float saldo;
    private float cheque_especial;

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public float getCheque_especial() {
        return cheque_especial;
    }

    public void setCheque_especial(float cheque_especial) {
        this.cheque_especial = cheque_especial;
    }

    @Override
    public String toString() {
        return this.chave + "Chave: - R$ " + this.saldo;
    }
}
