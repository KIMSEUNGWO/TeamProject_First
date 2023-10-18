package july.lease.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import july.lease.dao.product.ProductDao;
import july.lease.dao.productImage.ProductImageDao;
import july.lease.domain.Product;
import july.lease.dto.HomeResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

	private final ProductDao productDao;
	private final ProductImageDao productImageDao;
	
	public List<HomeResponseDto> findAllForHome(){
		if(productDao.findAllForHome() != null) {
			List<Product> products = productDao.findAllForHome();
			insertImageFilesInProducts(products);
			
			return mapToHomeResponseDto(products);
			
		} else {
			throw new IllegalArgumentException("상품을 찾을 수 없음");
		}
	}
	
	private void insertImageFilesInProducts(List<Product> products) {
        products.stream()
                .forEach(product -> {
                    product.setImages(
                            productImageDao.findAllByProductId(product.getProductId())
                    );
                });
        
    }
	
	private static List<HomeResponseDto> mapToHomeResponseDto(List<Product> products) {
        return products.stream()
                .map(product -> 
                		new HomeResponseDto(
                				product.getProductName(), product.getProductPrice(), product.getImages())
                ).collect(Collectors.toList());
    }
}
