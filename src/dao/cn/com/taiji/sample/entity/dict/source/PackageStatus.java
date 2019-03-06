package cn.com.taiji.sample.entity.dict.source;
/**
 * 待分装状态
 * @author admin02
 * 2018年10月31日 下午1:16:25
 */
public enum PackageStatus {

	WAITPACKAGE("待分装包装", "01"  ){},
	WAITCHECK("待分装请验","02"){},	
	WAITEXAM("待分装检验","03"){},	
	WAITPASS("待分装放行","04"){},	
	WAITSTORE("待分装入库","05"){},	
	
	;
	private String value;//接口代号
	private String code;//接口名称
	
	private PackageStatus(String value,String code) {
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