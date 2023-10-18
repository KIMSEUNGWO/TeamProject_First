package july.lease.dao.product;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import july.lease.domain.Product;
import july.lease.dto.EditProductRequestDto;
import july.lease.dto.ProductDetailResponseDto;
import july.lease.dto.ProductListDto;
import july.lease.dto.RentAbleDateDto;

@Mapper
public interface ProductMapper {
	
	void save(Product product);
	
	Product findByProductId(Long productId);
	
	List<Product> findAllForHome();
	
	Product findByProductIdForEdit(Long productId);
	
	void editProduct(@Param("productId")Long productId, @Param("product") EditProductRequestDto productRequestDto);

	List<ProductListDto> findByMemberIdExceptProductWithProductId(
			@Param("memberId")Long memberId, @Param("productId")Long productId);
	
	Product findByProductIdForProductDetail(Long productId);
	
	void delete(Long productId);
	
	void reRent(Long productId);
	
	int addVisitCount(@Param("productId") Long productId, @Param("responseDto") ProductDetailResponseDto responseDto);
	
	String findNicknameByProductId(Long productId);
	
}