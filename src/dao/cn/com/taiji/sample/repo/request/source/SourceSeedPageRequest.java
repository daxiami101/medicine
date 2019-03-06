/*
 * Date: 2015年11月7日
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.sample.repo.request.source;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaPageableDataRequest;
import cn.com.taiji.sample.entity.dict.source.BuySell;
import cn.com.taiji.sample.entity.dict.source.ReproduceMaterial;
import cn.com.taiji.sample.entity.dict.source.ReproduceMethod;
import cn.com.taiji.sample.entity.dict.source.ReproducePlace;
import cn.com.taiji.sample.entity.source.SourceSeed;

/**
 * 
 * @author admin02
 * 2018年9月2日 下午5:53:46
 * TODO
 */
public class SourceSeedPageRequest extends JpaPageableDataRequest<SourceSeed>
{
//	@Size(min = 3, max = 16, message = "{userName.error}")
	private String seedNo;//种子批号
	private String medicineId;//药材名称--对应表
	private String latinName;//拉丁名
	private ReproduceMaterial reproduceMaterial;//繁殖材料--种子、种苗、其他
	private ReproduceMethod reproduceMethod;//繁殖方式
	private ReproducePlace reproducePlace;//繁殖地点
	private BuySell buySell;//购销方式
	public SourceSeedPageRequest()
	{
		this.orderBy = "insertTime";
		this.desc = true;
	}
	@Override
	public HqlBuilder toSelectHql()
	{
		HqlBuilder hql = new HqlBuilder("from " + clazz.getName() + " a where 1=1 ");
		hql.append(" and medicineId like :medicineId", like(medicineId));
		hql.append(" and latinName like :latinName", like(latinName));
		hql.append(" and reproduceMaterial=:reproduceMaterial", reproduceMaterial);
		hql.append(" and reproduceMethod=:reproduceMethod", reproduceMethod);
		hql.append(" and reproducePlace=:reproducePlace",reproducePlace);
		hql.append(" and buySell=:buySell",buySell);
		hql.append(" and seedNo=:seedNo",seedNo);
		return hql;
	}
	
	public String getSeedNo() {
		return seedNo;
	}
	public void setSeedNo(String seedNo) {
		this.seedNo = seedNo;
	}
	public String getMedicineId() {
		return medicineId;
	}
	public void setMedicineId(String medicineId) {
		this.medicineId = medicineId;
	}
	public String getLatinName() {
		return latinName;
	}
	public void setLatinName(String latinName) {
		this.latinName = latinName;
	}
	public ReproduceMaterial getReproduceMaterial() {
		return reproduceMaterial;
	}
	public void setReproduceMaterial(ReproduceMaterial reproduceMaterial) {
		this.reproduceMaterial = reproduceMaterial;
	}
	public ReproduceMethod getReproduceMethod() {
		return reproduceMethod;
	}
	public void setReproduceMethod(ReproduceMethod reproduceMethod) {
		this.reproduceMethod = reproduceMethod;
	}
	public ReproducePlace getReproducePlace() {
		return reproducePlace;
	}
	public void setReproducePlace(ReproducePlace reproducePlace) {
		this.reproducePlace = reproducePlace;
	}
	public BuySell getBuySell() {
		return buySell;
	}
	public void setBuySell(BuySell buySell) {
		this.buySell = buySell;
	}
	
}
