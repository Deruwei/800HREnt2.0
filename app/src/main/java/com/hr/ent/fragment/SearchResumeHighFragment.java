package com.hr.ent.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hr.ent.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchResumeHighFragment extends Fragment {
    public SearchResumeHighFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_resume_high, container, false);
    }
}
