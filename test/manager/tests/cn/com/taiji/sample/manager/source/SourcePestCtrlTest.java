package tests.cn.com.taiji.sample.manager.source;

import java.time.LocalDateTime;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.taiji.sample.entity.source.land.SourcePestCtrl;
import cn.com.taiji.sample.repo.jpa.source.SourcePestCtrlRepo;
import tests.MyNotTransationalTest;

public class SourcePestCtrlTest extends MyNotTransationalTest{

	@Autowired
	private SourcePestCtrlRepo repo;
	
	@Test
	public void insertt(){
		int year=2011;
		for(int i=0;i<5;i++){
			SourcePestCtrl model=new SourcePestCtrl();
			model.setDataSource("HNFRT");
			model.setEnrollNo("enroll"+i);
			model.setProduceDate(LocalDateTime.now());
			model.setCompany("辅仁"+i);
			model.setPreventEndTime(LocalDateTime.now());
			repo.save(model);
		}
	}
}
