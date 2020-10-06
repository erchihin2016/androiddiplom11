package com.example.androiddiplom;

import android.text.TextUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class NoteComparator implements Comparator<Note> {

    private final DateFormat format = new SimpleDateFormat("dd.MMM.yyyy", Locale.getDefault( ));

    @Override
    public int compare(final Note first, Note second) {
        try{
            if (TextUtils.isEmpty(first.getNoteDeadline()) && !TextUtils.isEmpty(second.getNoteDeadline())) {
                return 1;
            }
            if (TextUtils.isEmpty(first.getNoteDeadline())) {
                return 0;
            }
            if (TextUtils.isEmpty(second.getNoteDeadline())) {
                return -1;
            }

            Date firstDeadline = format.parse(first.getNoteDeadline());
            Objects.requireNonNull(firstDeadline);
            Date secondDeadline = format.parse(second.getNoteDeadline());
            return firstDeadline.compareTo(secondDeadline);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
