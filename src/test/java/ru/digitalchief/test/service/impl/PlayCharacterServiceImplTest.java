package ru.digitalchief.test.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import ru.digitalchief.test.dto.ItemDto;
import ru.digitalchief.test.dto.PlayCharacterDto;
import ru.digitalchief.test.entity.Item;
import ru.digitalchief.test.entity.PlayCharacter;
import ru.digitalchief.test.exception.PlayCharacterException;
import ru.digitalchief.test.mapper.PlayCharacterMapper;
import ru.digitalchief.test.repository.PlayCharacterRepository;

@ExtendWith(MockitoExtension.class)
class PlayCharacterServiceImplTest {

  @InjectMocks
  private PlayCharacterServiceImpl playCharacterService;
  @Mock
  private PlayCharacterRepository characterRepository;
  @Mock
  private PlayCharacterMapper characterMapper;

  @Test
  void testGetCharacterByName() {
    String name = "Test Character";
    PlayCharacter character = PlayCharacter.builder().name(name).build();

    Mockito.when(characterRepository.findPlayCharacterByName(name))
        .thenReturn(Optional.of(character));
    Mockito.when(characterMapper.convertCharacterToDto(character))
        .thenReturn(PlayCharacterDto.builder().name(name).build());

    PlayCharacterDto result = playCharacterService.getCharacterByName(name);

    assertNotNull(result);
    Mockito.verify(characterRepository, Mockito.times(1)).findPlayCharacterByName(name);
    Mockito.verify(characterMapper, Mockito.times(1)).convertCharacterToDto(character);
  }

  @Test
  void testGetCharacterByNameWhenNotFound() {

    String name = "Test Character";

    Mockito.when(characterRepository.findPlayCharacterByName(name)).thenReturn(Optional.empty());

    PlayCharacterException exception = Assertions.assertThrows(PlayCharacterException.class, () -> {
      playCharacterService.getCharacterByName(name);
    });

    assertEquals("Could not find character " + name, exception.getMessage());
    assertEquals(HttpStatus.NOT_FOUND.value(), exception.getStatus());

    Mockito.verify(characterRepository, Mockito.times(1)).findPlayCharacterByName(name);
    Mockito.verify(characterMapper, Mockito.never())
        .convertCharacterToDto(Mockito.any(PlayCharacter.class));
  }

  @Test
  void testFindByIdWithItems() {
    Long id = 1L;
    List<Item> items = new ArrayList<>();
    items.add(new Item());
    items.add(new Item());
    PlayCharacter character = PlayCharacter.builder().id(id).itemsList(items).build();

    List<ItemDto> itemsDto = new ArrayList<>();
    itemsDto.add(new ItemDto());
    itemsDto.add(new ItemDto());

    Mockito.when(characterRepository.findByIdWithItems(id)).thenReturn(Optional.of(character));
    Mockito.when(characterMapper.convertCharacterToDto(character))
        .thenReturn(PlayCharacterDto.builder().itemsDto(itemsDto).build());

    PlayCharacterDto result = playCharacterService.findByIdWithItems(id);

    assertNotNull(result.getItemsDto());
    Mockito.verify(characterRepository, Mockito.times(1)).findByIdWithItems(id);
    Mockito.verify(characterMapper, Mockito.times(1)).convertCharacterToDto(character);
  }

  @Test
  void testFindByIdWithItemsWhenNotFound() {
    Long id = 1L;

    Mockito.when(characterRepository.findByIdWithItems(id)).thenReturn(Optional.empty());

    PlayCharacterException exception = Assertions.assertThrows(PlayCharacterException.class, () -> {
      playCharacterService.findByIdWithItems(id);
    });

    assertEquals("Could not find character by id " + id, exception.getMessage());
    assertEquals(HttpStatus.NOT_FOUND.value(), exception.getStatus());

    Mockito.verify(characterRepository, Mockito.times(1)).findByIdWithItems(id);
    Mockito.verify(characterMapper, Mockito.never())
        .convertCharacterToDto(Mockito.any(PlayCharacter.class));
  }

  @Test
  void testFindAll() {
    List<PlayCharacter> characters = new ArrayList<>();
    characters.add(new PlayCharacter());
    characters.add(new PlayCharacter());

    List<PlayCharacterDto> charactersListDto = new ArrayList<>();
    charactersListDto.add(new PlayCharacterDto());
    charactersListDto.add(new PlayCharacterDto());

    Mockito.when(characterRepository.findAll()).thenReturn(characters);
    Mockito.when(characterMapper.convertListCharacterToListDto(characters)).thenReturn(charactersListDto);

    List<PlayCharacterDto> result = playCharacterService.findAll();

    assertNotNull(result);
    assertFalse(result.isEmpty());
    Mockito.verify(characterRepository, Mockito.times(1)).findAll();
    Mockito.verify(characterMapper, Mockito.times(1)).convertListCharacterToListDto(characters);
  }

  @Test
  void testFindAllWhenNotFound() {
    Mockito.when(characterRepository.findAll()).thenReturn(new ArrayList<>());

    PlayCharacterException exception = Assertions.assertThrows(PlayCharacterException.class, () -> {
      playCharacterService.findAll();
    });

    assertEquals("Could not find any play characters", exception.getMessage());

    Mockito.verify(characterRepository, Mockito.times(1)).findAll();
    Mockito.verify(characterMapper, Mockito.never()).convertListCharacterToListDto(Mockito.anyList());
  }

  @Test
  void testUpdateCharacter() {
    PlayCharacterDto characterDto = PlayCharacterDto.builder()
        .id(1L)
        .name("Test Character")
        .health(100)
        .level(10)
        .stamina(50)
        .strength(20)
        .build();

    PlayCharacter character = new PlayCharacter();
    Mockito.when(characterMapper.convertDtoToCharacter(characterDto)).thenReturn(character);

    playCharacterService.updateCharacter(characterDto);

    Mockito.verify(characterRepository, Mockito.times(1)).updatePlayCharacter(
        character.getId(), character.getName(), character.getHealth(), character.getLevel(),
        character.getStamina(), character.getStrength());
    Mockito.verify(characterMapper, Mockito.times(1)).convertDtoToCharacter(characterDto);
  }

  @Test
  void testDeleteCharacter() {
    Long id = 1L;
    playCharacterService.deleteCharacter(id);
    Mockito.verify(characterRepository, Mockito.times(1)).deleteById(id);
  }

  @Test
  void testSaveCharacter() {
    PlayCharacterDto characterDto = new PlayCharacterDto();
    PlayCharacter character = PlayCharacter.builder()
        .name("Test Character")
        .health(100)
        .level(10)
        .stamina(50)
        .strength(20)
        .build();

    Mockito.when(characterMapper.convertDtoToCharacter(characterDto)).thenReturn(character);

    playCharacterService.saveCharacter(characterDto);

    Mockito.verify(characterRepository, Mockito.times(1)).save(character);
    Mockito.verify(characterMapper, Mockito.times(1)).convertDtoToCharacter(characterDto);
  }
}