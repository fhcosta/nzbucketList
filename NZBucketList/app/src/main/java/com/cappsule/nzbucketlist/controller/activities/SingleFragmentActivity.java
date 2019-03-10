package com.cappsule.nzbucketlist.controller.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.cappsule.nzbucketlist.R;

public abstract class SingleFragmentActivity extends AppCompatActivity {

    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        //Fragment Manager is created to manage the fragments and it's lifecycle
        FragmentManager fragmentManager = getSupportFragmentManager();
        //Fragment to be inflated
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
        if (fragment == null){
            fragment = createFragment();
            //Place Fragment is added in the Fragment Manager
            fragmentManager.beginTransaction().add(R.id.fragment_container,fragment).commit();
        }
    }

}
