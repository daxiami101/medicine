package tests.cn.com.taiji.sample.manager.source;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.taiji.sample.entity.source.SourceSoldOut;
import cn.com.taiji.sample.repo.jpa.source.SourceSoldOutRepo;
import tests.MyNotTransationalTest;

public class SourceSoldOutTest extends MyNotTransationalTest{

	@Autowired
	private SourceSoldOutRepo repo;
	
	@Test
	public void insertt(){
		int year=2011;
		for(int i=0;i<5;i++){
			SourceSoldOut model=new SourceSoldOut();
			model.setDataSource("HNFRT");
			model.setMaterialName("name"+i);
			repo.save(model);
		}
	}
}
