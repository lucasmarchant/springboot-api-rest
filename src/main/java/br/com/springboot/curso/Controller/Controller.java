package br.com.springboot.curso.Controller;

import br.com.springboot.curso.Repository.UsuarioRepository;
import br.com.springboot.curso.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
public class Controller {

    // Injeção de dependência do repositório de usuários
    // Injeção de dependência: o Spring injeta automaticamente uma instância gerenciada pelo contêiner (como um bean)
    // correspondente ao tipo UsuarioRepository neste membro.
    @Autowired
    private UsuarioRepository usuarioRepository;

    /*  Método que responde a requisições GET para /{name} e retorna uma mensagem de saudação.
    // Endpoint para saudar o usuário com base no nome fornecido na URL
   @RequestMapping(value = "/{name}" , method = RequestMethod.GET)
   @ResponseStatus(HttpStatus.OK)
   public String greetingText(@PathVariable String name) {
       return "Hello " + name + "!";
    }
*/
    // Método que responde a requisições GET para /olamundo/{nome} e salva um novo usuário com o nome fornecido.
    // Endpoint para retornar uma saudação simples "Olá mundo" com base no nome fornecido na URL
    @RequestMapping (value = "/olamundo/{nome}" , method = RequestMethod.GET)
    public String retornaOlaMundo (@PathVariable String nome) {

        // Cria um novo objeto Usuario e define o nome fornecido.
        Usuario usuario = new Usuario();
        usuario.setNome(nome);


        // Salva o usuário no banco de dados usando o UsuarioRepository.
        usuarioRepository.save(usuario);

        // Retorna uma mensagem de saudação com o nome fornecido.
        return "Olá mundo " + nome;
    }


    //esse código representa um método em um controlador que responde a requisições GET feitas para /listatodos.
    // Ele busca todos os usuários no banco de dados e retorna uma lista de usuários
    // como uma resposta HTTP, com um status de sucesso.
    // Método que busca todos os usuários no banco de dados e retorna uma lista de usuários como uma resposta HTTP.
    // Endpoint para listar todos os usuários do banco de dados
    @GetMapping(value = "listatodos") //Primeiro método de API

    // Indica que o valor retornado pelo método deve ser serializado diretamente no corpo da resposta HTTP.
    @ResponseBody //retorna os dados para o corpo da resposta
    public ResponseEntity<java.util.List<Usuario>> listaUsuario() {

        // Busca todos os usuários no banco de dados usando o método findAll() do UsuarioRepository.
       List usuarios = usuarioRepository.findAll();
        // Retorna a lista de usuários como resposta HTTP com status OK (200)
       return new ResponseEntity<java.util.List<Usuario>>(usuarios , HttpStatus.OK); //retorna a lista em Json
   }

   @PostMapping(value = "salvar")  // Tem como função mapear a URL, ponto de extremidade, EndPoint
   @ResponseBody // Descrição da resposta
   public ResponseEntity<Usuario> salvar (@RequestBody Usuario usuario){  //Recebe os dados para salvar

       Usuario user = usuarioRepository.save(usuario);

       return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
   }

    @PutMapping(value = "atualizar")  // Tem como função mapear a URL, ponto de extremidade, EndPoint
    @ResponseBody // Descrição da resposta
    // ? é uma generics do java, neste caso vai ser um retorno generico, pode retornar tanto uma strig
    //quanto um objeto no caso Usuario
    public ResponseEntity<?> atualizar (@RequestBody Usuario usuario){  //Recebe os dados para salvar

        if (usuario.getId() == null){
            return new ResponseEntity<String>("Id não foi informado para atualização." , HttpStatus.OK);
        }

        Usuario user = usuarioRepository.saveAndFlush(usuario);

        return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    }

    @DeleteMapping(value = "delete")  // Tem como função mapear a URL, ponto de extremidade, EndPoint
    @ResponseBody // Descrição da resposta
    public ResponseEntity<String> delete (@RequestParam Long iduser){  //

        usuarioRepository.deleteById(iduser);

        return new ResponseEntity<String>("User deletado com suceso", HttpStatus.OK);
    }

    @GetMapping(value = "buscaruserid")  // Tem como função mapear a URL, ponto de extremidade, EndPoint
    @ResponseBody // Descrição da resposta
    public ResponseEntity<Usuario> buscaruserid (@RequestParam(name = "iduser") Long iduser){  // Métodoo que recebe os dados do Id para consultar

        Usuario usuario = usuarioRepository.findById(iduser).get(); // Pesquisa no banco de dados

        return new ResponseEntity<Usuario>(usuario, HttpStatus.OK); // Retorna pra tela
    }


    @GetMapping(value = "buscarPorNome")  // Tem como função mapear a URL, ponto de extremidade, EndPoint
    @ResponseBody // Descrição da resposta
    public ResponseEntity<List<Usuario>> buscarPorNome (@RequestParam(name = "nome") String nome){  // Métodoo que recebe os dados por nome para consultar

        List<Usuario> usuario = usuarioRepository.buscarPorNome(nome.trim().toUpperCase()); // Pesquisa no banco de dados

        return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK); // Retorna pra tela
    }

}
