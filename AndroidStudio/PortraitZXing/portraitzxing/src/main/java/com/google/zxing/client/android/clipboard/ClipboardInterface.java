/*
 * Copyright (C) 2012 ZXing authors
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

package com.google.zxing.client.android.clipboard;

import android.annotation.TargetApi;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;

/**
 * Abstraction over the {@link ClipboardManager} API that manages copying and pasting.
 *
 * @author MichaelX(xiong_it) added.{@link https://github.com/xiong-it}
 * use android.text.ClipboardManager to backward compatible. 
 */
public final class ClipboardInterface {

	private static final String TAG = ClipboardInterface.class.getSimpleName();

	private ClipboardInterface() {
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public static CharSequence getText(Context context) {
		if (under11()) {
			return getManagerUnder11(context).getText();
		} else {
			ClipboardManager clipboard = getManager(context);
			ClipData clip = clipboard.getPrimaryClip();
			return hasText(context) ? clip.getItemAt(0).coerceToText(context) : null;
		}

	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public static void setText(CharSequence text, Context context) {
		if (text != null) {
			try {
				if (under11()) {
					getManagerUnder11(context).setText(text);
				} else {
					getManager(context).setPrimaryClip(ClipData.newPlainText(null, text));
				}
			} catch (NullPointerException | IllegalStateException e) {
				// Have seen this in the wild, bizarrely
				Log.w(TAG, "Clipboard bug", e);
			}
		}
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public static boolean hasText(Context context) {
		ClipData clip = null;
		if (under11()) {
			android.text.ClipboardManager clipboard = getManagerUnder11(context);
			return clipboard.hasText();
		} else {
			ClipboardManager clipboard = getManager(context);
			clip = clipboard.getPrimaryClip();
			return clip != null && clip.getItemCount() > 0;
		}
	}

	private static ClipboardManager getManager(Context context) {
		return (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
	}

	/**
	 * @author MichaelX(xiong_it) added.{@link https://github.com/xiong-it}
	 * 
	 * use android.text.ClipboarManager to backward compatible under API 11
	 */
	private static android.text.ClipboardManager getManagerUnder11(Context context) {
		return (android.text.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
	}

	/**
	 * @author MichaelX(xiong_it) added.{@link https://github.com/xiong-it}
	 * 
	 * @return current apiLevel is under11?
	 */
	private static boolean under11() {
		return Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB;
	}

}
