package app.com.flexmoneytransfer;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class RecipientsActivity extends AppCompatActivity {

    public static Intent newInstance(Context context){
        return new Intent(context, RecipientsActivity.class);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recepeint);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.currencyColor), PorterDuff.Mode.SRC_ATOP);

        View view = findViewById(R.id.add_recipient_layout);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(AddRecipientActivity.newInstance(RecipientsActivity.this));
            }
        });
        CollapsingToolbarLayout toolbarLayout = findViewById(R.id.collapsing_toolbar);
        toolbarLayout.setTitle("Who are you sending to");
    }
}
