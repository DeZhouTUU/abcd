package com.dezhoutuu.mytuu.Widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dezhoutuu.mytuu.R;
import com.dezhoutuu.mytuu.Utils.TUUToast;

/**
 * Created by fatJiang on 2018/4/20.
 */

public class PopLayout extends RelativeLayout {

    public PopLayout(final Context context) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.layout_pop, null, false);
        TextView mText = (TextView)v.findViewById(R.id.btn_pop);
        mText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                new TUUToast(context,"aaaaaa");
            }
        });
        addView(v);
    }


}
