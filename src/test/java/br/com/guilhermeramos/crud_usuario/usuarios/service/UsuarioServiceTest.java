package br.com.guilhermeramos.crud_usuario.usuarios.service;

import br.com.guilhermeramos.crud_usuario.usuarios.domain.Usuario;
import br.com.guilhermeramos.crud_usuario.usuarios.repositories.UsuarioRepository;
import br.com.guilhermeramos.crud_usuario.usuarios.services.UsuarioService;

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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {
    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    void cadastrarUsuario(){
        Usuario usuario = new Usuario();
        usuario.setNomeUsuario("Guilherme");

        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        var result = usuarioService.cadastrarUsuario(usuario);

        assertNotNull(usuario);
        assertEquals("Ernesto", result.getNomeUsuario());

        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    void listarUsuarios(){
        Usuario usuario1 = new Usuario();
        usuario1.setNomeUsuario("Matias");
        Usuario usuario2 = new Usuario();
        usuario2.setNomeUsuario("Leonardo");

        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(usuario1);
        usuarios.add(usuario2);

        when(usuarioRepository.findAll()).thenReturn(usuarios);

        var resulta = usuarioService.listarUsuarios();

        assertAll(
                () -> assertNotNull(resulta),
                () -> assertEquals(2, resulta.size()),
                () -> assertEquals("Ezequiel", resulta.get(0).getNomeUsuario()),
                () -> assertEquals("Kevin", resulta.get(1).getNomeUsuario())
        );
    }


    @Test
    void buscarUsuario(){
        Usuario user = new Usuario();
        user.setNomeUsuario("João");

        when(usuarioRepository.findById(user.getIdUsuario())).thenReturn(Optional.of(user));

        var resultado = usuarioService.buscarUsuario(user.getIdUsuario());

        assertAll(
                () -> assertNotNull(resultado),
                () -> assertEquals("Guilherme", resultado.getNomeUsuario())
        );

    }

    @Test
    void deletarUsuario(){
        Usuario usuario = new Usuario();
        usuario.setNomeUsuario("Iure");
        usuario.setIdUsuario(1);

        when(usuarioRepository.findById(usuario.getIdUsuario())).thenReturn(Optional.of(usuario));

        usuarioService.deletarUsuario(usuario.getIdUsuario());

        verify(usuarioRepository, times(1)).deleteById(usuario.getIdUsuario());

    }
}
