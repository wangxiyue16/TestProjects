package com.example.handlerimageortext;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.handlerimageortext.DownLoadImage.ImageCallback;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity {
	private ListView listview;
	private ProgressDialog dialog;
	private MyAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listview=(ListView)findViewById(R.id.listView1);
		dialog=new ProgressDialog(this);
		dialog.setTitle("提示");
		dialog.setMessage("正在下载，请稍后...");
		adapter=new MyAdapter(this	);

		new MyTask().execute(CommonUri.PRODUCT_URL);
	}

	public class MyTask extends AsyncTask<String, Void, List<Map<String,Object>>>{
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// 显示对话框
			dialog.show();
		}

		@Override
		protected List<Map<String, Object>> doInBackground(String... params) {
			List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
			try {
				// 获取网络JSON格式数据
				HttpClient httpClient=new DefaultHttpClient();
				HttpPost httpPost=new HttpPost(params[0]);
				HttpResponse httpResponse=httpClient.execute(httpPost);
				if(httpResponse.getStatusLine().getStatusCode()==200){
					String jsonString=EntityUtils.toString(httpResponse.getEntity(),"utf-8");
					// 解析Json格式数据，并使用一个List<Map>存放
					JSONArray jsonArray=new JSONArray(jsonString);
					for(int i=0;i<jsonArray.length();i++){
						JSONObject jsonObject=jsonArray.getJSONObject(i);
						Map<String,Object> map=new HashMap<String, Object>();
						map.put("name",jsonObject.get("name"));
						map.put("price",jsonObject.get("price"));
						map.put("imageName",jsonObject.get("imageName"));
						list.add(map);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		}
		@Override
		protected void onPostExecute(List<Map<String, Object>> result) {
			super.onPostExecute(result);
			// 把查询到的数据传递给适配器
			adapter.setData(result);
			// 为ListView设定适配器
			listview.setAdapter(adapter);
			adapter.notifyDataSetChanged();
			// 隐藏对话框
			dialog.dismiss();
		}
	}

	public class MyAdapter extends BaseAdapter{
		private Context context;
		private LayoutInflater layoutInflater;
		private List<Map<String,Object>> list=null;
		public MyAdapter(Context context){
			this.context=context;
			layoutInflater=LayoutInflater.from(context);
		}

		public void setData(List<Map<String,Object>> list){
			this.list=list;
		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view=null;
			if(convertView==null){
				view=layoutInflater.inflate(R.layout.item,null);
			}else{
				view=convertView;
			}
			TextView name=(TextView)view.findViewById(R.id.textView1);
			TextView price=(TextView)view.findViewById(R.id.textView2);
			final ImageView imageview=(ImageView)view.findViewById(R.id.imageView1);
			name.setText(list.get(position).get("name").toString());
			price.setText(list.get(position).get("price").toString());

			DownLoadImage downLoadImage=new DownLoadImage(CommonUri.PRODUCT_IMG+list.get(position).get("imageName").toString());
			downLoadImage.loadImage(new ImageCallback() {
				@Override
				public void getDrawable(Drawable draw) {
					imageview.setImageDrawable(draw);
				}
			});
			return view;
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
