package july.lease.dao.list;

import java.util.List;

import org.springframework.stereotype.Repository;

import july.lease.domain.Criteria;
import july.lease.dto.ItemListDto;
import lombok.extern.slf4j.Slf4j;

@Repository
public class ItemListDao {

	private final ItemListMapper itemListMapper;

    public ItemListDao(ItemListMapper itemListMapper) {
        this.itemListMapper = itemListMapper;
    }

    public List<ItemListDto> getItemListAJAX(String search, String startDate, String endDate, String categoryId, long startRow, long endRow) {
   
        return itemListMapper.getItemListAJAX(search, startDate, endDate, categoryId, startRow, endRow);
    }
}
