package cn.com.taiji.sample.entity.dict.source;
/**
 * 待分装状态
 * @author admin02
 * 2018年10月31日 下午1:16:25
 */
public enum ProduceStatus {

	WAITPACKAGE("待分装出库", "01"  ){},
	PACKAGE("成品包装","02"){},	
	CHECK("成品请验","03"){},	
	EXAM("成品检验","04"){},	
	PASS("成品放行","05"){},	
	STORE("成品入库","06"){},	
	
	;
	private String value;//接口代号
	private String code;//接口名称
	
	private ProduceStatus(String value,String code) {
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