package com.example.fernando.SimpleAPP;

import android.content.Context;

import java.util.Calendar;
import java.util.Date;

public class CreatedTimeHelper {

    private Context mContext;

    public CreatedTimeHelper(Context context) {
        mContext = context;
    }

    //region TIME ELAPSED SINCE POST MANAGER

    /**
     * Metodo encargado de determinar cuanto tiempo ha pasado desde que fue creado el post
     * y entregar un mensaje basado en la diferencia entre dicho tiempo y el tiempo actual
     *
     * @param date Date
     * @return String
     */
    private String getTimeAgo(Date date) {

        if (date == null) {
            return null;
        }

        long time = date.getTime();

        Date curDate = currentDate();
        long now = curDate.getTime();
        if (time > now || time <= 0) {
            return null;
        }

        int dim = getTimeDistanceInMinutes(time);

        String timeAgo;

        if (dim == 0) {
            timeAgo = mContext.getResources().getString(R.string.date_util_term_less) + " " + mContext.getResources().getString(R.string.date_util_term_a) + " " + mContext.getResources().getString(R.string.date_util_unit_minute);
        } else if (dim == 1) {
            return "1 " + mContext.getResources().getString(R.string.date_util_unit_minute);
        } else if (dim >= 2 && dim <= 44) {
            timeAgo = dim + " " + mContext.getResources().getString(R.string.date_util_unit_minutes);
        } else if (dim >= 45 && dim <= 119) {
            timeAgo = mContext.getResources().getString(R.string.date_util_term_an) + " " + mContext.getResources().getString(R.string.date_util_unit_hour);
        } else if (dim >= 120 && dim <= 1439) {
            timeAgo = (Math.round(dim / 60)) + " " + mContext.getResources().getString(R.string.date_util_unit_hours);
        } else if (dim >= 1440 && dim <= 2519) {
            timeAgo = "1 " + mContext.getResources().getString(R.string.date_util_unit_day);
        } else if (dim >= 2520 && dim <= 43199) {
            timeAgo = (Math.round(dim / 1440)) + " " + mContext.getResources().getString(R.string.date_util_unit_days);
        } else if (dim >= 43200 && dim <= 86399) {
            timeAgo = mContext.getResources().getString(R.string.date_util_term_a) + " " + mContext.getResources().getString(R.string.date_util_unit_month);
        } else if (dim >= 86400 && dim <= 525599) {
            timeAgo = (Math.round(dim / 43200)) + " " + mContext.getResources().getString(R.string.date_util_unit_months);
        } else if (dim >= 525600 && dim <= 655199) {
            timeAgo = mContext.getResources().getString(R.string.date_util_term_a) + " " + mContext.getResources().getString(R.string.date_util_unit_year);
        } else if (dim >= 655200 && dim <= 914399) {
            timeAgo = mContext.getResources().getString(R.string.date_util_prefix_over) + " " + mContext.getResources().getString(R.string.date_util_term_a) + " " + mContext.getResources().getString(R.string.date_util_unit_year);
        } else if (dim >= 914400 && dim <= 1051199) {
            timeAgo = mContext.getResources().getString(R.string.date_util_prefix_almost) + " 2 " + mContext.getResources().getString(R.string.date_util_unit_years);
        } else {
            timeAgo = (Math.round(dim / 525600)) + " " + mContext.getResources().getString(R.string.date_util_unit_years);
        }

        return timeAgo + " " + mContext.getResources().getString(R.string.date_util_suffix);
    }


    //region Jquery TimeAgo Plugin Modificado

    /**
     * Metodo Encargado de determinar el el timpo actual
     *
     * @return Date
     */
    private static Date currentDate() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    /**
     * Metodo encargado de calcular la diferencia de tiempo en minutos
     *
     * @param time long
     * @return int
     */
    private static int getTimeDistanceInMinutes(long time) {
        long timeDistance = currentDate().getTime() - time;
        return Math.round((Math.abs(timeDistance) / 1000) / 60);
    }

    /**
     * Metodo Encargado de recibir la marca de tiempo en la que fue creado el post.
     *
     * @param timestamp int
     * @return String
     */
    public String getDate(long timestamp) {

        long unixSeconds = timestamp;
        Date date = new java.util.Date(unixSeconds * 1000L);
        return getTimeAgo(date);
    }

    //endregion
    //endregion
}
