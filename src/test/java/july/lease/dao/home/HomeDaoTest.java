//package july.lease.dao.home;
//
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//
//import july.lease.dto.HomeItemDto;
//import lombok.extern.slf4j.Slf4j;
//
//@SpringBootTest
//@Slf4j
//@ActiveProfiles("test")
//public class HomeDaoTest {
//
//    @Autowired
//    private HomeDao homeDao;
//
//    @Test
//    public void getAllItem() {
//        List<HomeItemDto> list = homeDao.getAllItem();
//        System.out.println("list : " + list);
//        assertNotNull(list);
//        
//    }
//
//
//}