package com.lithium.leona.openstud.helpers;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.TypedValue;

import androidx.core.content.ContextCompat;

import com.lithium.leona.openstud.R;
import com.lithium.leona.openstud.data.PreferenceManager;

public class ThemeEngine {
    private static SharedPreferences pref;

    private static void setupSharedPreferences(Context context) {
        if (pref != null) return;
        pref = context.getSharedPreferences("ThemePrefs", 0); // 0 - for private mode
    }

    public static void applyExamTheme(Activity activity) {
        Theme theme = PreferenceManager.getTheme(activity);
        switch (theme) {
            case SYSTEM:
                if (isLightTheme(activity)) activity.setTheme(R.style.NoActionBarAppLightTheme);
                else activity.setTheme(R.style.ExamDarkTheme);
                break;
            case LIGHT:
                activity.setTheme(R.style.NoActionBarAppLightTheme);
                break;
            case DARK:
                activity.setTheme(R.style.ExamDarkTheme);
                break;
            case BLACK:
                activity.setTheme(R.style.ExamBlackTheme);
                break;
        }
    }

    public static void applyProfileTheme(Activity activity) {
        Theme theme = PreferenceManager.getTheme(activity);
        switch (theme) {
            case SYSTEM:
                if (isLightTheme(activity)) activity.setTheme(R.style.NoActionBarAppLightTheme);
                else activity.setTheme(R.style.NoActionBarAppDarkTheme);
                break;
            case LIGHT:
                activity.setTheme(R.style.NoActionBarAppLightTheme);
                break;
            case BLACK:
                activity.setTheme(R.style.NoActionBarAppBlackTheme);
                break;
            case DARK:
                activity.setTheme(R.style.NoActionBarAppDarkTheme);
                break;

        }
    }

    public static void applyPaymentsTheme(Activity activity) {
        Theme theme = PreferenceManager.getTheme(activity);
        switch (theme) {
            case SYSTEM:
                if (isLightTheme(activity)) activity.setTheme(R.style.NoActionBarAppLightTheme);
                else activity.setTheme(R.style.NoActionBarAppDarkTheme);
                break;
            case LIGHT:
                activity.setTheme(R.style.NoActionBarAppLightTheme);
                break;
            case DARK:
                activity.setTheme(R.style.NoActionBarAppDarkTheme);
                break;
            case BLACK:
                activity.setTheme(R.style.NoActionBarAppBlackTheme);
                break;
        }
    }

    public static void applySearchTheme(Activity activity) {
        Theme theme = PreferenceManager.getTheme(activity);
        switch (theme) {
            case SYSTEM:
                if (isLightTheme(activity)) activity.setTheme(R.style.NoActionBarAppLightTheme);
                else activity.setTheme(R.style.NoActionBarAppDarkTheme);
                break;
            case LIGHT:
                activity.setTheme(R.style.NoActionBarAppLightTheme);
                break;
            case DARK:
                activity.setTheme(R.style.NoActionBarAppDarkTheme);
                break;
            case BLACK:
                activity.setTheme(R.style.NoActionBarAppBlackTheme);
                break;
        }
    }

    public static void applyStatsTheme(Activity activity) {
        Theme theme = PreferenceManager.getTheme(activity);
        switch (theme) {
            case SYSTEM:
                if (isLightTheme(activity)) activity.setTheme(R.style.NoActionBarAppLightTheme);
                else activity.setTheme(R.style.NoActionBarAppDarkTheme);
                break;
            case LIGHT:
                activity.setTheme(R.style.NoActionBarAppLightTheme);
                break;
            case DARK:
                activity.setTheme(R.style.NoActionBarAppDarkTheme);
                break;
            case BLACK:
                activity.setTheme(R.style.NoActionBarAppBlackTheme);
                break;
        }
    }

    public static void applySearchClassroomTheme(Activity activity) {
        Theme theme = PreferenceManager.getTheme(activity);
        switch (theme) {
            case SYSTEM:
                if (isLightTheme(activity)) activity.setTheme(R.style.NoActionBarAppLightTheme);
                else activity.setTheme(R.style.NoActionBarAppDarkTheme);
                break;
            case LIGHT:
                activity.setTheme(R.style.NoActionBarAppLightTheme);
                break;
            case DARK:
                activity.setTheme(R.style.NoActionBarAppDarkTheme);
                break;
            case BLACK:
                activity.setTheme(R.style.NoActionBarAppBlackTheme);
                break;
        }
    }

    public static void applyClassroomTimetableTheme(Activity activity) {
        Theme theme = PreferenceManager.getTheme(activity);
        switch (theme) {
            case SYSTEM:
                if (isLightTheme(activity)) activity.setTheme(R.style.NoActionBarAppLightTheme);
                else activity.setTheme(R.style.NoActionBarAppDarkTheme);
                break;
            case LIGHT:
                activity.setTheme(R.style.NoActionBarAppLightTheme);
                break;
            case DARK:
                activity.setTheme(R.style.NoActionBarAppDarkTheme);
                break;
            case BLACK:
                activity.setTheme(R.style.NoActionBarAppBlackTheme);
                break;
        }
    }

    public static int getPrimaryTextColor(Activity activity) {
        int tintColor;
        TypedValue tV = new TypedValue();
        Resources.Theme theme = activity.getTheme();
        boolean success = theme.resolveAttribute(R.attr.primaryTextColor, tV, true);
        if (success) tintColor = tV.data;
        else tintColor = ContextCompat.getColor(activity, android.R.color.white);
        return tintColor;
    }

    public static int getSecondaryTextColor(Activity activity) {
        int tintColor;
        TypedValue tV = new TypedValue();
        Resources.Theme theme = activity.getTheme();
        boolean success = theme.resolveAttribute(R.attr.secondaryTextColor, tV, true);
        if (success) tintColor = tV.data;
        else tintColor = ContextCompat.getColor(activity, android.R.color.darker_gray);
        return tintColor;
    }

    public static void applyAboutTheme(Activity activity) {
        Theme theme = PreferenceManager.getTheme(activity);
        switch (theme) {
            case SYSTEM:
                if (isLightTheme(activity))
                    activity.setTheme(R.style.AppTheme_MaterialAboutActivityLight);
                else activity.setTheme(R.style.AppTheme_MaterialAboutActivityDark);
                break;
            case LIGHT:
                activity.setTheme(R.style.AppTheme_MaterialAboutActivityLight);
                break;
            case DARK:
                activity.setTheme(R.style.AppTheme_MaterialAboutActivityDark);
                break;
            case BLACK:
                activity.setTheme(R.style.AppTheme_MaterialAboutActivityBlack);
                break;
        }
    }

    public static void applyCalendarTheme(Activity activity) {
        Theme theme = PreferenceManager.getTheme(activity);
        switch (theme) {
            case SYSTEM:
                if (isLightTheme(activity)) activity.setTheme(R.style.NoActionBarAppLightTheme);
                else activity.setTheme(R.style.NoActionBarAppDarkTheme);
                break;
            case LIGHT:
                activity.setTheme(R.style.NoActionBarAppLightTheme);
                break;
            case DARK:
                activity.setTheme(R.style.NoActionBarAppDarkTheme);
                break;
            case BLACK:
                activity.setTheme(R.style.NoActionBarAppBlackTheme);
                break;
        }
    }

    public static int getDialogTheme(Activity activity) {
        Theme theme = PreferenceManager.getTheme(activity);
        switch (theme) {
            case SYSTEM:
                if (isLightTheme(activity)) return R.style.ThemeLightDialog;
                else return R.style.ThemeDarkDialog;
            case LIGHT:
                return R.style.ThemeLightDialog;
            case DARK:
                return R.style.ThemeDarkDialog;
            case BLACK:
                return R.style.ThemeDarkDialog;
            default:
                return R.style.ThemeLightDialog;
        }
    }

    public static int getAlertDialogTheme(Activity activity) {
        Theme theme = PreferenceManager.getTheme(activity);
        switch (theme) {
            case SYSTEM:
                if (isLightTheme(activity)) return R.style.ThemeLightAlertDialog;
                else return R.style.ThemeDarkAlertDialog;
            case LIGHT:
                return R.style.ThemeLightAlertDialog;
            case DARK:
                return R.style.ThemeDarkAlertDialog;
            case BLACK:
                return R.style.ThemeBlackAlertDialog;
            default:
                return R.style.ThemeLightAlertDialog;
        }
    }

    public static void applySettingsTheme(Activity activity) {
        Theme theme = PreferenceManager.getTheme(activity);
        switch (theme) {
            case SYSTEM:
                if (isLightTheme(activity)) activity.setTheme(R.style.PreferencesLight);
                else activity.setTheme(R.style.PreferencesDark);
                break;
            case LIGHT:
                activity.setTheme(R.style.PreferencesLight);
                break;
            case DARK:
                activity.setTheme(R.style.PreferencesDark);
                break;
            case BLACK:
                activity.setTheme(R.style.PreferencesBlack);
                break;
        }
    }

    public static int getAboutTheme(Activity activity) {
        Theme theme = PreferenceManager.getTheme(activity);
        switch (theme) {
            case SYSTEM:
                if (isLightTheme(activity)) return R.style.AboutLibrariesThemeLight;
                else return R.style.AboutLibrariesThemeDark;
            case LIGHT:
                return R.style.AboutLibrariesThemeLight;
            case DARK:
                return R.style.AboutLibrariesThemeDark;
            case BLACK:
                return R.style.AboutLibrariesThemeBlack;
            default:
                return R.style.AboutLibrariesThemeLight;
        }
    }

    public static void applyNewsTheme(Activity activity) {
        Theme theme = PreferenceManager.getTheme(activity);
        switch (theme) {
            case SYSTEM:
                if (isLightTheme(activity)) activity.setTheme(R.style.NoActionBarAppLightTheme);
                else activity.setTheme(R.style.NoActionBarAppDarkTheme);
                break;
            case LIGHT:
                activity.setTheme(R.style.NoActionBarAppLightTheme);
                break;
            case DARK:
                activity.setTheme(R.style.NoActionBarAppDarkTheme);
                break;
            case BLACK:
                activity.setTheme(R.style.NoActionBarAppBlackTheme);
                break;
        }
    }

    public static void applyCustomCourseTheme(Activity activity) {
        Theme theme = PreferenceManager.getTheme(activity);
        switch (theme) {
            case SYSTEM:
                if (isLightTheme(activity)) activity.setTheme(R.style.NoActionBarAppLightTheme);
                else activity.setTheme(R.style.NoActionBarAppDarkTheme);
                break;
            case LIGHT:
                activity.setTheme(R.style.NoActionBarAppLightTheme);
                break;
            case DARK:
                activity.setTheme(R.style.NoActionBarAppDarkTheme);
                break;
            case BLACK:
                activity.setTheme(R.style.NoActionBarAppBlackTheme);
                break;
        }
    }

    public static int getDatePickerTheme(Activity activity) {
        Theme theme = PreferenceManager.getTheme(activity);
        switch (theme) {
            case SYSTEM:
                if (isLightTheme(activity)) return R.style.DatePickerLightTheme;
                else return R.style.DatePickerDarkTheme;
            case LIGHT:
                return R.style.DatePickerLightTheme;
            case DARK:
                return R.style.DatePickerDarkTheme;
            case BLACK:
                return R.style.DatePickerDarkTheme;
            default:
                return R.style.DatePickerLightTheme;
        }
    }

    public static int getTimePickerTheme(Context context) {
        Theme theme = PreferenceManager.getTheme(context);
        switch (theme) {
            case SYSTEM:
                if (isLightTheme(context)) return R.style.TimePickerDialogLightTheme;
                else return R.style.TimePickerDialogDarkTheme;
            case LIGHT:
                return R.style.TimePickerDialogLightTheme;
            case DARK:
                return R.style.TimePickerDialogDarkTheme;
            case BLACK:
                return R.style.TimePickerDialogDarkTheme;
            default:
                return R.style.TimePickerDialogLightTheme;
        }
    }

    public static boolean isLightTheme(Context context) {
        Theme theme = PreferenceManager.getTheme(context);
        switch (theme) {
            case SYSTEM:
                return !isDarkSystemThemeEnabled(context);
            case LIGHT:
                return true;
        }
        return false;
    }

    private static boolean isDarkSystemThemeEnabled(Context activity) {
        if (activity == null) return true;
        switch (activity.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK) {
            case Configuration.UI_MODE_NIGHT_YES:
                return true;
            case Configuration.UI_MODE_NIGHT_NO:
                return false;
        }
        return false;
    }

    public enum Theme {
        SYSTEM(0), LIGHT(1), DARK(2), BLACK(3);
        private int value;

        Theme(int value) {
            this.value = value;
        }

        public static Theme getTheme(int theme) {
            if (theme == 0) return SYSTEM;
            else if (theme == 1) return LIGHT;
            else if (theme == 2) return DARK;
            else if (theme == 3) return BLACK;
            else return SYSTEM;
        }

        public int getValue() {
            return value;
        }
    }
}