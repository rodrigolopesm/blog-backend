package com.example.blog.repository;

import com.example.blog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository para acessar a tabela de Posts no banco de dados.
 *
 * IMPORTANTE: Você NÃO precisa implementar os métodos!
 * O Spring Data JPA cria a implementação automaticamente.
 *
 * JpaRepository<Post, Long> significa:
 * - Post = A entidade que queremos manipular
 * - Long = O tipo do ID da entidade
 *
 * Métodos automáticos que você ganha de graça:
 * - findAll() - busca todos os posts
 * - findById(id) - busca por ID
 * - save(post) - salva ou atualiza
 * - delete(post) - deleta
 * - count() - conta quantos posts existem
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    /**
     * Buscar post por slug (URL amigável).
     *
     * O Spring entende o nome do método e cria a query SQL automaticamente!
     * findBy + Slug = SELECT * FROM posts WHERE slug = ?
     *
     * Optional = pode retornar vazio se não encontrar
     */
    Optional<Post> findBySlug(String slug);

    /**
     * Verificar se existe um post com determinado slug
     * exists + By + Slug = SELECT COUNT(*) > 0 FROM posts WHERE slug = ?
     */
    boolean existsBySlug(String slug);
}
