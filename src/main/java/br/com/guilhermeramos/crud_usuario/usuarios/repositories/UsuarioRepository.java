package br.com.guilhermeramos.crud_usuario.usuarios.repositories;

import br.com.guilhermeramos.crud_usuario.usuarios.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
