package tests.cn.com.taiji.sample.manager.source;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.taiji.sample.entity.source.SourceHarvest;
import cn.com.taiji.sample.manager.medicine.SourceHarvestManager;
import cn.com.taiji.sample.repo.jpa.source.SourceHarvestRepo;
import tests.MyNotTransationalTest;

public class SourceHarvestTest extends MyNotTransationalTest{

	@Autowired
	private SourceHarvestRepo repo;
	@Autowired
	private SourceHarvestManager manager;
	@Test
	public void search(){
		String id="f1f9cac16c0d47678fac95b2d0d63d60";
		SourceHarvest model = manager.findById(id);
		System.out.println("model===============:"+model);
		
		
	}
	@Test
	public void insertt(){
		int year=2011;
		for(int i=0;i<5;i++){
			SourceHarvest model=new SourceHarvest();
			model.setDataSource("HNFRT");
			model.setArea(550.0);
//			model.setHarvestId("麦冬"+i);
//			model.setHarvestTime(LocalDateTime.now());
			model.setHarvestPart("部位"+i);
			repo.save(model);
		}
	}
}
