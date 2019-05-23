package com.example.android.tccdesign;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    Classe classe = new Classe();
    Intent abrir;

    //This is our viewPager
    private ViewPager viewPager;

    //Fragments

    CarteirinhaFragment carterinhaFragment;
    NotasFragment notasFragment;
    SecretariaFragment secretariaFragment;
    MenuItem prevMenuItem;
    CardapioFragment cardapioFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton configbutton = findViewById(R.id.configButton);
        configbutton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, settingActivity.class));
            }
        });

        //Initializing viewPager
        viewPager = findViewById(R.id.viewpager);

        //Initializing the bottomNavigationView
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_Home:
                                viewPager.setCurrentItem(0);
                                break;
                            case R.id.action_Notas:
                                viewPager.setCurrentItem(1);
                                break;
                            case R.id.action_cardapio:
                                viewPager.setCurrentItem(2);
                                break;
                            case R.id.action_secretaria:
                                viewPager.setCurrentItem(3);
                                break;
                        }
                        return false;
                    }
                });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                }
                else
                {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                Log.d("page", "onPageSelected: "+position);
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationView.getMenu().getItem(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        setupViewPager(viewPager);
    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        carterinhaFragment = new CarteirinhaFragment();
        notasFragment = new NotasFragment();
        cardapioFragment = new CardapioFragment();
        secretariaFragment = new SecretariaFragment();
        adapter.addFragment(carterinhaFragment);
        adapter.addFragment(notasFragment);
        adapter.addFragment(cardapioFragment);
        adapter.addFragment(secretariaFragment);
        viewPager.setAdapter(adapter);
    }

    protected void onStop()
    {
        super.onStop();
        // We need an Editor object to make preference changes.
        // All objects are from android.context.Context
    }



}
