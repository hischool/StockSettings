package com.android.stocksettings;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;

public class CameraSound extends PreferenceActivity {
	private static final String KEY_CAMERA_SOUND = "camera_sound";
	private static final String KEY_RECORD_SOUND = "record_sound";
	private static final String KEY_FOCUS_SOUND = "focus_sound";
	private CheckBoxPreference cameraSound;
	private CheckBoxPreference recordSound;
	private CheckBoxPreference focusSound;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		addPreferencesFromResource(R.xml.camera_sound);
		cameraSound = (CheckBoxPreference) findPreference(KEY_CAMERA_SOUND);
		recordSound = (CheckBoxPreference) findPreference(KEY_RECORD_SOUND);
		focusSound = (CheckBoxPreference) findPreference(KEY_FOCUS_SOUND);
	}

	public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen,
			Preference preference) {
		if (preference == cameraSound) {
			RootCmd.RunRootCmd("busybox mount -o remount,rw /system");
			if (!cameraSound.isChecked()) {
				RootCmd.RunRootCmd("mv /system/media/audio/ui/camera_click.ogg /system/media/audio/ui/camera_click.bak");
			} else {
				RootCmd.RunRootCmd("mv /system/media/audio/ui/camera_click.bak /system/media/audio/ui/camera_click.ogg");
			}
		} else if (preference == recordSound) {
			RootCmd.RunRootCmd("busybox mount -o remount,rw /system");
			if (!recordSound.isChecked()) {
				RootCmd.RunRootCmd("mv /system/media/audio/ui/VideoRecord.ogg /system/media/audio/ui/VideoRecord.bak");
			} else {
				RootCmd.RunRootCmd("mv /system/media/audio/ui/VideoRecord.bak /system/media/audio/ui/VideoRecord.ogg");
			}
		} else if (preference == focusSound) {
			RootCmd.RunRootCmd("busybox mount -o remount,rw /system");
			if (!focusSound.isChecked()) {
				RootCmd.RunRootCmd("mv /system/media/audio/ui/camera_focus.ogg /system/media/audio/ui/camera_focus.bak");
			} else {
				RootCmd.RunRootCmd("mv /system/media/audio/ui/camera_focus.bak /system/media/audio/ui/camera_focus.ogg");
			}
		}
		return super.onPreferenceTreeClick(preferenceScreen, preference);

	}
}
