package com.example.ivanovnv.secondtaskforevalution;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URLEncoder;

/**
 * Created by IvanovNV on 06.03.2018.
 */

public class FindFragment extends Fragment {

    Button mFindButton;
    TextView mFindTextView;

    public static FindFragment newInstance() {

        Bundle args = new Bundle();

        FindFragment fragment = new FindFragment();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * create view and initialize private fields
     *
     */

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fr_find,container,false);
        mFindButton = v.findViewById(R.id.bt_find);
        mFindTextView = v.findViewById(R.id.ed_find);
        return v;
    }

    /**
     * set OnClickListener for FindButton
     */
    @Override
    public void onResume() {
        mFindButton.setOnClickListener(OnFindButtonClickListener);
        super.onResume();
    }

    /**
     * activity paused, so unset OnClickListener for FindButton
     */
    @Override
    public void onPause() {
        mFindButton.setOnClickListener(null);
        super.onPause();
    }

    /**
     * Processing click on FindButton.
     * if entered text in not empty,
     * read value from SharedPreferences and create url for intent,
     * start intent
     */

    private View.OnClickListener OnFindButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if(isValidText()) {

                SharedPreferencesHelper mSharedPreferencesHelper = new SharedPreferencesHelper(getActivity());
                String value = mSharedPreferencesHelper.readValue();
                try {
                    value = value + "?q=" + URLEncoder.encode(mFindTextView.getText().toString(),"UTF-8");
                }
                catch (Throwable t)
                {}

                Uri uri=Uri.parse(value);

                Intent intent = new Intent(Intent.ACTION_VIEW,uri);

                if(intent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(intent);
                }

            }

        }
    };

    /**
     * test for entered text is not empty
     */

    private boolean isValidText(){
        if (mFindTextView.getText().toString().isEmpty())
        {
            Toast.makeText(getActivity(), R.string.toast_error,Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
