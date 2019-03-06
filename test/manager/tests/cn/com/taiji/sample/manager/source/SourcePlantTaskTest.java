package tests.cn.com.taiji.sample.manager.source;

import java.time.LocalDateTime;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.taiji.sample.entity.dict.source.PlantMethod;
import cn.com.taiji.sample.entity.dict.source.SoilTexture;
import cn.com.taiji.sample.entity.source.SourcePlantTask;
import cn.com.taiji.sample.repo.jpa.source.SourcePlantTaskRepo;
import tests.MyNotTransationalTest;

public class SourcePlantTaskTest extends MyNotTransationalTest{

	@Autowired
	private SourcePlantTaskRepo repo;
	
	@Test
	public void find(){
//		SourceEnvironment sourceEnvironment = repo.findById("1").get();
//		System.out.println("=========="+sourceEnvironment);
	}
	@Test
	public void insertt(){
		int year=2011;
		for(int i=0;i<5;i++){
			SourcePlantTask model=new SourcePlantTask();
			model.setArea(20.0);
//			model.setEndPlantTime(LocalDateTime.now());
//			model.setStartPlantTime(LocalDateTime.now());
//			model.setFarmerId("");
			model.setOriginalPalce("北京");
			model.setPlantMethod(PlantMethod.INTERPLATE);
			model.setTaskNo("task"+i);
			repo.save(model);
		}
	}
}
