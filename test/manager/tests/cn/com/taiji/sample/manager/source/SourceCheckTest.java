package tests.cn.com.taiji.sample.manager.source;

import java.time.LocalDateTime;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.taiji.sample.entity.source.SourceCheck;
import cn.com.taiji.sample.repo.jpa.source.SourceCheckRepo;
import tests.MyNotTransationalTest;

public class SourceCheckTest extends MyNotTransationalTest{

	@Autowired
	private SourceCheckRepo repo;
	
	@Test
	public void insertt(){
		int year=2011;
		for(int i=0;i<5;i++){
			SourceCheck model=new SourceCheck();
			model.setDataSource("HNFRT");
			model.setCompanyOrderId("orderNo"+i);
			model.setMaterialName("麦冬"+i);
			model.setNum(5.0+i);
			
			repo.save(model);
		}
	}
}
