/*
 * Date: 2015年11月7日
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.sample.repo.request.source;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaPageableDataRequest;
import cn.com.taiji.sample.entity.source.SourceCounty;

/**
 * 
 * @author admin02
 * 2018年9月2日 下午5:53:46
 * TODO
 */
public class SourceCountyPageRequest extends JpaPageableDataRequest<SourceCounty>
{
	private String cityId;
	private String countyId;
	private String countyName;
	
	public SourceCountyPageRequest()
	{
//		this.orderBy = "namePy";
	}
	@Override
	public HqlBuilder toSelectHql()
	{
		HqlBuilder hql = new HqlBuilder("from " + clazz.getName() + " a where 1=1 ");
		hql.append(" and cityId like :cityId", like(cityId));
		hql.append(" and countyId=:countyId", countyId);
		hql.append(" and countyName=:countyName",countyName);
		return hql;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getCountyId() {
		return countyId;
	}
	public void setCountyId(String countyId) {
		this.countyId = countyId;
	}
	public String getCountyName() {
		return countyName;
	}
	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}
	
}
