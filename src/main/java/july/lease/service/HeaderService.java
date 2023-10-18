package july.lease.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import july.lease.dao.header.HeaderDao;
import july.lease.domain.Category;
import july.lease.dto.HeaderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class HeaderService {

	
	private final HeaderDao headerDao;
	
	public List<HeaderDto> getCategory() {
		 
		List<Category> list = headerDao.getCategory();

		return listToHeaderDto(list);
	}
	
	public static List<HeaderDto> listToHeaderDto(List<Category> list) {
	
		return list.stream().map(cate->
			new HeaderDto(cate.getCategoryId(), cate.getCategoryId2(), cate.getCategoryName())).collect(Collectors.toList());
	}
	
	public int notReadMessageCnt(Long memberId) {
		return headerDao.notReadMessageCnt(memberId);
	}
	
}
