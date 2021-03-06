package cn.com.taiji.sample.entity.dict.source;

public enum ResourceType
{
	A_XTGL("系统管理", "xtgl.png","fa fa-cog") {},
	B_SAMPLE("示例", "xlgl.png","fa fa-align-left") {},
	C_CONFIG("配置", "xlgl.png","fa fa-cogs") {},
	D_CONFIG("配置", "xlgl.png","icon24_nav03") {},
	E_XMGL("项目管理", "xlgl.png","fa fa-cogs") {},
	F_YCZZ("药材种植", "xlgl.png","fa fa-leaf") {},
	G_YPSC("饮片生产", "xlgl.png","fa fa-medkit") {},
	H_YCZZ("药材经营", "xlgl.png","fa fa-money ") {},
	;

	private final String value;
	private final String logoPic;
	private final String logoClass;

	private ResourceType(String value, String logoPic, String logoClass)
	{
		this.value = value;
		this.logoPic = logoPic;
		this.logoClass = logoClass;
	}

	public String getLogoClass()
	{
		return logoClass;
	}

	public String getValue()
	{
		return value;
	}

	public String getLogoPic()
	{
		return logoPic;
	}
}