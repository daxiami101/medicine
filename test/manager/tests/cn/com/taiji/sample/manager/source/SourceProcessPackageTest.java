package tests.cn.com.taiji.sample.manager.source;

import java.time.LocalDateTime;
import java.util.Calendar;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.taiji.sample.entity.source.SourceProcessPackage;
import cn.com.taiji.sample.repo.jpa.source.SourceProcessPackageRepo;
import tests.MyNotTransationalTest;

public class SourceProcessPackageTest extends MyNotTransationalTest{

	@Autowired
	private SourceProcessPackageRepo repo;
	
	@Test
	public void insertt(){
		int year=2011;
		for(int i=0;i<5;i++){
			SourceProcessPackage model=new SourceProcessPackage();
			model.setDataSource("HNFRT");
			model.setHarvestDate(Calendar.getInstance());
			model.setLevel("level"+i);
			repo.save(model);
		}
	}
}
