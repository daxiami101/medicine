package cn.com.taiji.sample.entity.source;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import cn.com.taiji.sample.entity.dict.source.SoilTexture;
/**
 * 
 * @author admin02
 * 2018年9月2日 下午3:29:53
 * 产地环境
 */
@Entity
@Table(name = "source_environment")
public class SourceEnvironment extends AbstractInsertTimeEntity
{
	private String location;//省
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
	private Double climateReport;
	@Column(name="location",nullable = false, length = 100)
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	@Column(name="year",nullable = false, length = 100)
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	@Column(name="soil_Type",nullable = false, length = 100)
	public String getSoilType() {
		return soilType;
	}
	public void setSoilType(String soilType) {
		this.soilType = soilType;
	}
	@Enumerated(EnumType.STRING)
	@Column(name="soil_Texture",nullable = false, length = 100)
	public SoilTexture getSoilTexture() {
		return soilTexture;
	}
	public void setSoilTexture(SoilTexture soilTexture) {
		this.soilTexture = soilTexture;
	}
	@Column(name="ph",nullable = false, length = 100)
	public Double getPh() {
		return ph;
	}
	public void setPh(Double ph) {
		this.ph = ph;
	}
	@Column(name="soil_Report",nullable = false, length = 100)
	public String getSoilReport() {
		return soilReport;
	}
	public void setSoilReport(String soilReport) {
		this.soilReport = soilReport;
	}
	@Column(name="water_Type",nullable = false, length = 100)
	public String getWaterType() {
		return waterType;
	}
	public void setWaterType(String waterType) {
		this.waterType = waterType;
	}
	@Column(name="water_Report",nullable = false, length = 100)
	public String getWaterReport() {
		return waterReport;
	}
	public void setWaterReport(String waterReport) {
		this.waterReport = waterReport;
	}
	@Column(name="annual_Precipitation",nullable = false, length = 100)
	public Double getAnnualPrecipitation() {
		return annualPrecipitation;
	}
	public void setAnnualPrecipitation(Double annualPrecipitation) {
		this.annualPrecipitation = annualPrecipitation;
	}
	@Column(name="frostless_Day",nullable = false, length = 100)
	public Double getFrostlessDay() {
		return frostlessDay;
	}
	public void setFrostlessDay(Double frostlessDay) {
		this.frostlessDay = frostlessDay;
	}
	@Column(name="average_Temperature",nullable = false, length = 100)
	public Double getAverageTemperature() {
		return averageTemperature;
	}
	public void setAverageTemperature(Double averageTemperature) {
		this.averageTemperature = averageTemperature;
	}
	@Column(name="accumulated_Temperature",nullable = false, length = 100)
	public Double getAccumulatedTemperature() {
		return accumulatedTemperature;
	}
	public void setAccumulatedTemperature(Double accumulatedTemperature) {
		this.accumulatedTemperature = accumulatedTemperature;
	}
	@Column(name="month_Min_Avg_Temperature",nullable = false, length = 100)
	public Double getMonthMinAvgTemperature() {
		return monthMinAvgTemperature;
	}
	public void setMonthMinAvgTemperature(Double monthMinAvgTemperature) {
		this.monthMinAvgTemperature = monthMinAvgTemperature;
	}
	@Column(name="month_Max_Avg_Temperature",nullable = false, length = 100)
	public Double getMonthMaxAvgTemperature() {
		return monthMaxAvgTemperature;
	}
	public void setMonthMaxAvgTemperature(Double monthMaxAvgTemperature) {
		this.monthMaxAvgTemperature = monthMaxAvgTemperature;
	}
	@Column(name="year_Abs_Min_Temperature",nullable = false, length = 100)
	public Double getYearAbsMinTemperature() {
		return yearAbsMinTemperature;
	}
	public void setYearAbsMinTemperature(Double yearAbsMinTemperature) {
		this.yearAbsMinTemperature = yearAbsMinTemperature;
	}
	@Column(name="year_Abs_Max_Temperature",nullable = false, length = 100)
	public Double getYearAbsMaxTemperature() {
		return yearAbsMaxTemperature;
	}
	public void setYearAbsMaxTemperature(Double yearAbsMaxTemperature) {
		this.yearAbsMaxTemperature = yearAbsMaxTemperature;
	}
	@Column(name="year_Sun_Hour",nullable = false, length = 100)
	public Double getYearSunHour() {
		return yearSunHour;
	}
	public void setYearSunHour(Double yearSunHour) {
		this.yearSunHour = yearSunHour;
	}
	@Column(name="climate_Report",nullable = false, length = 100)
	public Double getClimateReport() {
		return climateReport;
	}
	public void setClimateReport(Double climateReport) {
		this.climateReport = climateReport;
	}	
}
