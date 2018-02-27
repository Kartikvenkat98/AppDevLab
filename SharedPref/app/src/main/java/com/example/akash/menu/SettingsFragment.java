package com.example.akash.menu;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.EditTextPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v7.preference.PreferenceScreen;
import android.support.v7.preference.PreferenceFragmentCompat;


public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.pref_settings);
        SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
        PreferenceScreen preferenceScreen = getPreferenceScreen();

        int count = preferenceScreen.getPreferenceCount();
        for(int i=0;i<count;i++){
            Preference pref = preferenceScreen.getPreference(i);
            if(!(pref instanceof CheckBoxPreference)){
                String value = sharedPreferences.getString(pref.getKey(),"");
                setPreferenceSummary(pref,value);
            }
        }

    }

    private void setPreferenceSummary(Preference preference,String value){
        if(preference instanceof ListPreference){
            ListPreference listPreference = (ListPreference) preference;
            int prefIndex = listPreference.findIndexOfValue(value);
            if(prefIndex>=0){
                listPreference.setSummary(listPreference.getEntries()[prefIndex]);
            }
        }
        else if(preference instanceof EditTextPreference){
            preference.setSummary(value);
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        Preference pref = findPreference(s);
        if(pref != null){
            if(!(pref instanceof CheckBoxPreference)){
                String value = sharedPreferences.getString(findPreference(s).getKey(),"");
                setPreferenceSummary(pref,value);
            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }
}
