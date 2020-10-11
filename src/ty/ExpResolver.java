package ty;

import java.util.Stack;

/**
 * @author ��׺ת��׺
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
	public static boolean isNUm(char x) { //�ж��Ƿ�Ϊ����;
		return(x>='0'&&x<='9');
	}
	
	public static int priority(char x)          //����һ�������������ȼ�
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
	 * ���ɺ�׺���ʽ
	 * @param str
	 * @return
	 */
	public static String creatSuffixExepression(String str) {
		int num=0;
		int tmp=0;
		int stackLen=0;//ջ�ĳ���
		String expression="";//�������ַ���
		char element='a';
		boolean haveNum= false;//�Ƿ�������
		Stack<Character> stack= new Stack<>();//�ַ�ջ
		str=str+"!";//Ϊ���жϽ����������!��Ϊ�ַ�����β
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
				if(c=='(') {//������������������ջ
					num++;
					stack.push(c);
				}else if(c=='+'||c=='-'||c=='!') { //��Ϊ+-���ȼ��������ȫ������
					if(num>0) {//���������������ֹͣ
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
					}else {//ȫ������;
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
					//������β��
					if(c=='!') {
						break;
					}
					stack.push(c);
					expression+=' ';
				}else if(c=='*'||c=='/') {
					if(str.charAt(i-1)==' '){
						while (!stack.empty() && priority(c) <= priority(stack.peek())){//����ջ�����ȼ����ڵ�ǰԪ�صķ���;
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
				}else if(c==' ') {//�����ո���ӿո�;
					expression+=' ';
				}
			}
		}
		return expression;
	}
}
