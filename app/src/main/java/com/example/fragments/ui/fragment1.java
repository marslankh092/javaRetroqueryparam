package com.example.fragments.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fragments.helper.DisplayAdapter;
import com.example.fragments.helper.Operations;
import com.example.fragments.rooms.RoomDbClient;
import com.example.fragments.server.BaseApiService;
import com.example.fragments.server.ModelClassMovie;
import com.example.fragments.R;
import com.example.fragments.server.Search;
import com.example.fragments.server.UtilsAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class fragment1 extends Fragment implements Operations {

    //view for fragment
    View view;

    //service for interface retrofit
    private BaseApiService mApiService;

    //textview for listview
    TextView tv1;

    RecyclerView dataRv;
    FrameLayout frameLayout;
    private ArrayList<Search> searches = new ArrayList();
    DisplayAdapter displayAdapter;
    FragmentManager fm;

    public fragment1(FragmentManager fm) {
        this.fm = fm;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_fragment1, container, false);
        tv1 = view.findViewById(R.id.tv);
        dataRv = view.findViewById(R.id.searches_rv);
        frameLayout = view.findViewById(R.id.frameLayout);

        mApiService = UtilsAPI.getApiService();

        displayAdapter = new DisplayAdapter(fragment1.this);
        dataRv.setAdapter(displayAdapter);
        searches = (ArrayList<Search>) RoomDbClient.getInstance(getContext()).getDao().getStoredSearches();
        if (searches.size() == 0) {
            mApiService.readData("83b533f9", "movie", 1).enqueue(new Callback<ModelClassMovie>() {
                @Override
                public void onResponse(Call<ModelClassMovie> call, Response<ModelClassMovie> response) {

                    ModelClassMovie modelClassMovie;
                    modelClassMovie = response.body();

                    List<Search> listCategory = modelClassMovie.getSearch();
                    RoomDbClient.getInstance(getContext()).getDao().inAllMovies(listCategory);
                    Toast.makeText(getActivity(), "DataFrom Server", Toast.LENGTH_SHORT).show();

                    displayAdapter.setupData((ArrayList<Search>) listCategory);
                }

                @Override
                public void onFailure(Call<ModelClassMovie> call, Throwable t) {
                    Toast.makeText(getActivity(), "Failure", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(getActivity(), "Data from Local DB", Toast.LENGTH_SHORT).show();

            displayAdapter.setupData(searches);
        }


        return view;
    }

    public static int counter =1;
    @Override
    public void detailBtn(Search search) {
        //adds here counter =3
        if(counter==3){
            displayad(search);
            counter=1;
        }else {
            counter++;
            transactToFragmentB(search);
        }
    }

    private void displayad(Search search){
        //intersel.show.. func
        // onaddismiss.. transactToFragmentB(Search search) call this method here for adds
    }

    private void transactToFragmentB(Search search){

        Fragment nestedFragment = new fragment2(search.getTitle());
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.addToBackStack("fragment-2");
        transaction.add(R.id.frameLayout, nestedFragment).commit();
        frameLayout.setVisibility(View.VISIBLE);
        dataRv.setVisibility(View.GONE);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(fm!=null) {
            if (fm.getBackStackEntryCount() == 1) {
                frameLayout.setVisibility(View.GONE);
                dataRv.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void deleteBtn(Search search) {
        RoomDbClient.getInstance(getContext()).getDao().deleteMovie(search);
        searches.remove(search);
        displayAdapter.notifyDataSetChanged();
        Toast.makeText(getActivity(), "Data deleted from Local DB", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateBtn(Search search, int position) {
        search.setTitle("Updated Title at " + position);
        RoomDbClient.getInstance(getContext()).getDao().updateMovie(search);
        displayAdapter.notifyDataSetChanged();
        Toast.makeText(getActivity(), "Data updated from Local DB", Toast.LENGTH_SHORT).show();
    }
}