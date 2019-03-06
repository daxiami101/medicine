/*
 * Date: 2015年11月7日
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.sample.repo.request.source;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaPageableDataRequest;
import cn.com.taiji.sample.entity.dict.source.SoilTexture;
import cn.com.taiji.sample.entity.source.SourceEnvironment;

/**
 * 
 * @author admin02
 * 2018年9月2日 下午5:53:46
 * TODO
 */
public class SourceEnvironmentPageRequest extends JpaPageableDataRequest<SourceEnvironment>
{
//	@Size(min = 3, max = 16, message = "{userName.error}")
	private String provinceId;//省
	private String countyId;//县
	private String year;//年份
	//土壤
	private String soilType;//土壤类型
	private SoilTexture soilTexture; ;//土壤质地
	private Double ph;// pH值
	private String soilReport;//土壤检测报告
	//水源
	private String waterType;//水源
	private String waterReport;//水质检测报告
	//气候
	private Double annualPrecipitation;//年降水量(mm)
	private Double frostlessDay;//无霜期(天)
	private Double  averageTemperature 	;		//	年平均气温(℃)
	private Double accumulatedTemperature; 		//	≧10℃积温(℃)
	private Double monthMinAvgTemperature;			//	最冷月平均气温(℃)
	private Double monthMaxAvgTemperature;		//	最热月平均气温(℃)
	private Double yearAbsMinTemperature	;	//	年绝对最低气温(℃)
	private Double yearAbsMaxTemperature	;	//	年绝对最高气温(℃)
	private Double yearSunHour;		//	年日照时数(小时)
	
	public SourceEnvironmentPageRequest()
	{
		this.orderBy = "insertTime";
		this.desc = true;
	}
	@Override
	public HqlBuilder toSelectHql()
	{
		HqlBuilder hql = new HqlBuilder("from " + clazz.getName() + " a where 1=1 ");
		hql.append(" and year=:year", year);
		hql.append(" and provinceId=:provinceId", provinceId);
		hql.append(" and countyId=:countyId", countyId);
		hql.append(" and soilType=:soilType", soilType);
		hql.append(" and soilTexture=:soilTexture", soilTexture);
		hql.append(" and waterType=:waterType", waterType);
//		hql.append(" and exists(select b from Unit b where b.id=a.unit.id and b.code like :code)",rightLike(unitLikeCode));
		return hql;
	}
	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	public String getCountyId() {
		return countyId;
	}
	public void setCountyId(String countyId) {
		this.countyId = countyId;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getSoilType() {
		return soilType;
	}
	public void setSoilType(String soilType) {
		this.soilType = soilType;
	}
	public SoilTexture getSoilTexture() {
		return soilTexture;
	}
	public void setSoilTexture(SoilTexture soilTexture) {
		this.soilTexture = soilTexture;
	}
	public Double getPh() {
		return ph;
	}
	public void setPh(Double ph) {
		this.ph = ph;
	}
	public String getSoilReport() {
		return soilReport;
	}
	public void setSoilReport(String soilReport) {
		this.soilReport = soilReport;
	}
	public String getWaterType() {
		return waterType;
	}
	public void setWaterType(String waterType) {
		this.waterType = waterType;
	}
	public String getWaterReport() {
		return waterReport;
	}
	public void setWaterReport(String waterReport) {
		this.waterReport = waterReport;
	}
	public Double getAnnualPrecipitation() {
		return annualPrecipitation;
	}
	public void setAnnualPrecipitation(Double annualPrecipitation) {
		this.annualPrecipitation = annualPrecipitation;
	}
	public Double getFrostlessDay() {
		return frostlessDay;
	}
	public void setFrostlessDay(Double frostlessDay) {
		this.frostlessDay = frostlessDay;
	}
	public Double getAverageTemperature() {
		return averageTemperature;
	}
	public void setAverageTemperature(Double averageTemperature) {
		this.averageTemperature = averageTemperature;
	}
	public Double getAccumulatedTemperature() {
		return accumulatedTemperature;
	}
	public void setAccumulatedTemperature(Double accumulatedTemperature) {
		this.accumulatedTemperature = accumulatedTemperature;
	}
	public Double getMonthMinAvgTemperature() {
		return monthMinAvgTemperature;
	}
	public void setMonthMinAvgTemperature(Double monthMinAvgTemperature) {
		this.monthMinAvgTemperature = monthMinAvgTemperature;
	}
	public Double getMonthMaxAvgTemperature() {
		return monthMaxAvgTemperature;
	}
	public void setMonthMaxAvgTemperature(Double monthMaxAvgTemperature) {
		this.monthMaxAvgTemperature = monthMaxAvgTemperature;
	}
	public Double getYearAbsMinTemperature() {
		return yearAbsMinTemperature;
	}
	public void setYearAbsMinTemperature(Double yearAbsMinTemperature) {
		this.yearAbsMinTemperature = yearAbsMinTemperature;
	}
	public Double getYearAbsMaxTemperature() {
		return yearAbsMaxTemperature;
	}
	public void setYearAbsMaxTemperature(Double yearAbsMaxTemperature) {
		this.yearAbsMaxTemperature = yearAbsMaxTemperature;
	}
	public Double getYearSunHour() {
		return yearSunHour;
	}
	public void setYearSunHour(Double yearSunHour) {
		this.yearSunHour = yearSunHour;
	}
}
