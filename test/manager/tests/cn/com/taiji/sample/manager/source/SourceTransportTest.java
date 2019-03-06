package tests.cn.com.taiji.sample.manager.source;

import java.time.LocalDateTime;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.taiji.sample.entity.source.SourceTransport;
import cn.com.taiji.sample.repo.jpa.source.SourceTransportRepo;
import tests.MyNotTransationalTest;

public class SourceTransportTest extends MyNotTransationalTest{

	@Autowired
	private SourceTransportRepo repo;
	
	@Test
	public void insertt(){
		int year=2011;
		for(int i=0;i<5;i++){
			SourceTransport model=new SourceTransport();
			model.setDataSource("HNFRT");
			model.setMedicineName("麦冬"+i);
			model.setProcessId("processId"+i);
			model.setSecurityContractCode("code"+i);
//			model.setTransportTime(LocalDateTime.now());
			repo.save(model);
		}
	}
}
