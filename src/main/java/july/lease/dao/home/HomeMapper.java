package july.lease.dao.home;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import july.lease.domain.Criteria;
import july.lease.domain.Product;
import july.lease.dto.HomeItemDto;
import july.lease.dto.HomePopularItemDto;

@Mapper
public interface HomeMapper {

	// �럹�씠吏� 泥섎━ �썑 由ъ뒪�듃 議고쉶
	List<HomeItemDto> getAllItem(Criteria cri); 
	List<HomeItemDto> getAllItemAJAX(@Param("startRow") Long startRow, @Param("endRow") Long endRow); 
	
	List<HomePopularItemDto> popularItem();
}
