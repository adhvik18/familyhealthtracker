package com.example.familyhealthtracker;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	public Button newUserButton,existingUserButton;
    public TextView text1,text2;
    public ListView fileslist;
    
    public void initialize()
    {
    	newUserButton = (Button) findViewById(R.id.buttonNewUser);
    	existingUserButton = (Button) findViewById(R.id.buttonExistingUser);
    	text1 = (TextView) findViewById(R.id.text1);
        text2 = (TextView) findViewById(R.id.text2);
        fileslist= (ListView) findViewById(R.id.listView1);
    }
    
    private List<String> getList(File parentDir, String pathToParentDir) {

        ArrayList<String> inFiles = new ArrayList<String>();
        String[] fileNames = parentDir.list();

        for (String fileName : fileNames) {
            if (fileName.toLowerCase().endsWith(".txt") || fileName.toLowerCase().endsWith(".rtf") || fileName.toLowerCase().endsWith(".txd")) {
                inFiles.add(pathToParentDir + fileName);
            } else {
                File file = new File(parentDir.getPath() + "/" + fileName);
                if (file.isDirectory()) {
                    inFiles.addAll(getList(file, pathToParentDir + fileName + "/"));
                }
            }
        }

        return inFiles;
    }
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initialize();
		File dir=getFilesDir();
		
		List<String> files = getList(dir,dir.getAbsolutePath()); 
		 
		ArrayAdapter<String> arrayAdapter =      
		         new ArrayAdapter<String>(this,android.R.layout.activity_list_item, files);
		         fileslist.setAdapter(arrayAdapter);
		
		
		newUserButton.setOnClickListener(new View.OnClickListener(){
			
			@Override
			public void onClick(View arg0){
				//TODO Auto-generated method stub
				newUserButtonAction();
			}
		});
		
		existingUserButton.setOnClickListener(new View.OnClickListener(){
			
			@Override
			public void onClick(View arg0){
				//TODO Auto-generated method stub
				existingUserButtonAction();
			}
		});
	}
	
	void newUserButtonAction()
    {
    	Intent next= new Intent(MainActivity.this,NewUserActivity.class);
    	startActivity(next);
    }

	void existingUserButtonAction()
    {
    	Intent next= new Intent(MainActivity.this,ExistingUserActivity.class);
    	startActivity(next);
    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
