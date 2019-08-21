package com.wambu.expensemanager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddIncome extends Activity implements AdapterView.OnItemSelectedListener {


    private static final String TAG="Add Income";
    private Button btnSave;
    private EditText amoutTxt,descTxt,dateTxt;
    DatabaseHelper mDatabaseHelper;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_income);

        btnSave=findViewById(R.id.btnSave);
        amoutTxt=findViewById(R.id.txtamt);
        descTxt=findViewById(R.id.optDesc);
        dateTxt=findViewById(R.id.date);

        mDatabaseHelper= new DatabaseHelper(this);


        final Spinner spinner = findViewById(R.id.income_spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View v = super.getView(position, convertView, parent);
                if (position == getCount()) {
                    ((TextView)v.findViewById(android.R.id.text1)).setText("");
                    ((TextView)v.findViewById(android.R.id.text1)).setHint(getItem(getCount())); //"Hint to be displayed"
                }

                return v;
            }

            @Override
            public int getCount() {
                return super.getCount()-1; // you dont display last item. It is used as hint.
            }

        };

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.add("Business");
        adapter.add("Salary");
        adapter.add("Loan");
        adapter.add("Dividends");
        adapter.add("Misc");
        adapter.add("Select Type Of Income"); //This is the text that will be displayed as hint.


        spinner.setAdapter(adapter);
        spinner.setSelection(adapter.getCount()); //set the hint the default selection so it appears on launch.
        spinner.setOnItemSelectedListener(this);



        //date picker methods

        final Calendar myCalendar = Calendar.getInstance();
        EditText editText= dateTxt;
        final DatePickerDialog.OnDateSetListener date= new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR,year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
            updateLabel();
            }
        };

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddIncome.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String amountST= amoutTxt.getText().toString().trim();
                int amount=Integer.parseInt(amountST);

                String text=spinner.getSelectedItem().toString();

                String description=descTxt.getText().toString().trim();
                String date=dateTxt.getText().toString();

                addData(amount,text,description,date);

                Intent intent=new Intent(getApplicationContext(),Dashboard.class);
                startActivity(intent);

            }
        });

    }

    private void updateLabel() {
        EditText editText= findViewById(R.id.date);
        final Calendar myCalendar = Calendar.getInstance();

        String myFormat = "dd/MM/yy"; //In which format you need
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);

        editText.setText(sdf.format(myCalendar.getTime()));
    }

    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    public void addData(int amount, String text, String description, String date){

        boolean insertData = mDatabaseHelper.addData(amount,text,description,date);

        if(insertData){
            toastMessage("Data Successfully Inserted");

        }
        else{
            toastMessage("Something Went Wrong");
        }

    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text=parent.getItemAtPosition(position).toString();

        if(position==0){


        }



    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}