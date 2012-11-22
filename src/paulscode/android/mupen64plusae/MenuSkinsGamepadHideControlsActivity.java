package paulscode.android.mupen64plusae;


import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;


// TODO: Comment thoroughly
public class MenuSkinsGamepadHideControlsActivity extends PreferenceActivity
{
    public static MenuSkinsGamepadHideControlsActivity mInstance = null;
	public static boolean hideCbuttons = false;
	public static boolean hideABbuttons = false;
	public static boolean hideRbutton = false;
	public static boolean hideZbutton = false;
	public static boolean hideStartbutton = false;

    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        mInstance = this;

        Globals.checkLocale( this );
		
		addPreferencesFromResource( R.layout.preferences_hide_controls );
          
        // Hide C button
        final CheckBoxPreference settingsHideCButtons = (CheckBoxPreference) findPreference( "menuSkinsGamepadHideControlsC" );
        settingsHideCButtons.setOnPreferenceClickListener( new OnPreferenceClickListener() {
            
            public boolean onPreferenceClick( Preference preference )
            {
				hideCbuttons = !hideCbuttons;
                MenuActivity.gui_cfg.put( "GAME_PAD", "hideCbuttons", (settingsHideCButtons.isChecked() ? "1" : "0") );
                return true;
            }
        });
		
        // Hide A-B button
        final CheckBoxPreference settingsHideABButtons = (CheckBoxPreference) findPreference( "menuSkinsGamepadHideControlsAB" );
        settingsHideABButtons.setOnPreferenceClickListener( new OnPreferenceClickListener() {
            
            public boolean onPreferenceClick( Preference preference )
            {
				hideABbuttons = !hideABbuttons;
                MenuActivity.gui_cfg.put( "GAME_PAD", "hideABbuttons", (settingsHideABButtons.isChecked() ? "1" : "0") );
                return true;
            }
        });
        // Hide R button
        final CheckBoxPreference settingsHideRButton = (CheckBoxPreference) findPreference( "menuSkinsGamepadHideControlsR" );
        settingsHideRButton.setOnPreferenceClickListener( new OnPreferenceClickListener() {
            
            public boolean onPreferenceClick( Preference preference )
            {
				hideRbutton = !hideRbutton;
                MenuActivity.gui_cfg.put( "GAME_PAD", "hideRbutton", (settingsHideRButton.isChecked() ? "1" : "0") );
                return true;
            }
        });
		
        // Hide Z button
        final CheckBoxPreference settingsHideZButton = (CheckBoxPreference) findPreference( "menuSkinsGamepadHideControlsZ" );
        settingsHideZButton.setOnPreferenceClickListener( new OnPreferenceClickListener() {
            
            public boolean onPreferenceClick( Preference preference )
            {
				hideZbutton = !hideZbutton;
                MenuActivity.gui_cfg.put( "GAME_PAD", "hideZbutton", (settingsHideZButton.isChecked() ? "1" : "0") );
                return true;
            }
        });
		
        // Hide Start button
        final CheckBoxPreference settingsHideStartButton = (CheckBoxPreference) findPreference( "menuSkinsGamepadHideControlsStart" );
        settingsHideStartButton.setOnPreferenceClickListener( new OnPreferenceClickListener() {
            
            public boolean onPreferenceClick( Preference preference )
            {
				hideStartbutton = !hideStartbutton;
                MenuActivity.gui_cfg.put( "GAME_PAD", "hideStartbutton", (settingsHideStartButton.isChecked() ? "1" : "0") );
                return true;
            }
        });
    }
}
