package com.shalahudinfadil.bimbelms.commons;

import jakarta.annotation.Nonnull;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.mapstruct.ObjectFactory;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ReferenceMapper {
    @PersistenceContext
    private EntityManager entityManager;

    @ObjectFactory
    public <T> T map(@Nonnull final String id, @TargetType Class<T> type) {
        return entityManager.getReference(type, id);
    }

}
