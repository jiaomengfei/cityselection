package com.example.jiao.cityapplication;

import org.junit.Test;

public class IntegerReverse {

    //第一种：无限制整数的逆序输出。
    @Test
    public void integerReverse1() {
        int num = 12345;
        while (num != 0) {
            System.out.print(num % 10);
            num /= 10;
        }
    }

    //第二种：非负整数的逆序输出(结果String化)。
    @Test
    public void integerReverse2() {
        int num = 12345;
        String reverse = "";
        while (num != 0) {
            reverse += num % 10;
            num /= 10;
        }
        System.out.println(reverse);
    }

    //第三种：非特殊情况的逆序输出（例如：非100,非10000等)
    @Test
    public void integerReverse3() {
        int num = 12345;
        int result = 0;
        while (num!=0){
            int x = num%10;
            result = result*10+x;
            num /=10;
        }
        System.out.println(result);
    }

}
