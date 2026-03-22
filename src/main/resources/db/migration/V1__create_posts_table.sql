-- Migration para criar a tabela de posts
-- V1 = versão 1 (Flyway executa em ordem)

CREATE TABLE posts (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    slug VARCHAR(255) NOT NULL UNIQUE,
    content TEXT NOT NULL,
    publish_date TIMESTAMP NOT NULL,
    reading_time INTEGER,
    featured_image VARCHAR(500)
);

-- Tabela auxiliar para categorias (relacionamento 1:N)
CREATE TABLE post_categories (
    post_id BIGINT NOT NULL,
    category VARCHAR(100) NOT NULL,
    FOREIGN KEY (post_id) REFERENCES posts(id) ON DELETE CASCADE
);

-- Índice para buscar posts por slug rapidamente
CREATE INDEX idx_posts_slug ON posts(slug);

-- Inserir um post de exemplo
INSERT INTO posts (title, slug, content, publish_date, reading_time, featured_image)
VALUES (
    'Meu Primeiro Post',
    'meu-primeiro-post',
    '# Bem-vindo ao meu blog!

Este é meu primeiro post. Aqui vou compartilhar conhecimentos sobre programação, arquitetura de software e muito mais.

## Por que criar um blog?

Escrever sobre o que aprendo me ajuda a:
- Solidificar o conhecimento
- Ajudar outras pessoas
- Documentar minha jornada

## O que vem por aí?

Nos próximos posts vou falar sobre:
- Spring Boot e REST APIs
- React e desenvolvimento frontend
- Boas práticas de código

Fique ligado!',
    '2026-03-22 10:00:00',
    3,
    'https://images.unsplash.com/photo-1499750310107-5fef28a66643?w=800'
);

-- Adicionar categorias ao post
INSERT INTO post_categories (post_id, category)
VALUES
    (1, 'Java'),
    (1, 'Spring Boot'),
    (1, 'Tutorial');
