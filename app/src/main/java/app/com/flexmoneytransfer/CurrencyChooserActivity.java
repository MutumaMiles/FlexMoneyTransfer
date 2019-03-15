package app.com.flexmoneytransfer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Currency;
import java.util.Locale;
import java.util.Random;

import app.com.flexmoneytransfer.model.CurrencyModel;

public class CurrencyChooserActivity extends AppCompatActivity {
    private static double exchangeRate;
    private static final Integer SENT_CURRENCY = 24;
    private static final Integer RECEIVED_CURRENCY = 9;
    TextView currencyCode, currencyCode_2, countryFlag, countryFlag_2;
    EditText moneySent, moneyReceived;
    private static double MIN_RATE = 1;
    private static double MAX_RATE = 200.34;

    Random random = new Random();
    double rate;
    private boolean isMoneySent,isMoneyReceived;

    public static Intent newInstance(Context context) {
        return new Intent(context, CurrencyChooserActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_chooser);

        currencyCode = findViewById(R.id.currency_code);
        currencyCode_2 = findViewById(R.id.currency_code_2);
        countryFlag = findViewById(R.id.currency_flag);
        countryFlag_2 = findViewById(R.id.currency_flag_2);

        sentToEmoji(countryFlag, currencyCode);
        recievedToEmoji(countryFlag_2, currencyCode_2);

        rate = MIN_RATE + random.nextDouble() * (MAX_RATE - MIN_RATE);
        moneySent = findViewById(R.id.currency_sent);
        final double[] toBeRecieved = {Double.parseDouble(moneySent.getText().toString()) * rate};
        moneyReceived = findViewById(R.id.currency_received);
        moneyReceived.setText(String.valueOf(toBeRecieved[0]));

        moneySent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isMoneySent=true;
                isMoneyReceived=false;
            }
        });
        moneyReceived.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isMoneyReceived=true;
                isMoneySent=false;
            }
        });
        moneySent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable money) {
                toBeRecieved[0] = Double.parseDouble(money.toString()) * rate;
                Toast.makeText(CurrencyChooserActivity.this, "hey "+toBeRecieved[0], Toast.LENGTH_SHORT).show();
                if(isMoneySent)
                    moneyReceived.setText(String.valueOf(toBeRecieved[0]));
            }
        });
        moneyReceived.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable money) {
                toBeRecieved[0] = Double.parseDouble(money.toString()) / rate;
                Toast.makeText(CurrencyChooserActivity.this, "hey34 "+toBeRecieved[0], Toast.LENGTH_SHORT).show();
                if(isMoneyReceived)
                     moneySent.setText(String.valueOf(toBeRecieved[0]));
            }
        });


        View currencyToSend = findViewById(R.id.layout_one);
        View currencyToReceieved = findViewById(R.id.layout_two);

        currencyToSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = CurrencyList.newInstance(CurrencyChooserActivity.this);
                startActivityForResult(intent, SENT_CURRENCY);
            }
        });
        currencyToReceieved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = CurrencyList.newInstance(CurrencyChooserActivity.this);
                startActivityForResult(intent, RECEIVED_CURRENCY);
            }
        });

        Button proceedbtn = findViewById(R.id.proceed);
        proceedbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(RecipientsActivity.newInstance(CurrencyChooserActivity.this));
            }
        });
    }

    private void sentToEmoji(TextView countryFlagTxt, TextView countryCodeTxt) {
        ArrayList<CurrencyModel> result = new ArrayList<>();
        ArrayList<Locale> list = new ArrayList<>(Arrays.asList(Locale.getAvailableLocales()));
        for (int i = 0; i < list.size(); i++) {
            String country = list.get(i).getDisplayCountry();
            try {
                Locale pickedLocal = new Locale(list.get(i).getISO3Language(), list.get(i).getCountry());
                String code = Currency.getInstance(pickedLocal).getCurrencyCode();
                Currency currency = Currency.getInstance(code);
                String currencySymbol = currency.getSymbol(pickedLocal);
                if (currencySymbol.length() > 1) {
                    currencySymbol = currency.getSymbol();
                }
                CurrencyModel model = new CurrencyModel();
                model.setCurrencyName(currency.getDisplayName());
                model.setSymbol(code);
                model.setCountryFlag(localeToEmoji(code));
                result.add(model);
            } catch (Exception e) {
            }
        }
        countryFlagTxt.setText(result.get(0).getCountryFlag());
        countryCodeTxt.setText(result.get(0).getSymbol());
    }

    private void recievedToEmoji(TextView countryFlagTxt, TextView countryCodeTxt) {
        ArrayList<CurrencyModel> result = new ArrayList<>();
        ArrayList<Locale> list = new ArrayList<>(Arrays.asList(Locale.getAvailableLocales()));
        for (int i = 0; i < list.size(); i++) {
            String country = list.get(i).getDisplayCountry();
            try {
                Locale pickedLocal = new Locale(list.get(i).getISO3Language(), list.get(i).getCountry());
                String code = Currency.getInstance(pickedLocal).getCurrencyCode();
                Currency currency = Currency.getInstance(code);
                String currencySymbol = currency.getSymbol(pickedLocal);
                if (currencySymbol.length() > 1) {
                    currencySymbol = currency.getSymbol();
                }
                CurrencyModel model = new CurrencyModel();
                model.setCurrencyName(currency.getDisplayName());
                model.setSymbol(code);
                model.setCountryFlag(localeToEmoji(code));
                result.add(model);
            } catch (Exception e) {
            }
        }
        countryFlagTxt.setText(result.get(1).getCountryFlag());
        countryCodeTxt.setText(result.get(1).getSymbol());
    }

    private String localeToEmoji(String countryCode) {
        int firstLetter = Character.codePointAt(countryCode, 0) - 0x41 + 0x1F1E6;
        int secondLetter = Character.codePointAt(countryCode, 1) - 0x41 + 0x1F1E6;
        return new String(Character.toChars(firstLetter)) + new String(Character.toChars(secondLetter));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == SENT_CURRENCY) {
            if (resultCode == Activity.RESULT_OK) {
                countryFlag.setText(data.getStringExtra("currency_flag"));
                currencyCode.setText(data.getStringExtra("currency_code"));
            }
        }
        if (requestCode == RECEIVED_CURRENCY) {
            if (resultCode == Activity.RESULT_OK) {
                countryFlag_2.setText(data.getStringExtra("currency_flag"));
                currencyCode_2.setText(data.getStringExtra("currency_code"));
            }
        }
        rate = MIN_RATE + random.nextDouble() * (MAX_RATE - MIN_RATE);
    }
}