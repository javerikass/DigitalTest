package ru.digitalchief.test.mapper;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;
import ru.digitalchief.test.dto.PlayCharacterDto;
import ru.digitalchief.test.entity.PlayCharacter;

@Component
@RequiredArgsConstructor
public class PlayCharacterMapper {

  private final ModelMapper modelMapper;
  private final ItemMapper itemMapper;

  public PlayCharacterDto convertCharacterToDto(PlayCharacter playCharacter) {
    PlayCharacterDto map = modelMapper.map(playCharacter, PlayCharacterDto.class);
    if (playCharacter.getItemsList() != null) {
      map.setItemsDto(itemMapper.convertListItemToListDto(playCharacter.getItemsList()));
    }
    return map;
  }

  public PlayCharacter convertDtoToCharacter(PlayCharacterDto playCharacterDto) {
    return modelMapper.map(playCharacterDto, PlayCharacter.class);
  }

  public List<PlayCharacterDto> convertListCharacterToListDto(
      List<PlayCharacter> listPlayCharacter) {
    return modelMapper.map(listPlayCharacter, new TypeToken<List<PlayCharacterDto>>() {
    }.getType());
  }
}
