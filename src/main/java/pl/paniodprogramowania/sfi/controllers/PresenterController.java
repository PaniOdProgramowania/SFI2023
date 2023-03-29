package pl.paniodprogramowania.sfi.controllers;

import java.util.List;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.paniodprogramowania.sfi.dto.Presenter;
import pl.paniodprogramowania.sfi.service.PresenterService;

@RestController
@AllArgsConstructor
@Slf4j
public class PresenterController {

  @Autowired
  private PresenterService presenterService;


  @GetMapping("/presenters")
  public List<Presenter> getPresenters(){
    log.info("fetching all Presenters");
    return presenterService.getPresenters();
  }

  @GetMapping("/presenters/{presenterId}")
  public Presenter getPresenterBy(@PathVariable String presenterId){
    log.info("fetching Presenter with id " + presenterId);
    return presenterService.getPresenter(Long.valueOf(presenterId));
  }

  @PostMapping("/presenters/")
  public void addPresenter(@RequestBody Presenter presenter){
    log.info("fetching Presenter with new id");
    presenterService.savePresenter(presenter);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<String> handleBadIdInRequest(IllegalArgumentException exception,
                                                     HttpServletRequest httpServletRequest) {
    log.warn("bad presenter id was provided");
    return new ResponseEntity<>("Provided id is not valid", HttpStatus.NOT_FOUND);
  }
}
