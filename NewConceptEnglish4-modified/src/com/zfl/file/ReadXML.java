package com.zfl.file;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.parsers.*;

import org.w3c.dom.*;
import org.xml.sax.*;

public class ReadXML
{
	public  static ArrayList<String> nameList = new ArrayList<>();
	public  static ArrayList<String> contentList = new ArrayList<>();
	public  static ArrayList<String> wordsList = new ArrayList<>();
	
	public void readXml(InputStream is)
	{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try
		{
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(is);

			NodeList lessonList = doc.getElementsByTagName("LESSON");
			System.out.println("¹²ÓÐ" + lessonList.getLength() + "¸ölesson");
			for (int i = 0; i < lessonList.getLength(); i++)
			{
				Node lesson = lessonList.item(i);
				Element elem = (Element) lesson;
				//System.out.println("id:" + elem.getAttribute("id"));
				for (Node node = lesson.getFirstChild(); node != null; node = node.getNextSibling())
				{					
					if (node.getNodeType() == Node.ELEMENT_NODE)
					{
						String name = node.getNodeName();
						String value = node.getFirstChild().getNodeValue();
						if (name.equals("NAME")) {
							nameList.add(value);
						}else if (name.equals("CONTENT")) {
							contentList.add(value);
						}else if (name.equals("WORDS")) {
							wordsList.add(value);
						}										
					}					
				}
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
