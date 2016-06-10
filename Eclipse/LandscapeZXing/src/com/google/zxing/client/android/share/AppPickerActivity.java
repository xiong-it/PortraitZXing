/*
 * Copyright (C) 2009 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.zxing.client.android.share;

import android.annotation.TargetApi;
import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.view.View;
import android.widget.Adapter;
import android.widget.ListView;

import java.util.List;

import com.google.zxing.client.android.wifi.WifiConfigManager;

/**
 * Activity for picking an installed application to share via Intent.
 */
public final class AppPickerActivity extends ListActivity {

	private AsyncTask<Object, Object, List<AppInfo>> backgroundTask;

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	protected void onResume() {
		super.onResume();
		backgroundTask = new LoadPackagesAsyncTask(this);
		/**
		 * @author MichaelX(xiong_it)modified.{@link https://github.com/xiong-it}
		 * 
		 * {@link http://stackoverflow.com/questions/12227682/android-2-3-3-asynctask-call-throws-nosuchfielderror}
		 * THREAD_POOL_EXECUTOR were added in API Level 11
		 */
//		backgroundTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			backgroundTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
		} else {
			backgroundTask.execute();
		}
	}

	@Override
	protected void onPause() {
		AsyncTask<?, ?, ?> task = backgroundTask;
		if (task != null) {
			task.cancel(true);
			backgroundTask = null;
		}
		super.onPause();
	}

	@Override
	protected void onListItemClick(ListView l, View view, int position, long id) {
		Adapter adapter = getListAdapter();
		if (position >= 0 && position < adapter.getCount()) {
			String packageName = ((AppInfo) adapter.getItem(position)).getPackageName();
			Intent intent = new Intent();
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
			intent.putExtra("url", "market://details?id=" + packageName); // Browser.BookmarkColumns.URL
			setResult(RESULT_OK, intent);
		} else {
			setResult(RESULT_CANCELED);
		}
		finish();
	}

}
