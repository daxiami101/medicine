package tests.cn.com.taiji.sample.manager.source;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.taiji.sample.entity.dict.source.FarmerType;
import cn.com.taiji.sample.entity.dict.source.SoilTexture;
import cn.com.taiji.sample.entity.source.SourceEnvironment;
import cn.com.taiji.sample.entity.source.SourceFarmer;
import cn.com.taiji.sample.repo.jpa.source.SourceEnvironmentRepo;
import tests.MyNotTransationalTest;

public class SourceEnvironmentTest extends MyNotTransationalTest{

	@Autowired
	private SourceEnvironmentRepo repo;
	
	@Test
	public void find(){
		SourceEnvironment sourceEnvironment = repo.findById("1").get();
		System.out.println("=========="+sourceEnvironment);
	}
	@Test
	public void insertt(){
		int year=2011;
		for(int i=0;i<5;i++){
			SourceEnvironment model=new SourceEnvironment();
			model.setAccumulatedTemperature(5.0*i);
			model.setAnnualPrecipitation(2.0*i);
			model.setAverageTemperature(3.0*i);
			model.setFrostlessDay(5.0);
			model.setMonthMaxAvgTemperature(2.0*i);
			model.setMonthMinAvgTemperature(0.1*i);
			model.setPh(7.0);
			model.setSoilTexture(SoilTexture.CLAY);
			model.setSoilType("土壤类型"+i);
			model.setWaterType("水源类型");
			model.setYear(year+i+"");
			model.setYearAbsMaxTemperature(2.0);
			model.setYearAbsMinTemperature(3.0);
			model.setYearSunHour(555.8);
			repo.save(model);
		}
	}
}
