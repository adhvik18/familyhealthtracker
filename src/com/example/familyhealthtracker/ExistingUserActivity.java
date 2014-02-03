package com.example.familyhealthtracker;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ExistingUserActivity extends Activity {
	
	public ListView listSavedFiles;
	public String[] SavedFiles;
	public static final String EXTRA_MESSAGE1 = "USER_NAME";
	public static final String EXTRA_MESSAGE2 = "AGE";
	public static final String NEXT_LINE="\n";
	public String userName;
	public String age;
	public String gender;
	public String[] menuItems={"View","Delete"};

	
	public void initialize()
	{
		listSavedFiles = (ListView)findViewById(R.id.listView1);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_existing_user);
		initialize();
		showSavedFiles();
		
		listSavedFiles.setClickable(true);
		listSavedFiles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	    
		  @Override
		  public void onItemClick(AdapterView<?> arg0,View v,int position,long id) {

		    Object o = listSavedFiles.getItemAtPosition(position);
		    Intent next = new Intent(ExistingUserActivity.this,DetailUserName.class);
			next.putExtra(EXTRA_MESSAGE1,o.toString());
		    startActivity(next);
			}
		});
		
		/*listSavedFiles.setOnItemLongClickListener(new OnItemLongClickListener() {
			public boolean onItemLongClick(AdapterView<?> parent,View v,int position,long id) {
				
				Object o = listSavedFiles.getItemAtPosition(position);
				File dir=getFilesDir();
				File file=new File(dir,o.toString());
				boolean deleted=file.delete();
				return true;
			}
			
			
		}); */
		
}
	
	public void showSavedFiles(){
		   SavedFiles = getApplicationContext().fileList();
		   ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,
		     SavedFiles);

		   listSavedFiles.setAdapter(adapter);
		   registerForContextMenu(listSavedFiles);
		  }
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
	  if (v.getId()==R.id.listView1) {
	    AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
	    menu.setHeaderTitle(SavedFiles[info.position]);
	    for (int i = 0; i<menuItems.length; i++) {
	      menu.add(Menu.NONE, i, i, menuItems[i]);
	    }
	  }
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
	  AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
	  int menuItemIndex = item.getItemId();
	  String menuItemName = menuItems[menuItemIndex];
	  String listItemName = SavedFiles[info.position];
	  if(menuItemName=="View")
	  {
		    Intent next = new Intent(ExistingUserActivity.this,DetailUserName.class);
			next.putExtra(EXTRA_MESSAGE1,listItemName);
		    startActivity(next);  
	  }
	  else if(menuItemName=="Delete")
	  {
		   File dir=getFilesDir();
		   File file=new File(dir,listItemName);
		   boolean deleted=file.delete();
	  }
	  return true;
	  
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.existing_user, menu);
		return true;
	}

}
