package com.shalahudinfadil.bimbelms.repository;

import com.shalahudinfadil.bimbelms.entity.Level;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LevelRepo extends JpaRepository<Level,UUID> {
}
