package july.lease.service;

import java.util.List;

import org.springframework.stereotype.Service;

import july.lease.dao.list.ListMapper;
import july.lease.dto.ListDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ListServiceImpl implements ListService{
	
	private final ListMapper listMapper;
	
	@Override
	public List<ListDto> getList(String searchWord, String startDate, String endDate, String sort, String category) {
		return listMapper.getList(searchWord, startDate, endDate, sort, category);
	}

	@Override
	public List<ListDto> getListAJAX(String searchWord, String startDate, String endDate, String sort, String startRow, String endRow, String category) {
		return listMapper.getListAJAX(searchWord, startDate, endDate, sort, startRow, endRow, category);
	}

	@Override
	public String getCount(String searchWord, String startDate, String endDate, String category) {
		return listMapper.getCount(searchWord, startDate, endDate, category);
	}

}
