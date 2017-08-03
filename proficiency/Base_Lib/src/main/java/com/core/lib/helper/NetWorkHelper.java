package com.core.lib.helper;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

import com.core.lib.base.BaseApplication;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * 
 * function: 网络助手类
 * 
 * @author:linhuan
 */
public class NetWorkHelper {

	private static final String TAG = NetWorkHelper.class.getSimpleName();
	/**
	 * KEY:网络传输用,user-agent1
	 */
	public static final String NETWORK_KEY_USER_AGENT1 = "User-Agent1";

	public static final short TYPE_IP_V4 = 4;
	public static final short TYPE_IP_V6 = 6;
	
	/**
	 * 检查网络连接是否可用
	 * 
	 * @return
	 */
	public static boolean isNetworkAvailable() {
		ConnectivityManager cm = (ConnectivityManager) BaseApplication.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
		if (Helper.isNull(cm)) {
			return false;
		}
		NetworkInfo[] netinfo = cm.getAllNetworkInfo();
		if (Helper.isNull(netinfo)) {
			return false;
		}
		int size = netinfo.length;
		for (int i = 0; i < size; i++) {
			if (netinfo[i].isConnected()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取当前ip
	 * @param ipType ipv4或者ipv6，请使用{@link #TYPE_IP_V4}或者{@link #TYPE_IP_V6}
	 * @return 当前ip
	 */
	public static String getLocalIpAddress(short ipType) {
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
					en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses();
						enumIpAddr.hasMoreElements();) {
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress()) {
						switch (ipType) {
						
						case TYPE_IP_V4:
							if (inetAddress instanceof Inet4Address) {
								return inetAddress.getHostAddress().toString();
							}
							break;
							
						case TYPE_IP_V6:
							if (inetAddress instanceof Inet6Address) {
								return inetAddress.getHostAddress().toString();
							}
							break;

						default:
							break;
							
						}
					}
				}
			}
		} catch (SocketException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * 判断WIFI网络是否可用
	 *
	 * @param context
	 * @return
	 */
	public static boolean isWifiConnected(Context context) {
		if (context != null) {
			// 获取手机所有连接管理对象(包括对wi-fi,net等连接的管理)
			ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			// 获取NetworkInfo对象
			NetworkInfo networkInfo = manager.getActiveNetworkInfo();
			//判断NetworkInfo对象是否为空 并且类型是否为WIFI
			if (networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
				return networkInfo.isAvailable();
			}
		}
		return false;
	}

	/**
	 * 判断MOBILE网络是否可用
	 *
	 * @param context
	 * @return
	 */
	public static boolean isMobileConnected(Context context) {
		if (context != null) {
			//获取手机所有连接管理对象(包括对wi-fi,net等连接的管理)
			ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			//获取NetworkInfo对象
			NetworkInfo networkInfo = manager.getActiveNetworkInfo();
			//判断NetworkInfo对象是否为空 并且类型是否为MOBILE
			if (networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_MOBILE)
				return networkInfo.isAvailable();
		}
		return false;
	}

	/**
	 * 获取当前网络连接的类型信息
	 * 原生
	 *
	 * @param context
	 * @return
	 */
	public static int getConnectedType(Context context) {
		if (context != null) {
			//获取手机所有连接管理对象
			ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			//获取NetworkInfo对象
			NetworkInfo networkInfo = manager.getActiveNetworkInfo();
			if (networkInfo != null && networkInfo.isAvailable()) {
				//返回NetworkInfo的类型
				return networkInfo.getType();
			}
		}
		return -1;
	}

	/**
	 * 获取当前的网络状态 ：没有网络-0：WIFI网络1：4G网络-4：3G网络-3：2G网络-2
	 * 自定义
	 *
	 * @param context
	 * @return
	 */
	public static int getAPNType(Context context) {
		//结果返回值
		int netType = 0;
		//获取手机所有连接管理对象
		ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		//获取NetworkInfo对象
		NetworkInfo networkInfo = manager.getActiveNetworkInfo();
		//NetworkInfo对象为空 则代表没有网络
		if (networkInfo == null) {
			return netType;
		}
		//否则 NetworkInfo对象不为空 则获取该networkInfo的类型
		int nType = networkInfo.getType();
		if (nType == ConnectivityManager.TYPE_WIFI) {
			//WIFI
			netType = 1;
		} else if (nType == ConnectivityManager.TYPE_MOBILE) {
			int nSubType = networkInfo.getSubtype();
			TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
			//3G   联通的3G为UMTS或HSDPA 电信的3G为EVDO
			if (nSubType == TelephonyManager.NETWORK_TYPE_LTE
					&& !telephonyManager.isNetworkRoaming()) {
				netType = 4;
			} else if (nSubType == TelephonyManager.NETWORK_TYPE_UMTS
					|| nSubType == TelephonyManager.NETWORK_TYPE_HSDPA
					|| nSubType == TelephonyManager.NETWORK_TYPE_EVDO_0
					&& !telephonyManager.isNetworkRoaming()) {
				netType = 3;
				//2G 移动和联通的2G为GPRS或EGDE，电信的2G为CDMA
			} else if (nSubType == TelephonyManager.NETWORK_TYPE_GPRS
					|| nSubType == TelephonyManager.NETWORK_TYPE_EDGE
					|| nSubType == TelephonyManager.NETWORK_TYPE_CDMA
					&& !telephonyManager.isNetworkRoaming()) {
				netType = 2;
			} else {
				netType = 2;
			}
		}
		return netType;
	}

	/**
	 * 判断GPS是否打开
	 *ACCESS_FINE_LOCATION权限
	 * @param context
	 * @return
	 */
	public static boolean isGPSEnabled(Context context) {
		//获取手机所有连接LOCATION_SERVICE对象
		LocationManager locationManager = ((LocationManager) context.getSystemService(Context.LOCATION_SERVICE));
		return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
	}
	
}
