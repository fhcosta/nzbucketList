package com.cappsule.nzbucketlist.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import android.content.Context;

import com.cappsule.nzbucketlist.R;

public class BucketList {

    private static BucketList sBucketList;

    private List<Place> mPlaces;

    public static BucketList get(Context context){
        if (sBucketList == null){
            sBucketList =  new BucketList(context);
        }

        return sBucketList;
    }

    private BucketList(Context context){

        mPlaces = new ArrayList<>();

        String[] registeredPlaces = context.getResources().getStringArray(R.array.places);
        String[] registeredPlacesDescription = context.getResources().getStringArray(R.array.places_description);


        for (int i = 0; i < registeredPlaces.length; i++){
            Place place = new Place();
            place.setmName(registeredPlaces[i]);
            place.setmVisited(false);
            place.setmShortDescription(registeredPlacesDescription[i]);
            place.setmImageName(registeredPlaces[i].toLowerCase());

            mPlaces.add(place);
        }

    }


    public List<Place> getPlaces(){
        return mPlaces;
    }

    public Place getPlace(UUID id){
        for (Place place : mPlaces){
            if(place.getmID().equals(id)){
                return place;
            }
        }

        return null;
    }


}
