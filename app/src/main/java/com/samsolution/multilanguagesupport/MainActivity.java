package com.samsolution.multilanguagesupport;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Locale;


        //Important methods

        /*Locale.getDefault().getLanguage()       ---> en
        Locale.getDefault().getISO3Language()   ---> eng
        Locale.getDefault().getCountry()        ---> US
        Locale.getDefault().getISO3Country()    ---> USA
        Locale.getDefault().getDisplayCountry() ---> United States
        Locale.getDefault().getDisplayName()    ---> English (United States)
        Locale.getDefault().toString()          ---> en_US
        Locale.getDefault().getDisplayLanguage()---> English*/




public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocalLang();
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));

        Button changeMyLanguageBtn = findViewById(R.id.changeMyLanguage);

        changeMyLanguageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //show alertDialog to display list of language, one can be selected
                showChangeLanguageDialog();

            }
        });
    }

    private void showChangeLanguageDialog() {
        final String[] langList = {"عربى", " বাংলা", "Française", "日本語", "English"};

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
        mBuilder.setTitle("Choose Language... ");
        mBuilder.setSingleChoiceItems(langList, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (which == 0) {
                    setLocal("Ar");
                    recreate();
                } else if (which == 1) {
                    setLocal("Bn");
                    recreate();
                } else if (which == 2) {
                    setLocal("Fr");
                    recreate();
                } else if (which == 3) {
                    setLocal("ja");
                    recreate();
                } else if (which == 4) {
                    setLocal("en");
                    recreate();
                }
                dialog.dismiss();

            }
        });
        AlertDialog mDialog = mBuilder.create();
        mDialog.show();  //showDialog
    }

    private void setLocal(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getBaseContext().getResources().updateConfiguration(configuration, getBaseContext().getResources().getDisplayMetrics());

        //Temp Database
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_Lang", lang);
        editor.apply();
    }

    public void loadLocalLang() {
        SharedPreferences sharedPreferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = sharedPreferences.getString("My_Lang", Locale.getDefault().getLanguage());
        setLocal(language);
    }

}