package ru.digitalchief.test.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.digitalchief.test.dto.PlayCharacterDto;
import ru.digitalchief.test.entity.PlayCharacter;
import ru.digitalchief.test.exception.PlayCharacterException;
import ru.digitalchief.test.mapper.PlayCharacterMapper;
import ru.digitalchief.test.repository.PlayCharacterRepository;
import ru.digitalchief.test.service.PlayCharacterService;

@Service
@Transactional
@RequiredArgsConstructor
public class PlayCharacterServiceImpl implements PlayCharacterService {

  private final PlayCharacterMapper mapper;
  private final PlayCharacterRepository playCharacterRepository;

  @Override
  public PlayCharacterDto getCharacterByName(String name) {
    PlayCharacter character = playCharacterRepository.findPlayCharacterByName(name)
        .orElseThrow(() -> PlayCharacterException.getPlayCharacterException(
            "Could not find character " + name,
            HttpStatus.NOT_FOUND));
    return mapper.convertCharacterToDto(character);
  }

  @Override
  public PlayCharacterDto findByIdWithItems(Long id) {
    PlayCharacter character = playCharacterRepository.findByIdWithItems(id)
        .orElseThrow(() -> PlayCharacterException.getPlayCharacterException(
            "Could not find character by id " + id,
            HttpStatus.NOT_FOUND));
    return mapper.convertCharacterToDto(character);
  }

  @Override
  public List<PlayCharacterDto> findAll() {
    List<PlayCharacter> characterList = playCharacterRepository.findAll();
    if (characterList.isEmpty()) {
      throw PlayCharacterException.getPlayCharacterException("Could not find any play characters",
          HttpStatus.NOT_FOUND);
    }
    return mapper.convertListCharacterToListDto(characterList);
  }

  @Override
  public void updateCharacter(PlayCharacterDto playCharacter) {
    PlayCharacter character = mapper.convertDtoToCharacter(playCharacter);
    playCharacterRepository.updatePlayCharacter(character.getId(), character.getName(),
        character.getHealth(), character.getLevel(),
        character.getStamina(), character.getStrength());
  }

  @Override
  public void deleteCharacter(Long id) {
    playCharacterRepository.deleteById(id);
  }

  @Override
  public void saveCharacter(PlayCharacterDto playCharacter) {
    playCharacterRepository.save(mapper.convertDtoToCharacter(playCharacter));
  }
}
