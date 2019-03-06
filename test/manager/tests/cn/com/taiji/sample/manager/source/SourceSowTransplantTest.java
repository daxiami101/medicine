package tests.cn.com.taiji.sample.manager.source;

import java.time.LocalDateTime;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.taiji.sample.entity.dict.source.SoilTexture;
import cn.com.taiji.sample.entity.source.land.SourceSowTransplant;
import cn.com.taiji.sample.repo.jpa.source.SourceSowTransplantRepo;
import tests.MyNotTransationalTest;

public class SourceSowTransplantTest extends MyNotTransationalTest{

	@Autowired
	private SourceSowTransplantRepo repo;
	
	@Test
	public void insertt(){
		int year=2011;
		for(int i=0;i<5;i++){
			SourceSowTransplant model=new SourceSowTransplant();
			model.setDataSource("HNFRT");
//			model.setMethod("播种");
			model.setPlantNum(22.0+1);
//			model.setPlantTime(LocalDateTime.now());
			model.setSeedId("asd"+i);
			model.setSeedNum(456.0+50+i);
			model.setTaskId("2a7a17552ca2487593d2e748eef35b07");
			repo.save(model);
		}
	}
}
