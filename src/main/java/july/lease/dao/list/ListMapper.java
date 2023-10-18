package july.lease.dao.list;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import july.lease.dto.ListDto;

@Mapper
public interface ListMapper {

	List<ListDto> getList(@Param("searchWord") String searchWord,
							@Param("startDate") String startDate,
							@Param("endDate") String endDate,
							@Param("sort") String sort,
							@Param("category") String category);
	
	List<ListDto> getListAJAX(@Param("searchWord") String searchWord, 
							@Param("startDate") String startDate, 
							@Param("endDate") String endDate, 
							@Param("sort") String sort,
							@Param("startRow") String startRow,
							@Param("endRow") String endRow,
							@Param("category") String category);
	
	String getCount(@Param("searchWord") String searchWord,
					@Param("startDate") String startDate,
					@Param("endDate") String endDate,
					@Param("category") String category);
}
