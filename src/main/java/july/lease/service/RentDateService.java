package july.lease.service;

import org.springframework.stereotype.Service;

import july.lease.dao.rentDate.RentDateDao;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RentDateService {
	
	private final RentDateDao rentDateDao;
	
	public String findRentAbleDateByProductId(Long productId) {
		return rentDateDao.findRentAbleDateByProductId(productId);
	}

}
