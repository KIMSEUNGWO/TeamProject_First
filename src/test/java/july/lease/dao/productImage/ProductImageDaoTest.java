package july.lease.dao.productImage;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import july.lease.domain.ProductImage;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class ProductImageDaoTest {
	
	@Autowired
	public ProductImageDao productImageDao;
	
	@Test
	void save() {
		//given
		List<ProductImage> list = new ArrayList<>();
		list.add(new ProductImage(1L, "tent1.jpg", "a1.jpg"));
		list.add(new ProductImage(1L, "tent2.jpg", "a2.jpg"));
		productImageDao.save(list);
		
		//when
		List<ProductImage> findList = productImageDao.findAllByProductId(1L);
		
		//then
		assertThat(findList.size()).isEqualTo(list.size());
		assertThat(findList.get(0).getProductImageId())
			.isEqualTo(list.get(0).getProductImageId());
	}

}
