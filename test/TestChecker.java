import org.junit.Test;
import ty.ExpChecker;

public class TestChecker {
    @Test
    public void testChecker(){
        String s2 = "1 + 2 + 3";
        String s5 = "2 + 1 + 3";
        String s = "1 + 2 + 3";
        String s1 = "(1 + 2) + 3";
        String s3 = "1 * 2 * 3";
        String s4 = "3 + 1 + 2";
        System.out.println(s+"---"+s1+" 比照结果是 "+ExpChecker.isEqual(s,s1));
        System.out.println(s1+"---"+s2+" 比照结果是 "+ExpChecker.isEqual(s1,s2));
        System.out.println(s+"---"+s2+" 比照结果是 "+ExpChecker.isEqual(s,s2));
        System.out.println(s+"---"+s3+" 比照结果是 "+ExpChecker.isEqual(s,s3));
        System.out.println(s+"---"+s4+" 比照结果是 "+ExpChecker.isEqual(s,s4));
        System.out.println(s+"---"+s5+" 比照结果是 "+ExpChecker.isEqual(s,s5));
        System.out.println(ExpChecker.isEqual(s1,s1));
    }
}
