package ty;

import java.util.Stack;

public class ExpCounter {
    public static void main(String[] args) {
        System.out.println(count("7 + 28/5"));//12'3/5
        System.out.println(count("15/4 / 3/6 + 6 - 1"));//12'1/2
    }

    /**
     * 负责计算式子
     *
     * @param str1 一个正常的中缀表达式
     * @return 答案
     */
    public static String count(String str1) {
        //创建后缀表达式
        String str = ExpResolver.creatSuffixExepression(str1);
        char c = 'b';
        boolean haveNum = false;//是否有数字
        boolean haveFraction = false;//是否为分数
        int tmp = 0;//保存数字也充当分母
        int numer = 0;//分子
        int div = 0;//最大公约数
        Value a = null;
        Value b = null;
        Value curResult = null;

        Stack<Value> number = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            c = str.charAt(i);
            //把连续数字的缓存下来
            if (isNum(c)) {
                haveNum = true;
                tmp = tmp * 10 + (c - '0');
            } else {
                if (haveNum) {
                    haveNum = false;
                    if (isSpace(c)) {
                        if (haveFraction) {
                            Value xx = new Value(numer, tmp);
                            number.push(xx);//存分数进栈
                            tmp = 0;
                        } else {
                            Value xx = new Value(tmp, 1);
                            number.push(xx);//存整数进栈
                            tmp = 0;
                        }
                    } else {
                        haveFraction = true;
                        numer = tmp;
                        tmp = 0;
                        continue;
                    }
                }
                if (c == ' ') {
                    if (haveFraction) {
                        haveFraction = false;
                    }
                    continue;
                } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                    b = number.pop();
                    a = number.pop();
                    switch (c) {
                        case '+':
                            curResult = add(a, b);
                            break;
                        case '-':
                            curResult = subtract(a, b);
                            break;
                        case '*':
                            curResult = mul(a, b);
                            break;
                        case '/':
                            curResult = division(a, b);
                            break;
                    }
                }
                number.push(curResult);
            }
        }
        if (number.size() > 1) {
            //throw new Exception("错误");
            System.out.println("错误"); //如果所有操作符都进行相应操作数后栈内还有多个元素，则说明出错
        }
        Value result = number.pop();
        if (result.up != 0) {               //若分子不为0，则对分子分母约分
            div = gcd(curResult.down, curResult.up);//最大公因子
            result.up /= div;
            result.down /= div;
        }
        // System.out.println(z.numerator+"/"+z.denominator);
        return ExpCounter.transform(result.up, result.down);
    }

    public static boolean isNum(char x) { //是否为数字
        return (x >= '0' && x <= '9');
    }

    public static Value add(Value a, Value b) { //加法
        Value result = new Value(1, 1);
        result.up = a.up * b.down + b.up * a.down;
        result.down = a.down * b.down;
        return result;
    }

    public static Value subtract(Value a, Value b) { //减法
        Value result = new Value(1, 1);
        result.up = a.up * b.down - b.up * a.down;
        result.down = a.down * b.down;
        return result;
    }

    public static Value mul(Value a, Value b) { //乘法
        Value result = new Value(1, 1);
        result.up = a.up * b.up;
        result.down = a.down * b.down;
        return result;
    }

    public static Value division(Value a, Value b) { //除法
        Value result = new Value(1, 1);
        result.up = a.up * b.down;
        result.down = a.down * b.up;
        return result;
    }

    public static int gcd(int a, int b) { //辗转相除法求最大公约数
        {
            int c = a % b;
            while (c != 0) {
                a = b;
                b = c;
                c = a % b;
            }
            return b;
        }
    }

    public static boolean isSpace(char x) {
        return (x == ' ' || x == '*' || x == '+' || x == '-'); //判断不为除法符号;
    }

	/**
	 * 把i/j转化为真分数
	 * @param i
	 * @param j
	 * @return
	 */
    public static String transform(int i, int j) {
        if (i >= j) {
            if (j == 1) {
                return i + "";
            }
            return i / j + "'" + i % j + "/" + j;
        } else {
            return i + "/" + j;
        }
    }
}
