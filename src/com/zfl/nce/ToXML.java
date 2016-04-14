package com.zfl.nce;
import java.io.*;

// SAX classes.
import org.xml.sax.*;
import org.xml.sax.helpers.*;

import android.os.Environment;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.sax.*;

public class ToXML  {

  BufferedReader in;
  StreamResult out;
  StringBuffer sb;

  TransformerHandler th;
  AttributesImpl atts;


  public void doit (File txtfile, File xmlfile) {
    try{ 	
      in = new BufferedReader(new FileReader(txtfile));
      out = new StreamResult(xmlfile);
      sb = new StringBuffer();
      initXML();
      String str;
      String lesson;
      while ((str = in.readLine()) != null) {
    	  while ( str.trim().startsWith("Lesson")){ 
    		  lesson = str;
        	  lesson = str.trim() + "     "+in.readLine().trim();//获得lesson的名字。有的有中文，有的没有。
        	  while (!(str = in.readLine().trim()).startsWith("First listen and")) {}
		      str = in.readLine();//跳过多余的两行
		      str = in.readLine();
		      while (!(str = in.readLine().trim()).startsWith("New words and expression")) {
		         sb.append(str);			
		      }	
		      process(lesson,sb.toString());
		      sb.delete(0, sb.length());
    		}
    	  }
       
//      while ((str = in.readLine()) != null && ((str=in.readLine()).equals("Lesson"))) {
//         process(str);
//      }
      in.close();
      closeXML();
      
    }
    catch (Exception e) { e.printStackTrace(); }
  }


  public void initXML() throws ParserConfigurationException,
      TransformerConfigurationException, SAXException {
    // JAXP + SAX
    SAXTransformerFactory tf = 
       (SAXTransformerFactory) SAXTransformerFactory.newInstance();

    th = tf.newTransformerHandler();
    Transformer serializer = th.getTransformer();
    serializer.setOutputProperty(OutputKeys.ENCODING,"ISO-8859-1");
    // pretty XML output
    serializer.setOutputProperty
        ("{http://xml.apache.org/xslt}indent-amount", "4");
    serializer.setOutputProperty(OutputKeys.INDENT,"yes");
    th.setResult(out);
    th.startDocument();
    atts = new AttributesImpl();
    th.startElement("","","ENGLISH",atts);
  }

  public void process (String s,String sb) throws SAXException {
    //String [] elements = s.split("\\|");
    atts.clear();
    th.startElement("","","LESSON",atts);

    th.startElement("","","NAME",atts);
    th.characters(s.toCharArray(), 0, s.length());
    //th.characters(elements[0].toCharArray(),0,elements[0].length());
    th.endElement("","","NAME");

    th.startElement("","","CONTENT",atts);
    th.characters(sb.toCharArray(), 0, sb.length());
    //th.characters(elements[1].toCharArray(),0,elements[1].length());
    th.endElement("","","CONTENT");

    th.endElement("","","LESSON");
  }

  public void closeXML() throws SAXException {
    th.endElement("","","ENGLISH");
    th.endDocument();  }
}