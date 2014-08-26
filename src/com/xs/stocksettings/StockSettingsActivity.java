package com.xs.stocksettings;

import android.os.Bundle;
import android.preference.*;
import android.widget.Toast;

public class StockSettingsActivity extends PreferenceActivity {
	
	private static final String ABOUT = "about";
	
	private Preference mAbout;
	
	public void onCreate (Bundle SavedInstanceState) {
		super.onCreate(SavedInstanceState);
		addPreferencesFromResource(R.xml.activity_stocksettings);
		
		mAbout = (Preference) findPreference(ABOUT);
	}
	
	public boolean onPreferenceTreeClick (PreferenceScreen preferencescreen , Preference preference) {
		if (preference == mAbout) {
			Toast.makeText(this, "附加设置由XS开发，授权给小华使用，严禁他人盗窃！", 2000).show();
		}
		return false;
	}
	
}
