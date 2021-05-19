package br.com.grupo06.wishlist.domain.modelViews;

import br.com.grupo06.wishlist.domain.entity.ProdutoEntity;

public class ClienteProdutoSimples {

    private Integer id;

    private String nome;

    private String email;

    private ProdutoEntity produto;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ProdutoEntity getProduto() {
        return produto;
    }

    public void setProduto(ProdutoEntity produto) {
        this.produto = produto;
    }
}
