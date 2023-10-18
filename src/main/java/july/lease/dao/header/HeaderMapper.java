package july.lease.dao.header;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import july.lease.domain.Category;

@Mapper
public interface HeaderMapper {

	// 카테고리 가져오기
	List<Category> getCategory();
	
	// 안읽은 쪽지 카운트
	int notReadMessageCnt(Long memberId);
}
