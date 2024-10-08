package br.com.guilhermeramos.crud_usuario.consulta.repositories;

import br.com.guilhermeramos.crud_usuario.consulta.domain.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaRepository  extends JpaRepository<Consulta, Long> {
}
