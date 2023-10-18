package july.lease.dao.order;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import july.lease.domain.Orders;

@Mapper
public interface OrdersMapper {
	
	int checkOrdersIfValid(Long productId);
	
	void save(Orders order);
	
	List<String> findOrderRentDateByProductId(Long productId);
	
	Orders findByOrderId(Long orderId);
	
	int findConfirmStatusCountByProductId(Long productId);
}
