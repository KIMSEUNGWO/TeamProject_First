package july.lease.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import july.lease.dao.member.MemberDao;
import july.lease.dao.mypage.MyPageMapper;
import july.lease.dao.productImage.ProductImageMapper;
import july.lease.domain.Member;
import july.lease.domain.Product;
import july.lease.dto.MyPageMainInfoDto;
import july.lease.dto.MyPageOrderItemsDto;
import july.lease.dto.MyPageRentItemsDto;
import july.lease.dto.MyPageSellItemsDto;
import july.lease.dto.MyPageSellitemsModalDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MyPageServiceImpl implements MyPageService {
	
	private final MyPageMapper myPageMapper;
	private final ProductImageMapper imageMapper;
	private final BCryptPasswordEncoder encoder;
	private final MemberDao memberDao;
	
	@Override
	public String findByName(Long memberId) {
		return myPageMapper.findByName(memberId);
	}
	
	@Override
	public List<Integer> compressInfo(Long memberId) {
		
		List<Integer> list = new ArrayList<>();
		
		list.add(myPageMapper.findByOrderCount(memberId));
		list.add(myPageMapper.findBySellCount(memberId));
		list.add(myPageMapper.findByMyitemsCount(memberId));
		list.add(myPageMapper.findByWaitItems(memberId));
		
		return list;
	}

	@Override
	public List<MyPageMainInfoDto> confirmCheck(Long memberId) {
		return myPageMapper.confirmCheck(memberId);
	}

	@Override
	public List<Object> sellItemsInfo(Long memberId) {
		List<Object> list = new ArrayList<>();
		list.add(myPageMapper.findBySellingItem(memberId));
		list.add(myPageMapper.todayReturnItem(memberId));
		String totalBenefit = myPageMapper.totalBenefit(memberId);
		System.out.println(totalBenefit);
		list.add(totalBenefit == null ? "0" : totalBenefit);
		
		return list;
	}

	@Override
	public List<MyPageSellItemsDto> sellItemsList(Long memberId) {
		return myPageMapper.itemSellList(memberId);
	}

	@Override
	public List<Product> endItems(Long memberId) {
		List<Product> list = myPageMapper.endItems(memberId);
		list.stream().forEach(product -> product.setImages(imageMapper.findAllByProductId(product.getProductId())));
		return list;
	}

	@Override
	public List<MyPageMainInfoDto> confirmCheckAJAX(Long memberId, Long startRow, Long endRow) {
		return myPageMapper.confirmCheckAJAX(memberId, startRow, endRow);
	}

	@Override
	public List<MyPageSellItemsDto> sellItemsListAJAX(Long memberId, Long startRow, Long endRow) {
		return myPageMapper.itemSellListAJAX(memberId, startRow, endRow);
	}

	@Override
	public List<MyPageOrderItemsDto> orderItems(Long memberId) {
		return myPageMapper.orderItems(memberId);
	}

	@Override
	public List<MyPageOrderItemsDto> orderItemsAJAX(Long memberId, Long startRow, Long endRow) {
		return myPageMapper.orderItemsAJAX(memberId, startRow, endRow);
	}

	@Override
	public Member findByPw(Long memberId, String password) {		
		Member member = memberDao.selectOne(memberId);
		if (encoder.matches(password, member.getMemberPassword())) {
			member.setMemberPhone(new StringBuffer(member.getMemberPhone()).insert(7, "-").insert(3, "-").toString()); // 핸드폰 '-' 단위추가
			return member;
		}
		return  null;
	}

	@Override
	public boolean validConfirm(Long memberId, Long productId, Long orderId) {
		if (myPageMapper.validConfirm1(memberId, productId) == 0) return false;
		if (myPageMapper.validConfirm2(orderId, productId) == 0) return false;
		return true;
	}

	@Override
	@Transactional
	public boolean confirmUpdate(Long orderId, Long productId, Long confirmId) {
		return myPageMapper.confirmUpdate(orderId, productId, confirmId) == 1 ? true : false;
	}

	@Override
	public MyPageSellitemsModalDto sellItemsModal(Long memberId, Long productId) {
		
		return myPageMapper.sellItemsModal(memberId, productId);
	}

	@Override
	public boolean comparePw(Long memberId, String pw, String pwCheck) {
		Member member = memberDao.selectOne(memberId);
		
		if (!pw.equals(pwCheck)) return false;
		if (pw.length() < 8 || pw.length() > 15) return false;
		String reg = "[\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)\\_\\+\\=\\-\\`\\~]";
		String reg2 = "[a-zA-Z0-9]";
		Matcher m = Pattern.compile(reg).matcher(pw);
		Matcher m2 = Pattern.compile(reg2).matcher(pw);
		if (!m.find()) return false;
		if (!m2.find()) return false;
		
		if (encoder.matches(pw, member.getMemberPassword())) return false;
		
		return true;
	}

	@Override
	public Member updatePw(Long memberId, String pw) {
		int result = myPageMapper.updatePw(memberId, encoder.encode(pw));
		if (result == 0) {
			return null;
		}
		return memberDao.selectOne(memberId);
	}

	@Override
	public boolean productRent(Long productId) {
		int result = myPageMapper.productRent(productId);
		return result == 0 ? false : true;
	}

	@Override
	public boolean productReturn(Long productId) {
		int result = myPageMapper.productReturn(productId);
		return result == 0 ? false : true;
	}

	@Override
	public List<MyPageRentItemsDto> rentList(Long memberId) {
		return myPageMapper.rentList(memberId);
	}



}
