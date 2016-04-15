package com.zfl.nce;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.zfl.R;
import com.zfl.file.ReadXML;
import com.zfl.file.WordsFileRead;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.ScrollingMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ContentActivity extends Activity {

	StringBuffer sb0 = new StringBuffer();
	StringBuffer sb1 = new StringBuffer();
	StringBuffer sb2 = new StringBuffer();
	StringBuffer sb3 = new StringBuffer();
	StringBuffer sb4 = new StringBuffer();
	StringBuffer sb5 = new StringBuffer();
	int position = 0;
	TextView tv;
	SpannableString textString;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contentlayout);

		Bundle bundle = this.getIntent().getExtras();
		position = bundle.getInt("position");
		setTitle(ReadXML.nameList.get(position));
		tv = (TextView) findViewById(R.id.contentView);
		Button b0 = (Button) findViewById(R.id.button0);
		Button b1 = (Button) findViewById(R.id.button1);
		Button b2 = (Button) findViewById(R.id.button2);
		Button b3 = (Button) findViewById(R.id.button3);
		Button b4 = (Button) findViewById(R.id.button4);
		Button b5 = (Button) findViewById(R.id.button5);

		b0.setOnClickListener(new ButtonListener());
		b1.setOnClickListener(new ButtonListener());
		b2.setOnClickListener(new ButtonListener());
		b3.setOnClickListener(new ButtonListener());
		b4.setOnClickListener(new ButtonListener());
		b5.setOnClickListener(new ButtonListener());
		tv.setText(ReadXML.contentList.get(position));
		tv.setMovementMethod(ScrollingMovementMethod.getInstance());
		// 高亮顯示生詞
		String[] wordStrings = ReadXML.wordsList.get(position).split(";");

		for (String string : wordStrings) {
			if (WordsFileRead.wordmap.containsKey(string)) {
				int level = WordsFileRead.wordmap.get(string);// 獲得單詞的等級
				switch (level) {
				case 0:
					sb0.append(string + ";");
					break;
				case 1:
					sb1.append(string + ";");
					break;
				case 2:
					sb2.append(string + ";");
					break;
				case 3:
					sb3.append(string + ";");
					break;
				case 4:
					sb4.append(string + ";");
					break;
				case 5:
					sb5.append(string + ";");
					break;

				default:
					break;
				}
			}
		}
		sb4.append(sb5);
		sb3.append(sb4);
		sb2.append(sb3);
		sb1.append(sb2);
		sb0.append(sb1);
		
	}

	/**
	 * 多个关键字高亮变色
	 * 
	 * @param color
	 *            变化的色值
	 * @param text
	 *            文字
	 * @param keyword
	 *            文字中的关键字数组
	 * @return
	 */
	public static SpannableString highlight(String text, String[] keyword) {
		SpannableString s = new SpannableString(text);

		if (keyword.length == 0) {
			return s;
		}

		for (int i = 0; i < keyword.length; i++) {
			Pattern p = Pattern.compile(keyword[i]);
			Matcher m = p.matcher(s);
			while (m.find()) {
				int start = m.start();
				int end = m.end();
				s.setSpan(new ForegroundColorSpan(Color.RED), start, end,
						Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
			}
		}
		return s;
	}

	
	/**
	 * 监听按钮事件，选择要高亮的单词的等级
	 * 
	 * @author seu
	 * 
	 */
	private class ButtonListener implements OnClickListener {
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.button0:
				textString = textString = highlight(
						ReadXML.contentList.get(position), sb0.toString()
								.split(";"));
				tv.setText(textString);
				break;
			case R.id.button1:
				textString = textString = highlight(
						ReadXML.contentList.get(position), sb1.toString()
								.split(";"));
				tv.setText(textString);
				break;
			case R.id.button2:
				textString = textString = highlight(
						ReadXML.contentList.get(position), sb2.toString()
								.split(";"));
				tv.setText(textString);
				break;
			case R.id.button3:
				textString = textString = highlight(
						ReadXML.contentList.get(position), sb3.toString()
								.split(";"));
				tv.setText(textString);
				break;
			case R.id.button4:
				textString = textString = highlight(
						ReadXML.contentList.get(position), sb4.toString()
								.split(";"));
				tv.setText(textString);
				break;
			case R.id.button5:
				textString = textString = highlight(
						ReadXML.contentList.get(position), sb5.toString()
								.split(";"));
				tv.setText(textString);
				break;

			default:
				break;
			}
		}

	}

}
