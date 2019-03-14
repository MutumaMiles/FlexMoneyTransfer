package app.com.flexmoneytransfer;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import app.com.flexmoneytransfer.fragment.SignInFragment;

public class MainActivity extends AppCompatActivity {
    private static Fragment mFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fragment fragment = new SignInFragment();
        final FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_from_right, R.anim.slide_to_left, R.anim.slide_from_left, R.anim.slide_to_right).add(R.id.fragment_container, fragment).addToBackStack(null).commit();
    }


    public static void setmFragment(Fragment fragment) {
        mFragment = fragment;

    }
}
