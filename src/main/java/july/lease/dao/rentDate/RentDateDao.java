package july.lease.dao.rentDate;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;

import july.lease.domain.RentDate;
import july.lease.dto.RentAbleDateDto;
import july.lease.dto.RentAbleRequestDto;
import july.lease.dto.RentOrderStatusDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
@RequiredArgsConstructor
public class RentDateDao {
	
	private final RentDateMapper rentDateMapper;
	
	public List<RentDate> save(List<RentDate> rentDates) {
		for(RentDate rentDate : rentDates) {
			rentDateMapper.save(rentDate);
			//log.info("save rentDate={}", rentDate);
		}
		return rentDates;
	}
	
	public RentDate findByRentDateId(Long rentDateId) {
		return rentDateMapper.findByRentDateId(rentDateId);
	}
	
	public List<RentDate> findByProductId(Long productId) {
		return rentDateMapper.findByProductId(productId);
	}
	
	public List<RentOrderStatusDto> checkOrders(Long productId){
		List<RentOrderStatusDto> rentOrderStatusDto = rentDateMapper.checkOrders(productId);
		//log.info("checkOrders rentOrderStatusDto={}",rentOrderStatusDto);
		// rentOrderStatusDto가 널이면 비어인는 컬렉션 반환
		return rentOrderStatusDto != null ? rentOrderStatusDto : Collections.emptyList();
	}
	
	public int rentOrderStatusSize(Long productId, RentAbleRequestDto rentAbleRequestDto) {
		int result = rentDateMapper.rentOrderStatusSize(productId, rentAbleRequestDto);
		//log.info("RentDateDao rentOrderStatusSize={}", result);
		return result;
	}
	
	public void delete(List<RentDate> rentDates) {
		for(RentDate rentDate : rentDates) {
			rentDateMapper.delete(rentDate.getRentDateId());	
		}
	}
	
	public String findRentAbleDateByProductId(Long productId) {
		List<String> rentDates = rentDateMapper.findRentAbleDateByProductId(productId);
		//log.info("RentDateDao findRentAbleDateByProductId={}", rentDates);
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<rentDates.size(); i++) {
		    sb.append(rentDates.get(i));
		    if(rentDates.size()-1 != i) {
		        sb.append(",");
		    }
		}
		//log.info("RentDateDao findRentAbleDateByProductId={}", sb.toString());
		
		return sb.toString();
	}
	
	public Long findRentDateIdByRentAbleStartDateAndProductId(String rentAbleStartDate, Long productId ) {
		Long result = rentDateMapper.findRentDateIdByRentAbleStartDateAndProductId(rentAbleStartDate, productId);
		//log.info("findRentDateIdByRentAbleStartDateAndProductId={}", result);
		return result;
	}
	
	public List<RentAbleDateDto> findRentAbleDatesWithinOrderDates(Long productId, String orderRentStartDate, String orderRentEndDate) {
		return rentDateMapper.findRentAbleDatesWithinOrderDates(productId, orderRentStartDate, orderRentEndDate);
	}

}
