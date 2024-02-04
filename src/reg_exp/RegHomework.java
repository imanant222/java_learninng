package reg_exp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegHomework {

	public static void main(String[] args) {
		/**
		 *homework01
		 *规定电子邮件规则为
		 *1.只能有一个@
		 *2.@前面是用户名,可以是a-ZA-Z0-9-字符
		 *3.@后面是域名,井目域名只能是英文字母，比如sohu.com或者tsinghua.org.cn
		 *4.写出对应的正则表达式，验证输入的字符串是否为满足规则
		 */
		String content="260@qq.ok.com";
		String reg = "^[\\w_-]+@([a-zA-Z]+\\.)+[a-zA-Z]+$";
		System.out.println(content.matches(reg));
		
		/**
		 * hoemwork02
		 * 要求验证是不是整数或者小数
		 * 提示：这个题要考虑正数和负数
		 * 比如：123-34534.89-87.9-0.010.45等
		 */
		String content2="0.208";
		String reg2 = "^[-|+]?([1-9]\\d*|0)(\\.\\d+)?$";
		System.out.println(content2.matches(reg2));
		
		/**
		 * Homeworko3
		 * 对一个url进行解析.java
		 	https://www.sohu.com:8080/abc/index.htm
			a）要求得到协议是什么？
				http
			b）域名是什么？
				www.sohu.com
			c）端口是什么？
				8080
			d）文件名是什么？
				index.htm
		 */
		String content3 = "https://www.sohu.com:8080/abc/index.htm";
		String reg3 = "^([a-zA-z]+)://([a-zA-Z.]+):(\\d+)[\\w-/]*/([\\w.]+)$";
		Pattern pattern = Pattern.compile(reg3);
		Matcher matcher = pattern.matcher(content3);
		if(matcher.matches()) {
			System.out.println("整体匹配成功");
			System.out.println(matcher.group(1));
			System.out.println(matcher.group(2));
			System.out.println(matcher.group(3));
			System.out.println(matcher.group(4));
		}else {
			System.out.println("整体匹配不成功");
		}
	}

}
