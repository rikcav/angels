package com.system.angels.repository;

import com.system.angels.domain.Gestante;
import com.system.angels.domain.User;
import com.system.angels.domain.enums.Raca;
import com.system.angels.domain.enums.Sexo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class GestanteRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GestanteRepository gestanteRepository;

    private User createUser(String username) {
        User user = new User();
        user.setUsername(username); // Aceita um username dinâmico
        user.setPassword("securePassword");
        user.setName("Maria Silva");
        user.setRole("USER");
        return user;
    }

    private Gestante createGestante(String cpf, String nome, String username) {
        User user = createUser(username);
        userRepository.save(user);

        Gestante gestante = new Gestante();
        gestante.setCpf(cpf);
        gestante.setEmail("maria.silva@gmail.com");
        gestante.setNome(nome);
        gestante.setDataNascimento(java.sql.Date.valueOf(LocalDate.of(1990, 1, 1)));
        gestante.setRaca(Raca.NEGRO);
        gestante.setSexo(Sexo.FEMININO);
        gestante.setUser(user);
        return gestante;
    }

    @Test
    public void testFindGestanteByCpf() {
        Gestante gestante = createGestante("12345678901", "Maria", "Mario");
        gestanteRepository.save(gestante);

        Optional<Gestante> foundGestante = gestanteRepository.findGestanteByCpf("12345678901");

        assertThat(foundGestante).isPresent();
        assertThat(foundGestante.get().getCpf()).isEqualTo("12345678901");
        assertThat(foundGestante.get().getNome()).isEqualTo("Maria");
    }

    @Test
    public void testFindGestanteByCpf_NotFound() {
        Optional<Gestante> foundGestante = gestanteRepository.findGestanteByCpf("00000000000");

        assertThat(foundGestante).isNotPresent();
    }

    @Test
    public void testSaveGestante() {
        Gestante gestante = createGestante("98765432100", "Ana", "Ano");

        Gestante savedGestante = gestanteRepository.save(gestante);

        assertThat(savedGestante.getId()).isNotNull();
        assertThat(savedGestante.getNome()).isEqualTo("Ana");
        assertThat(savedGestante.getCpf()).isEqualTo("98765432100");
    }

    @Test
    public void testUpdateGestante() {
        Gestante gestante = createGestante("22222222222", "Joana", "Joano");
        Gestante savedGestante = gestanteRepository.save(gestante);

        savedGestante.setNome("Joana Updated");
        Gestante updatedGestante = gestanteRepository.save(savedGestante);

        assertThat(updatedGestante.getNome()).isEqualTo("Joana Updated");
    }

    @Test
    public void testDeleteGestante() {
        Gestante gestante = createGestante("33333333333", "Paula", "Paulo");
        Gestante savedGestante = gestanteRepository.save(gestante);

        gestanteRepository.delete(savedGestante);

        Optional<Gestante> deletedGestante = gestanteRepository.findById(savedGestante.getId());
        assertThat(deletedGestante).isNotPresent();
    }

    @Test
    public void testFindAllGestantes() {
        Gestante gestante1 = createGestante("11111111111", "Maria", "Mario");
        Gestante gestante2 = createGestante("22222222222", "Ana", "Ano");
        gestante2.setEmail("maisa.silva@gmail.com");
        gestanteRepository.save(gestante1);
        gestanteRepository.save(gestante2);

        List<Gestante> gestantes = gestanteRepository.findAll();

        assertThat(gestantes).hasSize(2);
        assertThat(gestantes).extracting(Gestante::getCpf).contains("11111111111", "22222222222");
    }

    @Test
    public void testGestanteUniqueCpf() {
        Gestante gestante1 = createGestante("44444444444", "Lara", "Laro");
        gestanteRepository.save(gestante1);

        Gestante gestante2 = createGestante("44444444444", "Sofia", "Sofio");

        org.junit.jupiter.api.Assertions.assertThrows(Exception.class, () -> {
            gestanteRepository.save(gestante2);
        });
    }

    @Test
    public void testFindGestanteByCpfWithDifferentCase() {
        Gestante gestante = createGestante("55555555555", "Beatriz", "Dante");
        gestanteRepository.save(gestante);

        Optional<Gestante> foundGestante = gestanteRepository.findGestanteByCpf("55555555555");

        assertThat(foundGestante).isPresent();
        assertThat(foundGestante.get().getNome()).isEqualTo("Beatriz");
    }
}
