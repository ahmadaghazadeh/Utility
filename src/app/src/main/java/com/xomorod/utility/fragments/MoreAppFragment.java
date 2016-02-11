package com.xomorod.utility.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xomorod.utility.R;
import com.xomorod.utility.business.CardViewType;
import com.xomorod.utility.business.TitleSection;

import butterknife.Bind;
import butterknife.ButterKnife;
import jp.satorufujiwara.binder.recycler.RecyclerBinderAdapter;

/**
 * Created by Ahmad on 2/7/2016.
 */
public class MoreAppFragment extends Fragment{

    private SwipeRefreshLayout swipeContainer;
    public static final String ARG_PAGE = "ARG_PAGE";

    private String mUrl;

    public static MoreAppFragment newInstance(String url) {
        Bundle args = new Bundle();
        args.putString(ARG_PAGE, url);
        MoreAppFragment fragment = new MoreAppFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUrl = getArguments().getString(ARG_PAGE);
    }

    @Bind(R.id.recycler_view_main)
    RecyclerView recyclerView;
    private StaggeredGridLayoutManager mStaggeredLayoutManager;

    private final RecyclerBinderAdapter<TitleSection, CardViewType> adapter
            = new RecyclerBinderAdapter<>();


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        final View v = inflater.inflate(R.layout.fragment_more_app, container, false);
        ButterKnife.bind(this, v);

        return v;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mStaggeredLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

   }


}
