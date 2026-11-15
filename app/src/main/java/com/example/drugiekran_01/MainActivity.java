package com.example.drugiekran_01;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SeekBar;
import android.widget.CheckBox;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner spCourse = findViewById(R.id.spCourse);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.courses,
                android.R.layout.simple_spinner_item
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCourse.setAdapter(adapter);

        findViewById(R.id.btnNext).setOnClickListener(v -> openSummary());
    }

    private void openSummary() {
        EditText etName = findViewById(R.id.etName);
        EditText etEmail = findViewById(R.id.etEmail);
        Spinner spCourse = findViewById(R.id.spCourse);
        SeekBar sbLevel = findViewById(R.id.sbLevel);
        CheckBox cbNewsletter = findViewById(R.id.cbNewsletter);

        Intent intent = new Intent(this, SummaryActivity.class);

        intent.putExtra(SummaryActivity.EXTRA_NAME, etName.getText().toString());
        intent.putExtra(SummaryActivity.EXTRA_EMAIL, etEmail.getText().toString());
        intent.putExtra(SummaryActivity.EXTRA_COURSE, spCourse.getSelectedItem().toString());
        intent.putExtra(SummaryActivity.EXTRA_LEVEL, sbLevel.getProgress());
        intent.putExtra(SummaryActivity.EXTRA_NEWSLETTER, cbNewsletter.isChecked());

        startActivity(intent);
    }
}