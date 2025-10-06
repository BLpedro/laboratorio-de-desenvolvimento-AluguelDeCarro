package grupox.alugueldecarros.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import grupox.alugueldecarros.entity.Aluguel;
import grupox.alugueldecarros.repository.AluguelRepository;

@Service
public class AluguelService {

    @Autowired
    private AluguelRepository aluguelRepository;

    public Aluguel createAluguel(Aluguel aluguel) {
        return aluguelRepository.save(aluguel);
    }


    public Aluguel updateAluguel(Long id, Aluguel updateAluguel) {
        return aluguelRepository.findById(id)
                .map(aluguel -> {
                    aluguel.setClienteID(updateAluguel.getClienteID());
                    aluguel.setAutomovelID(updateAluguel.getAutomovelID());
                    aluguel.setStatusPedido(updateAluguel.getStatusPedido());
                    aluguel.setQuantidadeDias(updateAluguel.getQuantidadeDias());
                    aluguel.setAgenteID(updateAluguel.getAgenteID());
                    return aluguelRepository.save(aluguel);
                })
                .orElseThrow(() -> new RuntimeException("Aluguel não encontrado com id: " + id));
    }

    public void deleteAluguel(Long id) {
        aluguelRepository.deleteById(id);
    }


    public Aluguel getAluguelById(Long id) {
        return aluguelRepository.findById(id)   
                .orElseThrow(() -> new RuntimeException("Aluguel não encontrado com id: " + id));
    }

    public java.util.List<Aluguel> getAllAlugueis() {
        return aluguelRepository.findAll();
    }
}