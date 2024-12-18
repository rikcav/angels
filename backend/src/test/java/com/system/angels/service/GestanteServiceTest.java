package com.system.angels.service;

import com.system.angels.domain.Gestante;
import com.system.angels.domain.User;
import com.system.angels.domain.enums.Raca;
import com.system.angels.domain.enums.Sexo;
import com.system.angels.dto.create.GestanteDTO;
import com.system.angels.dto.response.GestanteRO;
import com.system.angels.exceptions.GestanteNotFoundException;
import com.system.angels.exceptions.InvalidRequestException;
import com.system.angels.repository.DadosEvolutivosRepository;
import com.system.angels.repository.GestanteRepository;
import com.system.angels.repository.UserRepository;
import com.system.angels.service.impl.EmailService;
import com.system.angels.service.impl.GestanteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class GestanteServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private GestanteRepository gestanteRepository;

    @Mock
    private DadosEvolutivosRepository dadosEvolutivosRepository;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private GestanteService gestanteService;

    private GestanteDTO gestanteDTO;
    private Gestante gestante;
    private User user;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new User();
        user.setUsername("mariazinha");
        user.setPassword("securePassword");
        user.setName("Maria Silva");
        user.setRole("USER");

        // Mock para encontrar o usuário
        when(userRepository.findByUsername("mariazinha")).thenReturn(Optional.of(user));

        // Mock para salvar um usuário
        when(userRepository.save(any(User.class))).thenReturn(user);

        gestanteDTO = new GestanteDTO(
                "Maria Silva",
                new Date(),
                "maria.silva@gmail.com",
                "12345678900",
                Raca.NEGRO,
                Sexo.FEMININO,
                "mariazinha");

        gestante = new Gestante();
        gestante.setId(1L);
        gestante.setNome("Maria");
        gestante.setDataNascimento(new Date());
        gestante.setCpf("12345678901");
        gestante.setRaca(Raca.NEGRO);
        gestante.setSexo(Sexo.FEMININO);
        gestante.setUser(user);
    }


    @Test
    public void testGestantes() {
        when(gestanteRepository.findAll()).thenReturn(List.of(gestante));

        List<GestanteRO> gestantes = gestanteService.gestantes();

        assertThat(gestantes).hasSize(1);
        assertThat(gestantes.get(0).nome()).isEqualTo("Maria");
    }

    @Test
    public void testGestantePorId() {
        when(gestanteRepository.findById(1L)).thenReturn(Optional.of(gestante));

        GestanteRO gestanteRO = gestanteService.gestantePorId(1L);

        assertThat(gestanteRO).isNotNull();
        assertThat(gestanteRO.nome()).isEqualTo("Maria");
    }

    @Test
    public void testGestantePorId_NotFound() {
        when(gestanteRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(GestanteNotFoundException.class, () -> gestanteService.gestantePorId(1L));
    }

    @Test
    public void testGestantePorCpf() {
        when(gestanteRepository.findGestanteByCpf("12345678901")).thenReturn(Optional.of(gestante));

        GestanteRO gestanteRO = gestanteService.gestantePorCpf("12345678901");

        assertThat(gestanteRO).isNotNull();
        assertThat(gestanteRO.cpf()).isEqualTo("12345678901");
    }

    @Test
    public void testGestantePorCpf_NotFound() {
        when(gestanteRepository.findGestanteByCpf("12345678901")).thenReturn(Optional.empty());

        assertThrows(GestanteNotFoundException.class, () -> gestanteService.gestantePorCpf("12345678901"));
    }

    @Test
    public void testRegistrarGestante() {
        when(gestanteRepository.save(any(Gestante.class))).thenReturn(gestante);

        GestanteRO savedGestante = gestanteService.registrarGestante(gestanteDTO);

        assertThat(savedGestante).isNotNull();
        assertThat(savedGestante.cpf()).isEqualTo("12345678901");
    }

    @Test
    public void testRegistrarGestante_InvalidRequest() {
        // Simular que o usuário existe
        when(userRepository.findByUsername("mariazinha")).thenReturn(Optional.of(user));

        // Simular que o save do Gestante lança InvalidRequestException
        doThrow(InvalidRequestException.class).when(gestanteRepository).save(any(Gestante.class));

        // Verificar se a exceção é lançada
        assertThrows(InvalidRequestException.class, () -> gestanteService.registrarGestante(gestanteDTO));
    }

    @Test
    public void testAtualizarGestante() {
        when(gestanteRepository.findById(1L)).thenReturn(Optional.of(gestante));
        when(gestanteRepository.save(any(Gestante.class))).thenReturn(gestante);

        GestanteRO updatedGestante = gestanteService.atualizarGestante(1L, gestanteDTO);

        assertThat(updatedGestante).isNotNull();
        assertThat(updatedGestante.nome()).isEqualTo("Maria");
    }

    @Test
    public void testAtualizarGestante_NotFound() {
        when(gestanteRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(GestanteNotFoundException.class, () -> gestanteService.atualizarGestante(1L, gestanteDTO));
    }

    @Test
    public void testDeletarGestante() {
        when(gestanteRepository.findById(1L)).thenReturn(Optional.of(gestante));

        gestanteService.deletarGestante(1L);

        verify(gestanteRepository, times(1)).delete(gestante);
    }

    @Test
    public void testDeletarGestante_NotFound() {
        when(gestanteRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(GestanteNotFoundException.class, () -> gestanteService.deletarGestante(1L));
    }
}
