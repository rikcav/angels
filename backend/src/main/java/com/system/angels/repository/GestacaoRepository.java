package com.system.angels.repository;

import com.system.angels.domain.Gestacao;
import com.system.angels.dto.response.GestacaoRO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GestacaoRepository extends JpaRepository<Gestacao, Long> {

    public List<Gestacao> findByGestanteId(Long gestanteId);

    public List<Gestacao> findByUser_Username(String username);
}
