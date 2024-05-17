package br.utfpr.edu;

public class Teste {
    private int idfixo;
    private String descricao;
    private String situacao;

    public int getIdfixo() {
        return idfixo;
    }

    public void setIdfixo(int idfixo) {
        this.idfixo = idfixo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public Teste() {
    }

    public Teste(int idfixo, String descricao, String situacao) {
        this.idfixo = idfixo;
        this.descricao = descricao;
        this.situacao = situacao;
    }
}
