package com.zfl.file;


/**
 * �^�V�ַ���
 * @author seu
 * ��1�����˳���ĸ��������ʽ [^(A-Za-z)] 
 * ��2�����˳����ֵ�������ʽ [^(0-9)] 
 * ��3�����˳����ĵ�������ʽ [^(\\u4e00-\\u9fa5) 
 * ��4�����˳���ĸ�����ֺ����ĵ�������ʽ[^(a-zA-Z0-9\\u4e00-\\u9fa5)]
 */
public final class FilterUtil {

	
	/**
	 * ���˳���ĸ�Ϳո�
	 * 
	 * @param alph
	 * @return 
	 */
	public static String filterAlphabet(String alph) {
		alph = alph.replaceAll("[^(A-Za-z)]", "");
		return alph;
	}
	
	/**
	 * ���˳���ĸ�Ϳո�
	 * 
	 * @param alph
	 * @return 
	 */
	public static String filterAlphabetAndSpace(String alph) {
		alph = alph.replaceAll("[^(A-Za-z)\\ \\.\\?]", "");
		return alph;
	}

	/**
	 * �^�V�����ַ�
	 * @param chin
	 * @return
	 */
	public static String filterChinese(String chin) {
		chin = chin.replaceAll("[^(\\u4e00-\\u9fa5)]", "");
		return chin;
	}
	
	/**
	 * �^�V�����ַ�
	 * @param chin
	 * @return
	 */
	public static String filterNumber(String chin) {
		chin = chin.replaceAll("[^(0-9)]", "");
		return chin;
	}
}
