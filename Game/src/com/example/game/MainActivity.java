package com.example.game;

import java.util.Random;

import android.R.string;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Build;

public class MainActivity extends ActionBarActivity {

	static int flag = 0;
	static TextView tvRightWrong;
    static TextView tvDebugBox;
	static EditText etUserGuess;
	static int p;
	static TextView tvDebugBoxU;
	static Random rando = new Random();
	static int userGuess;
	static String userGuessBuffer = "";
	static Button btNext;
	static Button btSubmit;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getRandom();
        
        tvRightWrong = (TextView)findViewById(R.id.textViewRightWrong);
        etUserGuess = (EditText)findViewById(R.id.editTextUserGuess);
        btNext = (Button)findViewById(R.id.buttonNext);
        btSubmit = (Button)findViewById(R.id.buttonSubmit);
        btSubmit.setEnabled(false);
        
        etUserGuess.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

               if(s.toString().trim().length()==0){
                    btSubmit.setEnabled(false);
                  } else {
                    btSubmit.setEnabled(true);
                  }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                    int after) {
                // TODO Auto-generated method stub

            }

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
        });
        
        btSubmit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				    	tvRightWrong.setVisibility(v.VISIBLE);
				    	userGuessBuffer = etUserGuess.getText().toString();
				    	userGuess = Integer.parseInt(userGuessBuffer);
				    	guessCheck(v);
			}
		});

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }
    
    public static void getRandom()
    
    {
    	p = rando.nextInt((10 - 1) + 1) + 1;
    }
    public static void guessCheck(View v)
    {
    		if(userGuess == p)
    		{
    			tvRightWrong.setText("Nice job! Tap the green checkmark, then tap the next button to move to the next round.");
    			btNext.setVisibility(View.VISIBLE);
    			btSubmit.setVisibility(View.INVISIBLE);
    		}
    		else
    		{
    			tvRightWrong.setText("Not quite, try again");
    			etUserGuess.setText("");
    		}
    }
    public void nextPage(View v)
    {
    	Intent intent = new Intent(MainActivity.this, PageTwo.class);
    	startActivity(intent);
    }
    protected void onRestart()
	{
		super.onRestart();
	}
    
    protected void onResume()
	{
		super.onResume();
		if(flag == 0)
		{
			flag++;
		}
		else if(flag == 1)
		{
			finish();
		}
	}
}
