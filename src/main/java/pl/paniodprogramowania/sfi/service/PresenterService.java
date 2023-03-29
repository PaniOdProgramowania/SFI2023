package pl.paniodprogramowania.sfi.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.paniodprogramowania.sfi.domain.PresenterEntity;
import pl.paniodprogramowania.sfi.domain.WorkshopEntity;
import pl.paniodprogramowania.sfi.dto.Presenter;
import pl.paniodprogramowania.sfi.repositories.PresenterRepository;

@Service
public class PresenterService {
  @Autowired
  private PresenterRepository presenterRepository;

  public List<Presenter> getPresenters() {
    return presenterRepository.findAll()
        .stream()
        .map(PresenterService::getPresenter)
        .toList();
  }

  public void savePresenter(Presenter presenter) {
    presenterRepository.save(PresenterEntity.builder()
            .presenterSurname(presenter.getPresenterSurname())
            .presenterName(presenter.getPresenterName())
//            .workshopsRunByThisPresenter()
            .build());
  }

  private static Presenter getPresenter(PresenterEntity presenterEntity) {
    return Presenter
        .builder()
        .presenterName(presenterEntity.getPresenterName())
        .presenterSurname(presenterEntity.getPresenterSurname())
        .presenterId(presenterEntity.getPresenterId())
        .workshopsRunByThisPresenter(presenterEntity
            .getWorkshopsRunByThisPresenter()
            .stream()
            .map(WorkshopEntity::getWorkshopId)
            .toList())
        .build();
  }

  public Presenter getPresenter(Long presenterId) {
    Optional<PresenterEntity> contact = presenterRepository.findById(presenterId);
    if (contact.isEmpty()){
      throw new IllegalArgumentException("no presenter with id " + presenterId);
    }
    return getPresenter(contact.get());
  }
}
