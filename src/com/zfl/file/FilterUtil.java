package com.zfl.file;


/**
 * 過濾字符串
 * @author seu
 * （1）过滤出字母的正则表达式 [^(A-Za-z)] 
 * （2）过滤出数字的正则表达式 [^(0-9)] 
 * （3）过滤出中文的正则表达式 [^(\\u4e00-\\u9fa5) 
 * （4）过滤出字母、数字和中文的正则表达式[^(a-zA-Z0-9\\u4e00-\\u9fa5)]
 */
public final class FilterUtil {

	
	/**
	 * 过滤出字母和空格
	 * 
	 * @param alph
	 * @return 
	 */
	public static String filterAlphabet(String alph) {
		alph = alph.replaceAll("[^(A-Za-z)]", "");
		return alph;
	}
	
	/**
	 * 过滤出字母和空格
	 * 
	 * @param alph
	 * @return 
	 */
	public static String filterAlphabetAndSpace(String alph) {
		alph = alph.replaceAll("[^(A-Za-z)\\ \\.\\?]", "");
		return alph;
	}

	/**
	 * 過濾中文字符
	 * @param chin
	 * @return
	 */
	public static String filterChinese(String chin) {
		chin = chin.replaceAll("[^(\\u4e00-\\u9fa5)]", "");
		return chin;
	}
	
	/**
	 * 過濾中文字符
	 * @param chin
	 * @return
	 */
	public static String filterNumber(String chin) {
		chin = chin.replaceAll("[^(0-9)]", "");
		return chin;
	}
}
