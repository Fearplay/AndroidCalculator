package com.example.androidcalculator;

import android.content.Context;
import android.content.res.Resources;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.text.DecimalFormat;

public class AndroidCalculatorMainActivity extends AppCompatActivity {
    private Button calculateButton;

    private TextView firstNumberView;
    private TextView secondNumberView;
    private TextView resultView;
    private EditText firstField;
    private EditText secondField;
    private EditText resultField;
    private RadioButton additionButton;
    private RadioButton subtractionButton;
    private RadioButton multiplicationButton;
    private RadioButton divisionButton;
    private RadioGroup radioGroup;
    private double firstNumber;
    private double secondNumber;
    private double result;
    private Context context;
    private Resources resources;
    DecimalFormat smallerResult = new DecimalFormat("###,###.###");

    private boolean isEmpty(EditText editText) {
        if (editText.getText().toString().trim().length() > 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemEN:

                context = LocaleHelper.setLocale(AndroidCalculatorMainActivity.this, "en");
                resources = context.getResources();
                getSupportActionBar().setTitle(resources.getString(R.string.app_name));
                calculateButton.setText(resources.getString(R.string.calculate_button));
                firstNumberView.setText(resources.getString(R.string.first_number_view));
                secondNumberView.setText(resources.getString(R.string.second_number_view));
                resultView.setText(resources.getString(R.string.result_view));
                firstField.setHint(resources.getString(R.string.hint_field));
                secondField.setHint(resources.getString(R.string.hint_field));
                additionButton.setText(resources.getString(R.string.radio_addition));
                subtractionButton.setText(resources.getString(R.string.radio_subtraction));
                multiplicationButton.setText(resources.getString(R.string.radio_multiplication));
                divisionButton.setText(resources.getString(R.string.radio_division));
                return true;
            case R.id.itemCZ:
                context = LocaleHelper.setLocale(AndroidCalculatorMainActivity.this, "cs");
                resources = context.getResources();
                getSupportActionBar().setTitle(resources.getString(R.string.app_name));
                calculateButton.setText(resources.getString(R.string.calculate_button));
                firstNumberView.setText(resources.getString(R.string.first_number_view));
                secondNumberView.setText(resources.getString(R.string.second_number_view));
                resultView.setText(resources.getString(R.string.result_view));
                firstField.setHint(resources.getString(R.string.hint_field));
                secondField.setHint(resources.getString(R.string.hint_field));
                additionButton.setText(resources.getString(R.string.radio_addition));
                subtractionButton.setText(resources.getString(R.string.radio_subtraction));
                multiplicationButton.setText(resources.getString(R.string.radio_multiplication));
                divisionButton.setText(resources.getString(R.string.radio_division));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resources = getResources();


        calculateButton = (Button) findViewById(R.id.calculateButton);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        firstNumberView = (TextView) findViewById(R.id.firstNumberView);
        secondNumberView = (TextView) findViewById(R.id.secondNumberView);
        resultView = (TextView) findViewById(R.id.resultView);
        firstField = (EditText) findViewById(R.id.firstField);
        secondField = (EditText) findViewById(R.id.secondField);
        resultField = (EditText) findViewById(R.id.resultField);
        additionButton = (RadioButton) findViewById(R.id.additionButton);
        subtractionButton = (RadioButton) findViewById(R.id.subtractionButton);
        multiplicationButton = (RadioButton) findViewById(R.id.multiplicationButton);
        divisionButton = (RadioButton) findViewById(R.id.divisionButton);
        firstField.setHint(R.string.hint_field);
        secondField.setHint(R.string.hint_field);


        calculateButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                if (additionButton.isChecked()) {
                    if (isEmpty(firstField) || isEmpty(secondField)) {
                        Toast.makeText(getBaseContext(), resources.getString(R.string.both_fields), Toast.LENGTH_SHORT).show();
                    } else {
                        firstNumber = Double.parseDouble(firstField.getText().toString());
                        secondNumber = Double.parseDouble(secondField.getText().toString());
                        result = firstNumber + secondNumber;

                    }
                } else if (subtractionButton.isChecked()) {
                    if (isEmpty(firstField) || isEmpty(secondField)) {
                        Toast.makeText(getBaseContext(), resources.getString(R.string.both_fields), Toast.LENGTH_SHORT).show();
                    } else {
                        firstNumber = Double.parseDouble(firstField.getText().toString());
                        secondNumber = Double.parseDouble(secondField.getText().toString());
                        result = firstNumber - secondNumber;

                    }
                } else if (multiplicationButton.isChecked()) {
                    if (isEmpty(firstField) || isEmpty(secondField)) {
                        Toast.makeText(getBaseContext(), resources.getString(R.string.both_fields), Toast.LENGTH_SHORT).show();
                    } else {
                        firstNumber = Double.parseDouble(firstField.getText().toString());
                        secondNumber = Double.parseDouble(secondField.getText().toString());
                        result = firstNumber * secondNumber;

                    }
                } else if (divisionButton.isChecked()) {
                    if (isEmpty(firstField) || isEmpty(secondField)) {
                        Toast.makeText(getBaseContext(), resources.getString(R.string.both_fields), Toast.LENGTH_SHORT).show();
                    } else {

                        firstNumber = Double.parseDouble(firstField.getText().toString());
                        secondNumber = Double.parseDouble(secondField.getText().toString());
                        result = firstNumber / secondNumber;

                    }
                } else {
                    Toast.makeText(getBaseContext(), resources.getString(R.string.choose_option), Toast.LENGTH_SHORT).show(); //tento radek dela problemy nebot neni nic ulozenyho v getString kdyz se to spousti
                }
                if (Double.isInfinite(result) && !isEmpty(firstField) && !isEmpty(secondField)) {
                    Toast.makeText(getBaseContext(), resources.getString(R.string.division_by_zero), Toast.LENGTH_SHORT).show();
                    resultField.setText("");
                } else if (isEmpty(firstField) || isEmpty(secondField)) {
                    resultField.setText("");
                } else if (additionButton.isChecked() == false && subtractionButton.isChecked() == false && multiplicationButton.isChecked() == false && divisionButton.isChecked() == false) {
                    Toast.makeText(getBaseContext(), resources.getString(R.string.choose_option), Toast.LENGTH_SHORT).show();
                    resultField.setText("");
                } else {
                    resultField.setText(smallerResult.format(result));
                }


            }
        });
    }
}