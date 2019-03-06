package tests.cn.com.taiji.sample.manager.source;

import java.time.LocalDateTime;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.taiji.sample.entity.dict.source.SoilTexture;
import cn.com.taiji.sample.entity.source.land.SourceWeed;
import cn.com.taiji.sample.repo.jpa.source.SourceWeedRepo;
import tests.MyNotTransationalTest;

public class SourceWeedTest extends MyNotTransationalTest{

	@Autowired
	private SourceWeedRepo repo;
	
	@Test
	public void insertt(){
		int year=2011;
		for(int i=0;i<5;i++){
			SourceWeed model=new SourceWeed();
			model.setDataSource("HNFRT");
			model.setNum(55.0);
//			model.setProduceDate(LocalDateTime.now());
//			model.setPurchaseDate(LocalDateTime.now());
//			model.setWeedCom("辅仁"+i);
//			model.setWeedEndTime(LocalDateTime.now());
//			model.setWeedStartTime(LocalDateTime.now());
			repo.save(model);
		}
	}
}
