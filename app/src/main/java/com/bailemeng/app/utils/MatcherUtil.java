package com.bailemeng.app.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则匹配工具类
 * @author 续写经典
 * @date 2015/11/3
 */
public final class MatcherUtil
{
	private MatcherUtil() { }

	/**
	 * 手机号验证
	 * 
	 * @param str
	 * @return 验证通过返回true
	 */
	public static boolean isPhone(String str) {
		Pattern p = Pattern.compile("^[1][3,4,5,6,7,8][0-9]{9}$"); // 验证手机号
		Matcher m = p.matcher(str);
		return m.matches();
	}

	/** * 数字和字母组合
	 * @param psd
	 * @return */
	public static boolean isNumAndLetter(String psd) {
		Pattern p = Pattern
				.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,12}$");
		Matcher m = p.matcher(psd);
		return m.matches();
	}

	/**
	 * email格式验证
	 */
	public static boolean isEmail(String email) {
		String emailPattern="^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
	    Pattern p = Pattern.compile(emailPattern);
	    Matcher m = p.matcher(email);
	    return m.matches();
	}

	/**
	 * 验证licenc是否为正确的身份证格式
	 *
	 * @param licenc
	 * @return
	 */
	public static boolean isIdentityCard(String licenc) {
		boolean flag = true;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        /*
         * { 11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",
         * 21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",
         * 33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",
         * 42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",
         * 51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",
         * 63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外" }
         */
		String provinces = "11,12,13,14,15,21,22,23,31,32,33,34,35,36,37,41,42,43,44,45,46,50,51,52,53,54,61,62,63,64,65,71,81,82,91";

		Pattern pattern = Pattern.compile("^[1-9]\\d{14}");
		Matcher matcher = pattern.matcher(licenc);
		Pattern pattern2 = Pattern.compile("^[1-9]\\d{16}[\\d,x,X]$");
		Matcher matcher2 = pattern2.matcher(licenc);
		// 粗略判断
		if (!matcher.find() && !matcher2.find()) {
//			view.setError("身份证号必须为15或18位数字（最后一位可以为X）");
			flag = false;
		} else if (matcher.find() || matcher2.find()){
			// 判断出生地
			if (provinces.indexOf(licenc.substring(0, 2)) == -1) {
//				view.setError("身份证号前两位不正确！");
				flag = false;
			}

			// 判断出生日期
			if (licenc.length() == 15) {
				String birth = "19" + licenc.substring(6, 8) + "-"
						+ licenc.substring(8, 10) + "-"
						+ licenc.substring(10, 12);
				try {
					Date birthday = sdf.parse(birth);
					if (!sdf.format(birthday).equals(birth)) {
//						view.setError("出生日期非法！");
						flag = false;
					}
					if (birthday.after(new Date())) {
//						view.setError("出生日期不能在今天之后！");
						flag = false;
					}
				} catch (ParseException e) {
//					view.setError("出生日期非法！");
					flag = false;
				}
			} else if (licenc.length() == 18) {
				String birth = licenc.substring(6, 10) + "-"
						+ licenc.substring(10, 12) + "-"
						+ licenc.substring(12, 14);
				try {
					Date birthday = sdf.parse(birth);
					if (!sdf.format(birthday).equals(birth)) {
//						view.setError("出生日期非法！");
						flag = false;
					}
					if (birthday.after(new Date())) {
//						view.setError("出生日期不能在今天之后！");
						flag = false;
					}
				} catch (ParseException e) {
//					view.setError("出生日期非法！");
					flag = false;
				}
			} else {
//				view.setError("身份证号位数不正确，请确认！");
				flag = false;
			}
		} else {
			flag = false;
		}
		if (!flag) {
//			view.requestFocus();
		}
		return flag;
	}

	public static String replaceIDCard(String cardNo){
		if (cardNo.length() == 15) {
			cardNo = cardNo.replace(cardNo.substring(4,12),"********");
		} else if (cardNo.length() == 18) {
			cardNo = cardNo.replace(cardNo.substring(6, 14),"********");
		}
		return cardNo;
	}
}
