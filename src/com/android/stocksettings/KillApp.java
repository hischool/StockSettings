package com.android.stocksettings;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;

public class KillApp extends PreferenceActivity {

	private static final String KILL_APP_MUSIC = "kill_app_music";
	private static final String KILL_APP_EMAIL = "kill_app_email";
	private static final String KILL_APP_BROWSER = "kill_app_browser";
	private static final String KILL_APP_FM = "kill_app_fm";
	private static final String KILL_APP_GAMECENTER = "kill_app_gamecenter";

	private CheckBoxPreference mKillAppMusic;
	private CheckBoxPreference mKillAppEmail;
	private CheckBoxPreference mKillAppBrowser;
	private CheckBoxPreference mKillAppFm;
	private CheckBoxPreference mKillAppGameCenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		addPreferencesFromResource(R.xml.kill_app);

		mKillAppMusic = (CheckBoxPreference) findPreference(KILL_APP_MUSIC);
		mKillAppEmail = (CheckBoxPreference) findPreference(KILL_APP_EMAIL);
		mKillAppBrowser = (CheckBoxPreference) findPreference(KILL_APP_BROWSER);
		mKillAppFm = (CheckBoxPreference) findPreference(KILL_APP_FM);
		mKillAppGameCenter = (CheckBoxPreference) findPreference(KILL_APP_GAMECENTER);
	}

	public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen,
			Preference preference) {
		if (preference == mKillAppMusic) {
			RootCmd.RunRootCmd("busybox mount -o remount,rw /system");
			if (mKillAppMusic.isChecked()) {
				RootCmd.RunRootCmd("mv /system/app/Music.apk /system/app/Music.bak");
			} else {
				RootCmd.RunRootCmd("mv /system/app/Music.bak /system/app/Music.apk");
			}
		}

		if (preference == mKillAppEmail) {
			RootCmd.RunRootCmd("busybox mount -o remount,rw /system");
			if (mKillAppEmail.isChecked()) {
				RootCmd.RunRootCmd("mv /system/app/Email.apk /system/app/Email.bak");
			} else {
				RootCmd.RunRootCmd("mv /system/app/Email.bak /system/app/Email.apk");
			}
		}

		if (preference == mKillAppBrowser) {
			RootCmd.RunRootCmd("busybox mount -o remount,rw /system");
			if (mKillAppBrowser.isChecked()) {
				RootCmd.RunRootCmd("mv /system/app/Browser.apk /system/app/Browser.bak");
			} else {
				RootCmd.RunRootCmd("mv /system/app/Browser.bak /system/app/Browser.apk");
			}
		}
		
		if (preference == mKillAppFm) {
			RootCmd.RunRootCmd("busybox mount -o remount,rw /system");
			if (mKillAppFm.isChecked()) {
				RootCmd.RunRootCmd("mv /system/app/FM.apk /system/app/FM.bak");
			} else {
				RootCmd.RunRootCmd("mv /system/app/FM.bak /system/app/FM.apk");
			}
		}

		if (preference == mKillAppGameCenter) {
			RootCmd.RunRootCmd("busybox mount -o remount,rw /system");
			if (mKillAppGameCenter.isChecked()) {
				RootCmd.RunRootCmd("mv /system/app/GameCenter.apk /system/app/GameCenter.bak");
			} else {
				RootCmd.RunRootCmd("mv /system/app/GameCenter.bak /system/app/GameCenter.apk");
			}
		}
		return false;
	}
}
