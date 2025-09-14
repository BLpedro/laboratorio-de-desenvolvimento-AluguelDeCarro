package grupox.alugueldecarros.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import grupox.alugueldecarros.entity.Aluguel;

public interface AluguelRepository extends JpaRepository<Aluguel, Long> {
}