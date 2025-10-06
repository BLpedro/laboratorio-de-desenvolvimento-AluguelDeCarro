package grupox.alugueldecarros.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import grupox.alugueldecarros.entity.Aluguel;
import grupox.alugueldecarros.service.AluguelService;


@RestController
@RequestMapping("/alugueis")
public class AluguelController {

    @Autowired
    private AluguelService aluguelService;

    @GetMapping
    public List<Aluguel> getAllAlugueis() {
        return aluguelService.getAllAlugueis();
    }


    @GetMapping("/{id}")
    public Aluguel getAluguelById(@PathVariable Long id) {
        try {
            return aluguelService.getAluguelById(id);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping
    public Aluguel createAluguel(@RequestBody Aluguel aluguel) {
        return aluguelService.createAluguel(aluguel);
    }

    @PutMapping("/{id}")
    public Aluguel updateAluguel(@PathVariable Long id, @RequestBody Aluguel aluguel) {
        aluguel.setId(id);
        return aluguelService.updateAluguel(id, aluguel);
    }

    @DeleteMapping("/{id}")
    public void deleteAluguel(@PathVariable Long id) {
        aluguelService.deleteAluguel(id);
    }
}