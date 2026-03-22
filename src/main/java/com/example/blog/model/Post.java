package com.example.blog.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Entidade que representa um Post do blog no banco de dados.
 *
 * @Entity - Diz ao Spring que essa classe é uma tabela no banco
 * @Data - Lombok gera getters, setters, toString automaticamente
 * @NoArgsConstructor - Lombok cria construtor vazio (JPA precisa)
 * @AllArgsConstructor - Lombok cria construtor com todos os campos
 */
@Entity
@Table(name = "posts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    /**
     * @Id - Chave primária
     * @GeneratedValue - O banco gera automaticamente (auto-increment)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Título do post (obrigatório)
     */
    @Column(nullable = false)
    private String title;

    /**
     * Slug é a parte da URL (ex: "meu-primeiro-post")
     * Deve ser único para cada post
     */
    @Column(nullable = false, unique = true)
    private String slug;

    /**
     * Conteúdo completo do post (pode ser grande, por isso TEXT)
     * Para PostgreSQL, basta usar columnDefinition = "TEXT"
     */
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    /**
     * Data de publicação
     */
    @Column(nullable = false)
    private LocalDateTime publishDate;

    /**
     * Tempo estimado de leitura em minutos
     */
    private Integer readingTime;

    /**
     * URL da imagem destacada (opcional)
     */
    private String featuredImage;

    /**
     * Lista de categorias (ex: ["Java", "Spring Boot", "Tutorial"])
     * @ElementCollection - Cria uma tabela separada para as categorias
     */
    @ElementCollection
    @CollectionTable(name = "post_categories", joinColumns = @JoinColumn(name = "post_id"))
    @Column(name = "category")
    private List<String> categories;
}
