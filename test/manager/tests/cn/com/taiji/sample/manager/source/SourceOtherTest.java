package tests.cn.com.taiji.sample.manager.source;

import java.time.LocalDateTime;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.taiji.sample.entity.source.land.SourceOther;
import cn.com.taiji.sample.repo.jpa.source.SourceOtherRepo;
import tests.MyNotTransationalTest;

public class SourceOtherTest extends MyNotTransationalTest{

	@Autowired
	private SourceOtherRepo repo;
	
	@Test
	public void insertt(){
		int year=2011;
		for(int i=0;i<5;i++){
			SourceOther model=new SourceOther();
			model.setDataSource("HNFRT");
			model.setOperation("操作"+i);
//			model.setOperationEndTime(LocalDateTime.now());
//			model.setOperationStartTime(LocalDateTime.now());
			repo.save(model);
		}
	}
}
