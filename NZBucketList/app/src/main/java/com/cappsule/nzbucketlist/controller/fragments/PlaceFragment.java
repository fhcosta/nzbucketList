package com.cappsule.nzbucketlist.controller.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;
import com.cappsule.nzbucketlist.R;
import com.cappsule.nzbucketlist.controller.activities.PlaceActivity;
import com.cappsule.nzbucketlist.model.BucketList;
import com.cappsule.nzbucketlist.model.Place;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

public class PlaceFragment extends Fragment {

    private Place mPlace;
    private TextView mPlaceNameField;
    private TextView mPlaceShortDescriptionField;
    private ImageView mPlaceImageView;
    private CheckBox mWasVisited;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID placeID = (UUID)getActivity().getIntent().getSerializableExtra(PlaceActivity.EXTRA_PLACE_ID);
        mPlace = BucketList.get(getActivity()).getPlace(placeID);
        if (mPlace.wasVisited()) {
            setHasOptionsMenu(true);
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View placeView = inflater.inflate(R.layout.fragment_place, container, false);

        mPlaceNameField = (TextView)placeView.findViewById(R.id.txtDetailPlaceName);


        mPlaceShortDescriptionField = (TextView) placeView.findViewById(R.id.txtPlaceShortDescription);


        mPlaceImageView = (ImageView)placeView.findViewById(R.id.imgPlaceImage);

        mWasVisited = (CheckBox)placeView.findViewById(R.id.ckbxPlaceVisited);



        mPlaceNameField.setText(mPlace.getmName());
        mPlaceNameField.setFocusable(false);
        mPlaceShortDescriptionField.setText(mPlace.getmShortDescription());
        mPlaceShortDescriptionField.setFocusable(false);
        int resID = getResources().getIdentifier(mPlace.getmImageName(), "drawable" , getActivity().getPackageName());
        mPlaceImageView.setImageResource(resID);
        mWasVisited.setChecked(mPlace.wasVisited());

        mWasVisited.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPlace.wasVisited()){
                    mPlace.setmVisited(false);
                    setHasOptionsMenu(false);
                }else{

                    setHasOptionsMenu(true);

                    mPlace.setmVisited(true);
                }

            }
        });

        return placeView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_place, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        shareImage();

        return super.onOptionsItemSelected(item);
    }

    public void shareImage(){

        int resID = getResources().getIdentifier(mPlace.getmImageName(), "drawable" , getActivity().getPackageName());

        Bitmap b =BitmapFactory.decodeResource(getResources(),resID);
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("image/jpg");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(getContext().getContentResolver(),
                b, mPlace.getmImageName(), null);
        Uri imageUri =  Uri.parse(path);
        share.putExtra(Intent.EXTRA_STREAM, imageUri);


        String shareDescription = "I visited " + mPlace.getmName() + "!\n" + mPlace.getmShortDescription();
        share.putExtra(Intent.EXTRA_TEXT,shareDescription);

        startActivity(Intent.createChooser(share, "Select"));

    }

}
