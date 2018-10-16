package com.lithium.leona.openstud.helpers;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.lithium.leona.openstud.R;
import com.lithium.leona.openstud.activities.SettingsPrefActivity;

import java.util.Objects;


public class LayoutHelper {
    public static NavigationView setupNavigationDrawer(AppCompatActivity aca, DrawerLayout dl){
        NavigationView navigationView = aca.findViewById(R.id.nav_view);
        return navigationView;
    }

    public static void setupToolbar(AppCompatActivity aca, Toolbar toolbar, int icon) {
        aca.setSupportActionBar(toolbar);
        ActionBar actionbar = aca.getSupportActionBar();
        Objects.requireNonNull(actionbar).setDisplayHomeAsUpEnabled(true);
        Drawable drawable = ResourcesCompat.getDrawable(aca.getResources(), icon, null);
        if (drawable != null) {
            drawable = DrawableCompat.wrap(drawable);
            DrawableCompat.setTint(drawable, Color.WHITE);
            actionbar.setHomeAsUpIndicator(drawable);
        }
    }


    public static Snackbar createTextSnackBar(View v, int string_id, int length){
        Snackbar snackbar = Snackbar
                .make(v, string_id, length);
        snackbar.show();
        return snackbar;
    }

    public synchronized  static void createActionSnackBar(View v, final int string_id, final int action_id, int length, View.OnClickListener listener) {
        Snackbar snackbar = Snackbar
                .make(v, string_id, length).setAction(action_id, listener);
        snackbar.show();
    }

}
