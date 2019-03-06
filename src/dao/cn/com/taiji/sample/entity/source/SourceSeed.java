package cn.com.taiji.sample.entity.source;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import cn.com.taiji.sample.entity.dict.source.BuySell;
import cn.com.taiji.sample.entity.dict.source.ReproduceMaterial;
import cn.com.taiji.sample.entity.dict.source.ReproduceMethod;
import cn.com.taiji.sample.entity.dict.source.ReproducePlace;
/**
 * 
 * @author admin02
 * 2018年9月2日 下午3:29:53
 * 种源管理
 */
@Entity
@Table(name = "source_seed")
public class SourceSeed extends AbstractInsertTimeEntity
{
	private String seedNo;//种子批号
	private String medicineId;//药材名称--对应表
	private String latinName;//拉丁名
	private ReproduceMaterial reproduceMaterial;//繁殖材料--种子、种苗、其他
	private ReproduceMethod reproduceMethod;//繁殖方式
	private ReproducePlace reproducePlace;//繁殖地点
	private BuySell buySell;//购销方式
	@Column(name="medicine_Id",nullable = false, length = 100)
	public String getMedicineId() {
		return medicineId;
	}
	public void setMedicineId(String medicineId) {
		this.medicineId = medicineId;
	}
	@Column(name="latin_Name",nullable = false, length = 100)
	public String getLatinName() {
		return latinName;
	}
	public void setLatinName(String latinName) {
		this.latinName = latinName;
	}
	@Enumerated(EnumType.STRING)
	@Column(name="reproduce_Material",nullable = false, length = 100)
	public ReproduceMaterial getReproduceMaterial() {
		return reproduceMaterial;
	}
	public void setReproduceMaterial(ReproduceMaterial reproduceMaterial) {
		this.reproduceMaterial = reproduceMaterial;
	}
	@Enumerated(EnumType.STRING)
	@Column(name="reproduce_Method",nullable = false, length = 100)
	public ReproduceMethod getReproduceMethod() {
		return reproduceMethod;
	}
	public void setReproduceMethod(ReproduceMethod reproduceMethod) {
		this.reproduceMethod = reproduceMethod;
	}
	@Enumerated(EnumType.STRING)
	@Column(name="reproduce_Place",nullable = false, length = 100)
	public ReproducePlace getReproducePlace() {
		return reproducePlace;
	}
	public void setReproducePlace(ReproducePlace reproducePlace) {
		this.reproducePlace = reproducePlace;
	}
	@Enumerated(EnumType.STRING)
	@Column(name="buy_Sell",nullable = false, length = 100)
	public BuySell getBuySell() {
		return buySell;
	}
	public void setBuySell(BuySell buySell) {
		this.buySell = buySell;
	}
	@Column(name="seed_No",nullable = false, length = 100)
	public String getSeedNo() {
		return seedNo;
	}
	public void setSeedNo(String seedNo) {
		this.seedNo = seedNo;
	}
	
	
	
}
