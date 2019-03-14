package app.com.flexmoneytransfer;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationViewEx navigationViewEx=findViewById(R.id.bottom_navigation);
        navigationViewEx.enableAnimation(false);
        navigationViewEx.enableShiftingMode(false);
        navigationViewEx.enableItemShiftingMode(false);
        FloatingActionButton sendMoneyBtn= findViewById(R.id.send_money_btn);
        sendMoneyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(CurrencyChooserActivity.newInstance(HomeActivity.this));
            }
        });
    }
}
