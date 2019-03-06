package tests.cn.com.taiji.sample.manager.source;

import java.time.LocalDateTime;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.taiji.sample.entity.source.SourcePrintLog;
import cn.com.taiji.sample.repo.jpa.source.SourcePrintLogRepo;
import tests.MyNotTransationalTest;

public class SourceLogPrintTest extends MyNotTransationalTest{

	@Autowired
	private SourcePrintLogRepo repo;
	
	@Test
	public void insertt(){
		int year=2011;
		for(int i=0;i<50;i++){
			SourcePrintLog model=new SourcePrintLog();
			model.setDataSource("HNFRT");
			model.setPrintTime(LocalDateTime.now());
			model.setProduceId("id"+i);
			repo.save(model);
		}
	}
}
