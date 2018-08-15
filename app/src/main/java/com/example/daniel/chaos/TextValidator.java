package com.example.daniel.chaos;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

public abstract class TextValidator implements TextWatcher {

    public static class Required extends TextValidator {

        Required(final TextView textView) {
            super(textView);
        }

        @Override
        protected boolean validate(final TextView textView, final String text) {
            final boolean valid = text!=null && !text.isEmpty();
            if (!valid)
            {
                setError("Please enter the required information.");
            }

            return valid;
        }
    }

    public static class RangeInt extends Required {

        public static final int noMin = Integer.MIN_VALUE;
        public static final int noMax = Integer.MAX_VALUE;

        private int min_ = noMin;
        private int max_ = noMax;

        RangeInt(final TextView textView, final int min, final int max) {
            super(textView);
            min_ = min;
            max_ = max;
        }

        @Override
        protected boolean validate(final TextView textView, final String text) {
            boolean valid = super.validate(textView, text);
            if (valid)
            {
                int value = Integer.MAX_VALUE;
                try {
                    value = Integer.parseInt(text);
                }
                catch (NumberFormatException e) {
                    // Do nothing.
                }
                valid = value != Integer.MAX_VALUE && (min_ == noMin || value >= min_) && (max_ == noMax || value <= max_);
                if (!valid)
                {
                    String errorMessage = "Please enter a value between " + Integer.toString(min_) + " and " + Integer.toString(max_) +".";
                    setError(errorMessage);
                }
            }

            return valid;
        }
    }

    private final TextView textView_;

    TextValidator(final TextView textView) {
        textView_ = textView;
    }

    protected void setError(final String errorMessage) {
        textView_.setError(errorMessage);
    }

    protected abstract boolean validate(final TextView textView, final String text);

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        // Do nothing.
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        final String text = s.toString().trim();
        validate(textView_, text);
    }

    @Override
    public void afterTextChanged(Editable s) {
        // Do nothing.
    }
}
