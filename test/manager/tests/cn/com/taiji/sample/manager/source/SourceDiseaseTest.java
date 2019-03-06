package tests.cn.com.taiji.sample.manager.source;

import java.time.LocalDateTime;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.taiji.sample.entity.source.land.SourceDiseaseCtrl;
import cn.com.taiji.sample.repo.jpa.source.SourceDiseaseCtrlRepo;
import tests.MyNotTransationalTest;

public class SourceDiseaseTest extends MyNotTransationalTest{

	@Autowired
	private SourceDiseaseCtrlRepo repo;
	
	@Test
	public void insertt(){
		int year=2011;
		for(int i=0;i<5;i++){
			SourceDiseaseCtrl model=new SourceDiseaseCtrl();
			model.setDataSource("HNFRT");
			model.setEnrollNo("enroll"+i);
//			model.setProduceDate(LocalDateTime.now());
			model.setCompany("辅仁"+i);
//			model.setPreventEndTime(LocalDateTime.now());
			repo.save(model);
		}
	}
}
