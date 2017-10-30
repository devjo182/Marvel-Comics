package com.app.devjo.marvelcomics.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.app.devjo.marvelcomics.R;
import com.app.devjo.marvelcomics.adapter.ComicsAdapter;
import com.app.devjo.marvelcomics.apiConnect.ApiEndpointsInterface;
import com.app.devjo.marvelcomics.apiConnect.RetrofitApiConnect;
import com.app.devjo.marvelcomics.models.Comic;
import com.app.devjo.marvelcomics.models.ComicDataWrapperModel;
import com.app.devjo.marvelcomics.models.Configuration;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public Context mContext;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager ;

    private CompositeDisposable mCompositeDisposable;
    private List<Comic> mListComics;
    private ComicDataWrapperModel comicDataWrapperModelData;
    private ApiEndpointsInterface apiServiceClient;
    RetrofitApiConnect retrofitApiConnect;
    private String TitleStartsWith="";
    private Boolean searchBy = false;
    Intent mIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //this.subscription = Observable.subscribe();
        mContext = getApplicationContext();
        String option=  Configuration.COMICS_LIST_DEFAULT;;

        mIntent = getIntent();
        if (mIntent.getData() != null) {
            option= getIntent().getExtras().getString("option");
            TitleStartsWith= getIntent().getExtras().getString("valueFindByTitle");
            searchBy = getIntent().getExtras().getBoolean("valueOrderBy");
            if(!TitleStartsWith.equals(""))
                option = Configuration.COMICS_LIST_FIND_TITLE;
            if(searchBy)
                option = Configuration.COMICS_LIST_ORDER_BY;
        }

        retrofitApiConnect = RetrofitApiConnect.getRetrofitConnect();
        apiServiceClient =  retrofitApiConnect.getRetrofit().create(ApiEndpointsInterface.class);
        initRecyclerView();
        loadDataApi(option);

        //Limpiamos los ítems del adaptador
        //adaptador.clear();

        //Cargamos el adaptador con nuevos datos...

        //Refrescamos el adaptador
        //adaptador.notifyDataSetChanged();
    }

    private void initRecyclerView() {

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
       // GridLayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }


    private void loadDataApi(String opt) {
        mCompositeDisposable = new CompositeDisposable();
        switch(opt){
            case Configuration.COMICS_LIST_DEFAULT:
                mCompositeDisposable.add(apiServiceClient.getListComics(
                        Configuration.getPublicKey(),
                        Configuration.getTs(),
                        Configuration.getHash(),
                        Configuration.getLIMIT()
                ).observeOn(AndroidSchedulers.mainThread()
                ).subscribeOn(Schedulers.io()
                ).subscribe(this::handleResponse,this::handleError));
            break;
            case Configuration.COMICS_LIST_FIND_TITLE:
                    mCompositeDisposable.add(apiServiceClient.getListComicsByTitleStartsWith(
                            Configuration.getPublicKey(),
                            Configuration.getTs(),
                            Configuration.getHash(),
                            Configuration.getLIMIT(),
                            TitleStartsWith
                    ).observeOn(AndroidSchedulers.mainThread()
                    ).subscribeOn(Schedulers.io()
                    ).subscribe(this::handleResponse,this::handleError));
            case Configuration.COMICS_LIST_ORDER_BY:
                   mCompositeDisposable.add(apiServiceClient.getListComicsOrderBy(
                           Configuration.getPublicKey(),
                           Configuration.getTs(),
                           Configuration.getHash(),
                           Configuration.getLIMIT(),
                           Configuration.getTitle()
                   ).observeOn(AndroidSchedulers.mainThread()
                   ).subscribeOn(Schedulers.io()
                   ).subscribe(this::handleResponse,this::handleError));
            default: restartActivity(Configuration.COMICS_LIST_DEFAULT, "", false);
        }
    }

    private void handleResponse(ComicDataWrapperModel comicDataWrapperModel) {
        comicDataWrapperModelData = new ComicDataWrapperModel();
        comicDataWrapperModelData = comicDataWrapperModel;
        mListComics = new ArrayList<>(comicDataWrapperModelData.getData().getResults());
        mAdapter = new ComicsAdapter(mListComics, mContext);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void restartActivity(String opt, String valueFindByTitle, Boolean valueOrderBy){
        mIntent = new Intent(this, Main2Activity.class);
        Bundle mBundle = new Bundle();
        mBundle.putString("option", opt);
        mBundle.putString("valueFindByTitle", valueFindByTitle);
        mBundle.putBoolean("valueOrderBy", valueOrderBy);
        mIntent.putExtras(mBundle);
        startActivity(mIntent);
        finish();
    }

    private void handleError(Throwable error) {
        Toast.makeText(this, "Error "+error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        return;
        //super.onBackPressed();
    }

    private void closeDrawer (){
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

   // @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.clear();
    }
}
