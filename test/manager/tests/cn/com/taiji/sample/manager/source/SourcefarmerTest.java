package tests.cn.com.taiji.sample.manager.source;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.taiji.sample.entity.dict.source.FarmerType;
import cn.com.taiji.sample.entity.source.SourceFarmer;
import cn.com.taiji.sample.repo.jpa.source.SourceFarmerRepo;
import tests.MyNotTransationalTest;

public class SourcefarmerTest extends MyNotTransationalTest{

	@Autowired
	private SourceFarmerRepo repo;
	
	@Test
	public void insertt(){
		for(int i=0;i<50;i++){
			SourceFarmer model=new SourceFarmer();
			model.setAduitStatus("未审核");
			model.setArea(1.0*i);
			model.setFarmerNo("test"+i);
			model.setFarmerType(FarmerType.COMPANY);
			model.setMedicineName("麦冬"+i);
			model.setContractNum("合同号"+i);
			repo.save(model);
		}
		
	}
}
