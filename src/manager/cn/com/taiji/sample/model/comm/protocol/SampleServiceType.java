/*
 * Date: 2015年6月25日
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.sample.model.comm.protocol;

import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Peream <br>
 *         Create Time：2015年6月25日 下午5:36:39<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
public enum SampleServiceType
{
	SAMPLE("交易相关", "^SAMPLE_\\S+$") {},
	COMMSAMPLE("commSample", "^COMMSAMPLE_\\S+\\.(json|xml)$") {},
	;
	private static Logger logger = LoggerFactory.getLogger(SampleServiceType.class);
	private final String value;
	private final Pattern reqNamePattern;

	private SampleServiceType(String value, String reqNameRegex)
	{
		this.value = value;
		this.reqNamePattern = Pattern.compile(reqNameRegex);
	}

	public String getValue()
	{
		return value;
	}

	public Pattern getReqNamePattern()
	{
		return reqNamePattern;
	}

	public static SampleServiceType fromName(String name)
	{
		try
		{
			return SampleServiceType.valueOf(name);
		}
		catch (Exception e)
		{
			logger.error(e.getMessage());
			return null;
		}
	}

}
