package com.example.blog.controller;

import com.example.blog.model.Post;
import com.example.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller que expõe os endpoints REST da API.
 *
 * @RestController = @Controller + @ResponseBody
 * Significa: todas as respostas são JSON (não HTML)
 *
 * @RequestMapping("/api/posts") = todos os endpoints começam com /api/posts
 * @CrossOrigin = permite que o frontend React (porta diferente) acesse
 */
@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    /**
     * GET /api/posts
     * Retorna todos os posts em formato JSON
     *
     * Exemplo de resposta:
     * [
     *   {
     *     "id": 1,
     *     "title": "Meu Primeiro Post",
     *     "slug": "meu-primeiro-post",
     *     ...
     *   }
     * ]
     */
    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    /**
     * GET /api/posts/{slug}
     * Retorna um post específico pelo slug
     *
     * @PathVariable = pega o valor da URL
     * Ex: GET /api/posts/meu-primeiro-post
     *     slug = "meu-primeiro-post"
     *
     * ResponseEntity = permite controlar status HTTP (200, 404, 500...)
     */
    @GetMapping("/{slug}")
    public ResponseEntity<Post> getPostBySlug(@PathVariable String slug) {
        Post post = postService.getPostBySlug(slug);
        return ResponseEntity.ok(post);
    }

    /**
     * Exception Handler: captura PostNotFoundException
     * e retorna 404 Not Found em vez de 500 Internal Server Error
     */
    @ExceptionHandler(PostService.PostNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePostNotFound(PostService.PostNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(404, ex.getMessage());
        return ResponseEntity.status(404).body(error);
    }

    /**
     * Classe interna para padronizar respostas de erro
     */
    record ErrorResponse(int status, String message) {}
}
