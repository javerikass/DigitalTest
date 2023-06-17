package ru.digitalchief.test.service;

import java.util.List;
import ru.digitalchief.test.dto.PlayCharacterDto;

public interface PlayCharacterService {

  PlayCharacterDto getCharacterByName(String name);

  PlayCharacterDto findByIdWithItems(Long id);

  List<PlayCharacterDto> findAll();

  void updateCharacter(PlayCharacterDto playCharacter);

  void deleteCharacter(Long id);

  void saveCharacter(PlayCharacterDto playCharacter);
}
