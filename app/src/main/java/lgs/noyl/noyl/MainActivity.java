package lgs.noyl.noyl;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    private AdView mAdView;
    EditText Y_et;
    EditText M_et;
    EditText D_et;
    Button result_button;
    TextView result;
    AlertDialog dialog;
    RadioGroup radioGroup;
    long result_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(MainActivity.this, getString(R.string.admob_app_id));

        mAdView = findViewById(R.id.adView);

        AdRequest adRequest = new AdRequest.Builder().build();

        mAdView.loadAd(adRequest);

        Y_et = findViewById(R.id.put_Y_edit);
        M_et = findViewById(R.id.put_M_edit);
        D_et = findViewById(R.id.put_D_edit);
        result_button = findViewById(R.id.result_button);
        result = findViewById(R.id.result);
        radioGroup = findViewById(R.id.radiogroup);

        result_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Y_et.getText().toString().length() == 0 || M_et.getText().toString().length() == 0 || D_et.getText().toString().length() == 0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View view = inflater.inflate(R.layout.bulider_,null);
                    builder.setView(view);
                    Button ok = view.findViewById(R.id.ok);
                    ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                    dialog = builder.create();
                    dialog.show();
                }else {
                    int get_Y = Integer.parseInt(Y_et.getText().toString());
                    int get_M = Integer.parseInt(M_et.getText().toString());
                    int get_D = Integer.parseInt(D_et.getText().toString());
                    long now = System.currentTimeMillis();
                    Date now_date = new Date(now);
                    SimpleDateFormat Y = new SimpleDateFormat("yyyy");
                    SimpleDateFormat M = new SimpleDateFormat("MM");
                    SimpleDateFormat D = new SimpleDateFormat("dd");
                    int now_Y = Integer.parseInt(Y.format(now_date));
                    int now_M = Integer.parseInt(M.format(now_date));
                    int now_D = Integer.parseInt(D.format(now_date));
                    Calendar cal2 = new GregorianCalendar(now_Y,now_M,now_D);
                    Calendar cal1 = new GregorianCalendar(get_Y,get_M,get_D);
                    result_time = cal2.getTimeInMillis() - cal1.getTimeInMillis();
                    long get_result_time = result_time;
                    get_result_time = get_result_time / 1000;
                    get_result_time = get_result_time / 3600;
                    get_result_time = get_result_time / 24;
                    result.setText(get_result_time + "일 \n 살아오셨네요!");
                }
            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.D : {
                        long get_result_time = result_time;
                        get_result_time = get_result_time / 1000;
                        get_result_time = get_result_time / 3600;
                        get_result_time = get_result_time / 24;
                        result.setText(get_result_time + "일 \n 살아오셨네요!");
                        break;
                    }
                    case R.id.H : {
                        long get_result_time = result_time;
                        get_result_time = get_result_time / 1000;
                        get_result_time = get_result_time / 3600;
                        result.setText(get_result_time + "시 \n 살아오셨네요!");
                        break;
                    }
                    case R.id.m : {
                        long get_result_time = result_time;
                        get_result_time = get_result_time / 1000;
                        get_result_time = get_result_time / 60;
                        result.setText(get_result_time + "분 \n 살아오셨네요!");
                        break;
                    }
                    case R.id.S : {
                        long get_result_time = result_time;
                        get_result_time = get_result_time / 1000;
                        result.setText(get_result_time + "초 \n 살아오셨네요!");
                        break;
                    }
                }
            }
        });

    }
}
