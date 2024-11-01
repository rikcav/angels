package com.system.angels.repository;

import com.system.angels.domain.DadosEvolutivos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DadosEvolutivosRepository extends JpaRepository<DadosEvolutivos, Long> {
    List<DadosEvolutivos> findAllByGestante_id(Long id);

    Optional<DadosEvolutivos> findFirstByGestante_idOrderByIdDesc(Long id);
}
