package july.lease.dao.product;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import july.lease.domain.Product;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class ProductDaoTest {
	
	@Autowired
	private ProductDao productDao;
	
	@Test
	void findByProductIdForEdit() {
		//given
		Product product = new Product(1L, 101L, "상품1", 10000, "내용1", "서울");
		productDao.save(product);
		Product saveProduct = productDao.findByProductId(product.getProductId());
	
		//when
		Product findProduct = productDao.findByProductIdForEdit(saveProduct.getProductId());
		
		//then
		Assertions.assertThat(findProduct.getProductContent()).isEqualTo(saveProduct.getProductContent());
	}

}
