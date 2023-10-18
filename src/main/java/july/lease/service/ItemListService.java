package july.lease.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import july.lease.dao.list.ItemListMapper;
import july.lease.domain.Criteria;
import july.lease.dto.ItemListDto;

@Service
public class ItemListService {
	
	@Autowired
	private ItemListMapper itemListMapper;
	
	
	public List<ItemListDto> getItemListAJAX(String search, String startDate, String endDate, String categoryId,long startRow, long endRow) {
        List<ItemListDto> lists = itemListMapper.getItemListAJAX(search, startDate, endDate, categoryId, startRow, endRow);
		return lists;
    }



	 public List<ItemListDto> getItemList(Criteria cri) {
	
	        return itemListMapper.getItemList(cri);
	 }
		
		
	}
