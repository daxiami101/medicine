package tests.cn.com.taiji.sample.manager.source;

import java.util.Calendar;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.taiji.sample.entity.source.SourceWaitPackage;
import cn.com.taiji.sample.repo.jpa.source.SourceWaitPackageRepo;
import tests.MyNotTransationalTest;

public class SourceWaitPackageTest extends MyNotTransationalTest{

	@Autowired
	private SourceWaitPackageRepo repo;
	
	@Test
	public void insertt(){
		int year=2011;
		for(int i=0;i<5;i++){
			SourceWaitPackage model=new SourceWaitPackage();
			model.setDataSource("HNFRT");
			model.setComcode("123456");
			model.setPackageTime(Calendar.getInstance());
			model.setStandard("国家标准"+i);
			repo.save(model);
		}
	}
}
