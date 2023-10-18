package july.lease.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Category {
	
	private Long categoryId;
	private Long categoryId2;
	private String categoryName;
	
	public Category() {
	}

	public Category(String categoryName) {
		this.categoryName = categoryName;
	}
	
	
	
	
}
