package com.frv.messenger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class CreateMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_message);
    }

    public void onSendMessage(View view) {
        EditText message = (EditText) findViewById(R.id.message);
        String textMessage = message.getText().toString();
//        Intent intent = new Intent(this, ReceiveMessageActivity.class);
//        intent.putExtra(ReceiveMessageActivity.EXTRA_MESSAGE, textMessage);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");

        String chooserTitle = getString(R.string.chooser);
        Intent chooserIntent = Intent.createChooser(intent, chooserTitle);

        intent.putExtra(Intent.EXTRA_TEXT, textMessage);
//        startActivity(intent);
        startActivity(chooserIntent);
    }
}