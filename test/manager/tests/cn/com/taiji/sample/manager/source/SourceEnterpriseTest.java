package tests.cn.com.taiji.sample.manager.source;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.taiji.sample.entity.source.SourceEnterprise;
import cn.com.taiji.sample.repo.jpa.source.SourceEnterpriseRepo;
import tests.MyNotTransationalTest;

public class SourceEnterpriseTest extends MyNotTransationalTest{

	@Autowired
	private SourceEnterpriseRepo repo;
	
	@Test
	public void insertt(){
		int year=2011;
		for(int i=0;i<5;i++){
			SourceEnterprise model=new SourceEnterprise();
			model.setDataSource("HNFRT");
			model.setEmployeeNum(2+i);
			model.setName("XXX股份有限公司"+i);
			repo.save(model);
		}
	}
}
