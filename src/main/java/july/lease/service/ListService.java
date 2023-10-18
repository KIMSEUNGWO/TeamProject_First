package july.lease.service;

import java.util.List;

import july.lease.dto.ListDto;

public interface ListService {
	
	List<ListDto> getList(String searchWord, String startDate, String endDate, String sort, String category);
	List<ListDto> getListAJAX(String searchWord, String startDate, String endDate, String sort, String startRow, String endRow, String category);
	String getCount(String searchWord, String startDate, String endDate, String category);

}
