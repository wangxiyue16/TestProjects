package com.example.handlerimageortext;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class DownLoadImage {
	private String image_path;

	public DownLoadImage(String image_path) {
		// 保存图片的下载地址
		this.image_path = image_path;
	}

	public void loadImage(final ImageCallback callback) {
		final Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				// 接受到消息后，调用接口回调的方法
				callback.getDrawable((Drawable) msg.obj);
			}
		};
		// 开启一个新线程用于访问图片数据
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					// 下载图片为Drawable对象
					Drawable drawable = Drawable.createFromStream(new URL(
							image_path).openStream(), "");
					// 把图片对象包装成一个消息发送给Handler
					Message message = Message.obtain();
					message.what = 1;
					message.obj = drawable;
					handler.sendMessage(message);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	// 定义一个公开的接口，用于执行回调操作
	public interface ImageCallback {
		public void getDrawable(Drawable draw);
	}
}
