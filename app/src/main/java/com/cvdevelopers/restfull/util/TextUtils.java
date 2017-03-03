package com.cvdevelopers.restfull.util;

import android.content.Context;
import android.text.Editable;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.style.ImageSpan;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;


import java.util.regex.Pattern;

/**
 * Where text likes to party
 */
public class TextUtils {

    // yyyy-mm-dd
    private static final String ISO8601_DATE_FORMAT = "%s-%s-%s";
    private static int ISO8601_YEAR_CHAR_LEN = 4;
    private static int ISO8601_MONTH_CHAR_LEN = 2;
    private static int ISO8601_DAY_CHAR_LEN = 2;

    private static final char ONE_SPACE = ' ';

    // http://examples.javacodegeeks.com/core-java/util/regex/matcher/validate-email-address-with-java-regular-expression-example/
    private static final Pattern EMAIL_PATTERN = android.util.Patterns.EMAIL_ADDRESS;

    // Passwords must be at least 6 characters (spethial character no longer necessary)
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?!.*\\s).{6,}$");

    public static boolean isEmailValid(final String email) {
        return email.length() > 0 && EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean isPasswordValid(final String password) {
        return PASSWORD_PATTERN.matcher(password).matches();
    }

    public static String getTrimmedString(EditText editText) {
        return getString(editText).trim();
    }

    public static String getString(EditText editText) {
        return editText.getText().toString();
    }

    /**
     * Set first character to upper case
     * @param string the string to capitalize
     * @return copy of original string with first letter capitalized
     */
    public static String capitalizeFirstLetter(String string) {
        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }


    protected static String getSpaces(int numSpaces) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < numSpaces; i++) {
            sb.append(ONE_SPACE);
        }
        return sb.toString();
    }

    /**
     * Generate a {@link CharSequence} that has a trailing icon  separated by a single space.
     * @param context
     * @param label
     * @param drawableResId
     * @param numSpaces Number of spaces between the label and the drawable
     * @return
     */
    public static Spannable createTextWithTrailingIcon(Context context, String label,
            int drawableResId, int numSpaces) {
        label = label + getSpaces(numSpaces + 1);
        Spannable text = new SpannableString(label);

        // replace the last trailing space we appended above with the drawable:
        text.setSpan(new ImageSpan(context, drawableResId,
                        ImageSpan.ALIGN_BASELINE), label.length() - 1, label.length(),
                Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        return text;
    }

    /**
     * Generate a {@link CharSequence} that has a leading icon  separated by a single space.
     * @param context
     * @param label
     * @param drawableResId
     * @param numSpaces Number of spaces between the label and the drawable.
     * @return
     */
    public static Spannable createTextWithLeadingIcon(Context context, String label,
            int drawableResId, int numSpaces) {
        label = getSpaces(numSpaces + 1) + label;
        Spannable text = new SpannableString(label);

        // replace the first space we appended above with the drawable:
        text.setSpan(new ImageSpan(context, drawableResId,
                        ImageSpan.ALIGN_BASELINE), 0, 1,
                Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        return text;
    }




}
