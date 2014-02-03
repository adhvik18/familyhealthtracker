package com.example.familyhealthtracker;

import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;

public class HeartRate extends Activity {
	
	public Button cancelButton;
	public TextView time;
	
	public void initialize()
	{
		cancelButton= (Button)findViewById(R.id.buttonCancel);
		time = (TextView) findViewById(R.id.timestamp);
		java.text.DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());
		String date = dateFormat.format(new Date());
		time.setText(date);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_heart_rate);
		initialize();
		GraphViewData[] data = new GraphViewData[10];
        double v = 0;
        for (int i = 0; i < 10; i++) {
            v += 0.1;
            data[i] = new GraphViewData(i, Math.sin(v));            
        }
        GraphViewSeries seriesSin = new GraphViewSeries("Sinus", null, data);  
        GraphView graphView = new LineGraphView(this, "DemoGraphView");
        graphView.addSeries(seriesSin);
        graphView.setViewPort(2, 10);

        LinearLayout layout = (LinearLayout) findViewById(R.id.subLayout);  
        layout.addView(graphView);       
        cancelButton.setOnClickListener(new View.OnClickListener(){
			
			@Override
			public void onClick(View arg0){
				//TODO Auto-generated method stub
				cancelButtonAction();
			}
		});
	}

	
	
	public void cancelButtonAction()
    {
    	finish();
    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.heart_rate, menu);
		return true;
	}

}
