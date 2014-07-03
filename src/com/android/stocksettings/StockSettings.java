package com.android.stocksettings;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemProperties;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.provider.Settings;
import android.widget.Toast;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;


public class StockSettings extends PreferenceActivity implements OnPreferenceChangeListener {
	
    private static final String ALLOW__CRT_LOCKSCREEN = "allow_crt_lockscreen";
    private static final String KEY_DEVICE = "camera_change";
    private static final String FLASH_RECOVERY = "flash_recovery";
    private static final String SWITCH_VOLD = "switch_vold";
    private Preference mFlashRecovery;
    private CheckBoxPreference mCrtLockscreen;
    private ListPreference mCameraChange;
    private ListPreference mSwitchVold;
    
    //Encryption
    private static final String MOD_DEVICE = SystemProperties.get("ro.product.mod_device");
    //private static final String XS = SystemProperties.get("ro.weibo.com");
    
	protected void onCreate (Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		addPreferencesFromResource(R.xml.stocksettings);
		mFlashRecovery = (Preference)findPreference(FLASH_RECOVERY);
		mCrtLockscreen = (CheckBoxPreference)findPreference(ALLOW__CRT_LOCKSCREEN);
		mCameraChange = (ListPreference)findPreference(KEY_DEVICE);
		mSwitchVold = (ListPreference)findPreference(SWITCH_VOLD);
		mCameraChange.setOnPreferenceChangeListener(this);
		mSwitchVold.setOnPreferenceChangeListener(this);
        setListPreferenceSummary(mCameraChange);
        setListPreferenceSummary(mSwitchVold);
        
        //if ((!this.MOD_DEVICE.equals("8297_xs")) || (!this.XS.equals("weibo.com/acexs"))) {
        if ((!this.MOD_DEVICE.equals("8297_"))) {
        	Toast.makeText(getApplicationContext(), "您可能是盗版ROM受害者，请前往MIUI官网下载正版ROM，作者XS！",
        	Toast.LENGTH_SHORT).show();
        	finish();
        }

	}

	public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
		if(preference == mFlashRecovery){
			new AlertDialog.Builder(StockSettings.this)
			.setTitle(R.string.confirm)
			.setMessage("您确定刷入并重启进入刷机模式？")   
			.setNegativeButton(R.string.yes,
			new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,
						int whichButton) {
						RootCmd.RunRootCmd("dd if=/sdcard/recovery.img of=/dev/recovery");
						RootCmd.RunRootCmd("reboot recovery");
				}
			})
			.setPositiveButton(R.string.no,null)
			.show();
		}
		if(preference == mCrtLockscreen){
			if(mCrtLockscreen.isChecked()){
			Settings.System.putInt(getContentResolver(), "allow_crt_lockscreen", 1);
			}
			else{
			Settings.System.putInt(getContentResolver(), "allow_crt_lockscreen", 0);
			}
		}
		return super.onPreferenceTreeClick(preferenceScreen, preference);
	}
	
	public void setListPreferenceSummary(ListPreference mListPreference) {
		if(mListPreference == mCameraChange){
			if(0 == Integer.parseInt(mListPreference.getValue())) {
				mListPreference.setSummary(R.string.Hongmi_camera);
			}
			else{
				mListPreference.setSummary(R.string.CP_camera);	
			}
		}
		if(mListPreference == mSwitchVold){
			if(0 == Integer.parseInt(mListPreference.getValue())){
				mListPreference.setSummary(R.string.External_sdcard);
			}
			else{
				mListPreference.setSummary(R.string.Internal_sdcard);
			}
		}
	}
	
	public boolean onPreferenceChange(Preference preference, Object newValue) {
		if(mCameraChange == preference){
			String prefsValueCameraChange = (String) newValue;
			mCameraChange.setValue(prefsValueCameraChange);
			int mode = Integer.parseInt(prefsValueCameraChange); 
			switch (mode) {
			case 0:
				preference.setSummary(R.string.Hongmi_camera);
                RootCmd.RunRootCmd("busybox mount -o remount,rw /system");
                RootCmd.RunRootCmd("mv /system/app/CP_camera.apk /system/app/CP_camera.bak");
                RootCmd.RunRootCmd("mv /system/app/Hongmi_camera.bak /system/app/Hongmi_camera.apk");
				break;
			case 1:
				preference.setSummary(R.string.CP_camera);
                RootCmd.RunRootCmd("busybox mount -o remount,rw /system");
                RootCmd.RunRootCmd("mv /system/app/Hongmi_camera.apk /system/app/Hongmi_camera.bak");
                RootCmd.RunRootCmd("mv /system/app/CP_camera.bak /system/app/CP_camera.apk");
				break;
			default:
				break;
			}
		}
		if(mSwitchVold == preference){
			String prefsValueSwitchVold = (String) newValue;
			mSwitchVold.setValue(prefsValueSwitchVold);
			int mode = Integer.parseInt(prefsValueSwitchVold);
			switch (mode) {
			case 0:
				preference.setSummary(R.string.External_sdcard);
                RootCmd.RunRootCmd("busybox mount -o remount,rw /system");
                RootCmd.RunRootCmd("busybox cp /system/bin/stocksettings/external_vold /system/bin/vold");
                RootCmd.RunRootCmd("busybox cp /system/bin/stocksettings/external_sdcard /system/bin/sdcard");
                RootCmd.RunRootCmd("busybox chmod 0755 /system/bin/vold");
                RootCmd.RunRootCmd("busybox chmod 0755 /system/bin/sdcard");
                RootCmd.RunRootCmd("reboot");
				break;
			case 1:
				preference.setSummary(R.string.Internal_sdcard);
                RootCmd.RunRootCmd("busybox mount -o remount,rw /system");
                RootCmd.RunRootCmd("busybox cp /system/bin/stocksettings/internal_vold /system/bin/vold");
                RootCmd.RunRootCmd("busybox cp /system/bin/stocksettings/internal_sdcard /system/bin/sdcard");
                RootCmd.RunRootCmd("busybox chmod 0755 /system/bin/vold");
                RootCmd.RunRootCmd("busybox chmod 0755 /system/bin/sdcard");
                RootCmd.RunRootCmd("reboot");
				break;
			default:
				break;
			}
		}
		return true;
    }
	
}
