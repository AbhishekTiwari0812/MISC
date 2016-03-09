package com.example.abhishektiwari.akaar;
/**
  Created by Abhishek Tiwari on 4/3/2015.
 */
import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ItemAdapter extends ArrayAdapter<StudentDetail> {
    private ArrayList<StudentDetail> objects;
    /* here we must override the constructor for ArrayAdapter
    * the only variable we care about now is ArrayList<StudentDetail> objects,
    * because it is the list of objects we want to display.
    */
    public ItemAdapter(Context context, int textViewResourceId, ArrayList<StudentDetail> objects) {
        super(context, textViewResourceId, objects);
        this.objects = objects;
    }

    /*
     * we are overriding the getView method here - this is what defines how each
     * list item will look.
     */
    public View getView(int position, View convertView, ViewGroup parent){

        // assign the view we are converting to a local variable
        View v = convertView;

        // first check to see if the view is null. if so, we have to inflate it.
        // to inflate it basically means to render, or show, the view.
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.list_item, null);
        }

		/*
		 * Recall that the variable position is sent in as an argument to this method.
		 * The variable simply refers to the position of the current object in the list. (The ArrayAdapter
		 * iterates through the list we sent it)
		 *
		 * Therefore, i refers to the current StudentDetail object.
		 */
        StudentDetail i = objects.get(position);

        if (i != null) {

            TextView ttd = (TextView) v.findViewById(R.id.student_name);
            TextView mtd = (TextView) v.findViewById(R.id.entry_number);
            TextView btd = (TextView) v.findViewById(R.id.percentage_attendance);

            if (ttd != null){
                ttd.setText(i.getName());
            }
            if (mtd != null){
                mtd.setText("" + i.getPrice()+"%");
            }
            if (btd != null){
                btd.setText(i.getDetails());
            }
        }

        // the view must be returned to our activity
        return v;

    }

}
