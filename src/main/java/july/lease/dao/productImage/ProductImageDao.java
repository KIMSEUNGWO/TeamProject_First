package july.lease.dao.productImage;

import java.util.List;

import org.springframework.stereotype.Repository;

import july.lease.domain.ProductImage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
@RequiredArgsConstructor
public class ProductImageDao {
	
	private final ProductImageMapper productImageMapper;
	
	public List<ProductImage> save(List<ProductImage> productImages) {
		for(ProductImage image : productImages) {
			productImageMapper.save(image);
			//log.info("productImage={}", image);
		}
		return productImages;
	}
	
	public List<ProductImage> findAllByProductId(Long productId){
		return productImageMapper.findAllByProductId(productId);
	}
	
	public void deleteProductImageByProductId(Long productId){
		productImageMapper.deleteProductImageByProductId(productId);
	}
}
