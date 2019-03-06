package tests.cn.com.taiji.sample.manager.source;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.taiji.sample.entity.source.SourceProcess;
import cn.com.taiji.sample.repo.jpa.source.SourceProcessRepo;
import tests.MyNotTransationalTest;

public class SourceProcessTest extends MyNotTransationalTest{

	@Autowired
	private SourceProcessRepo repo;
	
	@Test
	public void insertt(){
		int year=2011;
		for(int i=0;i<5;i++){
			SourceProcess model=new SourceProcess();
			model.setDataSource("HNFRT");
			model.setNonMediQuality(44.0+i);
			model.setPostProcessQuality("44");
			repo.save(model);
		}
	}
}
