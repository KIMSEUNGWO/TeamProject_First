package july.lease.dao.header;

import java.util.List;

import org.springframework.stereotype.Repository;

import july.lease.domain.Category;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
@RequiredArgsConstructor
public class HeaderDao {

	private final HeaderMapper headerMapper;
	
	public List<Category> getCategory() {
		return headerMapper.getCategory();
	}
	
	public int notReadMessageCnt(Long memberId) {
		return headerMapper.notReadMessageCnt(memberId);
	}
}
