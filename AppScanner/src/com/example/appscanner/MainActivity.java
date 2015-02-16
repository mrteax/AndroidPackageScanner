package com.example.appscanner;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class MainActivity extends Activity {
	LinearLayout container;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		container = (LinearLayout) this.findViewById(R.id.list_container);
		setupView();
	}
	
	@SuppressWarnings("deprecation")
	private void setupView() {
		List<PackageInfo> list = getAllApps(this);
		PackageManager pm = this.getPackageManager();
		PackageInfo info;
		LayoutParams params = new LinearLayout.LayoutParams(150, 150);
		for (int i = 0; i < list.size(); i++) {
			info = list.get(i);
			LinearLayout item = new LinearLayout(this);
			item.setOrientation(LinearLayout.HORIZONTAL);
			ImageView icon = new ImageView(this);
			icon.setLayoutParams(params);
			icon.setBackgroundDrawable(info.applicationInfo.loadIcon(pm));
			TextView title = new TextView(this);
			title.setText(info.applicationInfo.loadLabel(pm) + "\n" + info.packageName);
			item.addView(icon);
			item.addView(title);
			container.addView(item);
		}
	}
	public static List<PackageInfo> getAllApps(Context context) {
		List<PackageInfo> apps = new ArrayList<PackageInfo>();
		PackageManager pManager = context.getPackageManager();
		//获取手机内所有应用
		List<PackageInfo> paklist = pManager.getInstalledPackages(0);
//		for (int i = 0; i < paklist.size(); i++) {
//			PackageInfo pak = (PackageInfo) paklist.get(i);
//			//判断是否为非系统预装的应用程序
//			if ((pak.applicationInfo.flags & pak.applicationInfo.FLAG_SYSTEM) <= 0) {
//				// customs applications
//				apps.add(pak);
//			}
//		}
		apps = paklist;
		return apps;
	}
	
}
