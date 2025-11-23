package com.curso.boot.catalogoFilmes.config;

import com.curso.boot.catalogoFilmes.dao.FilmeDaoImpl;
import com.curso.boot.catalogoFilmes.dao.GeneroDao;
import com.curso.boot.catalogoFilmes.dao.GeneroFilmeDao;
import com.curso.boot.catalogoFilmes.domain.Filme;
import com.curso.boot.catalogoFilmes.dao.FilmeDao;
import com.curso.boot.catalogoFilmes.domain.Genero;
import com.curso.boot.catalogoFilmes.domain.GeneroFilme;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
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
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;


@Configuration
public class DataInitializer {


    private DatabaseMetaData dataSource;

    @Bean
    @Transactional
    CommandLineRunner initDatabase(FilmeDaoImpl filmeDao, GeneroDao generoDao, GeneroFilmeDao generoFilmeDao, DataSource dataSource) {
        return args -> {

            try (Connection conn = dataSource.getConnection();
                 Statement stmt = conn.createStatement()) {

                // Aumenta o limite para 64 MB (bom para imagens grandes)
                stmt.execute("SET GLOBAL max_allowed_packet = 64 * 1024 * 1024");

                System.out.println("max_allowed_packet configurado para 64MB automaticamente.");
            } catch (Exception e) {
                // Caso o MySQL não permita (host remoto/shared hosting)
                System.err.println("Não foi possível configurar max_allowed_packet automaticamente: "
                        + e.getMessage());}

            //Criar gêneros apenas se não existirem
            if (generoDao.findAll().isEmpty()) {
                Genero acao = new Genero(); acao.setNomeGenero("Acao");
                Genero aventura = new Genero(); aventura.setNomeGenero("Aventura");
                Genero comedia = new Genero(); comedia.setNomeGenero("Comedia");
                Genero drama = new Genero(); drama.setNomeGenero("Drama");
                Genero terror = new Genero(); terror.setNomeGenero("Terror");
                Genero ficcao = new Genero(); ficcao.setNomeGenero("Ficcao Cientifica");
                Genero romance = new Genero(); romance.setNomeGenero("Romance");
                Genero fantasia = new Genero(); fantasia.setNomeGenero("Fantasia");
                Genero suspense = new Genero(); suspense.setNomeGenero("Suspense");
                Genero misterio = new Genero(); misterio.setNomeGenero("Misterio");
                Genero animacao = new Genero(); animacao.setNomeGenero("Animacao");
                Genero documentario = new Genero(); documentario.setNomeGenero("Documentario");
                Genero musical = new Genero(); musical.setNomeGenero("Musical");
                Genero historico = new Genero(); historico.setNomeGenero("Historico");
                Genero familia = new Genero(); familia.setNomeGenero("Familia");

                generoDao.save(acao);
                generoDao.save(aventura);
                generoDao.save(comedia);
                generoDao.save(drama);
                generoDao.save(terror);
                generoDao.save(ficcao);
                generoDao.save(romance);
                generoDao.save(fantasia);
                generoDao.save(suspense);
                generoDao.save(misterio);
                generoDao.save(animacao);
                generoDao.save(documentario);
                generoDao.save(musical);
                generoDao.save(historico);
                generoDao.save(familia);

            }

            // Evita duplicar dados a cada start
            if (filmeDao.findAll().isEmpty()) {


                byte[] img = Files.readAllBytes(Paths.get("src/main/resources/static/image/1.png"));
                byte[] imgBanner = Files.readAllBytes(Paths.get("src/main/resources/static/image/1b.png"));

                Filme filme = new Filme();
                filme.setNomeFilme("O Senhor dos Anéis: A Sociedade do Anel");
                filme.setDescricao("Um grupo parte em uma jornada para destruir um anel maligno.");
                filme.setDuracao(2);
                filme.setDataLancamento(LocalDate.of(2001, 12, 19));
                filme.setAvaliacao(9.0);
                filme.setDados(img);
                filme.setDadosBanner(imgBanner);
                filme.setClassificacaoIndicativa(12);
                filmeDao.save(filme);
                System.out.println("🎬 Filme inicial criado no banco!");
                Genero genero = generoDao.findByNome("Fantasia");
                GeneroFilme gf = new GeneroFilme();
                gf.setFilme(filme);
                gf.setGenero(genero);
                generoFilmeDao.save(gf);

                Filme f2 = new Filme();
                img = Files.readAllBytes(Paths.get("src/main/resources/static/image/2.png"));
                imgBanner = Files.readAllBytes(Paths.get("src/main/resources/static/image/2b.png"));
                f2.setNomeFilme("Matrix Ressurection");
                f2.setDescricao("Se o Sr. Anderson, conhecido como Neo, aprendeu alguma coisa é que a escolha, mesmo sendo uma ilusão, é a única maneira de sair - ou entrar - da Matrix.");
                f2.setDuracao(138);
                f2.setDataLancamento(LocalDate.of(2021, 12, 22));
                f2.setAvaliacao(5.6);
                f2.setClassificacaoIndicativa(14);
                f2.setDados(img);
                f2.setDadosBanner(imgBanner);
                filmeDao.save(f2);
                Genero genero2 = generoDao.findByNome("Ficcao Cientifica");
                GeneroFilme gf2 = new GeneroFilme();
                gf2.setFilme(f2);
                gf2.setGenero(genero2);
                generoFilmeDao.save(gf2);

                Filme f3 = new Filme();
                img = Files.readAllBytes(Paths.get("src/main/resources/static/image/3.png"));
                imgBanner = Files.readAllBytes(Paths.get("src/main/resources/static/image/3b.png"));
                f3.setNomeFilme("Avatar");
                f3.setDescricao("Um fuzileiro se envolve com um povo alienígena.");
                f3.setDuracao(162);
                f3.setDataLancamento(LocalDate.of(2009, 12, 18));
                f3.setAvaliacao(7.8);
                f3.setClassificacaoIndicativa(12);
                f3.setDados(img);
                f3.setDadosBanner(imgBanner);
                filmeDao.save(f3);
                Genero genero3 = generoDao.findByNome("Acao");
                GeneroFilme gf3 = new GeneroFilme();
                gf3.setFilme(f3);
                gf3.setGenero(genero3);
                generoFilmeDao.save(gf3);


                Filme f4 = new Filme();
                img = Files.readAllBytes(Paths.get("src/main/resources/static/image/4.png"));
                imgBanner = Files.readAllBytes(Paths.get("src/main/resources/static/image/4b.png"));
                f4.setNomeFilme("Interestelar");
                f4.setDescricao("Um grupo de astronautas viaja por um buraco de minhoca em busca de um novo lar para a humanidade.");
                f4.setDuracao(169);
                f4.setDataLancamento(LocalDate.of(2014, 11, 7));
                f4.setAvaliacao(8.6);
                f4.setClassificacaoIndicativa(10);
                f4.setDados(img);
                f4.setDadosBanner(imgBanner);
                filmeDao.save(f4);
                Genero genero4 = generoDao.findByNome("Ficcao Cientifica");
                GeneroFilme gf4 = new GeneroFilme();
                gf4.setFilme(f4);
                gf4.setGenero(genero4);
                generoFilmeDao.save(gf4);


                Filme f5 = new Filme();
                img = Files.readAllBytes(Paths.get("src/main/resources/static/image/5.png"));
                imgBanner = Files.readAllBytes(Paths.get("src/main/resources/static/image/5b.png"));
                f5.setNomeFilme("Batman: O Cavaleiro das Trevas");
                f5.setDescricao("Batman enfrenta o psicopata Coringa em uma batalha pelo caos em Gotham.");
                f5.setDuracao(152);
                f5.setDataLancamento(LocalDate.of(2008, 7, 18));
                f5.setAvaliacao(9.0);
                f5.setClassificacaoIndicativa(14);
                f5.setDados(img);
                f5.setDadosBanner(imgBanner);
                filmeDao.save(f5);
                Genero genero5 = generoDao.findByNome("Acao");
                GeneroFilme gf5 = new GeneroFilme();
                gf5.setFilme(f5);
                gf5.setGenero(genero5);
                generoFilmeDao.save(gf5);

// 3
                Filme f6 = new Filme();
                img = Files.readAllBytes(Paths.get("src/main/resources/static/image/6.png"));
                imgBanner = Files.readAllBytes(Paths.get("src/main/resources/static/image/6b.png"));
                f6.setNomeFilme("A Origem");
                f6.setDescricao("Um ladrão invade sonhos para roubar segredos corporativos.");
                f6.setDuracao(148);
                f6.setDataLancamento(LocalDate.of(2010, 7, 16));
                f6.setAvaliacao(8.8);
                f6.setClassificacaoIndicativa(14);
                f6.setDados(img);
                f6.setDadosBanner(imgBanner);
                filmeDao.save(f6);
                Genero genero6 = generoDao.findByNome("Ficcao Cientifica");
                GeneroFilme gf6 = new GeneroFilme();
                gf6.setFilme(f6);
                gf6.setGenero(genero6);
                generoFilmeDao.save(gf6);

// 4
                Filme f7 = new Filme();
                img = Files.readAllBytes(Paths.get("src/main/resources/static/image/7.png"));
                imgBanner = Files.readAllBytes(Paths.get("src/main/resources/static/image/7b.png"));
                f7.setNomeFilme("Matrix");
                f7.setDescricao("Um hacker descobre que a realidade é uma simulação e luta pela liberdade humana.");
                f7.setDuracao(136);
                f7.setDataLancamento(LocalDate.of(1999, 3, 31));
                f7.setAvaliacao(8.7);
                f7.setClassificacaoIndicativa(14);
                f7.setDados(img);
                f7.setDadosBanner(imgBanner);
                filmeDao.save(f7);
                Genero genero7 = generoDao.findByNome("Ficcao Cientifica");
                GeneroFilme gf7 = new GeneroFilme();
                gf7.setFilme(f7);
                gf7.setGenero(genero7);
                generoFilmeDao.save(gf7);

// 5
                Filme f8 = new Filme();
                img = Files.readAllBytes(Paths.get("src/main/resources/static/image/8.png"));
                imgBanner = Files.readAllBytes(Paths.get("src/main/resources/static/image/8b.png"));
                f8.setNomeFilme("Vingadores: Guerra Infinita");
                f8.setDescricao("Heróis se unem para impedir Thanos de destruir metade do universo.");
                f8.setDuracao(149);
                f8.setDataLancamento(LocalDate.of(2018, 4, 26));
                f8.setAvaliacao(8.4);
                f8.setClassificacaoIndicativa(12);
                f8.setDados(img);
                f8.setDadosBanner(imgBanner);
                filmeDao.save(f8);
                Genero genero8 = generoDao.findByNome("Acao");
                GeneroFilme gf8 = new GeneroFilme();
                gf8.setFilme(f8);
                gf8.setGenero(genero8);
                generoFilmeDao.save(gf8);

// 6
                Filme f9 = new Filme();
                img = Files.readAllBytes(Paths.get("src/main/resources/static/image/9.png"));
                imgBanner = Files.readAllBytes(Paths.get("src/main/resources/static/image/9b.png"));
                f9.setNomeFilme("Vingadores: Ultimato");
                f9.setDescricao("Os Vingadores tentam reverter o estalo de Thanos em uma última batalha.");
                f9.setDuracao(181);
                f9.setDataLancamento(LocalDate.of(2019, 4, 26));
                f9.setAvaliacao(8.4);
                f9.setClassificacaoIndicativa(12);
                f9.setDados(img);
                f9.setDadosBanner(imgBanner);
                filmeDao.save(f9);
                Genero genero9 = generoDao.findByNome("Acao");
                GeneroFilme gf9 = new GeneroFilme();
                gf9.setFilme(f9);
                gf9.setGenero(genero9);
                generoFilmeDao.save(gf9);

// 7
                Filme f10 = new Filme();
                img = Files.readAllBytes(Paths.get("src/main/resources/static/image/10.png"));
                imgBanner = Files.readAllBytes(Paths.get("src/main/resources/static/image/10b.png"));
                f10.setNomeFilme("Pantera Negra");
                f10.setDescricao("T'Challa retorna a Wakanda para assumir o trono e enfrenta um poderoso inimigo.");
                f10.setDuracao(134);
                f10.setDataLancamento(LocalDate.of(2018, 2, 16));
                f10.setAvaliacao(7.3);
                f10.setClassificacaoIndicativa(12);
                f10.setDados(img);
                f10.setDadosBanner(imgBanner);
                filmeDao.save(f10);
                Genero genero10 = generoDao.findByNome("Acao");
                GeneroFilme gf10 = new GeneroFilme();
                gf10.setFilme(f10);
                gf10.setGenero(genero10);
                generoFilmeDao.save(gf10);

// 8
                Filme f11 = new Filme();
                img = Files.readAllBytes(Paths.get("src/main/resources/static/image/11.png"));
                imgBanner = Files.readAllBytes(Paths.get("src/main/resources/static/image/11b.png"));
                f11.setNomeFilme("O Rei Leão");
                f11.setDescricao("Um jovem leão precisa assumir seu lugar como rei após a morte do pai.");
                f11.setDuracao(88);
                f11.setDataLancamento(LocalDate.of(1994, 6, 24));
                f11.setAvaliacao(8.5);
                f11.setClassificacaoIndicativa(0);
                f11.setDados(img);
                f11.setDadosBanner(imgBanner);
                filmeDao.save(f11);
                Genero genero11 = generoDao.findByNome("Animacao");
                GeneroFilme gf11 = new GeneroFilme();
                gf11.setFilme(f11);
                gf11.setGenero(genero11);
                generoFilmeDao.save(gf11);

// 9
                Filme f12 = new Filme();
                img = Files.readAllBytes(Paths.get("src/main/resources/static/image/12.png"));
                imgBanner = Files.readAllBytes(Paths.get("src/main/resources/static/image/12b.png"));
                f12.setNomeFilme("Toy Story");
                f12.setDescricao("Brinquedos ganham vida quando os humanos não estão olhando.");
                f12.setDuracao(81);
                f12.setDataLancamento(LocalDate.of(1995, 11, 22));
                f12.setAvaliacao(8.3);
                f12.setClassificacaoIndicativa(0);
                f12.setDados(img);
                f12.setDadosBanner(imgBanner);
                filmeDao.save(f12);
                Genero genero12 = generoDao.findByNome("Animacao");
                GeneroFilme gf12 = new GeneroFilme();
                gf12.setFilme(f12);
                gf12.setGenero(genero12);
                generoFilmeDao.save(gf12);

// 10
                Filme f13 = new Filme();
                img = Files.readAllBytes(Paths.get("src/main/resources/static/image/13.png"));
                imgBanner = Files.readAllBytes(Paths.get("src/main/resources/static/image/13b.png"));
                f13.setNomeFilme("Homem-Aranha 2");
                f13.setDescricao("Peter Parker enfrenta novos desafios enquanto luta contra o Dr. Octopus.");
                f13.setDuracao(127);
                f13.setDataLancamento(LocalDate.of(2004, 6, 30));
                f13.setAvaliacao(7.4);
                f13.setClassificacaoIndicativa(10);
                f13.setDados(img);
                f13.setDadosBanner(imgBanner);
                filmeDao.save(f13);
                Genero genero13 = generoDao.findByNome("Acao");
                GeneroFilme gf13 = new GeneroFilme();
                gf13.setFilme(f13);
                gf13.setGenero(genero13);
                generoFilmeDao.save(gf13);

// 11
                Filme f14 = new Filme();
                img = Files.readAllBytes(Paths.get("src/main/resources/static/image/14.png"));
                imgBanner = Files.readAllBytes(Paths.get("src/main/resources/static/image/14b.png"));
                f14.setNomeFilme("Divertida Mente");
                f14.setDescricao("As emoções de uma garota precisam guiá-la em uma mudança de vida.");
                f14.setDuracao(95);
                f14.setDataLancamento(LocalDate.of(2015, 6, 19));
                f14.setAvaliacao(8.1);
                f14.setClassificacaoIndicativa(0);
                f14.setDados(img);
                f14.setDadosBanner(imgBanner);
                filmeDao.save(f14);
                Genero genero14 = generoDao.findByNome("Animacao");
                GeneroFilme gf14 = new GeneroFilme();
                gf14.setFilme(f14);
                gf14.setGenero(genero14);
                generoFilmeDao.save(gf14);

// 12
                Filme f15 = new Filme();
                img = Files.readAllBytes(Paths.get("src/main/resources/static/image/15.png"));
                imgBanner = Files.readAllBytes(Paths.get("src/main/resources/static/image/15b.png"));
                f15.setNomeFilme("Shrek");
                f15.setDescricao("Um ogro precisa resgatar uma princesa para recuperar seu pântano.");
                f15.setDuracao(90);
                f15.setDataLancamento(LocalDate.of(2001, 5, 18));
                f15.setAvaliacao(8.0);
                f15.setClassificacaoIndicativa(0);
                f15.setDados(img);
                f15.setDadosBanner(imgBanner);
                filmeDao.save(f15);
                Genero genero15 = generoDao.findByNome("Animacao");
                GeneroFilme gf15 = new GeneroFilme();
                gf15.setFilme(f15);
                gf15.setGenero(genero15);
                generoFilmeDao.save(gf15);

// 13
                Filme f16 = new Filme();
                img = Files.readAllBytes(Paths.get("src/main/resources/static/image/16.png"));
                imgBanner = Files.readAllBytes(Paths.get("src/main/resources/static/image/16b.png"));
                f16.setNomeFilme("Os Incríveis");
                f16.setDescricao("Uma família de super-heróis precisa voltar à ativa para salvar o mundo.");
                f16.setDuracao(115);
                f16.setDataLancamento(LocalDate.of(2004, 11, 5));
                f16.setAvaliacao(8.0);
                f16.setClassificacaoIndicativa(0);
                f16.setDados(img);
                f16.setDadosBanner(imgBanner);
                filmeDao.save(f16);
                Genero genero16 = generoDao.findByNome("Animacao");
                GeneroFilme gf16 = new GeneroFilme();
                gf16.setFilme(f16);
                gf16.setGenero(genero16);
                generoFilmeDao.save(gf16);

// 14
                Filme f17 = new Filme();
                img = Files.readAllBytes(Paths.get("src/main/resources/static/image/17.png"));
                imgBanner = Files.readAllBytes(Paths.get("src/main/resources/static/image/17b.png"));
                f17.setNomeFilme("Procurando Nemo");
                f17.setDescricao("Um pai percorre o oceano para resgatar seu filho perdido.");
                f17.setDuracao(100);
                f17.setDataLancamento(LocalDate.of(2003, 5, 30));
                f17.setAvaliacao(8.2);
                f17.setClassificacaoIndicativa(0);
                f17.setDados(img);
                f17.setDadosBanner(imgBanner);
                filmeDao.save(f17);
                Genero genero17 = generoDao.findByNome("Animacao");
                GeneroFilme gf17 = new GeneroFilme();
                gf17.setFilme(f17);
                gf17.setGenero(genero17);
                generoFilmeDao.save(gf17);

// 15
                Filme f18 = new Filme();
                img = Files.readAllBytes(Paths.get("src/main/resources/static/image/18.png"));
                imgBanner = Files.readAllBytes(Paths.get("src/main/resources/static/image/18b.png"));
                f18.setNomeFilme("Gladiador");
                f18.setDescricao("Um general romano busca vingança contra o imperador corrupto.");
                f18.setDuracao(155);
                f18.setDataLancamento(LocalDate.of(2000, 5, 5));
                f18.setAvaliacao(8.5);
                f18.setClassificacaoIndicativa(16);
                f18.setDados(img);
                f18.setDadosBanner(imgBanner);
                filmeDao.save(f18);
                Genero genero18 = generoDao.findByNome("Acao");
                GeneroFilme gf18 = new GeneroFilme();
                gf18.setFilme(f18);
                gf18.setGenero(genero18);
                generoFilmeDao.save(gf18);

// 16
                Filme f19 = new Filme();
                img = Files.readAllBytes(Paths.get("src/main/resources/static/image/19.png"));
                imgBanner = Files.readAllBytes(Paths.get("src/main/resources/static/image/19b.png"));
                f19.setNomeFilme("O Lobo de Wall Street");
                f19.setDescricao("A ascensão e queda do corretor Jordan Belfort.");
                f19.setDuracao(180);
                f19.setDataLancamento(LocalDate.of(2013, 12, 25));
                f19.setAvaliacao(8.2);
                f19.setClassificacaoIndicativa(18);
                f19.setDados(img);
                f19.setDadosBanner(imgBanner);
                filmeDao.save(f19);
                Genero genero19 = generoDao.findByNome("Comedia");
                GeneroFilme gf19 = new GeneroFilme();
                gf19.setFilme(f19);
                gf19.setGenero(genero19);
                generoFilmeDao.save(gf19);

// 17
                Filme f20 = new Filme();
                img = Files.readAllBytes(Paths.get("src/main/resources/static/image/20.png"));
                imgBanner = Files.readAllBytes(Paths.get("src/main/resources/static/image/20b.png"));
                f20.setNomeFilme("Clube da Luta");
                f20.setDescricao("Um homem insatisfeito cria um clube secreto de luta clandestina.");
                f20.setDuracao(139);
                f20.setDataLancamento(LocalDate.of(1999, 10, 15));
                f20.setAvaliacao(8.8);
                f20.setClassificacaoIndicativa(18);
                f20.setDados(img);
                f20.setDadosBanner(imgBanner);
                filmeDao.save(f20);
                Genero genero20 = generoDao.findByNome("Acao");
                GeneroFilme gf20 = new GeneroFilme();
                gf20.setFilme(f20);
                gf20.setGenero(genero20);
                generoFilmeDao.save(gf20);

            } else {
                System.out.println("📀 Banco já possui filmes, nenhum registro criado.");
            }
        };
    }


}
