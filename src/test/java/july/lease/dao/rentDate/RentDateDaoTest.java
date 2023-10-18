package july.lease.dao.rentDate;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import july.lease.domain.RentDate;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class RentDateDaoTest {
	
	@Autowired
	private RentDateDao rentDateDao;
	
	@Test
	void save() {
	
		//given
		RentDate rentDate = new RentDate(1L, "2023-08-03", "2023-08-07");
		List<RentDate> rentDates = new ArrayList<>();
		rentDates.add(rentDate);
		
		//when
		rentDateDao.save(rentDates);
		RentDate findRentDate = rentDateDao.findByRentDateId(rentDate.getRentDateId());
		
		//then
		Assertions.assertThat(findRentDate.getRentDateId())
			.isEqualTo(rentDates.get(0).getRentDateId());
		
	}

}
