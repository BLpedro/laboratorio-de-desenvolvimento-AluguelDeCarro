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

import grupox.alugueldecarros.entity.Automovel;
import grupox.alugueldecarros.service.AutomovelService;


@RestController
@RequestMapping("/automoveis")
public class AutomovelController {
    
    @Autowired
    private AutomovelService automovelService;

    
    @GetMapping
public List<Automovel> getAllAutomoveis() {
    return automovelService.getAllAutomoveis();
}


    @GetMapping("/{id}")
    public Automovel getAutomovelById(@PathVariable Long id) {
        try {
            return automovelService.getAutomovelById(id);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping
    public Automovel createAutomovel(@RequestBody Automovel automovel) {
        return automovelService.createAutomovel(automovel);
    }

    @PutMapping("/{id}")
    public Automovel updateAutomovel(@PathVariable Long id, @RequestBody Automovel automovel) {
        automovel.setId(id);
        return automovelService.updateAutomovel(id, automovel);
    }

    @DeleteMapping("/{id}")
    public void deleteAutomovel(@PathVariable Long id) {
        automovelService.deleteAutomovel(id);
    }
}
