package br.com.springboot.curso.model;

import jakarta.persistence.*;

import java.io.Serializable;

// Indica que esta classe é uma entidade JPA e está mapeada para uma tabela no banco de dados.
@Entity
// Define um gerador de sequência para gerar valores para a chave primária.
@SequenceGenerator(name = "seq_usuario" , sequenceName = "seq_usuario" , allocationSize = 1, initialValue = 1)
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L; // Número de versão da serialização.

    @Id // Define o identificador único para a entidade.
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usuario")
    private Long Id;
    private String nome;
    private Integer idade;


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


}
