package com.core.lib.helper;

import com.core.lib.wiget.DelayTask;
import com.core.lib.wiget.AsynTask;

/**
 * function: DelayTask 助手类
 * 
 * @author:linhuan
 */
public class DelayTaskHelper {

	/**
	 * function: 延迟执行任务
	 *
	 * @param delayMS 延迟毫秒数
	 * @param listener 回调
	 * @return
	 * 
	 * @author:linhuan 2014年7月16日 上午9:38:49
	 */
	public static DelayTask doDelayTask(int delayMS, DelayTask.OnDelayExecuteListener listener){
		return doDelayTask(delayMS, 0, listener);
	}
	
	/**
	 * function: 延迟执行任务
	 *
	 * @param delayMS 延迟毫秒数
	 * @param repeatCount 重复次数
	 * @param listener 回调
	 * @return
	 * 
	 * @author:linhuan 2014年7月16日 上午9:39:40
	 */
	public static DelayTask doDelayTask(int delayMS, int repeatCount, DelayTask.OnDelayExecuteListener listener){
		DelayTask result = new DelayTask(delayMS, repeatCount, listener);
		result.start();
		return result;
	}

	/**
	 * function: 延迟执行任务
	 *
	 * @param listener 回调
	 * @return
	 *
	 * @author:linhuan 2014年7月16日 上午9:38:49
	 */
	public static AsynTask doAsynTask(AsynTask.OnAsynExecuteListener listener){
		AsynTask result = new AsynTask(listener);
		result.start();
		return result;
	}
	
}
