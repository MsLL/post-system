package com.upup.demo.postsystem;

/**
 * 
 * @Date 2021/1/29 下午11:11
 */
//java的assert关键字要开启-ea enale assert才会生效，不用它了，用Junit api
public class AssertTest {
    public static void main(String[] args) {
        double x =-123.45;
        assert x >= 0;//
        System.out.println(x);

        System.out.println("shouldnot reach here");
    }
}
