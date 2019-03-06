package tests.cn.com.taiji.sample.manager.source;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.taiji.sample.entity.source.SourceMedicineProduce;
import cn.com.taiji.sample.repo.jpa.source.SourceMedicineProduceRepo;
import tests.MyNotTransationalTest;

public class SourceMedicineProduceTest extends MyNotTransationalTest{

	@Autowired
	private SourceMedicineProduceRepo repo;
	
	@Test
	public void insertt(){
		int year=2011;
		for(int i=0;i<5;i++){
			SourceMedicineProduce model=new SourceMedicineProduce();
			model.setDataSource("HNFRT");
			model.setLeftNum(50.0+i);
			repo.save(model);
		}
	}
}
