package tests.cn.com.taiji.sample.manager.source;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.taiji.sample.entity.source.SourceMedicineStore;
import cn.com.taiji.sample.repo.jpa.source.SourceMedicineStoreRepo;
import tests.MyNotTransationalTest;

public class SourceMedicineStorTest extends MyNotTransationalTest{

	@Autowired
	private SourceMedicineStoreRepo repo;
	
	@Test
	public void insertt(){
		int year=2011;
		for(int i=0;i<5;i++){
			SourceMedicineStore model=new SourceMedicineStore();
			model.setDataSource("HNFRT");
			model.setMaterialId("id"+i);
			model.setStandard("staedard"+i);
			repo.save(model);
		}
	}
}
