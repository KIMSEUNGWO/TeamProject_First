package july.lease.dao.productImage;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import july.lease.domain.ProductImage;

@Mapper
public interface ProductImageMapper {
	
	void save(ProductImage productImage);
	
	List<ProductImage> findAllByProductId(Long productId);

	void deleteProductImageByProductId(Long productId);
}
