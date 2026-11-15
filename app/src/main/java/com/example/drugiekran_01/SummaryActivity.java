
package com.example.drugiekran_01;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SummaryActivity extends AppCompatActivity {

    public static final String EXTRA_NAME = "com.example.drugiekran_01.NAME";
    public static final String EXTRA_EMAIL = "com.example.drugiekran_01.EMAIL";
    public static final String EXTRA_COURSE = "com.example.drugiekran_01.COURSE";
    public static final String EXTRA_LEVEL = "com.example.drugiekran_01.LEVEL";
    public static final String EXTRA_NEWSLETTER = "com.example.drugiekran_01.NEWSLETTER";
    public static final String EXTRA_RESULT = "com.example.drugiekran_01.RESULT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        String name = getIntent().getStringExtra(EXTRA_NAME);
        String email = getIntent().getStringExtra(EXTRA_EMAIL);
        String course = getIntent().getStringExtra(EXTRA_COURSE);
        int level = getIntent().getIntExtra(EXTRA_LEVEL, 0);
        boolean newsletter = getIntent().getBooleanExtra(EXTRA_NEWSLETTER, false);

        String newsletterText = newsletter
                ? getString(R.string.yes_short)
                : getString(R.string.no_short);

        String summary = getString(
                R.string.summary_format,
                name,
                email,
                course,
                level,
                newsletterText
        );

        TextView tvSummary = findViewById(R.id.tvSummary);
        tvSummary.setText(summary);

        findViewById(R.id.btnEdit).setOnClickListener(v -> finish());

        findViewById(R.id.btnConfirm).setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra(EXTRA_RESULT, "Zapis został zatwierdzony");
            setResult(RESULT_OK, resultIntent);
            finish();
        });

        findViewById(R.id.btnShare).setOnClickListener(v -> {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, summary);

            try {
                startActivity(Intent.createChooser(
                        shareIntent,
                        getString(R.string.share_via)
                ));
            } catch (ActivityNotFoundException e) {
                Toast.makeText(
                        this,
                        getString(R.string.error_no_app),
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }
}
