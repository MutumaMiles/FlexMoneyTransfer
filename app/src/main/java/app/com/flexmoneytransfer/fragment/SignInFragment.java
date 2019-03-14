package app.com.flexmoneytransfer.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import app.com.flexmoneytransfer.MainActivity;
import app.com.flexmoneytransfer.R;


public class SignInFragment extends Fragment {

    public static SignInFragment newInstance(){
        return new SignInFragment();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.login_layout,container,false);
        TextView createAccountTxt=view.findViewById(R.id.create_account);

        final Fragment fragment=new SignUpFragment();

        createAccountTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_from_right, R.anim.slide_to_left, R.anim.slide_from_left, R.anim.slide_to_right).replace(R.id.fragment_container, fragment).addToBackStack(null).commit();
            }
        });
        MainActivity.setmFragment(fragment);
        return view;
    }

}
