package com.cvdevelopers.restfull.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.Button;

import com.cvdevelopers.restfull.R;
import com.cvdevelopers.restfull.util.TextUtils;


/**
 * A Button that accepts image "decorations" on either or both the leading and trailing borders
 * of it's text label.
 */
public class DecoratedButton extends Button {

    private static final int TWO_SPACES = 2;

    public DecoratedButton(Context context) {
        super(context);
        init(context, null);
    }

    public DecoratedButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public DecoratedButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(21)
    public DecoratedButton(Context context, AttributeSet attrs, int defStyleAttr,
                           int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    protected void init(Context context, AttributeSet attrs) {

        TypedArray attributeList = context
                .obtainStyledAttributes(attrs, R.styleable.DecoratedButton, 0, 0);

        int leadingDrawableResId = attributeList.
                getResourceId(R.styleable.DecoratedButton_leadingDrawable, 0);
        int trailingDrawableResId = attributeList.
                getResourceId(R.styleable.DecoratedButton_trailingDrawable, 0);

        // need to do this in order to support spannable text:
        // http://stackoverflow.com/questions/29007746/button-settext-with-spannable-dosent-work-for-android-5-0-lollipop
        setAllCaps(false);

        // force upper case to conform with material design button convention:
        final String txt = getText().toString().toUpperCase();

        if (leadingDrawableResId != 0) {
            setText(TextUtils.createTextWithLeadingIcon(
                    context, txt, leadingDrawableResId, TWO_SPACES));
        }

        if (trailingDrawableResId != 0) {
            setText(TextUtils.createTextWithTrailingIcon(
                    context, txt, trailingDrawableResId, TWO_SPACES));
        }
    }
}
