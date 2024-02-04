package reg_exp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegPrac {

	public static void main(String[] args) {
//		String content = "1996年1月，Sun公司发布了Java的第一个开发工具包（JDK 1.0），这是Java发展历程中的重要里程碑，标志着Java成为一种独立的开发工具。9月，约8.3万个网页应用了Java技术来制作。10月，Sun公司发布了Java平台的第一个即时（JIT）编译器。\n"
//				+ "1997年2月，JDK 1.1面世，在随后的3周时间里，达到了22万次的下载量。4月2日，Java One会议召开，参会者逾一万人，创当时全球同类会议规模之纪录。9月，Java Developer Connection社区成员超过10万。\n"
//				+ "1998年12月8日，第二代Java平台的企业版J2EE发布。1999年6月，Sun公司发布了第二代Java平台（简称为Java2）的3个版本：J2ME（Java2 Micro Edition，Java2平台的微型版），应用于移动、无线及有限资源的环境；J2SE（Java 2 Standard Edition，Java 2平台的标准版），应用于桌面环境；J2EE（Java 2Enterprise Edition，Java 2平台的企业版），应用于基于Java的应用服务器。Java 2平台的发布，是Java发展过程中最重要的一个里程碑，标志着Java的应用开始普及。\n"
//				+ "1999年4月27日，HotSpot虚拟机发布。HotSpot虚拟机发布时是作为JDK 1.2的附加程序提供的，后来它成为了JDK 1.3及之后所有版本的Sun JDK的默认虚拟机 [11]。";
//		String regStr = "\\d\\d\\d\\d";
//		Pattern pattern = Pattern.compile(regStr);
//		Matcher matcher = pattern.matcher(content);
//		while(matcher.find()) {
//			System.out.println(matcher.group(0));
//		}
//		String[] s = "mac-jack20math~ai".split("-|~|\\d+");
//		for (String string : s){
//			System.out.println(string);
//		}
		System.out.println("我我我好开开心心心啊啊啊".replaceAll("(.)\\1+", "$1"));
	}

}
