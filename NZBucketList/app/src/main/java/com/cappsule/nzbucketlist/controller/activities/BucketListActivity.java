package com.cappsule.nzbucketlist.controller.activities;

import android.support.v4.app.Fragment;

import com.cappsule.nzbucketlist.controller.fragments.BucketListFragment;

public class BucketListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new BucketListFragment();
    }


}
