package com.boajp.repositorios;

import com.boajp.modelo.RegistroJugadorEntidad;
import jakarta.persistence.*;

import java.util.List;

public class RegistroJugadorRepositorio {

    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;
    private EntityTransaction transaction;

    public RegistroJugadorRepositorio() {
        entityManagerFactory = Persistence.createEntityManagerFactory("default");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public void insertar(RegistroJugadorEntidad registroJugador) throws Exception {
        transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(registroJugador);
            transaction.commit();
        } catch (Exception exception) {
            transaction.rollback();
            throw new Exception("Error al intentar insertar registro de jugador");
        }
    }

    public void eliminar(RegistroJugadorEntidad registroJugador) throws Exception {
        transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            RegistroJugadorEntidad registroJugadorEncontrado = entityManager.find(RegistroJugadorEntidad.class, registroJugador.getJugador().getCodJugador());
            if (registroJugadorEncontrado != null)
                entityManager.remove(registroJugadorEncontrado);
            transaction.commit();
        } catch (Exception exception) {
            transaction.rollback();
            throw new Exception("Error al intentar eliminar registro de jugador");
        }
    }

    public void modificar(RegistroJugadorEntidad registroJugador) throws Exception {
        transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            RegistroJugadorEntidad registroJugadorEncontrado = entityManager.find(RegistroJugadorEntidad.class, registroJugador.getJugador().getCodJugador());
            registroJugadorEncontrado.setTemporada(registroJugador.getTemporada());
            if (registroJugadorEncontrado != null)
                entityManager.persist(registroJugadorEncontrado);
            transaction.commit();
        } catch (Exception exception) {
            transaction.rollback();
            throw new Exception("Error al intentar modificar registro de jugador");
        }
    }

    public List<RegistroJugadorEntidad> buscarTodosRegistrosJugadores() throws Exception {
        try {
            String jpql = "SELECT r FROM RegistroJugadorEntidad r";
            TypedQuery<RegistroJugadorEntidad> query = entityManager.createQuery(jpql, RegistroJugadorEntidad.class);
            return query.getResultList();
        } catch (Exception exception) {
            throw new Exception("Error al intentar extraer registros de jugadores.", exception);
        }
    }

    public List<RegistroJugadorEntidad> buscarJugadoresRegistradosUltimaTemporada() throws Exception {
        try {
            String query = "SELECT rj FROM RegistroJugadorEntidad rj WHERE rj.temporada.codTemporada = (SELECT MAX(m.temporada.codTemporada) FROM RegistroJugadorEntidad m)";
            TypedQuery<RegistroJugadorEntidad> resultado = entityManager.createQuery(query, RegistroJugadorEntidad.class);
            return resultado.getResultList();
        } catch (NoResultException e) {
            return null;
        } catch (Exception exception) {
            throw new Exception("Error al intentar extraer RegistroJugadorEntidad.", exception);
        }
    }

}
