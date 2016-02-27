package com.yuanhh.memory;

import java.util.ArrayList;
import java.util.List;

/**
 * (1) Java堆溢出
 * 
 * 		Java堆用于存储对象实例，在不断创建对象实例，
 * 		并且保证不会被GC回收(保证GC Roots到对象实例 具有可达)，
 * 		当创建的对象实例超过堆容量的上限，那么就会发生堆溢出，抛出OutOfMemoryError异常
 * 
 * 
 * (2) VM Args：-Xms10m -Xmx10m -XX:+HeapDumpOnOutOfMemoryError
 * 
 * 		-Xms：设置堆的最小值
 * 		-Xmx：设置堆的最大值
 * 		+HeapDumpOnOutOfMemoryError： 虚拟机堆溢出时dump当前内存堆转存快照
 * 		当-Xms和-Xmx参数设置成相等大小时，则Java堆不可自动扩展
 * 
 * @author yuanhuihui
 *      www.yuanhh.com
 */
public class HeapOOM {

	static class OOMObject {
	}

	public static void main(String[] args) {
		List<OOMObject> list = new ArrayList<OOMObject>();
		try{
			while (true) {
				list.add(new OOMObject());
			}
		}catch(OutOfMemoryError e){
			System.out.println("list size:"+list.size());
			throw e;
		}
	}
}

