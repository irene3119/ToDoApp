package com.simpleapp.irene.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class EditItemActivity extends AppCompatActivity {

    EditText etEditItem;
    TodoItem editItem;
    int pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        editItem = (TodoItem) getIntent().getSerializableExtra("editItem");
        etEditItem = (EditText) findViewById(R.id.etEditItem);
        etEditItem.setText(editItem.getTitle());
        pos = getIntent().getIntExtra("pos",0);
    }

    public void saveEditItem(View v) {
        // Prepare data intent
        Intent data = new Intent();
        // Pass relevant data back as a result
        editItem.setTitle(etEditItem.getText().toString());
        data.putExtra("editItem", editItem);
        data.putExtra("pos", pos);
        // Activity finished ok, return the data
        setResult(RESULT_OK, data); // set result code and bundle data for response
        finish(); // closes the activity, pass data to parent
    }

}
