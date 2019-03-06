package cn.com.taiji.sample.entity.dict.source;

public enum SoilTexture {

	LOAM("壤土", "01"  ){},
	SANDYSOIL ("沙土","02"){},	
	CLAY ("黏土","03"){},	
	
	;
	private String value;//
	private String code;//

	private SoilTexture(String value,String code) {
		this.code = code;
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
