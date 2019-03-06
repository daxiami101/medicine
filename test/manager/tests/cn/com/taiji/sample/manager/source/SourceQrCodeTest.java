package tests.cn.com.taiji.sample.manager.source;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.objenesis.instantiator.sun.MagicInstantiator;

import cn.com.taiji.sample.entity.source.SourceQrCode;
import cn.com.taiji.sample.repo.jpa.source.SourceQrCodeRepo;
import tests.MyNotTransationalTest;

public class SourceQrCodeTest extends MyNotTransationalTest{

	@Autowired
	private SourceQrCodeRepo repo;
	
	@Test
	public void insertt(){
		String[]medicineNames={"枸杞","白芍","地黄","山药","黄芩","当归","党参","金银花","蒲公英","连翘"};
        
		for(String medicineName:medicineNames){
			SourceQrCode model=new SourceQrCode();
			model.setDataSource("HNFRT");
			model.setMedicineName(medicineName);
			model.setUniqueItem("unique"+medicineName);
			model.setUrl("http://219.150.166.85:8081/sample/app/common/info/"+medicineName);
			System.out.println(model);
			repo.save(model);
		}
	}
	
	public static void main(String[] args) {
String[]medicineNames={"枸杞","白芍","地黄","山药","黄芩","当归","党参","金银花","蒲公英","连翘"};
        
		for(String medicineName:medicineNames){
			SourceQrCode model=new SourceQrCode();
			model.setDataSource("HNFRT");
			model.setMedicineName(medicineName);
			model.setUniqueItem("unique"+medicineName);
			model.setUrl("http://219.150.166.85:8081/sample/app/common/info/"+medicineName);
			System.out.println(model);
		}
	}
}
