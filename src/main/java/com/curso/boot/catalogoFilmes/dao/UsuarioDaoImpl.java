package com.curso.boot.catalogoFilmes.dao;

import com.curso.boot.catalogoFilmes.domain.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioDaoImpl extends AbstractDao<Usuario, Long> implements UsuarioDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Usuario findByEmailAndSenha(String email, String senha) {
        String jpql = "SELECT u FROM Usuario u WHERE u.email = :email AND u.senha = :senha";

        List<Usuario> lista = em.createQuery(jpql, Usuario.class)
                .setParameter("email", email)
                .setParameter("senha", senha)
                .getResultList();

        return lista.isEmpty() ? null : lista.get(0);
    }
}
