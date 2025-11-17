package com.curso.boot.catalogoFilmes.config;

import com.curso.boot.catalogoFilmes.dao.FilmeDaoImpl;
import com.curso.boot.catalogoFilmes.domain.Filme;
import com.curso.boot.catalogoFilmes.dao.FilmeDao;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Files;
import java.nio.file.Paths;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Statement;

import java.time.LocalDate;


@Configuration
public class DataInitializer {


    private DatabaseMetaData dataSource;

    @Bean
    @Transactional
    CommandLineRunner initDatabase(FilmeDaoImpl filmeDao) {
        return args -> {


            // Evita duplicar dados a cada start
            if (filmeDao.findAll().isEmpty()) {


                byte[] img = Files.readAllBytes(Paths.get("src/main/resources/static/image/1.png"));

                Filme filme = new Filme();
                filme.setNomeFilme("O Senhor dos Anéis: A Sociedade do Anel");
                filme.setDescricao("Um grupo parte em uma jornada para destruir um anel maligno.");
                filme.setDuracao(2);
                filme.setDataLancamento(LocalDate.of(2001, 12, 19));
                filme.setAvaliacao(9.0);
                filme.setDados(img);
                filme.setClassificacaoIndicativa(12);
                filmeDao.save(filme);
                System.out.println("🎬 Filme inicial criado no banco!");
                Filme f2 = new Filme();
                img = Files.readAllBytes(Paths.get("src/main/resources/static/image/2.png"));
                f2.setNomeFilme("Matrix Ressurection");
                f2.setDescricao("Se o Sr. Anderson, conhecido como Neo, aprendeu alguma coisa é que a escolha, mesmo sendo uma ilusão, é a única maneira de sair - ou entrar - da Matrix.");
                f2.setDuracao(138);
                f2.setDataLancamento(LocalDate.of(2021, 12, 22));
                f2.setAvaliacao(5.6);
                f2.setClassificacaoIndicativa(14);
                f2.setDados(img);
                filmeDao.save(f2);

                Filme f3 = new Filme();
                img = Files.readAllBytes(Paths.get("src/main/resources/static/image/3.png"));
                f3.setNomeFilme("Avatar");
                f3.setDescricao("Um fuzileiro se envolve com um povo alienígena.");
                f3.setDuracao(162);
                f3.setDataLancamento(LocalDate.of(2009, 12, 18));
                f3.setAvaliacao(7.8);
                f3.setClassificacaoIndicativa(12);
                f3.setDados(img);
                filmeDao.save(f3);

                // 1
                Filme f4 = new Filme();
                img = Files.readAllBytes(Paths.get("src/main/resources/static/image/4.png"));
                f4.setNomeFilme("Interestelar");
                f4.setDescricao("Um grupo de astronautas viaja por um buraco de minhoca em busca de um novo lar para a humanidade.");
                f4.setDuracao(169);
                f4.setDataLancamento(LocalDate.of(2014, 11, 7));
                f4.setAvaliacao(8.6);
                f4.setClassificacaoIndicativa(10);
                f4.setDados(img);
                filmeDao.save(f4);

//// 2
//                Filme f5 = new Filme();
//                img = Files.readAllBytes(Paths.get("src/main/resources/static/image/5.png"));
//                f5.setNomeFilme("Batman: O Cavaleiro das Trevas");
//                f5.setDescricao("Batman enfrenta o psicopata Coringa em uma batalha pelo caos em Gotham.");
//                f5.setDuracao(152);
//                f5.setDataLancamento(LocalDate.of(2008, 7, 18));
//                f5.setAvaliacao(9.0);
//                f5.setClassificacaoIndicativa(14);
//                f5.setDados(img);
//                filmeDao.save(f5);
//
//// 3
//                Filme f6 = new Filme();
//                img = Files.readAllBytes(Paths.get("src/main/resources/static/image/6.png"));
//                f6.setNomeFilme("A Origem");
//                f6.setDescricao("Um ladrão invade sonhos para roubar segredos corporativos.");
//                f6.setDuracao(148);
//                f6.setDataLancamento(LocalDate.of(2010, 7, 16));
//                f6.setAvaliacao(8.8);
//                f6.setClassificacaoIndicativa(14);
//                f6.setDados(img);
//                filmeDao.save(f6);
//
//// 4
//                Filme f7 = new Filme();
//                img = Files.readAllBytes(Paths.get("src/main/resources/static/image/7.png"));
//                f7.setNomeFilme("Matrix");
//                f7.setDescricao("Um hacker descobre que a realidade é uma simulação e luta pela liberdade humana.");
//                f7.setDuracao(136);
//                f7.setDataLancamento(LocalDate.of(1999, 3, 31));
//                f7.setAvaliacao(8.7);
//                f7.setClassificacaoIndicativa(14);
//                f7.setDados(img);
//                filmeDao.save(f7);
//
//// 5
//                Filme f8 = new Filme();
//                img = Files.readAllBytes(Paths.get("src/main/resources/static/image/8.png"));
//                f8.setNomeFilme("Vingadores: Guerra Infinita");
//                f8.setDescricao("Heróis se unem para impedir Thanos de destruir metade do universo.");
//                f8.setDuracao(149);
//                f8.setDataLancamento(LocalDate.of(2018, 4, 26));
//                f8.setAvaliacao(8.4);
//                f8.setClassificacaoIndicativa(12);
//                f8.setDados(img);
//                filmeDao.save(f8);
//
//// 6
//                Filme f9 = new Filme();
//                img = Files.readAllBytes(Paths.get("src/main/resources/static/image/9.png"));
//                f9.setNomeFilme("Vingadores: Ultimato");
//                f9.setDescricao("Os Vingadores tentam reverter o estalo de Thanos em uma última batalha.");
//                f9.setDuracao(181);
//                f9.setDataLancamento(LocalDate.of(2019, 4, 26));
//                f9.setAvaliacao(8.4);
//                f9.setClassificacaoIndicativa(12);
//                f9.setDados(img);
//                filmeDao.save(f9);
//
//// 7
//                Filme f10 = new Filme();
//                img = Files.readAllBytes(Paths.get("src/main/resources/static/image/10.png"));
//                f10.setNomeFilme("Pantera Negra");
//                f10.setDescricao("T'Challa retorna a Wakanda para assumir o trono e enfrenta um poderoso inimigo.");
//                f10.setDuracao(134);
//                f10.setDataLancamento(LocalDate.of(2018, 2, 16));
//                f10.setAvaliacao(7.3);
//                f10.setClassificacaoIndicativa(12);
//                f10.setDados(img);
//                filmeDao.save(f10);
//
//// 8
//                Filme f11 = new Filme();
//                img = Files.readAllBytes(Paths.get("src/main/resources/static/image/11.png"));
//                f11.setNomeFilme("O Rei Leão");
//                f11.setDescricao("Um jovem leão precisa assumir seu lugar como rei após a morte do pai.");
//                f11.setDuracao(88);
//                f11.setDataLancamento(LocalDate.of(1994, 6, 24));
//                f11.setAvaliacao(8.5);
//                f11.setClassificacaoIndicativa(0);
//                f11.setDados(img);
//                filmeDao.save(f11);
//
//// 9
//                Filme f12 = new Filme();
//                img = Files.readAllBytes(Paths.get("src/main/resources/static/image/12.png"));
//                f12.setNomeFilme("Toy Story");
//                f12.setDescricao("Brinquedos ganham vida quando os humanos não estão olhando.");
//                f12.setDuracao(81);
//                f12.setDataLancamento(LocalDate.of(1995, 11, 22));
//                f12.setAvaliacao(8.3);
//                f12.setClassificacaoIndicativa(0);
//                f12.setDados(img);
//                filmeDao.save(f12);
//
//// 10
//                Filme f13 = new Filme();
//                img = Files.readAllBytes(Paths.get("src/main/resources/static/image/13.png"));
//                f13.setNomeFilme("Homem-Aranha 2");
//                f13.setDescricao("Peter Parker enfrenta novos desafios enquanto luta contra o Dr. Octopus.");
//                f13.setDuracao(127);
//                f13.setDataLancamento(LocalDate.of(2004, 6, 30));
//                f13.setAvaliacao(7.4);
//                f13.setClassificacaoIndicativa(10);
//                f13.setDados(img);
//                filmeDao.save(f13);
//
//// 11
//                Filme f14 = new Filme();
//                img = Files.readAllBytes(Paths.get("src/main/resources/static/image/14.png"));
//                f14.setNomeFilme("Divertida Mente");
//                f14.setDescricao("As emoções de uma garota precisam guiá-la em uma mudança de vida.");
//                f14.setDuracao(95);
//                f14.setDataLancamento(LocalDate.of(2015, 6, 19));
//                f14.setAvaliacao(8.1);
//                f14.setClassificacaoIndicativa(0);
//                f14.setDados(img);
//                filmeDao.save(f14);
//
//// 12
//                Filme f15 = new Filme();
//                img = Files.readAllBytes(Paths.get("src/main/resources/static/image/15.png"));
//                f15.setNomeFilme("Shrek");
//                f15.setDescricao("Um ogro precisa resgatar uma princesa para recuperar seu pântano.");
//                f15.setDuracao(90);
//                f15.setDataLancamento(LocalDate.of(2001, 5, 18));
//                f15.setAvaliacao(8.0);
//                f15.setClassificacaoIndicativa(0);
//                f15.setDados(img);
//                filmeDao.save(f15);
//
//// 13
//                Filme f16 = new Filme();
//                img = Files.readAllBytes(Paths.get("src/main/resources/static/image/16.png"));
//                f16.setNomeFilme("Os Incríveis");
//                f16.setDescricao("Uma família de super-heróis precisa voltar à ativa para salvar o mundo.");
//                f16.setDuracao(115);
//                f16.setDataLancamento(LocalDate.of(2004, 11, 5));
//                f16.setAvaliacao(8.0);
//                f16.setClassificacaoIndicativa(0);
//                f16.setDados(img);
//                filmeDao.save(f16);
//
//// 14
//                Filme f17 = new Filme();
//                img = Files.readAllBytes(Paths.get("src/main/resources/static/image/17.png"));
//                f17.setNomeFilme("Procurando Nemo");
//                f17.setDescricao("Um pai percorre o oceano para resgatar seu filho perdido.");
//                f17.setDuracao(100);
//                f17.setDataLancamento(LocalDate.of(2003, 5, 30));
//                f17.setAvaliacao(8.2);
//                f17.setClassificacaoIndicativa(0);
//                f17.setDados(img);
//                filmeDao.save(f17);
//
//// 15
//                Filme f18 = new Filme();
//                img = Files.readAllBytes(Paths.get("src/main/resources/static/image/18.png"));
//                f18.setNomeFilme("Gladiador");
//                f18.setDescricao("Um general romano busca vingança contra o imperador corrupto.");
//                f18.setDuracao(155);
//                f18.setDataLancamento(LocalDate.of(2000, 5, 5));
//                f18.setAvaliacao(8.5);
//                f18.setClassificacaoIndicativa(16);
//                f18.setDados(img);
//                filmeDao.save(f18);
//
//// 16
//                Filme f19 = new Filme();
//                img = Files.readAllBytes(Paths.get("src/main/resources/static/image/19.png"));
//                f19.setNomeFilme("O Lobo de Wall Street");
//                f19.setDescricao("A ascensão e queda do corretor Jordan Belfort.");
//                f19.setDuracao(180);
//                f19.setDataLancamento(LocalDate.of(2013, 12, 25));
//                f19.setAvaliacao(8.2);
//                f19.setClassificacaoIndicativa(18);
//                f19.setDados(img);
//                filmeDao.save(f19);
//
//// 17
//                Filme f20 = new Filme();
//                img = Files.readAllBytes(Paths.get("src/main/resources/static/image/20.png"));
//                f20.setNomeFilme("Clube da Luta");
//                f20.setDescricao("Um homem insatisfeito cria um clube secreto de luta clandestina.");
//                f20.setDuracao(139);
//                f20.setDataLancamento(LocalDate.of(1999, 10, 15));
//                f20.setAvaliacao(8.8);
//                f20.setClassificacaoIndicativa(18);
//                f20.setDados(img);
//                filmeDao.save(f20);

            } else {
                System.out.println("📀 Banco já possui filmes, nenhum registro criado.");
            }
        };
    }
}
