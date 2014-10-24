package com.novytes.fuzzz.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.novytes.fuzzz.app.helpers.PreferenceHelper;
import com.novytes.fuzzz.app.helpers.TargetHelper;
import com.novytes.fuzzz.app.models.TargetInfo;


public class MainActivity extends Activity {

    private static int REQ_APP = 13;
    private TargetInfo info;
    private TargetHelper targetHelper;
    private PreferenceHelper prefHelper;

    private Context context;


    @InjectView(R.id.btn_select_app) Button btnSelectApp;
    @InjectView(R.id.btn_next) Button btnNext;
    @InjectView(R.id.btn_reset) Button btnReset;
    @InjectView(R.id.app_icon) ImageView appIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ButterKnife.inject(this);

        context = getApplicationContext();
        info = new TargetInfo();
        targetHelper = new TargetHelper();
        prefHelper = new PreferenceHelper(context);

        appIcon.setVisibility(View.INVISIBLE);
        btnSelectApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), AppChooserActivity.class);
                startActivityForResult(i, REQ_APP);
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                targetHelper.clearAllFields(info);
                prefHelper.writeTarget("target", info);
                appIcon.setVisibility(View.INVISIBLE);
                btnSelectApp.setVisibility(View.VISIBLE);
                Toast.makeText(context, "Reset done", Toast.LENGTH_SHORT).show();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!info.getAppName().isEmpty()){
                    Toast.makeText(context, "Going to next activity", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "Please select an application using the + button",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){

            if(requestCode == REQ_APP){
                String appName = data.getStringExtra("appname");
                Log.d("TAGGGG", appName);
                Drawable icon = null;
                try {
                    icon = getApplicationContext().getPackageManager().getApplicationIcon(appName);
                    btnSelectApp.setVisibility(View.INVISIBLE);
                    appIcon.setVisibility(View.VISIBLE);
                    appIcon.setImageDrawable(icon);
                    info.setAppName(appName);
                    prefHelper.writeTarget("target", info);
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }

            }

        }

    }
}
