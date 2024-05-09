package com.shalahudinfadil.bimbelms.repository;

import com.shalahudinfadil.bimbelms.entity.LevelTier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LevelTierRepo extends JpaRepository<LevelTier, UUID> {
}
