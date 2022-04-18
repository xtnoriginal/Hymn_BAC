package com.example.hymn_bac.ui.notifications;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SeekBarPreference;

import com.example.hymn_bac.R;
import com.example.hymn_bac.Theme.ThemeHelper;
import com.example.hymn_bac.databinding.FragmentNotificationsBinding;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;
    private final String TAG = "Notification Fragment";


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.settings, new SettingsFragment())
                .commit();



        return root;
    }


    public static class SettingsFragment extends PreferenceFragmentCompat {

        private final String TAG = "Settings Fragment";

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);


            ListPreference themeSelection = (ListPreference)  getPreferenceManager().findPreference("theme");
            themeSelection.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {


                    Log.i(TAG, "onPreferenceChange: "+newValue.toString());
                    ThemeHelper.applyTheme(newValue.toString().toLowerCase());



                    return false;
                }
            });


            SeekBarPreference seekBarPreference = (SeekBarPreference)  getPreferenceManager().findPreference("font_size");
            seekBarPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {


                    Log.i(TAG, "onPreferenceChange: "+newValue.toString());



                    return false;
                }
            });





            Preference preference = (Preference) getPreferenceManager().findPreference("clear_fav");
            preference.setOnPreferenceClickListener(
                    new Preference.OnPreferenceClickListener() {
                        @Override
                        public boolean onPreferenceClick(Preference preference) {

                            Log.i(TAG, "Clearing Favourites");
                            Toast.makeText(preference.getContext(), "Clearing Favourites!!!", Toast.LENGTH_SHORT).show();
                            return false;
                        }
                    }
            );

        }
    }










    /**
     * Email client intent to send support mail
     * Appends the necessary device information to email body
     * useful when providing support
     */
    public static void sendFeedback(Context context) {
        String body = null;
        try {
            body = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        body = "\n\n-----------------------------\nPlease don't remove this information\n Device OS: Android \n Device OS version: " +
                Build.VERSION.RELEASE + "\n App Version: " + body + "\n Device Brand: " + Build.BRAND +
                "\n Device Model: " + Build.MODEL + "\n Device Manufacturer: " + Build.MANUFACTURER;
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"xtnoriginal@icloud.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Query from android app");
        intent.putExtra(Intent.EXTRA_TEXT, body);
        //context.startActivity(Intent.createChooser(intent, context.getString(R.string.choose_email_client)));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}