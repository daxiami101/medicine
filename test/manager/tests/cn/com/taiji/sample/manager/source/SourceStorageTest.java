package tests.cn.com.taiji.sample.manager.source;

import java.time.LocalDateTime;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.taiji.sample.entity.source.SourceStore;
import cn.com.taiji.sample.repo.jpa.source.SourceStoreRepo;
import tests.MyNotTransationalTest;

public class SourceStorageTest extends MyNotTransationalTest{

	@Autowired
	private SourceStoreRepo repo;
	
	@Test
	public void insertt(){
		int year=2011;
		for(int i=0;i<5;i++){
			SourceStore model=new SourceStore();
			model.setDataSource("HNFRT");
			model.setHarvestId("麦冬"+i);
			model.setStoreMethod("方法"+i);
			model.setStoreCondition("低温"+i);
			repo.save(model);
		}
	}
}
