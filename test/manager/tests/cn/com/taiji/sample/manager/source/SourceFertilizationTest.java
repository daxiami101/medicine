package tests.cn.com.taiji.sample.manager.source;

import java.time.LocalDateTime;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.taiji.sample.entity.dict.source.SoilTexture;
import cn.com.taiji.sample.entity.source.land.SourceFertilization;
import cn.com.taiji.sample.repo.jpa.source.SourceFertilizationRepo;
import tests.MyNotTransationalTest;

public class SourceFertilizationTest extends MyNotTransationalTest{

	@Autowired
	private SourceFertilizationRepo repo;
	
	@Test
	public void insertt(){
		int year=2011;
		for(int i=0;i<5;i++){
			SourceFertilization model=new SourceFertilization();
			model.setDataSource("HNFRT");
			model.setEnrollNo("enroll"+i);
//			model.setFertEndTime(LocalDateTime.now());
//			model.setFertStartTime(LocalDateTime.now());
//			model.setProduceCom("河南辅仁"+i);
//			model.setProduceDate(LocalDateTime.now());
			
			repo.save(model);
		}
	}
}
