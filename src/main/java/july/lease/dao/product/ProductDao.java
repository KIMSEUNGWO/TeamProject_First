package july.lease.dao.product;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import july.lease.domain.Product;
import july.lease.dto.EditProductRequestDto;
import july.lease.dto.ProductDetailResponseDto;
import july.lease.dto.ProductListDto;
import july.lease.dto.RentAbleDateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
@RequiredArgsConstructor
public class ProductDao {
	
	private final ProductMapper productMapper;
	
	public Product save(Product product) {
		productMapper.save(product);
		Product saveProduct = findByProductId(product.getProductId());
		//log.info("save Product={}", saveProduct);
		return saveProduct;
	}
	
	public Product findByProductId(Long productId) {
		return productMapper.findByProductId(productId);
	}

	public List<Product> findAllForHome(){
		return productMapper.findAllForHome();
	}
	
	public Product findByProductIdForEdit(Long productId) {
		Product product = productMapper.findByProductIdForEdit(productId);
		//log.info("findByProductIdForEdit={}",product);
		return product;
	}
	
	public void editProduct(Long productId, EditProductRequestDto productRequestDto) {
		productMapper.editProduct(productId, productRequestDto);
	}
	
	public List<ProductListDto> findByMemberIdExceptProductWithProductId(Long memberId, Long productId){
		List<ProductListDto> list = productMapper.findByMemberIdExceptProductWithProductId(memberId, productId);
		//log.info("ProductDao findByMemberIdExceptProductWithProductId={}",list);
		return list;
	}
	
	public Product findByProductIdForProductDetail(Long productId) {
		Product product = productMapper.findByProductIdForProductDetail(productId);
		//log.info("ProductDao findByProductIdForProductDetail={}", product);
		return product;
	}
	
	public void delete(Long productId) {
		productMapper.delete(productId);
	}
	
	public void reRent(Long productId) {
		productMapper.reRent(productId);
	}
	
	public int addVisitCount(Long productId, ProductDetailResponseDto responseDto) {
		return productMapper.addVisitCount(productId, responseDto);
	}
	
	public String findNicknameByProductId(Long productId) {
		return productMapper.findNicknameByProductId(productId);
	}
	
	
}