package tests.cn.com.taiji.sample.manager.source;

import java.time.LocalDateTime;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.taiji.sample.entity.source.land.SourceGrowthRegulator;
import cn.com.taiji.sample.repo.jpa.source.SourceGrowthRegulatorRepo;
import tests.MyNotTransationalTest;

public class SourceGrowthRegulatorTest extends MyNotTransationalTest{

	@Autowired
	private SourceGrowthRegulatorRepo repo;
	
	@Test
	public void insertt(){
		int year=2011;
		for(int i=0;i<5;i++){
			SourceGrowthRegulator model=new SourceGrowthRegulator();
			model.setDataSource("HNFRT");
			model.setNum(55.0);
//			model.setProduceDate(LocalDateTime.now());
//			model.setPurchaseDate(LocalDateTime.now());
			model.setEnrollNo("123"+i);
			repo.save(model);
		}
	}
}
