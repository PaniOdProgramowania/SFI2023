package pl.paniodprogramowania.sfi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.paniodprogramowania.sfi.domain.WorkshopEntity;

@Repository
public interface WorkshopRepository extends JpaRepository<WorkshopEntity, Long> {
}
