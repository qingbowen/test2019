package com.zowoyoo.kintetsu.tools;

import android.app.ProgressDialog;
import android.os.Environment;
import android.util.DisplayMetrics;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 *
 * @author fada
 *
 */
public class AppInfoUtils {
	public static final String SDCARD_SOFT_STORE = Environment.getExternalStorageDirectory()+"/htjxsdk/";
	public static final File SDCARD = Environment.getExternalStorageDirectory();
	private static ProgressDialog mProgress = null;

	private static DisplayMetrics displayMetrics = new DisplayMetrics();
	private static final char HEX_DIGITS[] = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	public static String toHexString(byte[] b) {
		// String to byte
		StringBuilder sb = new StringBuilder(b.length * 2);
		for (int i = 0; i < b.length; i++) {
			sb.append(HEX_DIGITS[(b[i] & 0xf0) >>> 4]);
			sb.append(HEX_DIGITS[b[i] & 0x0f]);
		}
		return sb.toString();
	}

	/**
	 * 生成Md5
	 *
	 * @param s 字符串
	 * @return 字符串的MD5值
	 */
	public static String md5(String s) {
		try {
			// Create MD5 Hash
			MessageDigest digest = MessageDigest
					.getInstance("MD5");
			digest.update(s.getBytes());
			byte messageDigest[] = digest.digest();

			return toHexString(messageDigest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return "";
	}
	/**
	 * Json字符串转化成JSONObject
	 * @param str
	 * @return JSONObject对象
	 * @throws Exception
	 */
	public static JSONObject stringToJSONObject(String str) throws Exception {
		try {
			return new JSONObject(str);
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 方法描述：获取MAC地址
	 * @param context 上下文
	 * @return 获取MAC地址
	 */
//	public static String getMacAddress(Context context) {
//		SharedPreferences sp = context.getSharedPreferences("config",
//				Context.MODE_PRIVATE);
//		String macAddr = sp.getString("mac", "");
//		if (macAddr == "") {
//			WifiManager wifi = (WifiManager) context.getSystemService("wifi");
//			try {
//				WifiInfo wifiInfo = wifi.getConnectionInfo();
//				macAddr = wifiInfo.getMacAddress();
//				if (macAddr != null && macAddr != "") {
//					Editor edit = sp.edit();
//					edit.putString("mac", macAddr);
//					edit.commit();
//				} else {
//					return "";
//				}
//
//			} catch (Exception e) {
//				e.printStackTrace();
//				LogUtils.d("android.permission.ACCESS_WIFI_STATE should be add to AndroidManifest.xml!");
//			}
//		}
//
//		return macAddr;
//	}

	/**
	 * 方法描述：获取IMEI
	 */
//	public static String getIMEI(Context context) {
//		SharedPreferences sp = context.getSharedPreferences("config",
//				Context.MODE_PRIVATE);
//		String imei = sp.getString("imei", "");
//		if (imei == "") {
//			TelephonyManager tm = (TelephonyManager) context
//					.getSystemService(Context.TELEPHONY_SERVICE);
//			imei = tm.getDeviceId();
//			if (imei != null && imei != "") {
//				Editor edit = sp.edit();
//				edit.putString("imei", imei);
//				edit.commit();
//			} else {
//				return "";
//			}
//		}
//
//		return imei;
//	}

	/**
	 * 获取设备ID
	 * @param context
	 * @return
     */
//	public static String getDeviceId(Context context){
//		TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//		String deviceId = tm.getDeviceId();
//
//		if(deviceId == null){
//			deviceId = "";
//		}
//		return deviceId;
//	}

}
