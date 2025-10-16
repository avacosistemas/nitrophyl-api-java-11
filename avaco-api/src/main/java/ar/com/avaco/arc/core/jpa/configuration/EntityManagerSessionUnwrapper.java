package ar.com.avaco.arc.core.jpa.configuration;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.stereotype.Component;

@Component
public class EntityManagerSessionUnwrapper {

    /**
     * Devuelve la sesión de Hibernate desde un EntityManager estándar de JPA.
     */
    public Session unwrap(EntityManager entityManager) {
        if (entityManager == null) {
            throw new IllegalArgumentException("El EntityManager no puede ser nulo");
        }
        return entityManager.unwrap(Session.class);
    }
}
