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
import android.widget.TextView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.novytes.fuzzz.app.helpers.PreferenceHelper;
import com.novytes.fuzzz.app.helpers.TargetHelper;
import com.novytes.fuzzz.app.models.TargetInfo;


public class SelectTargetActivity extends Activity {

    private static int REQ_ACTIVITY = 14;
    private TargetInfo info;
    private TargetHelper targetHelper;
    private PreferenceHelper prefHelper;

    private Context context;

    @InjectView(R.id.btn_select_activity) Button btnSelectActivity;
    @InjectView(R.id.btn_back) Button btnBack;
    @InjectView(R.id.btn_next) Button btnNext;
    @InjectView(R.id.btn_reset) Button btnReset;
    @InjectView(R.id.txt_activity_name) TextView txtActivityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_activity_main);

        ButterKnife.inject(this);

        context = getApplicationContext();
        targetHelper = new TargetHelper();
        prefHelper = new PreferenceHelper(context);

        info = prefHelper.getTarget("target");
        if(info.getAppName().isEmpty()){
            Toast.makeText(context, "Target application not selected", Toast.LENGTH_LONG).show();
            finish();
        }

        btnSelectActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), TargetActivityChooser.class);
                i.putExtra("appname", info.getAppName());
                startActivityForResult(i, REQ_ACTIVITY);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                targetHelper.clearField(info, 1);
                prefHelper.writeTarget("target", info);
                finish();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                targetHelper.clearAllFields(info);
                prefHelper.writeTarget("target", info);
                finish();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        info = prefHelper.getTarget("target");
        if(info.getTargetActivity().isEmpty()){
            txtActivityName.setVisibility(View.INVISIBLE);
            btnSelectActivity.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.select_target, menu);
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

            if(requestCode == REQ_ACTIVITY){
                String activityName = data.getStringExtra("activityName");
                try {
                    btnSelectActivity.setVisibility(View.INVISIBLE);
                    info.setTargetActivity(activityName);
                    prefHelper.writeTarget("target", info);
                    txtActivityName.setText(activityName);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }
    }
}
