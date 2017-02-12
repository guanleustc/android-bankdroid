package com.example.mytaint;


import android.text.SpannableStringBuilder;
import android.widget.EditText;
import android.widget.TextView;
import dalvik.system.Taint;
import android.util.Log;

public class MyTaint {
    private static final String TAG = "MyTaint";

    //final static int taintTag = 0;
    final int taintTag = Taint.TAINT_TEST;


    static private void addTaintSpannableStringBuilder(SpannableStringBuilder sp){
        char c = Taint.addTaintChar('1', taintTag );
        //char c= '0';
        sp.append(c);
        sp.delete(sp.length() - 1, sp.length());
        System.out.println("tainting SpannableStringBuilder");
        Log.v(TAG, "tainting SpannableStringBuilder");

    }
    static public void addTaint(Object obj){
        //Taint.addTaintChar('a', Taint.TAINT_TEST);
        if(obj.getClass() == SpannableStringBuilder.class) {
            SpannableStringBuilder sp = (SpannableStringBuilder) obj;
            addTaintSpannableStringBuilder(sp);
        }else if (obj.getClass() == String.class){
            //Taint.addTaintString((String)obj, taintTag);
            System.out.println("tainting String");
            Log.v(TAG, "tainting String");
        }else if (obj.getClass() == EditText.class){
            EditText et = (EditText)obj;
            if(et.getText().getClass() == SpannableStringBuilder.class)
                addTaintSpannableStringBuilder((SpannableStringBuilder)et.getText());
            else{
                System.out.println("tainting unknow getText" + et.getClass() );
                Log.v(TAG, "tainting unknow getText" + et.getClass() );
            }
            System.out.println("tainting EditText");
            Log.v(TAG, "tainting EditText");
        }else if (obj.getClass() == TextView.class){
            TextView et = (TextView)obj;
            if(et.getText().getClass() == SpannableStringBuilder.class)
                addTaintSpannableStringBuilder((SpannableStringBuilder)et.getText());
            else{
                System.out.println("tainting unknow getText" + et.getClass() );
                Log.v(TAG, "tainting unknow getText" + et.getClass() );
            }
            System.out.println("tainting EditText");
            Log.v(TAG, "tainting EditText");
        }else {
            System.out.println("tainting unknow class" + obj.getClass());
            Log.v(TAG, "tainting unknow class" + obj.getClass());
        }
    }
}
