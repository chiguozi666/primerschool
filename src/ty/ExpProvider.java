package ty;

import java.util.Random;


/**
 * 生成中缀表达式
 */
public class ExpProvider {
    /**
     * 真分数转为假分数
     * @param a 一个假分数
     * @return
     */
    public static String trueToFalse(String a) {
        String b[] = a.split("'");
        String c[] = b[1].split("/");
        int d = Integer.parseInt(b[0]);//带分数中左边的整数
        int up = Integer.parseInt(c[0]);//分子
        int down = Integer.parseInt(c[1]);//分母
        int newUp = d * down + up;
        String result = newUp + "/" + down;
        return result;
    }

    /**
     * 创建一个range范围的整数
     * @param range 生成数的范围
     * @return
     */
    public static String createMath(int range) {//生成整数或分数
        Random rand = new Random();
        int a = rand.nextInt(range - 1);
        int b = rand.nextInt(4);//分子
        int c = rand.nextInt(6) + b;//分母  保证分母比分子大
        String d = Integer.toString(a);
        if (b >= 3) {
            if (d.equals("0")) {
                d = b + "/" + c;
            } else
                d = a + "'" + b + "/" + c;
        }
        if (d.equals("0")) d = Integer.toString(1);
        return d;
    }

    public static String createOp() {//随机生成运算符号
        Random rand = new Random();
        int a = rand.nextInt(4);
        String str = null;
        if (a == 0)
            str = "+";
        else if (a == 1)
            str = "-";
        else if (a == 2)
            str = "*";
        else
            str = "/";
        return str;
    }

    public static String createExp(int range) {//生成表达式
        String str = "";
        String str1 = "";
        do {
            str = "";//标准的式子
            str1 = "";//假分数的式子
            int t = 0;//算式中的数字个数
            Random rand = new Random();
            t = rand.nextInt(3) + 2;
            String[] number = new String[t];//存放数字
            String[] symbol = new String[t - 1];//存放运算符号,有t个数字就应该有t-1个符号
            String[] total = new String[4 * t - 3];//存放式子
            String[] total1 = new String[4 * t - 3];
            for (int i = 0; i < t; i++) {
                number[i] = createMath(range);
            }
            for (int i = 0; i < t - 1; i++) {
                symbol[i] = createOp();
            }
            //数字+空格+操作符+空格+数字刚好是模4操作
            for (int i = 0; i < 4 * t - 3; i++) {
                if (i % 4 == 0) {
                    total[i] = number[i / 4];
                    total1[i] = number[i / 4];
                } else if (i % 4 == 2) {
                    int k = (i + 2) / 4 - 1;
                    total[i] = symbol[k];
                    total1[i] = symbol[k];
                } else {
                    total[i] = " ";
                    total1[i] = " ";
                }
            }
            //total1把里面的真分数都转成假分数输出方便算答案
            for (int i = 0; i < 4 * t - 3; i++) {
                if (total1[i].contains("'")) {
                    total1[i] = trueToFalse(total1[i]);
                }
            }
            //当t=4的时候就加括号就是了
            if (t == 4) {
                if ((symbol[1] == "*" || symbol[1] == "/") && (symbol[0] == "+" || symbol[0] == "-")) {
                    total[0] = "(" + total[0];
                    total[4] = total[4] + ")";
                    total1[0] = "(" + total1[0];
                    total1[4] = total1[4] + ")";
                }
                if ((symbol[1] == "*" || symbol[1] == "-") && (symbol[2] == "+" || symbol[2] == "-")) {
                    total[8] = "(" + total[8];
                    total[12] = total[12] + ")";
                    total1[8] = "(" + total1[8];
                    total1[12] = total1[12] + ")";
                }
            }
            //拼接total字符串
            for (int i = 0; i < 4 * t - 3; i++) {
                str = str + total[i];
                str1 = str1 + total1[i];
            }
            //判断答案是否为负数，假如是负数就重来
        } while (ExpCounter.count(str1).contains("-"));
        // System.out.println(str);
        return str + " =" + ";" + str1;
    }

    public static void main(String[] args) {
        ExpProvider a = new ExpProvider();
        System.out.println(a.createExp(10));
    }
}
