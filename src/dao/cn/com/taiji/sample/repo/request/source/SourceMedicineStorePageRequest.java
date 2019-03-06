/*
 * Date: 2015年11月7日
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.sample.repo.request.source;

import java.time.LocalDateTime;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaPageableDataRequest;
import cn.com.taiji.sample.entity.source.SourceMedicineStore;

/**
 * 
 * @author admin02
 * 2018年9月2日 下午5:53:46
 * TODO
 */
public class SourceMedicineStorePageRequest extends JpaPageableDataRequest<SourceMedicineStore>
{
	private String materialName;//物料名称
	private String originalPlace;//产地
	private String materialId;//物料批号
	private String storeMethod;//贮藏方式
	private LocalDateTime storeTime ;
	
	public SourceMedicineStorePageRequest()
	{
//		this.orderBy = "namePy";
	}
	@Override
	public HqlBuilder toSelectHql()
	{
		HqlBuilder hql = new HqlBuilder("from " + clazz.getName() + " a where 1=1 ");
		hql.append(" and materialName like:materialName", like(materialName));
		hql.append(" and originalPlace=:originalPlace", originalPlace);
		hql.append(" and materialId=:materialId", materialId);
		hql.append(" and storeMethod=:storeMethod", storeMethod);
		return hql;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public String getOriginalPlace() {
		return originalPlace;
	}
	public void setOriginalPlace(String originalPlace) {
		this.originalPlace = originalPlace;
	}
	public String getMaterialId() {
		return materialId;
	}
	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}
	public String getStoreMethod() {
		return storeMethod;
	}
	public void setStoreMethod(String storeMethod) {
		this.storeMethod = storeMethod;
	}
	public LocalDateTime getStoreTime() {
		return storeTime;
	}
	public void setStoreTime(LocalDateTime storeTime) {
		this.storeTime = storeTime;
	}
	
}
