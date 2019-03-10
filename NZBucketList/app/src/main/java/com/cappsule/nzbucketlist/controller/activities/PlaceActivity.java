package com.cappsule.nzbucketlist.controller.activities;


import java.util.UUID;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.cappsule.nzbucketlist.controller.fragments.PlaceFragment;


public class PlaceActivity extends SingleFragmentActivity{

    public static final String EXTRA_PLACE_ID = "com.cappsule.nzbucketlist.place_id";

    public static Intent newIntent(Context packageContext, UUID placeID){
        Intent intent = new Intent(packageContext, PlaceActivity.class);
        intent.putExtra(EXTRA_PLACE_ID,placeID);

        return intent;
    }


    @Override
    protected Fragment createFragment() {

        return new PlaceFragment();
    }
}
