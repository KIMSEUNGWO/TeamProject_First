package july.lease.dao.rentDate;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import july.lease.domain.RentDate;
import july.lease.dto.RentAbleDateDto;
import july.lease.dto.RentAbleRequestDto;
import july.lease.dto.RentOrderStatusDto;

@Mapper
public interface RentDateMapper {

	void save(RentDate rentDate);
	
	RentDate findByRentDateId(Long rentDateId);
	
	List<RentDate> findByProductId(Long productId);
	
	List<RentOrderStatusDto> checkOrders(Long productId);
	
	int rentOrderStatusSize(@Param("productId") Long productId, @Param("rentAble") RentAbleRequestDto rentAbleRequestDto);
	
	void delete(Long rentDateId);
	
	List<String> findRentAbleDateByProductId(Long productId);
	
	Long findRentDateIdByRentAbleStartDateAndProductId(
			@Param("startDate") String rentAbleStartDate, @Param("productId") Long productId);
	
	List<RentAbleDateDto> findRentAbleDatesWithinOrderDates(
			@Param("productId") Long productId,
			@Param("orderRentStartDate")String orderRentStartDate, @Param("orderRentEndDate")String orderRentEndDate);
}
