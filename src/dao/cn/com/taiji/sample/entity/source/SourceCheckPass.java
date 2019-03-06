package cn.com.taiji.sample.entity.source;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
/**
 * 
 * @author admin02
 * 2018年9月2日 下午3:29:53
 * 种源管理
 */
@Entity
@Table(name = "source_check_pass")
public class SourceCheckPass extends AbstractInsertTimeEntity
{
	private String medicineName;//药材名称
	private String originalPlace;//产地
	private String materialId;//物料批号
	@Column(name="medicine_Name",nullable = false, length = 100)
	public String getMedicineName() {
		return medicineName;
	}
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
	@Column(name="original_Place",nullable = false, length = 100)
	public String getOriginalPlace() {
		return originalPlace;
	}
	public void setOriginalPlace(String originalPlace) {
		this.originalPlace = originalPlace;
	}
	@Column(name="material_Id",nullable = false, length = 100)
	public String getMaterialId() {
		return materialId;
	}
	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}
	
}
