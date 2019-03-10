package com.cappsule.nzbucketlist.model;

import java.util.UUID;

public class Place {

    private UUID mID;
    private String mName;
    private String mShortDescription;
    private String mImageName;
    private Boolean mVisited;

    public Place(){
        //For each member, create a random UUID for a place
        mID = UUID.randomUUID();
    }

    public UUID getmID() {
        return mID;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmShortDescription() {
        return mShortDescription;
    }

    public void setmShortDescription(String mShortDescription) {
        this.mShortDescription = mShortDescription;
    }

    public String getmImageName() {
        return mImageName;
    }

    public void setmImageName(String mImageName) {
        this.mImageName = mImageName;
    }

    public Boolean wasVisited() {
        return mVisited;
    }

    public void setmVisited(Boolean mVisited) {
        this.mVisited = mVisited;
    }

}
