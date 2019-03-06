/*
 * Date: 2013-6-19
 * author: Peream  (peream@gmail.com)
 *
 */
package tests.cn.com.taiji.sample.manager.comm;

import java.io.IOException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import tests.MyNotTransationalTest;
import cn.com.taiji.mycomm.model.comm.protocol.mycomm.MycommSampleRequest;
import cn.com.taiji.mycomm.model.comm.protocol.mycomm.MycommSampleResponse;
import cn.com.taiji.sample.manager.comm.MycommServiceManager;

/**
 * 
 * @author Peream <br>
 *         Create Time：2013-6-19 上午10:28:37<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
public class TestMycommServiceManager extends MyNotTransationalTest
{
	@Autowired
	private  MycommServiceManager manager;

	@Test
	public void sampleRequest() throws IOException
	{
		MycommSampleRequest request = new MycommSampleRequest();
		request.setName("陈培安");
		MycommSampleResponse res = manager.sampleRequest(request);
		echo(res);
	}
}
