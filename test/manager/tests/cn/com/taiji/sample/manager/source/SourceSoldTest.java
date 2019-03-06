package tests.cn.com.taiji.sample.manager.source;

import java.time.LocalDateTime;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.taiji.sample.entity.source.SourceSold;
import cn.com.taiji.sample.repo.jpa.source.SourceSoldRepo;
import tests.MyNotTransationalTest;

public class SourceSoldTest extends MyNotTransationalTest{

	@Autowired
	private SourceSoldRepo repo;
	
	@Test
	public void insertt(){
		int year=2011;
		for(int i=0;i<5;i++){
			SourceSold model=new SourceSold();
			model.setDataSource("HNFRT");
			model.setBarcode("123456");
			model.setClientName("client"+i);
			model.setCompanyOrderId("orderNo"+i);
			model.setCustomCode("customCode"+i);
//			model.setSoldTime(LocalDateTime.now());
			repo.save(model);
		}
	}
}
