package ty;

import java.util.Stack;

public class ExpCounter {
    public static void main(String[] args) {
        System.out.println(count("7 + 28/5"));//12'3/5
        System.out.println(count("15/4 / 3/6 + 6 - 1"));//12'1/2
    }

    /**
     * �������ʽ��
     *
     * @param str1 һ����������׺���ʽ
     * @return ��
     */
    public static String count(String str1) {
        //������׺���ʽ
        String str = ExpResolver.creatSuffixExepression(str1);
        char c = 'b';
        boolean haveNum = false;//�Ƿ�������
        boolean haveFraction = false;//�Ƿ�Ϊ����
        int tmp = 0;//��������Ҳ�䵱��ĸ
        int numer = 0;//����
        int div = 0;//���Լ��
        Value a = null;
        Value b = null;
        Value curResult = null;

        Stack<Value> number = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            c = str.charAt(i);
            //���������ֵĻ�������
            if (isNum(c)) {
                haveNum = true;
                tmp = tmp * 10 + (c - '0');
            } else {
                if (haveNum) {
                    haveNum = false;
                    if (isSpace(c)) {
                        if (haveFraction) {
                            Value xx = new Value(numer, tmp);
                            number.push(xx);//�������ջ
                            tmp = 0;
                        } else {
                            Value xx = new Value(tmp, 1);
                            number.push(xx);//��������ջ
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
            //throw new Exception("����");
            System.out.println("����"); //������в�������������Ӧ��������ջ�ڻ��ж��Ԫ�أ���˵������
        }
        Value result = number.pop();
        if (result.up != 0) {               //�����Ӳ�Ϊ0����Է��ӷ�ĸԼ��
            div = gcd(curResult.down, curResult.up);//�������
            result.up /= div;
            result.down /= div;
        }
        // System.out.println(z.numerator+"/"+z.denominator);
        return ExpCounter.transform(result.up, result.down);
    }

    public static boolean isNum(char x) { //�Ƿ�Ϊ����
        return (x >= '0' && x <= '9');
    }

    public static Value add(Value a, Value b) { //�ӷ�
        Value result = new Value(1, 1);
        result.up = a.up * b.down + b.up * a.down;
        result.down = a.down * b.down;
        return result;
    }

    public static Value subtract(Value a, Value b) { //����
        Value result = new Value(1, 1);
        result.up = a.up * b.down - b.up * a.down;
        result.down = a.down * b.down;
        return result;
    }

    public static Value mul(Value a, Value b) { //�˷�
        Value result = new Value(1, 1);
        result.up = a.up * b.up;
        result.down = a.down * b.down;
        return result;
    }

    public static Value division(Value a, Value b) { //����
        Value result = new Value(1, 1);
        result.up = a.up * b.down;
        result.down = a.down * b.up;
        return result;
    }

    public static int gcd(int a, int b) { //շת����������Լ��
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
        return (x == ' ' || x == '*' || x == '+' || x == '-'); //�жϲ�Ϊ��������;
    }

	/**
	 * ��i/jת��Ϊ�����
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
