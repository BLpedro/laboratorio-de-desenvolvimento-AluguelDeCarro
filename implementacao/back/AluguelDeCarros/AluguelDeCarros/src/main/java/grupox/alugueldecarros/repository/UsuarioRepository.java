package grupox.alugueldecarros.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import grupox.alugueldecarros.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByCnpjCpfAndSenha(String cnpjCpf, String senha);
}