package grupox.alugueldecarros.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import grupox.alugueldecarros.entity.Automovel;
import grupox.alugueldecarros.repository.AutomovelRepository;
@Service
public class AutomovelService {

    @Autowired
    private AutomovelRepository automovelRepository;

    public Automovel createAutomovel(Automovel automovel) {
        return automovelRepository.save(automovel);
    }

    public Automovel updateAutomovel(Long id, Automovel updateAutomovel) {
        return automovelRepository.findById(id)
                .map(automovel -> {
                    automovel.setModelo(updateAutomovel.getModelo());
                    automovel.setMarca(updateAutomovel.getMarca());
                    automovel.setAno(updateAutomovel.getAno());
                    automovel.setPlaca(updateAutomovel.getPlaca());
                    return automovelRepository.save(automovel);
                })
                .orElseThrow(() -> new RuntimeException("Automóvel não encontrado com id: " + id));
    }

    public void deleteAutomovel(Long id) {
        automovelRepository.deleteById(id);
    }

    public Automovel getAutomovelById(Long id) {
        return automovelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Automóvel não encontrado com id: " + id));
    }

    public List<Automovel> getAllAutomoveis() {
        return automovelRepository.findAll();
    }
}
