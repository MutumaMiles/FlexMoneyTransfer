package app.com.flexmoneytransfer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Currency;
import java.util.Locale;

import app.com.flexmoneytransfer.adapters.CurrencyAdapter;
import app.com.flexmoneytransfer.model.CurrencyModel;

public class CurrencyList extends AppCompatActivity  {
    RecyclerView recyclerView;

    public static Intent newInstance(Context context) {
        return new Intent(context, CurrencyList.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        EditText searchCurrency = findViewById(R.id.search_currency);
        searchCurrency.clearFocus();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = findViewById(R.id.currency_list_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CurrencyAdapter(getCountriesAndCurrency(), this));
    }

    public ArrayList<CurrencyModel> getCountriesAndCurrency() {
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
        return result;
    }

    private String localeToEmoji(String countryCode) {
        int firstLetter = Character.codePointAt(countryCode, 0) - 0x41 + 0x1F1E6;
        int secondLetter = Character.codePointAt(countryCode, 1) - 0x41 + 0x1F1E6;
        return new String(Character.toChars(firstLetter)) + new String(Character.toChars(secondLetter));
    }

    public void onCurrencyClicked(CurrencyModel currencyModel) {
        Intent intent=new Intent(this,CurrencyList.class);
        intent.putExtra("currency_flag",currencyModel.getCountryFlag());
        intent.putExtra("currency_code",currencyModel.getSymbol());
        setResult(RESULT_OK, intent);
        finish();
    }
}
