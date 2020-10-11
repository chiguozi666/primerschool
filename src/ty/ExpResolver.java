package ty;

import java.util.Stack;

/**
 * @author 中缀转后缀
 *
 */
public class ExpResolver {
	public static void test(String que) {
		String str=que;
		String suffix= creatSuffixExepression(str);
		System.out.println(suffix);
	}

	public static void main(String[] args) {
		System.out.println(creatSuffixExepression("1 + 2 * 3"));
		System.out.println(creatSuffixExepression("(1 + 2) * 3"));
	}
	public static boolean isNUm(char x) { //判断是否为数字;
		return(x>='0'&&x<='9');
	}
	
	public static int priority(char x)          //返回一个操作符的优先级
	{
	    if (x == '-' || x == '+')
	        return 1;
	    else if (x == '*' || x == '/')
	        return 2;
	    else if (x == '(')
	        return 0;
	    else
	        return -1;
	}

	/**
	 * 生成后缀表达式
	 * @param str
	 * @return
	 */
	public static String creatSuffixExepression(String str) {
		int num=0;
		int tmp=0;
		int stackLen=0;//栈的长度
		String expression="";//输出结果字符串
		char element='a';
		boolean haveNum= false;//是否有数字
		Stack<Character> stack= new Stack<>();//字符栈
		str=str+"!";//为了判断结束方便加入!做为字符串结尾
		for(int i=0;i<str.length();i++) {
			char c = str.charAt(i);
			if(isNUm(c)) {
				haveNum=true;
				tmp=tmp*10+(c-'0');
			}else {
				if(haveNum) {
					haveNum=false;
					expression+=tmp;
					tmp=0;
				}
				if(c=='(') {//遇到左括号无条件进栈
					num++;
					stack.push(c);
				}else if(c=='+'||c=='-'||c=='!') { //因为+-优先级最低所以全部弹出
					if(num>0) {//如果遇到左括号则停止
						stackLen= stack.size();
						for(int j=0;j<stackLen;j++) {
							element = stack.peek();
							if(element=='(') {
								break;
							}
							//change
							expression +=' ';
							expression+=stack.pop();
						}
					}else {//全部弹出;
						expression+=' ';
						if(stack.size()!=0) {
							stackLen = stack.size();
							for(int j = 0; j < stackLen; j++ ) {
								//change
								expression +=' ';
								expression += stack.pop();
							}
						}
					}
					//读到结尾了
					if(c=='!') {
						break;
					}
					stack.push(c);
					expression+=' ';
				}else if(c=='*'||c=='/') {
					if(str.charAt(i-1)==' '){
						while (!stack.empty() && priority(c) <= priority(stack.peek())){//弹出栈顶优先级大于当前元素的符号;
							expression +=' ';
							expression += stack.pop();
						}
						stack.push(c);
					}else {
						expression +=c;
					}
				}else if(c==')') {
					if(stack.size()!=0) {
						stackLen = stack.size();
						for(int j = 0; j < stackLen; j++ ) {
							element = stack.pop();
							if(element=='(') {
								break;
							}
							//change
							expression+=' ';
							expression += element;
						}
						num--;
					}
				}else if(c==' ') {//遇到空格则加空格;
					expression+=' ';
				}
			}
		}
		return expression;
	}
}
