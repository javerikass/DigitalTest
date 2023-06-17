package ru.digitalchief.test.mapper;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;
import ru.digitalchief.test.dto.ItemDto;
import ru.digitalchief.test.entity.Item;

@Component
@RequiredArgsConstructor
public class ItemMapper {

  private final ModelMapper modelMapper;

  public ItemDto convertItemToItemDto(Item item) {
    return modelMapper.map(item, ItemDto.class);
  }

  public Item convertItemDtoToItem(ItemDto itemDto) {
    return modelMapper.map(itemDto, Item.class);
  }

  public List<ItemDto> convertListItemToListDto(List<Item> list) {
    return modelMapper.map(list, new TypeToken<List<ItemDto>>() {
    }.getType());
  }

}
