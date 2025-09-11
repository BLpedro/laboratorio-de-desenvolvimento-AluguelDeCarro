package grupox.alugueldecarros.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import grupox.alugueldecarros.entity.Automovel;

public interface AutomovelRepository extends JpaRepository<Automovel, Long> {
}