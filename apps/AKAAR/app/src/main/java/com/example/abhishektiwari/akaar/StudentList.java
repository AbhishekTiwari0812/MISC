package com.example.abhishektiwari.akaar;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Abhishek Tiwari on 4/3/2015.
 */

public class StudentList extends ListActivity {

    // declare class variables
    private ArrayList<StudentDetail> m_parts = new ArrayList<StudentDetail>();
    private Runnable viewParts;
    private ItemAdapter m_adapter;
    private String[] StudentNames=MainActivity.StudentNames;

    /**
     * Called when the activity is first created.
     */
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_name_layout);

        // instantiate our ItemAdapter class
        m_adapter = new ItemAdapter(this, R.layout.list_item, m_parts);
        setListAdapter(m_adapter);

        // here we are defining our runnable thread.
        viewParts = new Runnable() {
            public void run() {
                handler.sendEmptyMessage(0);
            }
        };

        // here we call the thread we just defined - it is sent to the handler below.
        Thread thread = new Thread(null, viewParts, "MagentoBackground");
        thread.start();
    }

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            // create some objects
            // here is where you could also request data from a server
            // and then create objects from that data.
            m_parts.add(new StudentDetail("NAME","ENTRY NUMBER","ATTENDANCE"));

            String appendThis;
            for (int i = 0; i < StudentNames.length; i++) {
                if (i <= 9) {
                    appendThis = "0" + (i + 1);
                } else {
                    appendThis = "" + i;
                }
                Random rand=new Random();
                //Filling the student list with their name entry number and their attendance
                m_parts.add(new StudentDetail(StudentNames[i], "2013CSB10" + appendThis, ""+rand.nextInt(100)));

            }
            m_adapter = new ItemAdapter(StudentList.this, R.layout.list_item, m_parts);

            // display the list.
            setListAdapter(m_adapter);
        }
    };
}