package com.shalahudinfadil.bimbelms.repository;

import com.shalahudinfadil.bimbelms.entity.Lookup;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LookupRepo extends JpaRepository<Lookup, UUID> {
    @Query("Select e FROM Lookup e WHERE e.group = :group")
    public List<Lookup> getByGroup(@Param("group") String group, Sort sort);
}
