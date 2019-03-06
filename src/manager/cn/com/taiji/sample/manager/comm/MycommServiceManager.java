/*
 * Date: 2013-7-22
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.sample.manager.comm;

import java.io.IOException;

import cn.com.taiji.mycomm.model.comm.protocol.mycomm.MycommSampleRequest;
import cn.com.taiji.mycomm.model.comm.protocol.mycomm.MycommSampleResponse;

/**
 * 
 * @author Peream <br>
 *         Create Time：2013-7-22 下午3:57:18<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
public interface MycommServiceManager
{
	public MycommSampleResponse sampleRequest(MycommSampleRequest request) throws IOException;
}
