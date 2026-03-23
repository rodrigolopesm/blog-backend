package com.example.blog.config;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuração de métricas customizadas.
 *
 * MeterRegistry = registro central de todas as métricas
 * O Spring Boot injeta automaticamente
 */
@Configuration
public class MetricsConfig {

    /**
     * Cria um contador para visualizações de posts
     * Vai aparecer no Prometheus como: post_views_total
     */
    @Bean
    public Counter postViewsCounter(MeterRegistry registry) {
        return Counter.builder("post.views")
                .description("Total de visualizações de posts")
                .tag("type", "blog")
                .register(registry);
    }
}
