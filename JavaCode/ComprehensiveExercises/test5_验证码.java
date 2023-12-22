package JavaCode.ComprehensiveExercises;

import java.util.Random;

/*定义方法实现随机产生一个5位数的验证码
 * 需求：
 * 长度为5
 * 前四位为大写字母或小写字母
 * 最后一位是数字
 */
public class test5_验证码 {
    // 方法：
    // 如果要在一堆没有规律的数据中随机抽取
    // 可以先把这些数据放在数组当中
    // 在随机抽取一个索引
    public static void main(String[] args) {
        // 分析：
        // 1、大写字母和小写字母放到数组当中
        char[] arr = new char[52];
        for (int i = 0; i < arr.length; i++) {
            // 添加小写字母,ASCII码表，a=97
            if (i <= 25) {
                arr[i] = (char) (97 + i);
            } else {
                // 添加大写字母，A=65
                arr[i] = (char) (65 + i - 26);
            }
            // System.out.print(arr[i] + " ");
        }
        // 定义一个字符串类型的变量，用来记录最终结果
        String result = "";// 长度为0的字符串
        // 2、随机抽取4次
        Random r = new Random();
        for (int i = 0; i < 4; i++) {
            int RandomIndex = r.nextInt(arr.length);// 抽取数组中的随机索引
            // System.out.print(arr[RandomIndex]);
            result = result + arr[RandomIndex];// 与随机生成的4个字母进行拼接
        }
        // 3、随机抽取一个数字0-9
        int number = r.nextInt(10);
        result = result + number;
        System.out.println(result);
    }

}
