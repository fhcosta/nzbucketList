package com.cappsule.nzbucketlist.controller.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.cappsule.nzbucketlist.R;
import com.cappsule.nzbucketlist.controller.activities.PlaceActivity;
import com.cappsule.nzbucketlist.model.BucketList;
import com.cappsule.nzbucketlist.model.Place;

import java.util.List;

public class BucketListFragment extends Fragment {

    private RecyclerView mBucketListRecycleView;
    private PlaceAdapter mPlaceAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        refreshData();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View bucketListView = inflater.inflate(R.layout.fragment_bucket_list,container,false);

        mBucketListRecycleView = (RecyclerView) bucketListView.findViewById(R.id.bucket_list_recycle_view);

        //Recycler View requires a Layout Manager to work.
        // For this app, we are using a LinearLayoutManager as Layout Manager
        mBucketListRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));

        refreshData();

        return bucketListView;
    }

    private void refreshData(){

        BucketList bucketList = BucketList.get(getActivity());
        List<Place> places = bucketList.getPlaces();

        mPlaceAdapter = new PlaceAdapter(places);
        mBucketListRecycleView.setAdapter(mPlaceAdapter);

    }

    private class PlaceHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private Place mPlace;
        private TextView mPlaceNameTextView;
        private CheckBox mPlaceStatus;


        public PlaceHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            mPlaceNameTextView = (TextView) itemView.findViewById(R.id.txtPlaceName);
            mPlaceStatus = (CheckBox) itemView.findViewById(R.id.ckbxPlaceVisited);

            mPlaceStatus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mPlace.wasVisited()){
                        mPlace.setmVisited(false);
                    }else{
                        mPlace.setmVisited(true);
                    }

                }
            });
        }

        public void bindPlace(Place place){
            mPlace = place;
            mPlaceStatus.setChecked(mPlace.wasVisited());
            mPlaceNameTextView.setText(mPlace.getmName());
        }

        @Override
        public void onClick(View v) {
          Intent intent = PlaceActivity.newIntent(getActivity(),mPlace.getmID());
          startActivity(intent);
        }


    }


    private class PlaceAdapter extends RecyclerView.Adapter<PlaceHolder> {

        private List<Place> mPlaces;

        public PlaceAdapter(List<Place> places) {
            mPlaces = places;
        }

        @NonNull
        @Override
        public PlaceHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_place, viewGroup,false);

            return new PlaceHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull PlaceHolder placeHolder, int i) {
            Place place = mPlaces.get(i);
            placeHolder.bindPlace(place);
            placeHolder.mPlaceNameTextView.setText(place.getmName());
            placeHolder.mPlaceStatus.setChecked(place.wasVisited());


        }

        @Override
        public int getItemCount() {
            return mPlaces.size();
        }
    }
}

