package july.lease.dao.home;

import java.util.List;

import org.springframework.stereotype.Repository;

import july.lease.dao.member.MemberDao;
import july.lease.dao.member.MemberMapper;
import july.lease.domain.Criteria;
import july.lease.domain.Product;
import july.lease.dto.HomeItemDto;
import july.lease.dto.HomePopularItemDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
@RequiredArgsConstructor
public class HomeDao {

	private final HomeMapper homeMapper;
	
	public List<HomeItemDto> getAllItem(Criteria cri){
		return homeMapper.getAllItem(cri);
	}
	
	public List<HomePopularItemDto> popularItem(){
		return homeMapper.popularItem();
	}
	
	public List<HomeItemDto> getAllItemAJAX(Long startRow, Long endRow){
		return homeMapper.getAllItemAJAX(startRow, endRow);
	}
}
