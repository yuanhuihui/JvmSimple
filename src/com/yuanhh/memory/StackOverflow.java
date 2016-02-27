package com.yuanhh.memory;

/**
 * (1) Java栈溢出
 * 
 *      当方法栈的请求深度大于虚拟机所允许的最大深度，则抛出StackOverflowError异常
 * 
 * (2) VM Args：-Xss128k
 *      -Xss:设置本地方法栈大小
 * 
 * @author yuanhuihui
 * 
 *      www.yuanhh.com
 */
public class StackOverflow {

    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) throws Throwable {
        StackOverflow oom = new StackOverflow();
        try {
            oom.stackLeak();
        } catch (StackOverflowError e) {
            System.out.println("stack length:" + oom.stackLength);
          throw e;
        }
    }
}