package cn.com.taiji.sample.entity.dict.source;
/**
 * 物料状态
 * @author admin02
 * 2018年10月31日 下午1:16:25
 */
public enum MaterialStatus {

	PURCHASE("购买", "01"  ){},
	CHECK("请验","02"){},	
	EXAMPASS("检验放行","03"){},	
	STORE("药材入库","04"){},	
	
	;
	private String value;//接口代号
	private String code;//接口名称
	
	private MaterialStatus(String value,String code) {
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