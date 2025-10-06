package grupox.alugueldecarros.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import grupox.alugueldecarros.entity.Usuario;
import grupox.alugueldecarros.repository.UsuarioRepository;

@Service
public class UsuarioService {
         
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario createUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario updateUsuario(Long id, Usuario updateUsuario) {
        return usuarioRepository.findById(id)
                .map(usuario -> {
                    usuario.setNome(updateUsuario.getNome());
                    usuario.setEndereco(updateUsuario.getEndereco());
                    usuario.setRg(updateUsuario.getRg());
                    usuario.setCnpjCpf(updateUsuario.getCnpjCpf());
                    usuario.setTipo(updateUsuario.getTipo());
                    usuario.setSenha(updateUsuario.getSenha());
                    usuario.setSelecao(updateUsuario.getSelecao());
                    usuario.setSomaRendimento(updateUsuario.getSomaRendimento());
                    usuario.setProfissao(updateUsuario.getProfissao());
                    return usuarioRepository.save(usuario);
                })
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com id: " + id));
    }

    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario getUsuarioById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com id: " + id));
    }

    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario login(String cnpjCpf, String senha) {
        return usuarioRepository.findByCnpjCpfAndSenha(cnpjCpf, senha)
                .orElseThrow(() -> new RuntimeException("Credenciais inválidas"));
    }

    }