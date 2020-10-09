package com.dicoding.kotlin.submission2githubuser.preferences

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import com.dicoding.kotlin.submission2githubuser.R

class SettingsFragment : PreferenceFragmentCompat(), SharedPreferences.OnSharedPreferenceChangeListener {

    private lateinit var REMINDER: String
    private lateinit var reminderPref : SwitchPreferenceCompat


    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
        REMINDER = resources.getString(R.string.key_reminder)
        reminderPref = findPreference<SwitchPreferenceCompat>(REMINDER) as SwitchPreferenceCompat
        setSummary()
    }

    private fun setSummary() {
        val sh = preferenceManager.sharedPreferences
        reminderPref.isChecked = sh.getBoolean(REMINDER, false)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String?) {
        if(key.equals(REMINDER)){
            reminderPref.isChecked = sharedPreferences.getBoolean(REMINDER, false)
            AlarmReceiver.setRepeatingAlarm(context, reminderPref.isChecked)
        }
    }


    override fun onResume() {
        super.onResume()
        preferenceScreen.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        preferenceScreen.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
    }
}