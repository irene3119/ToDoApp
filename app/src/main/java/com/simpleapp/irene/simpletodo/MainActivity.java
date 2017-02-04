package com.simpleapp.irene.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<TodoItem> items;
    TodoItemAdapter itemsAdapter;
    ListView lvItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvItems = (ListView) findViewById(R.id.lvItems);
        readItems();
        itemsAdapter = new TodoItemAdapter(this, items);
        lvItems.setAdapter(itemsAdapter);
        setupListViewListener();
    }

    public void onAddItem(View v) {
        EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
        String itemText = etNewItem.getText().toString();
        TodoItem newItem = new TodoItem();
        newItem.setTitle(itemText);
        itemsAdapter.add(newItem);
        etNewItem.setText("");
        updateItems(newItem);
    }

    private void setupListViewListener() {
        lvItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter, View item, int pos,long id) {
                        deleteItem(pos);
                        items.remove(pos);
                        itemsAdapter.notifyDataSetChanged();
                        return true;
                    }
                }
        );//delete item

        lvItems.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapter, View item, int pos,long id) {
                        TodoItem editItem = items.get(pos);
                        launchComposeView(editItem,pos);
                    }
                }
        );//edit item
    }

    private void readItems() {

        items = (ArrayList)SQLite.select().
                from(TodoItem.class).queryList();
    }

    private void updateItems(TodoItem item) {
        item.save();
    }

    private void deleteItem(int index) {
        TodoItem delItem = new TodoItem();
        delItem.setId(items.get(index).getId());
        delItem.setTitle(items.get(index).getTitle());
        delItem.delete();
    }

    //launch sub-activity
    private final int REQUEST_CODE = 20;
    public void launchComposeView(TodoItem item, int pos) {
        // first parameter is the context, second is the class of the activity to launch
        Intent i = new Intent(MainActivity.this, EditItemActivity.class);
        // put "extras" into the bundle for access in the second activity
        i.putExtra("editItem", item);
        i.putExtra("pos",pos);
        // brings up the second activity
        startActivityForResult(i, REQUEST_CODE);
    }

    //return from sub-activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // REQUEST_CODE is defined above
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            // Extract name value from result extras
            TodoItem item = (TodoItem) data.getSerializableExtra("editItem");
            int pos = data.getExtras().getInt("pos");
            items.set(pos,item);
            itemsAdapter.notifyDataSetChanged();
            updateItems(item);
        }
    }
}
