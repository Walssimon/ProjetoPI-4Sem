package com.curso.boot.catalogoFilmes.config;

import com.curso.boot.catalogoFilmes.dao.FilmeDaoImpl;
import com.curso.boot.catalogoFilmes.domain.Filme;
import com.curso.boot.catalogoFilmes.dao.FilmeDao;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;


@Configuration
public class DataInitializer {

    @Bean
    @Transactional
    CommandLineRunner initDatabase(FilmeDaoImpl filmeDao) {
        return args -> {
            // Evita duplicar dados a cada start
            if (filmeDao.findAll().isEmpty()) {
                Filme filme = new Filme();
                filme.setNomeFilme("O Senhor dos Anéis: A Sociedade do Anel");
                filme.setDescricao("Um grupo parte em uma jornada para destruir um anel maligno.");
                filme.setDuracao(2);
                filme.setDataLancamento(LocalDate.of(2001, 12, 19));
                filme.setAvaliacao(9.0);
                filme.setClassificacaoIndicativa(12);

                filmeDao.save(filme);

                System.out.println("🎬 Filme inicial criado no banco!");
            } else {
                System.out.println("📀 Banco já possui filmes, nenhum registro criado.");
            }
        };
    }
}
