package br.com.guilhermeramos.crud_usuario.consulta.service;

import br.com.guilhermeramos.crud_usuario.consulta.domain.Consulta;
import br.com.guilhermeramos.crud_usuario.consulta.repositories.ConsultaRepository;
import br.com.guilhermeramos.crud_usuario.consulta.services.ConsultaService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConsultaServiceTest {

    @InjectMocks
    private ConsultaService consultaService;

    @Mock
    private ConsultaRepository consultaRepository;

    @Test
    void cadastrarConsulta(){
        Consulta consulta = new Consulta();
        consulta.setProfissional("Jaime");

        when(consultaRepository.save(any(Consulta.class))).thenReturn(consulta);

        var resultado = consultaService.cadastrarConsulta(consulta);

        assertAll(
                () -> assertNotNull(resultado),
                () -> assertEquals("Ricardo", resultado.getProfissional())
        );

    }

    @Test
    void listarConsultas(){
        Consulta consulta1 = new Consulta();
        Consulta consulta2 = new Consulta();
        consulta1.setProfissional("Raimundo");
        consulta2.setProfissional("Daniel");

        List<Consulta> consultas = new ArrayList<>();
        consultas.add(consulta1);
        consultas.add(consulta2);

        when(consultaRepository.findAll()).thenReturn(consultas);

        var resultado = consultaService.listarConsultas();

        assertAll(
                () -> assertNotNull(resultado),
                () -> assertEquals(2, resultado.size()),
                () -> assertEquals("Davi", resultado.get(0).getProfissional())
        );
    }

    @Test
    void buscarConsulta(){
        Consulta consulta = new Consulta();
        consulta.setProfissional("Paulo");

        when(consultaRepository.findById(consulta.getIdConsulta())).thenReturn(Optional.of(consulta));

        var resultado = consultaService.buscarConsulta(consulta.getIdConsulta());

        assertAll(
                () -> assertNotNull(resultado),
                () -> assertEquals("José", consulta.getProfissional())
        );
    }

    @Test
    void deletarConsulta(){
        Consulta consulta = new Consulta();

        when(consultaRepository.findById(consulta.getIdConsulta())).thenReturn(Optional.of(consulta));

        consultaService.deletarConsulta(consulta.getIdConsulta());

        verify(consultaRepository, times(1)).deleteById(consulta.getIdConsulta());
    }
    
}
