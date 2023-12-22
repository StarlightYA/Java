package JavaCode.ComprehensiveExercises;

import java.util.Random;
import java.util.Scanner;

public class test11_双色球 {
    public static void main(String[] args) {
        // 1、生成中奖号码
        int[] arr = CreateNumber();

        System.out.println();
        // 2、用户输入彩票号码(红球+蓝球)
        int[] UserInputArr = UserInputNumber();
        System.out.println("用户输入的彩票号码为：");
        for (int i = 0; i < UserInputArr.length; i++) {
            System.out.print(UserInputArr[i] + " ");
        }
        System.out.println();
        WinningSituation(arr, UserInputArr);
        System.out.print("本期中奖号码为：");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    // 创建数组用于添加用户输入的彩票号码
    public static int[] UserInputNumber() {
        // 6个红球 1个篮球 数组长度为7
        int[] arr = new int[7];
        // 1、键盘录入红球
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < arr.length - 1;) {
            System.out.println("请输入第" + (i + 1) + "个红球号码 ");
            int RedNumber = sc.nextInt();
            // RedNumber范围在1-33，且不重复
            if (RedNumber < 34 && RedNumber > 0) {
                if (!contains(arr, RedNumber)) {
                    arr[i] = RedNumber;
                    i++;
                } else {
                    System.out.println("该数字重复，请重新输入！");
                }
            } else {
                System.out.println("数字超出范围，请重新输入！");
            }
        }
        // 2、键盘录入蓝球
        System.out.println("请输入蓝球号码：");
        while (true) {
            int BlueNumber = sc.nextInt();
            if (BlueNumber < 17 && BlueNumber > 0) {
                arr[arr.length - 1] = BlueNumber;
                break;
            } else {
                System.out.println("输入有误，请重新输入！");
            }
        }
        sc.close();
        return arr;
    }

    // 创建数组用于添加中奖号码
    public static int[] CreateNumber() {
        // 6个红球 1个篮球 数组长度为7
        int[] arr = new int[7];
        // 随机生成号码，并添加到数组中
        // 红球：范围1-33，不可重复
        // 蓝球：范围1-16，可与红球重复

        // 生成红球号码并添加到数组中
        Random r = new Random();
        for (int i = 0; i < 6;) {
            // 获取红球号码
            int RedNumber = r.nextInt(33) + 1;
            // 如果红球数字在数组中不存在，则将该数字添加到数组中
            if (!contains(arr, RedNumber)) {
                arr[i] = RedNumber;
                i++;
            } else {
                System.out.println("该数字重复，请重新输入！");
            }
        }
        // 生成蓝球号码并添加到数组中
        int BlueNumber = r.nextInt(15) + 1;
        arr[arr.length - 1] = BlueNumber;
        return arr;
    }

    // 判断用户中奖情况
    public static void WinningSituation(int[] arr, int[] UserInputArr) {
        int RedCount = 0;
        int BlueCount = 0;
        for (int i = 0; i < UserInputArr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                if (UserInputArr[i] == arr[j]) {
                    RedCount++;
                    break;
                }
            }
        }
        if (arr[arr.length - 1] == UserInputArr[arr.length - 1]) {
            BlueCount++;
        }
        System.out.println("红球数量为：" + RedCount + "个");
        System.out.println("红球数量为：" + BlueCount + "个");
        if (RedCount == 6 && BlueCount == 1) {
            System.out.println("恭喜你，中1000万！");
        } else if (RedCount == 6 && BlueCount == 0) {
            System.out.println("恭喜你，中500万！");
        } else if (RedCount == 5 && BlueCount == 1) {
            System.out.println("恭喜你，中3000元！");
        } else if (RedCount == 5 && BlueCount == 0 || RedCount == 4 && BlueCount == 1) {
            System.out.println("恭喜你，中200元！");
        } else if (RedCount == 4 && BlueCount == 0 || RedCount == 3 && BlueCount == 1) {
            System.out.println("恭喜你，中10元！");
        } else if (RedCount == 2 && BlueCount == 1 || RedCount == 1 && BlueCount == 1
                || RedCount == 0 && BlueCount == 1) {
            System.out.println("恭喜你，中5元！");
        } else {
            System.out.println("谢谢惠顾！");
        }
    }

    // 用于判断数字在数组中是否存在
    public static boolean contains(int[] arr, int number) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == number) {
                return true;// 数字在数组中存在
            }
        }
        return false;// 数字在数组中不存在
    }
}
