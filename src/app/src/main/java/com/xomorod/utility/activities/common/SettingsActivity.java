package com.xomorod.utility.activities.common;


import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.RingtonePreference;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.xomorod.utility.R;
import com.xomorod.utility.business.CardViewType;
import com.xomorod.utility.business.MessageEvent;
import com.xomorod.utility.business.TitleSection;
import com.xomorod.utility.section.fontManager.CardFont;
import com.xomorod.utility.section.fontManager.FontData;
import com.xomorod.utility.section.themeManager.CardTheme;
import com.xomorod.utility.section.themeManager.ThemeData;
import com.xomorod.utility.logic.C;
import com.xomorod.utility.logic.Project;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import jp.satorufujiwara.binder.recycler.RecyclerBinderAdapter;

/**
 * A {@link PreferenceActivity} that presents a set of application settings. On
 * handset devices, settings are presented as a single list. On tablets,
 * settings are split by category, with category headers shown to the left of
 * the list of settings.
 * <p/>
 * See <a href="http://developer.android.com/design/patterns/settings.html">
 * Android Design: Settings</a> for design guidelines and the <a
 * href="http://developer.android.com/guide/topics/ui/settings.html">Settings
 * API Guide</a> for more information on developing a Settings UI.
 */
public class SettingsActivity extends AppCompatPreferenceActivity  {
    /**
     * A preference value change listener that updates the preference's summary
     * to reflect its new value.
     */

    private static Preference.OnPreferenceChangeListener sBindPreferenceSummaryToValueListener = new Preference.OnPreferenceChangeListener() {
        @Override
        public boolean onPreferenceChange(Preference preference, Object value) {
            String stringValue = value.toString();

            if (preference instanceof ListPreference) {
                // For list preferences, look up the correct display value in
                // the preference's 'entries' list.
                ListPreference listPreference = (ListPreference) preference;
                int index = listPreference.findIndexOfValue(stringValue);

                // Set the summary to reflect the new value.
                preference.setSummary(
                        index >= 0
                                ? listPreference.getEntries()[index]
                                : null);

            } else if (preference instanceof RingtonePreference) {
                // For ringtone preferences, look up the correct display value
                // using RingtoneManager.
                if (TextUtils.isEmpty(stringValue)) {
                    // Empty values correspond to 'silent' (no ringtone).
                    preference.setSummary(R.string.pref_ringtone_silent);

                } else {
                    Ringtone ringtone = RingtoneManager.getRingtone(
                            preference.getContext(), Uri.parse(stringValue));

                    if (ringtone == null) {
                        // Clear the summary if there was a lookup error.
                        preference.setSummary(null);
                    } else {
                        // Set the summary to reflect the new ringtone display
                        // name.
                        String name = ringtone.getTitle(preference.getContext());
                        preference.setSummary(name);
                    }
                }

            } else {
                // For all other preferences, set the summary to the value's
                // simple string representation.
                preference.setSummary(stringValue);
            }
            return true;
        }
    };

    /**
     * Helper method to determine if the device has an extra-large screen. For
     * example, 10" tablets are extra-large.
     */
    private static boolean isXLargeTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_XLARGE;
    }

    /**
     * Binds a preference's summary to its value. More specifically, when the
     * preference's value is changed, its summary (line of text below the
     * preference title) is updated to reflect the value. The summary is also
     * immediately updated upon calling this method. The exact display format is
     * dependent on the type of preference.
     *
     * @see #sBindPreferenceSummaryToValueListener
     */
    private static void bindPreferenceSummaryToValue(Preference preference) {
        // Set the listener to watch for value changes.
        preference.setOnPreferenceChangeListener(sBindPreferenceSummaryToValueListener);

        // Trigger the listener immediately with the preference's
        // current value.
        sBindPreferenceSummaryToValueListener.onPreferenceChange(preference,
                PreferenceManager
                        .getDefaultSharedPreferences(preference.getContext())
                        .getString(preference.getKey(), ""));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActionBar();

    }

    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onIsMultiPane() {
        return isXLargeTablet(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void onBuildHeaders(List<Header> target) {
        loadHeadersFromResource(R.xml.pref_headers, target);

    }

    /**
     * This method stops fragment injection in malicious applications.
     * Make sure to deny any unknown fragments here.
     */
    protected boolean isValidFragment(String fragmentName) {
        return PreferenceFragment.class.getName().equals(fragmentName)
                || GeneralPreferenceFragment.class.getName().equals(fragmentName)
                || LanguagePreferenceFragment.class.getName().equals(fragmentName)
                || ThemePreferenceFragment.class.getName().equals(fragmentName);
    }

    /**
     * This fragment shows general preferences only. It is used when the
     * activity is showing a two-pane settings UI.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static class GeneralPreferenceFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_general);
            setHasOptionsMenu(true);

            // Bind the summaries of EditText/List/Dialog/Ringtone preferences
            // to their values. When their values change, their summaries are
            // updated to reflect the new value, per the Android Design
            // guidelines.
            bindPreferenceSummaryToValue(findPreference("example_text"));
            bindPreferenceSummaryToValue(findPreference("example_list"));
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            if (id == android.R.id.home) {
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                return true;
            }
            return super.onOptionsItemSelected(item);
        }
    }

    /**
     * This fragment shows notification preferences only. It is used when the
     * activity is showing a two-pane settings UI.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static class ThemePreferenceFragment extends PreferenceFragment {


        @Bind(R.id.recycler_view_main)
        RecyclerView recyclerView;
        private StaggeredGridLayoutManager mStaggeredLayoutManager;

        private final RecyclerBinderAdapter<TitleSection, CardViewType> adapter
                = new RecyclerBinderAdapter<>();




        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                                 @Nullable Bundle savedInstanceState) {
            setHasOptionsMenu(true);
            final View v = inflater.inflate(R.layout.fragment_theme, container, false);
            ButterKnife.bind(this, v);

            return v;
        }
        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(mStaggeredLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(adapter);

            String[] themeListEng = getResources().getStringArray(R.array.ThemeListEn);
            String[] themeList = getResources().getStringArray(R.array.ThemeList);
            com.xomorod.utility.data.PreferenceManager pm= Project.getPref(view.getContext());
            int lastFirstVisiblePosition= pm.get(C.Fields.ThemeSelectedPos, 0);
            if(lastFirstVisiblePosition >0)
            {
                mStaggeredLayoutManager.scrollToPositionWithOffset(lastFirstVisiblePosition, 20);

            }
            for (int i = 0; i < themeList.length; i++) {
                ThemeData themeData;
                int color= R.color.md_white_1000 ;
                if(lastFirstVisiblePosition==i)
                    color= R.color.md_indigo_50 ;
                if(themeListEng[i].startsWith(C.Fields.rtl)) {

                    themeData=new ThemeData(themeList[i],themeListEng[i],color);
                }
                else
                {
                    themeData=new ThemeData(themeList[i],themeListEng[i],color);
                }


                CardTheme cardTheme=new CardTheme(getActivity(),themeData);
                adapter.add(TitleSection.Default, cardTheme);
            }

        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            if (id == android.R.id.home) {
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                return true;
            }
            return super.onOptionsItemSelected(item);
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static class FontPreferenceFragment extends PreferenceFragment {


        @Bind(R.id.recycler_view_main)
        RecyclerView recyclerView;
        private StaggeredGridLayoutManager mStaggeredLayoutManager;

        private final RecyclerBinderAdapter<TitleSection, CardViewType> adapter
                = new RecyclerBinderAdapter<>();




        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                                 @Nullable Bundle savedInstanceState) {
            setHasOptionsMenu(true);
            final View v = inflater.inflate(R.layout.fragment_font, container, false);
            ButterKnife.bind(this, v);

            return v;
        }
        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(mStaggeredLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(adapter);
            try {
                AssetManager mgr = getActivity().getAssets();
                com.xomorod.utility.data.PreferenceManager pm= Project.getPref(view.getContext());
                int lastFirstVisiblePosition= pm.get(C.Fields.FontSelectedPos, 0);
                String[] themeListEng = mgr.list(C.Fields.fonts);

                for (int i = 0; i < themeListEng.length; i++) {
                    FontData fontData;

                    int color= R.color.md_white_1000 ;
                    if(lastFirstVisiblePosition==i)
                        color= R.color.md_indigo_50 ;
                    if(themeListEng[i].startsWith(C.Fields.rtl)) {

                        fontData=new FontData(themeListEng[i],themeListEng[i].replace(C.Fields.rtl,"").replace("_"," ").replace(".ttf",""),getString(R.string.font_sample_persian),color);
                    }
                    else
                    {
                        fontData=new FontData(themeListEng[i],themeListEng[i].replace(C.Fields.ltr,"").replace("_"," ").replace(".ttf",""),getString(R.string.font_sample_english),color);
                    }
                    CardFont cardFont=new CardFont(getActivity(),fontData);
                    adapter.add(TitleSection.Default, cardFont);
                }


                if(lastFirstVisiblePosition >0)
                {
                    mStaggeredLayoutManager.scrollToPositionWithOffset(lastFirstVisiblePosition, 20);

                }
            } catch (Exception ex) {
                ex.getMessage();
            }

        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            if (id == android.R.id.home) {
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                return true;
            }
            return super.onOptionsItemSelected(item);
        }
    }

    /**
     * This fragment shows data and sync preferences only. It is used when the
     * activity is showing a two-pane settings UI.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static class LanguagePreferenceFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_language);
            setHasOptionsMenu(true);

            Preference lstLng=findPreference(getString(R.string.prefs_language_key));
            bindPreferenceSummaryToValue(lstLng);
            (lstLng).setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    Project.setLanguage(getActivity(),newValue.toString());
                    Intent intent=new Intent(C.Action.RefreshActivity);
                    EventBus.getDefault().post(new MessageEvent(intent));
                    return true;
                }
            });
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            if (id == android.R.id.home) {
                requireRefresh =true;
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                return false;
            }
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            requireRefresh =true;
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
