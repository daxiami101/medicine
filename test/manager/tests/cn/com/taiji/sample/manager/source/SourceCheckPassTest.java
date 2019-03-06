package tests.cn.com.taiji.sample.manager.source;

import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.taiji.sample.entity.source.SourceCheckPass;
import cn.com.taiji.sample.entity.source.SourcePlantTask;
import cn.com.taiji.sample.manager.medicine.land.SourcePlantTaskManager;
import cn.com.taiji.sample.repo.jpa.source.SourcePlantTaskRepo;
import tests.MyNotTransationalTest;

public class SourceCheckPassTest extends MyNotTransationalTest{

	@Autowired
	private SourcePlantTaskRepo repo;
	@Autowired
	private SourcePlantTaskManager manager;
	
	@Test
	public void find(){
		Optional<SourcePlantTask> findById2 = repo.findById("6951e9d8762b40c69369a3b665424b2b");
		System.out.println("findById2:"+findById2);
		SourcePlantTask findById = manager.findById("6951e9d8762b40c69369a3b665424b2b");
		System.out.println("findById:"+findById);
	}
//	@Test
//	public void insertt(){
//		int year=2011;
//		for(int i=0;i<5;i++){
//			SourceCheckPass model=new SourceCheckPass();
//			model.setDataSource("HNFRT");
//			model.setMaterialId("id"+i);
//			model.setMedicineName("name"+i);
//			repo.save(model);
//		}
//	}
}
