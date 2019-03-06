package tests.cn.com.taiji.sample.manager.source;

import java.time.LocalDateTime;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.taiji.sample.entity.source.SourcePurchase;
import cn.com.taiji.sample.repo.jpa.source.SourcePurchaseRepo;
import tests.MyNotTransationalTest;

public class SourcePurchaseTest extends MyNotTransationalTest{

	@Autowired
	private SourcePurchaseRepo repo;
	
	@Test
	public void insertt(){
		int year=2011;
		for(int i=0;i<5;i++){
			SourcePurchase model=new SourcePurchase();
			model.setDataSource("HNFRT");
			model.setCompanyOrderId("orderNo"+i);
			model.setLevel("level"+i);
			model.setMaterialKind("kind"+i);
			model.setMedicineCode("code"+i);
//			model.setPurchaseTime(LocalDateTime.now());
		
			repo.save(model);
		}
	}
}
