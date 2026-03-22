package com.example.blog.service;

import com.example.blog.model.Post;
import com.example.blog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service contém a LÓGICA DE NEGÓCIO da aplicação.
 *
 * Regra de ouro: Controller chama Service, Service chama Repository.
 * Nunca pule camadas (Controller não deve chamar Repository diretamente).
 *
 * @Service - Marca essa classe como um Service do Spring
 * @RequiredArgsConstructor - Lombok cria construtor com campos 'final' (injeção de dependência)
 */
@Service
@RequiredArgsConstructor
public class PostService {

    /**
     * Injeção de Dependência:
     * O Spring cria automaticamente um PostRepository e injeta aqui.
     * 'final' = obrigatório no construtor (boa prática)
     */
    private final PostRepository postRepository;

    /**
     * Buscar todos os posts.
     * Por enquanto simples, mas aqui você poderia adicionar:
     * - Ordenação (mais recentes primeiro)
     * - Filtros (apenas posts publicados)
     * - Paginação (para não retornar milhares de posts)
     */
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    /**
     * Buscar um post específico pelo slug.
     *
     * @param slug - URL amigável (ex: "meu-primeiro-post")
     * @return Post encontrado
     * @throws PostNotFoundException se não existir
     */
    public Post getPostBySlug(String slug) {
        return postRepository.findBySlug(slug)
                .orElseThrow(() -> new PostNotFoundException("Post não encontrado: " + slug));
    }

    /**
     * Exception customizada para quando um post não é encontrado.
     * Isso permite que o Controller saiba como responder (404 Not Found).
     */
    public static class PostNotFoundException extends RuntimeException {
        public PostNotFoundException(String message) {
            super(message);
        }
    }
}
