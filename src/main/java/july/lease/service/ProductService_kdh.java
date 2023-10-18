package july.lease.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import july.lease.common.FileStore;
import july.lease.dao.product.ProductDao;
import july.lease.dao.productImage.ProductImageDao;
import july.lease.dao.rentDate.RentDateDao;
import july.lease.domain.Product;
import july.lease.domain.ProductImage;
import july.lease.domain.RentDate;
import july.lease.dto.AddProductDto;
import july.lease.dto.EditProductRequestDto;
import july.lease.dto.EditProductResponseDto;
import july.lease.dto.ProductDetailResponseDto;
import july.lease.dto.ProductListDto;
import july.lease.dto.RentAbleRequestDto;
import july.lease.dto.RentOrderStatusDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService_kdh {
	
	private final ProductDao productDao;
	private final ProductImageDao productImageDao;
	private final RentDateDao rentDateDao;
	private final FileStore fileStore;
	
	@Value("${file.dir}")
    private String fileDir;
	
	@Transactional
	public Long addProduct(Long memberId, AddProductDto productDto) throws IOException {
		
		Product product = new Product(memberId, productDto.getCategoryId(), productDto.getProductName(),
										productDto.getProductPrice(), productDto.getProductContent()
										, productDto.getRegion1() + " " + productDto.getRegion2()); //우선 서울로 넣어놓음
		Product saveProduct = productDao.save(product);
		
		List<ProductImage> images = fileStore.storeFiles(productDto.getImages());
		setProductIdInImages(saveProduct.getProductId(), images);
		productImageDao.save(images);
		
		saveRentDates(saveProduct.getProductId(),productDto.getRentAbleStartDate(), 
				productDto.getRentAbleEndDate());

		return saveProduct.getProductId();
	}
	
	// ProductImage에 productId 저장
	private void setProductIdInImages(Long productId, List<ProductImage> images) {
		images.stream()
		.forEach(image -> {
			image.setProductId(productId);
		});
	}
	
	private void saveRentDates(Long productId, List<String> startDates,
								List<String> endDates) {
		
		List<RentDate> rentDateList = new ArrayList<>();
		int rentDatesSize = startDates.size();
		
		for(int i=0; i<rentDatesSize; i++) {
			RentDate rentDate = new RentDate(productId, startDates.get(i),
									endDates.get(i));
			rentDateList.add(rentDate);
		}
		
		rentDateDao.save(rentDateList);
		
	}
	
	public EditProductResponseDto findByProductIdForEdit(Long productId) {
		Product product = productDao.findByProductIdForEdit(productId);
		List<ProductImage> images = productImageDao.findAllByProductId(productId);
		List<RentDate> rentDates = rentDateDao.findByProductId(productId);
		
		String[] regions = product.getLocation().split(" ");
		
		EditProductResponseDto editProductDto = new EditProductResponseDto(
				product.getMemberId(), product.getProductName(), product.getProductPrice(),
				product.getProductContent(), product.getCategoryId(), product.getCategoryId3(),
				rentDates, images, product.getProductEndStatus(), regions[0], regions[1]
				);
		//log.info("findByProductIdForEdit editProductDto={}",editProductDto);
		return editProductDto;
	}
	
	public List<RentOrderStatusDto> checkOrders(Long productId){
		return rentDateDao.checkOrders(productId);
	}
	
	public int rentOrderStatusSize(Long productId, RentAbleRequestDto rentAbleRequestDto) {
		return rentDateDao.rentOrderStatusSize(productId, rentAbleRequestDto);
	}
	
	@Transactional
	public void editProduct(Long productId, EditProductRequestDto productRequestDto) throws IOException {
		
		List<MultipartFile> images = productRequestDto.getImages();
		//log.info("editProduct images={}",images);
		
		//실제로 파일이 첨부됐는지 확인하는 로직
		boolean allFilesEmpty = true;
		for (MultipartFile file : images) {
		    if (!file.isEmpty()) {
		        allFilesEmpty = false;
		        break;
		    }
		}
		
		//MultipartFile이 1개 이상이면 모든 이미지 삭제
		if(!allFilesEmpty) {
			//DB에 있는 이미지 객체 먼저 찾고
			List<ProductImage> findImagesInDB = productImageDao.findAllByProductId(productId);
			//log.info("ProductService_kdh editProduct 실행확인");
			//DB에 이미지데이터 삭제
			productImageDao.deleteProductImageByProductId(productId);
			//이미지객체를 통해 로컬디렉토리에 이미지파일 삭제
			removeAllImages(findImagesInDB);

			//로컬디렉토리에 이미지 저장
			List<ProductImage> savedImageInLocal = 
										fileStore.storeFiles(productRequestDto.getImages());
			setProductIdInImages(productId, savedImageInLocal);
			productImageDao.save(savedImageInLocal);
		}
		
		//Rent_date 검증 로직
		List<RentDate> dbRentDates = rentDateDao.findByProductId(productId);
		int dbSize = dbRentDates.size();
		int dtoSize = productRequestDto.getRentAbleStartDate().size();
		
		checkSaveAndDeleteIfValidOrNot(productRequestDto,dbRentDates, productId, dbSize, dtoSize);
		
		productDao.editProduct(productId, productRequestDto);
			
	}
	
	
	private void removeAllImages(List<ProductImage> images) {
		images.stream()
				.forEach(image -> {
					if(!new File(fileDir + image.getStoreImageName()).delete()) {
						//log.info("===============================로컬이미지파일삭제실패");
					}
				});
	}
	
	private void checkSaveAndDeleteIfValidOrNot(
			EditProductRequestDto productRequestDto,List<RentDate> dbRentDates,
			Long productId, int dbSize, int dtoSize) {
		List<RentDate> updateList = new ArrayList<>();
		List<RentDate> deleteList = new ArrayList<>();
		
		for(int i=0; i<dbSize; i++) {
			//log.info("Rent_Date db기준 검증로직 시작");
			String dbStartDate = dbRentDates.get(i).getRentAbleStartDate();
			int cnt = 0;
			for(int j=0; j<dtoSize; j++) {
				String dtoStartDate = productRequestDto.getRentAbleStartDate().get(j);
				// 1. db에 존재하고 dto에서도 존재하는거 (무시해야됨)
				if (dbStartDate.equals(dtoStartDate)){
					break;
					}
				else cnt++;
			}
			// 2. db에는 존재했는데 dto에서 삭제해서 db에서도 삭제해야될것
			if (cnt == dtoSize) {
				deleteList.add(dbRentDates.get(i));				
			}
		}
				
		for(int i=0; i<dtoSize; i++) {
			//log.info("Rent_Date dto기준 검증로직 시작");
			String dtoStartDate = productRequestDto.getRentAbleStartDate().get(i);
			int cnt = 0;
			for(int j=0; j<dbSize; j++) {
				String dbStartDate = dbRentDates.get(j).getRentAbleStartDate();
				// 1. db에 존재하고 dto에서도 존재하는거 (무시해야됨)
				if (dtoStartDate.equals(dbStartDate)) {
					break;
				}
				else cnt++;
			}
			// 2. dto에는 존재하지만 db에는 존재하지않아서 db에 추가해야할것
			if (cnt == dbSize) {
				updateList.add(new RentDate(productId,
						productRequestDto.getRentAbleStartDate().get(i),
						productRequestDto.getRentAbleEndDate().get(i)));				
			}
		}
		
		rentDateDao.save(updateList);
		rentDateDao.delete(deleteList);
	}
	
	public List<ProductListDto> findByMemberIdExceptProductWithProductId(Long memberId, Long productId){
		return productDao.findByMemberIdExceptProductWithProductId(memberId, productId);
	}
	
	public ProductDetailResponseDto findByProductIdForProductDetail(Long productId) {
		Product product = productDao.findByProductIdForProductDetail(productId);
		
		List<ProductImage> images = productImageDao.findAllByProductId(productId);
		List<RentDate> rentDates = rentDateDao.findByProductId(productId);
		
		ProductDetailResponseDto responseDto = new ProductDetailResponseDto(
				product.getMemberId(), product.getProductName(), product.getProductPrice(),
				product.getCategoryId(), product.getCategoryId2(), product.getCategoryName(),
				product.getCategoryName2(),
				product.getProductContent(), product.getProductEndStatus(),
				product.getLocation(), product.getProductVisitCount(), 
				product.getProductCreateDate(), images, rentDates);
		
		//log.info("ProductService_kdh findByMemberIdExceptProductWithProductId responseDto={}", responseDto);
		return responseDto;
	}
	
	public void delete(Long productId) {
		productDao.delete(productId);
	}
	
	public void reRent(Long productId) {
		productDao.reRent(productId);
	}
	
	public int addVisitCount(Long productId, ProductDetailResponseDto responseDto) {
		return productDao.addVisitCount(productId, responseDto);
	}
	
	public List<String> getImage(Long productId){
		return productImageDao.findAllByProductId(productId).stream().map(x -> x.getStoreImageName()).collect(Collectors.toList());
	}
	
	public String findNicknameByProductId(Long productId) {
		return productDao.findNicknameByProductId(productId);
	}
	
}
