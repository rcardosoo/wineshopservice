package com.wineshopservice.repository;

import com.wineshopservice.domain.Vinho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VinhoRepository extends JpaRepository<Vinho, Long> {
}
