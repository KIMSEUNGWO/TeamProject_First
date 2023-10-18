package july.lease.service;

import java.util.List;

import org.springframework.stereotype.Service;

import july.lease.dao.home.HomeDao;
import july.lease.domain.Criteria;
import july.lease.dto.HomeItemDto;
import july.lease.dto.HomePopularItemDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class HomeService {

	private final HomeDao homeDao;
	
	public List<HomePopularItemDto> papularItem(){
		List<HomePopularItemDto> popularList = homeDao.popularItem();		
		return popularList;
	}
	
	public List<HomeItemDto> getAllItem(Criteria cri){
		List<HomeItemDto> allList = homeDao.getAllItem(cri);
		// 무한반복 -> 자기자신을 호출
		// return getAllItem(); 
		return allList;
	}
	public List<HomeItemDto> getAllItemAJAX(Long startRow, Long endRow){
		List<HomeItemDto> allList = homeDao.getAllItemAJAX(startRow, endRow);
		return allList;
	}

	public int listCount() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
		
}
