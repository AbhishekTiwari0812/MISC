package com.example.abhishektiwari.internshipproject;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    String InputText;
    Button bt,clear;
    final static String url="http://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS-MML_HTMLorMML";
    EditText input;
    WebView w;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input = (EditText) findViewById(R.id.editText);
        bt = (Button) findViewById(R.id.button);
        clear=(Button)findViewById(R.id.clear);
        bt.setOnClickListener(this);
        clear.setOnClickListener(this);
        InputText= new String("");
        w=(WebView)findViewById(R.id.webview);
        w.getSettings().setJavaScriptEnabled(true);
        setURL(url);
    }

    private String GetValidForm(String input) {
        String validInput="";
        for (int i=0; i<input.length(); i++) {
            if (input.charAt(i) == '\\')
                validInput += "\\";
            else if (input.charAt(i) == '\'')
                validInput += '\\';
            else if (input.charAt(i) != '\n')
                validInput += input.charAt(i);

        }
        return validInput;
    }
    /*************************************************/
    /*****************************************************/
   @Override
   public void onClick(View v) {
       switch (v.getId()) {
           case R.id.button:
               if (input.getText().toString().length()!=0) {
                   w.loadUrl("javascript:document.getElementById('ResultString').innerHTML='\\\\[" + GetValidForm(input.getText().toString()) + "\\\\]';");
                   w.loadUrl("javascript:MathJax.Hub.Queue(['Typeset',MathJax.Hub]);");
               }
               else
               {
                   Context context = getApplicationContext();
                   Toast toast = Toast.makeText(context, "There's no input",Toast.LENGTH_LONG);
                   toast.show();
               }
               break;
           case R.id.clear:
               input.setText("");
               break;
       }
   }
    public void setURL(String url) {
        w.loadDataWithBaseURL("http://bar","<script type='text/javascript' "
                +"src='"+url+"'"
                +"></script><p id='ResultString'></p>","text/html","utf-8","");
    }
}
