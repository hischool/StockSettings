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
	private static final String KILL_APP_GAMECENTER = "kill_app_gamecenter";

	private CheckBoxPreference mKillAppMusic;
	private CheckBoxPreference mKillAppEmail;
	private CheckBoxPreference mKillAppBrowser;
	private CheckBoxPreference mKillAppGameCenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		addPreferencesFromResource(R.xml.kill_app);

		mKillAppMusic = (CheckBoxPreference) findPreference(KILL_APP_MUSIC);
		mKillAppEmail = (CheckBoxPreference) findPreference(KILL_APP_EMAIL);
		mKillAppBrowser = (CheckBoxPreference) findPreference(KILL_APP_BROWSER);
		mKillAppGameCenter = (CheckBoxPreference) findPreference(KILL_APP_GAMECENTER);
	}

	public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen,
			Preference preference) {
		if (preference == mKillAppMusic) {
			if (mKillAppMusic.isChecked()) {
				RootCmd.RunRootCmd("mv /system/app/Music.apk /system/app/Music.bak");
			} else {
				RootCmd.RunRootCmd("mv /system/app/Music.bak /system/app/Music.apk");
			}
		}
		if (preference == mKillAppEmail) {
			if (mKillAppEmail.isChecked()) {
				RootCmd.RunRootCmd("mv /system/app/Email.apk /system/app/Email.bak");
			} else {
				RootCmd.RunRootCmd("mv /system/app/Email.bak /system/app/Email.apk");
			}
		}
		if (preference == mKillAppBrowser) {
			if (mKillAppBrowser.isChecked()) {
				RootCmd.RunRootCmd("mv /system/app/Browser.apk /system/app/Browser.bak");
			} else {
				RootCmd.RunRootCmd("mv /system/app/Browser.bak /system/app/Browser.apk");
			}
		}
		if (preference == mKillAppGameCenter) {
			if (mKillAppGameCenter.isChecked()) {
				RootCmd.RunRootCmd("mv /system/app/GameCenter.apk /system/app/GameCenter.bak");
			} else {
				RootCmd.RunRootCmd("mv /system/app/GameCenter.bak /system/app/GameCenter.apk");
			}
		}
		return false;
	}
}
