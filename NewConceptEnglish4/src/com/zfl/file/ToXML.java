package com.zfl.file;

import java.io.*;
import java.security.PublicKey;

// SAX classes.
import org.xml.sax.*;
import org.xml.sax.helpers.*;

import android.annotation.SuppressLint;
import android.os.Environment;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.sax.*;

public class ToXML {

	BufferedReader in;
	StreamResult out;
	StringBuffer sb, sb2;

	TransformerHandler th;
	AttributesImpl atts;

	@SuppressLint("NewApi")
	public void doit(File txtfile, File xmlfile) {
		try {
			in = new BufferedReader(new FileReader(txtfile));
			out = new StreamResult(xmlfile);
			sb = new StringBuffer();
			sb2 = new StringBuffer();
			initXML();
			String str;
			String lesson;
			while ((str = in.readLine()) != null) {
				while (str.trim().startsWith("Lesson")) {
					lesson = str;
					lesson = str.trim() + "     "
							+ FilterUtil.filterAlphabetAndSpace(in.readLine().trim());// 获得lesson的名字。有的有中文，有的没有。
					while (!(str = in.readLine().trim())
							.startsWith("First listen and")) {
					}
					str = in.readLine();// 跳过多余的两行
					str = in.readLine();
					while (!(str = in.readLine().trim())
							.startsWith("New words and expression")) {
						sb.append(FilterUtil.filterAlphabet(str));
					}
					while (!(str = in.readLine().trim()).startsWith("参考译文")) {
						if (FilterUtil.filterChinese(str).equals("")
								|| FilterUtil.filterChinese(str).equals("()")) {// 不含中文，說明是單詞而不是翻譯
							if (str.contains("(")) {
								str = str.substring(0, str.indexOf("(") - 1);
							}
							sb2.append(str + ";");
						}
					}
					sb2.delete(sb2.length() - 2, sb2.length());
					int index = 0;
					while (index < sb.length()) {
						index = sb.indexOf(".", index);
						sb.insert(index+1, "\r\n");					
					}
					
					process(lesson, sb.toString(), sb2.toString());
					sb.delete(0, sb.length());
					sb2.delete(0, sb2.length());
				}
			}
			in.close();
			closeXML();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressLint("NewApi")
	public void initXML() throws ParserConfigurationException,
			TransformerConfigurationException, SAXException {
		// JAXP + SAX
		SAXTransformerFactory tf = (SAXTransformerFactory) SAXTransformerFactory
				.newInstance();

		th = tf.newTransformerHandler();
		Transformer serializer = th.getTransformer();
		serializer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
		// pretty XML output
		serializer.setOutputProperty(
				"{http://xml.apache.org/xslt}indent-amount", "4");
		serializer.setOutputProperty(OutputKeys.INDENT, "yes");
		th.setResult(out);
		th.startDocument();
		atts = new AttributesImpl();
		th.startElement("", "", "ENGLISH", atts);
	}

	@SuppressLint("NewApi")
	public void process(String s, String sb, String sb2) throws SAXException {
		// String [] elements = s.split("\\|");
		atts.clear();
		th.startElement("", "", "LESSON", atts);

		th.startElement("", "", "NAME", atts);
		th.characters(s.toCharArray(), 0, s.length());
		// th.characters(elements[0].toCharArray(),0,elements[0].length());
		th.endElement("", "", "NAME");

		th.startElement("", "", "CONTENT", atts);
		th.characters(sb.toCharArray(), 0, sb.length());
		// th.characters(elements[1].toCharArray(),0,elements[1].length());
		th.endElement("", "", "CONTENT");
		th.startElement("", "", "WORDS", atts);
		th.characters(sb2.toCharArray(), 0, sb2.length());
		// th.characters(elements[1].toCharArray(),0,elements[1].length());
		th.endElement("", "", "WORDS");
		th.endElement("", "", "LESSON");
	}

	@SuppressLint("NewApi")
	public void closeXML() throws SAXException {
		th.endElement("", "", "ENGLISH");
		th.endDocument();
	}

}