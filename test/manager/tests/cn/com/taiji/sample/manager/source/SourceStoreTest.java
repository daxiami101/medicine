package tests.cn.com.taiji.sample.manager.source;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.taiji.sample.entity.source.SourceStore;
import cn.com.taiji.sample.repo.jpa.source.SourceStoreRepo;
import tests.MyNotTransationalTest;

public class SourceStoreTest extends MyNotTransationalTest{

	@Autowired
	private SourceStoreRepo repo;
	
	@Test
	public void insertt(){
		int year=2011;
		for(int i=0;i<5;i++){
			SourceStore model=new SourceStore();
			model.setDataSource("HNFRT");
			model.setHarvestId("id"+i);
			model.setMeasure("m"+i);
			model.setStoreMethod("method"+i);
			repo.save(model);
		}
	}
}
