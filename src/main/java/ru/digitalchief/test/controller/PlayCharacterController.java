package ru.digitalchief.test.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.digitalchief.test.dto.PlayCharacterDto;
import ru.digitalchief.test.service.PlayCharacterService;

@RestController
@RequestMapping("/v1/character")
@RequiredArgsConstructor
public class PlayCharacterController {

  private final PlayCharacterService playCharacterService;

  @GetMapping("/")
  public List<PlayCharacterDto> getAllCharacters() {
    return playCharacterService.findAll();
  }

  @GetMapping("/getByName")
  public ResponseEntity<PlayCharacterDto> getCharacterByName(String name) {
    PlayCharacterDto character = playCharacterService.getCharacterByName(name);
    if (character != null) {
      return ResponseEntity.ok(character);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/getByIdWithItems/{id}")
  public ResponseEntity<PlayCharacterDto> getCharacterByIdWithItems(@PathVariable Long id) {
    PlayCharacterDto character = playCharacterService.findByIdWithItems(id);
    if (character != null) {
      return ResponseEntity.ok(character);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping("/create")
  public void createCharacter(@RequestBody PlayCharacterDto character) {
    playCharacterService.saveCharacter(character);
  }

  @PostMapping("/update")
  public ResponseEntity updateCharacterById(@RequestBody PlayCharacterDto character) {
    playCharacterService.updateCharacter(character);
    return ResponseEntity.ok("Ok");
  }

  @PostMapping("/delete/{id}")
  public void deleteCharacterByName(@PathVariable Long id) {
    playCharacterService.deleteCharacter(id);
  }
}

