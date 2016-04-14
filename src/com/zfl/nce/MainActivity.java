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
		//�Ƚ�txtת��xml�ļ���
		String txtname = "txtdata.txt";
		String xmlname = "xmldata.xml";
		File file = new File(Environment.getExternalStorageDirectory(),txtname);
    	File file2 = new File(Environment.getExternalStorageDirectory(),xmlname);
		ToXML toXML = new ToXML();
		toXML.doit(file,file2);
		
		//����xml��������ȡLESSON��CONTENT������ǩ����ʾ��listview�С�
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
		//****************************************final����
//ע��ԭ��getView�����е�int position�����Ƿ�final�ģ����ڸ�Ϊfinal
		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			 ViewHolder holder = null;
			if (convertView == null) {
				
				holder=new ViewHolder();  
				
				//�������Ϊ��vlist��ȡview  ֮���view���ظ�ListView
				
				convertView = mInflater.inflate(R.layout.vlist, null);
				holder.title = (TextView)convertView.findViewById(R.id.title);
				holder.viewBtn = (Button)convertView.findViewById(R.id.view_btn);
				convertView.setTag(holder);				
			}else {				
				holder = (ViewHolder)convertView.getTag();
			}		
			
			holder.title.setText((String)ReadXML.nameList.get(position));
			holder.viewBtn.setTag(position);
			//��Button��ӵ����¼�  ���Button֮��ListView��ʧȥ����  ��Ҫ��ֱ�Ӱ�Button�Ľ���ȥ��
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
		//****************************************�ڶ��ַ���������һ�㶼�ô��ַ���,����ԭ���һ������,�д��о�
	
//		public View getView(int position, View convertView, ViewGroup parent) {
//			 ViewHolder holder = null;
//			 MyListener myListener=null;
//			if (convertView == null) {
//				
//				holder=new ViewHolder();  
//				
//				//�������Ϊ��vlist��ȡview  ֮���view���ظ�ListView
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
//			//��Button��ӵ����¼�  ���Button֮��ListView��ʧȥ����  ��Ҫ��ֱ�Ӱ�Button�Ľ���ȥ��
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

	//��ȡ���������
	public final class ViewHolder {
		public TextView title;
		public Button viewBtn;
	}	
	
}