package JavaCode.ComprehensiveExercises;

/*心得：其实不准备写解密的代码的。
 *因为我发现，加密得到结果后重新运行，输入加密后的一串数字，这加密代码本身就有解密功能 
 *但当加密结果首位为0时，再输入加密结果让其解密，0这一位就被吞掉了，解密后的数据与原数据不符（少了0这一位）
 *究其原因，因为最开始的while循环中（也是题目要求的，输入要>0）要判断number是否为0
 *所以最终又补上了解密的代码，就是将加密流程反过来就行了
 *只是再对10取余这部分反转时要注意，
 *由于加密时通过对10取余的方式获取的
 *所以解密时就需要判断：数字在0-4之间，则+10;5-9之间不变
 *最后将存在数组中的各个数字拼接打印出就行了     
 */
import java.util.Scanner;

/*需求：
 * 某系统的数字密码 (大于0)，比如1983，采用加密方式进行传输，规则如下:
 * 先得到每位数，
 * 然后每位数都加上5，
 * 再对10求余，
 * 最后将所有数字反转，得到一串新数
 */
public class test7_数字加密 {
    public static void main(String[] args) {
        // 分析：
        // 1、把整数里面的每一位数字放到数组中
        // 1.1动态输入数字
        System.out.print("请输入原始数据：");
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        // 1.2统计数字长度
        int length = 0;
        int temp = number;
        while (number != 0) {
            number = number / 10;
            length++;
        }
        // System.out.println(length);
        // 1.3、定义动态数组，存放数据
        int[] arr = new int[length];
        int index = length - 1;
        while (temp != 0) {
            int right = temp % 10;
            temp = temp / 10;
            arr[index] = right;
            index--;
        }
        sc.close();
        System.out.println("----------开始加密----------：");
        // 2、每位数加5
        System.out.print("加5之后的数据为：");
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] + 5;
            System.out.print(+arr[i] + " ");
        }
        System.out.print("\n");
        // 3、再对10取余
        System.out.print("每位对10取余之后的数据为：");
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] % 10;
            System.out.print(arr[i] + " ");
        }
        System.out.print("\n");
        // 4、反转
        for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
            int t = arr[i];
            arr[i] = arr[j];
            arr[j] = t;
        }
        System.out.print("转后的数字为：");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        // 调用函数，打印解密后的最终结果
        System.out.println("最终结果为：" + FinallResult(arr));

        // 解密
        System.out.println("----------开始解密----------：");
        // 定义一个新数组，将加密步骤中，反转后的数字，运用拷贝存入到新数组中
        int[] NewArr = new int[length];
        for (int i = 0; i < NewArr.length; i++) {
            NewArr[i] = arr[i];
        }
        // 1、反转
        for (int i = 0, j = NewArr.length - 1; i < j; i++, j--) {
            int t = NewArr[i];
            NewArr[i] = NewArr[j];
            NewArr[j] = t;
        }
        // 2、由于加密时通过对10取余的方式获取的
        // 所以解密时就需要判断：数字在0-4之间，则+10;5-9之间不变
        for (int i = 0; i < NewArr.length; i++) {
            if (NewArr[i] >= 0 && NewArr[i] <= 4) {
                NewArr[i] = NewArr[i] + 10;
            }
        }
        // 3、每个数据-5
        for (int i = 0; i < NewArr.length; i++) {
            NewArr[i] = NewArr[i] - 5;
        }
        // 调用函数，打印解密后的最终结果
        System.out.println("解密后的最终结果为：" + FinallResult(NewArr));
    }

    public static String FinallResult(int[] arr) {
        // 5、把数组里面的每一位数字进行拼接，变成加密/解密后的结果
        String result = "";
        for (int i = 0; i < arr.length; i++) {
            result = result + arr[i];
        }
        return result;
    }
}
