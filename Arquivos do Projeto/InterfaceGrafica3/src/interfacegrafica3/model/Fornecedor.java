/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfacegrafica3.model;

public class Fornecedor extends PessoaJuridica {

    private String categoria;
    private Uf uf;

    public Fornecedor(int id, String cnpj, String inscricaoEstadual, String nomeFantasia, 
                      String nome, String email, String endereco, String telefone, 
                      String categoria, Uf uf) {
        super(cnpj, inscricaoEstadual, nomeFantasia, nome, email, endereco, telefone, id);
        this.categoria = categoria;
        this.uf = uf;
    }
    public Fornecedor(){
    };
    public String getCategoria() {
        return categoria;
    }

    public Uf getUf() {
        return uf;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setUf(Uf uf) {
        this.uf = uf;
    }
}
