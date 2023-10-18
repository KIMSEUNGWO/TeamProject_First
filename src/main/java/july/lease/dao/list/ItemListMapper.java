package july.lease.dao.list;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import july.lease.domain.Criteria;
import july.lease.dto.HomeItemDto;
import july.lease.dto.ItemListDto;

@Mapper
public interface ItemListMapper {
	
	 List<ItemListDto> getItemListAJAX(@Param("search") String search,
	            @Param("startDate") String startDate,
	            @Param("endDate") String endDate,
	            @Param("categoryId") String categoryId,
			 @Param("startRow") Long startRow, @Param("endRow") Long endRow);

	 List<ItemListDto> getItemList(Criteria cri);
	 
	

}
