package july.lease.service;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import july.lease.dao.product.ProductDao;
import july.lease.dao.product.ProductMapper;
import july.lease.dao.productImage.ProductImageDao;
import july.lease.domain.Product;
import july.lease.domain.ProductImage;
import july.lease.dto.HomeResponseDto;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
@Slf4j
public class ProductServiceTest {
	
	

}
