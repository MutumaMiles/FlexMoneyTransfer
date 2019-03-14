package app.com.flexmoneytransfer.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.List;

import app.com.flexmoneytransfer.CurrencyList;
import app.com.flexmoneytransfer.model.CurrencyModel;
import app.com.flexmoneytransfer.R;

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder> {
    private List<CurrencyModel> currencyModelList;
    private int lastPosition = -1;
    private Context context;

    public CurrencyAdapter(List<CurrencyModel> currencyModelList, Context context) {
        this.currencyModelList = currencyModelList;
        this.context=context;
    }

    @NonNull
    @Override
    public CurrencyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.currency_layout,null);
        return new CurrencyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrencyViewHolder currencyViewHolder, int i) {
        final CurrencyModel model=currencyModelList.get(i);

        currencyViewHolder.bindCurrency(model);
        setAnimation(currencyViewHolder.itemView,i);
        currencyViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CurrencyList currencyList = (CurrencyList) context;
                currencyList.onCurrencyClicked(model);

            }
        });
    }

    @Override
    public int getItemCount() {
        return currencyModelList.size();
    }
    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    public class CurrencyViewHolder extends RecyclerView.ViewHolder {
        private TextView currencyName,currencySymbol,countryFlag;
        public CurrencyViewHolder(@NonNull View itemView) {
            super(itemView);
            currencyName=itemView.findViewById(R.id.currency_name);
            currencySymbol=itemView.findViewById(R.id.currency_symbol);
            countryFlag=itemView.findViewById(R.id.country_flag);
        }

        void bindCurrency(CurrencyModel currencyModel){
            currencyName.setText(currencyModel.getCurrencyName());
            currencySymbol.setText(currencyModel.getSymbol());
            countryFlag.setText(currencyModel.getCountryFlag());
        }
    }
}
