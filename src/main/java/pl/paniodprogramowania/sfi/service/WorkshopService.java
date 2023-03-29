package pl.paniodprogramowania.sfi.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.paniodprogramowania.sfi.domain.StudentEntity;
import pl.paniodprogramowania.sfi.domain.WorkshopEntity;
import pl.paniodprogramowania.sfi.dto.Workshop;
import pl.paniodprogramowania.sfi.repositories.WorkshopRepository;

@Service
public class WorkshopService {

  @Autowired
  private WorkshopRepository workshopRepository;

  public List<Workshop> getWorkshops() {
    return workshopRepository.findAll().stream()
        .map(WorkshopService::getWorkshop)
        .toList();
  }

  private static Workshop getWorkshop(WorkshopEntity workshopEntity) {
    return Workshop
        .builder()
        .presenterId(workshopEntity.getPresenter().getPresenterId())
        .workshopId(workshopEntity.getWorkshopId())
        .workshopDateTime(workshopEntity.getWorkshopDateTime())
        .workshopDescription(workshopEntity.getWorkshopDescription())
        .workshopTitle(workshopEntity.getWorkshopTitle())
        .studentsAtThisWorkshop(workshopEntity
            .getStudentsAtThisWorkshop()
            .stream()
            .map(StudentEntity::getStudentId)
            .collect(Collectors.toList()))
        .build();
  }

  public Workshop getWorkshop(Long presenterId) {
    Optional<WorkshopEntity> contact = workshopRepository.findById(presenterId);
    if (contact.isEmpty()){
      throw new IllegalArgumentException("no workshop with id " + presenterId);
    }
    return getWorkshop(contact.get());
  }

}
