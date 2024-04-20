package br.com.springboot.curso.Repository;

import br.com.springboot.curso.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.yaml.snakeyaml.events.Event;

import java.util.List;

// Esta anotação indica que esta interface é um componente do Spring responsável por acessar e manipular dados relacionados à entidade Usuario.
@Repository
// Esta interface estende a interface JpaRepository, fornecida pelo Spring Data JPA, que fornece métodos padrão para executar operações CRUD (Create, Read, Update, Delete) em uma entidade.
// O primeiro parâmetro especifica o tipo de entidade com a qual esta interface trabalha (Usuario neste caso).
// O segundo parâmetro especifica o tipo do ID da entidade (Long neste caso).
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {


    /* Aqui você está declarando um método chamado buscarPorNome que utiliza a anotação @Query.
    A anotação @Query é usada para declarar consultas personalizadas no Spring Data JPA.
    Dentro dos parênteses da anotação @Query, você especifica a consulta JPQL (Java Persistence Query Language),
    que é uma linguagem de consulta orientada a objetos similar ao SQL, mas opera em entidades
    JPA em vez de tabelas de banco de dados.

    A consulta JPQL declarada aqui é: "select u from Usuario u where u.nome like %?1%".
    Isso significa que estamos selecionando todas as entidades Usuario onde o nome corresponde a um
    parâmetro passado na consulta. O %?1% é um parâmetro que será substituído pelo valor passado para o
    método buscarPorNome. O % é um caractere curinga que indica que o nome pode corresponder a qualquer sequência de
    caracteres antes ou depois do valor do parâmetro.

    Então, quando você chama o método buscarPorNome, passando um nome como argumento,
    o Spring Data JPA substitui ?1 pelo valor do parâmetro e executa a consulta JPQL para recuperar os usuários
     cujos nomes correspondem ao padrão especificado.

    Este método retorna uma lista de objetos Usuario que correspondem aos critérios da consulta.

    Esse método é útil quando você precisa buscar usuários por parte do nome, como em pesquisas onde o
    usuário pode digitar apenas uma parte do nome e esperar que o sistema retorne todos os usuários cujos nomes contêm
    essa parte.*/
    @Query(value = "select u from Usuario u where upper(trim(u.nome)) like %?1%")
    List<Usuario> buscarPorNome (String name);

}














//Nesse caso, a interface UsuarioRepository age como um intermediário entre o código do aplicativo e o
// banco de dados. Ela herda métodos úteis fornecidos pela interface JpaRepository, como save(), findById(),
// findAll(), deleteById(), etc., o que simplifica muito o acesso e a manipulação de dados no banco de dados
// relacionado à entidade Usuario. Além disso, a anotação @Repository indica ao Spring que esta interface é um
// componente de acesso a dados e deve ser gerenciada pelo contêiner Spring como um bean.

//No mundo do Spring, um "bean" é basicamente um objeto gerenciado pelo Spring.
//Imagina que o Spring é um organizador de brinquedos.
//Ele sabe onde cada brinquedo está e como usá-los quando você precisar.
//
//Então, quando colocamos a anotação @Repository em uma interface como
//UsuarioRepository, estamos dizendo ao Spring:
//"Ei, Spring, esta interface é especial! Você precisa gerenciar
//isso para nós". Assim, o Spring adiciona a interface ao seu
//"saco de brinquedos" e, sempre que precisarmos dela no nosso código,
//podemos pedir ao Spring para nos dar o que está dentro dessa interface.
//
//Então, quando queremos salvar um usuário no banco de dados, por exemplo,
//podemos simplesmente chamar um método do UsuarioRepository,
//e o Spring sabe exatamente o que fazer nos bastidores para fazer isso acontecer.
//Isso nos ajuda a escrever menos código e a manter nosso aplicativo organizado e fácil de entender.