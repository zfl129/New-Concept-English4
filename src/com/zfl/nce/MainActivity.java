package com.zfl.nce;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.zfl.nce.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	/** Called when the activity is first created. */
	ReadXML readXML = new ReadXML();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		//先将txt转成xml文件。
		String txtname = "txtdata.txt";
		String xmlname = "xmldata.xml";
		File file = new File(Environment.getExternalStorageDirectory(),txtname);
    	File file2 = new File(Environment.getExternalStorageDirectory(),xmlname);
		ToXML toXML = new ToXML();
		toXML.doit(file,file2);
		
		//解析xml，从中提取LESSON和CONTENT两个标签，显示在listview中。
		try {
			InputStream is = getAssets().open("xmldata.xml");
			readXML.readXml(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ListView listView = (ListView) findViewById(R.id.listView);
		MyAdapter adapter = new MyAdapter(this);
		listView.setAdapter(adapter);
		
		
	}

	public class MyAdapter extends BaseAdapter {

		private LayoutInflater mInflater;

		public MyAdapter(Context context) {
			this.mInflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return ReadXML.nameList.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}
		//****************************************final方法
//注意原本getView方法中的int position变量是非final的，现在改为final
		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			 ViewHolder holder = null;
			if (convertView == null) {
				
				holder=new ViewHolder();  
				
				//可以理解为从vlist获取view  之后把view返回给ListView
				
				convertView = mInflater.inflate(R.layout.vlist, null);
				holder.title = (TextView)convertView.findViewById(R.id.title);
				holder.viewBtn = (Button)convertView.findViewById(R.id.view_btn);
				convertView.setTag(holder);				
			}else {				
				holder = (ViewHolder)convertView.getTag();
			}		
			
			holder.title.setText((String)ReadXML.nameList.get(position));
			holder.viewBtn.setTag(position);
			//给Button添加单击事件  添加Button之后ListView将失去焦点  需要的直接把Button的焦点去掉
			holder.viewBtn.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent intent = new Intent();
					intent.setClass(MainActivity.this, ContentActivity.class);
					Bundle bundle = new Bundle();
					bundle.putInt("position", position);
					intent.putExtras(bundle);
					//intent.putExtra("position", position);
					//intent.putExtra("content", readXML.contentList.get(position));
					startActivity(intent);
					//showInfo(position);					
				}
			});
			
			//holder.viewBtn.setOnClickListener(MyListener(position));
					
			return convertView;
		}
	}
		//****************************************第二种方法，高手一般都用此种方法,具体原因，我还不清楚,有待研究
	
//		public View getView(int position, View convertView, ViewGroup parent) {
//			 ViewHolder holder = null;
//			 MyListener myListener=null;
//			if (convertView == null) {
//				
//				holder=new ViewHolder();  
//				
//				//可以理解为从vlist获取view  之后把view返回给ListView
//				 myListener=new MyListener(position);
//		           
//				convertView = mInflater.inflate(R.layout.vlist, null);
//				holder.title = (TextView)convertView.findViewById(R.id.title);
//				holder.info = (TextView)convertView.findViewById(R.id.info);
//				holder.viewBtn = (Button)convertView.findViewById(R.id.view_btn);
//				convertView.setTag(holder);				
//			}else {				
//				holder = (ViewHolder)convertView.getTag();
//			}		
//			
//			holder.title.setText((String)mData.get(position).get("title"));
//			holder.info.setText((String)mData.get(position).get("info"));
//			holder.viewBtn.setTag(position);
//			//给Button添加单击事件  添加Button之后ListView将失去焦点  需要的直接把Button的焦点去掉
//			holder.viewBtn.setOnClickListener( myListener);
//			
//			//holder.viewBtn.setOnClickListener(MyListener(position));
//					
//			return convertView;
//		}
//	}
//	
//	 private class MyListener implements OnClickListener{
//	        int mPosition;
//	        public MyListener(int inPosition){
//	            mPosition= inPosition;
//	        }
//	        @Override
//	        public void onClick(View v) {
//	            // TODO Auto-generated method stub
//	            Toast.makeText(ListViewActivity.this, title[mPosition], Toast.LENGTH_SHORT).show();
//	        }
//	        
//	    }
//
//	

	//提取出来方便点
	public final class ViewHolder {
		public TextView title;
		public Button viewBtn;
	}	
	
}