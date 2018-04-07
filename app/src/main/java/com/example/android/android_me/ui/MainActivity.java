package com.example.android.android_me.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

public class MainActivity
        extends AppCompatActivity
        implements OnImageClickListener {
    //Variables to store the values for the list index of the selected images
    private int headIndex;
    private int bodyIndex;
    private int legIndex;

    //The variable to track whether to display a two-pane or single-pane UI
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Determine if we are making a two-pane display
        if (findViewById(R.id.android_me_linear_layout) != null) {
            mTwoPane = true;

            //Get rid of the "Next" button
            Button nextButton = (Button) findViewById(R.id.next_button);
            nextButton.setVisibility(View.GONE);

            //Change the GridView to space out the images more on tablet
            GridView gridView = (GridView) findViewById(R.id.images_grid_view);
            gridView.setNumColumns(2);

            //Create new body part fragments
            if (savedInstanceState == null) {
                //Add the fragment to its container using a FragmentManager and a Transaction
                FragmentManager fragmentManager = getSupportFragmentManager();

                //Head fragment
                BodyPartFragment headFragment = new BodyPartFragment();
                headFragment.setImageIds(AndroidImageAssets.getHeads());
                fragmentManager.beginTransaction()
                        .add(R.id.head_container, headFragment)
                        .commit();

                //Body fragment
                BodyPartFragment bodyFragment = new BodyPartFragment();
                bodyFragment.setImageIds(AndroidImageAssets.getBodies());
                fragmentManager.beginTransaction()
                        .add(R.id.body_container, bodyFragment)
                        .commit();

                //Leg fragment
                BodyPartFragment legFragment = new BodyPartFragment();
                legFragment.setImageIds(AndroidImageAssets.getLegs());
                fragmentManager.beginTransaction()
                        .add(R.id.leg_container, legFragment)
                        .commit();
            }
        } else {
            mTwoPane = false;
        }
    }

    //Define the behaviour for onImageSelected
    public void onImageSelected(int position) {
        Toast.makeText(this, "Postition clicked = " + position, Toast.LENGTH_SHORT).show();

        int bodyPartNumber = position / 12;
        int listIndex = position - 12 * bodyPartNumber;

        if (mTwoPane) {
            //Create two-pane interaction
            BodyPartFragment newFragment = new BodyPartFragment();

            //Set the currently displayed item for the correct body part fragment
            switch (bodyPartNumber) {
                case 0:
                    newFragment.setImageIds(AndroidImageAssets.getHeads());
                    newFragment.setListIndex(listIndex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.head_container, newFragment)
                            .commit();
                    break;
                case 1:
                    newFragment.setImageIds(AndroidImageAssets.getBodies());
                    newFragment.setListIndex(listIndex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.body_container, newFragment)
                            .commit();
                    break;
                case 2:
                    newFragment.setImageIds(AndroidImageAssets.getLegs());
                    newFragment.setListIndex(listIndex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.leg_container, newFragment)
                            .commit();
                    break;
                default:
                    break;
            }
        } else {
            //Set the currently displayed item for the correct body part fragment
            switch (bodyPartNumber) {
                case 0:
                    headIndex = listIndex;
                    break;
                case 1:
                    bodyIndex = listIndex;
                    break;
                case 2:
                    legIndex = listIndex;
                    break;
                default:
                    break;
            }

            //Put this info in a Bundle and attach it to an Intent that will launch an AndroidMeActivity
            Bundle b = new Bundle();
            b.putInt("headIndex", headIndex);
            b.putInt("bodyIndex", bodyIndex);
            b.putInt("legIndex", legIndex);

            //Attach the Bundle to an intent
            final Intent intent = new Intent(this, AndroidMeActivity.class);
            intent.putExtras(b);

            //The "Next" button launches a new AndroidMeActivity
            Button nextButton = (Button) findViewById(R.id.next_button);
            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(intent);
                }
            });
        }
    }
}
