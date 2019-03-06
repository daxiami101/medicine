package tests.cn.com.taiji.sample.manager.source;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.taiji.sample.entity.source.land.SourceFertilization;
import cn.com.taiji.sample.manager.medicine.land.SourceFertilizationManager;
import cn.com.taiji.sample.repo.jpa.source.SourceIrrigationRepo;
import tests.MyNotTransationalTest;

public class SourceIrrigationTest extends MyNotTransationalTest{

	@Autowired
	private SourceIrrigationRepo repo;
	@Autowired
	private SourceFertilizationManager fertilizationManager;
	
	@Test
	public void insertt(){
		List<SourceFertilization> sourceFertilizations = fertilizationManager.listByTaskId("123");
		System.out.println("sourceFertilizations:"+sourceFertilizations.size());
	}
}
