package com.hr.ent.utils;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.hr.ent.R;

/**
 * 日期选择类
 *
 * @author 800hr:xuebaohua
 */
public class DataPickerDialog {
    /**
     * @param context
     * @param textView
     * @param cells    ：年月日为3部分，cells代表需要返回部分的数量
     */
    public static void showDialog(final Context context,
                                  final TextView textView, final int cells) {
        Dialog dialog = null;
        final Calendar c = Calendar.getInstance();
        dialog = new DatePickerDialog(context,
                new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker dp, int year, int month,
                                          int dayOfMonth) {
                        // 未来时间判断
                        if (year < c.get(Calendar.YEAR)) {

                            Toast.makeText(context,
                                    context.getString(R.string.date1),
                                    Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (year == c.get(Calendar.YEAR)
                                && month < c.get(Calendar.MONDAY)) {
                            Toast.makeText(context,
                                    context.getString(R.string.date1),
                                    Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (year == c.get(Calendar.YEAR)
                                && month == c.get(Calendar.MONDAY)
                                && dayOfMonth < c.get(Calendar.DAY_OF_MONTH)) {
                            Toast.makeText(context,
                                    context.getString(R.string.date1),
                                    Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (cells == 2) {// yyyy-MM
                            textView.setText(year + "-" + (month + 1));
                        } else if (cells == 3) {
                            if (month < 9) {
                                if (dayOfMonth <9) {
                                    textView.setText(year + "-0" + (month + 1)
                                            + "-0" + dayOfMonth);
                                } else {
                                    textView.setText(year + "-0" + (month + 1)
                                            + "-" + dayOfMonth);
                                }
                            } else {
                                if (dayOfMonth <9) {
                                    textView.setText(year + "-" + (month + 1)
                                            + "-0" + dayOfMonth);
                                } else {
                                    textView.setText(year + "-" + (month + 1)
                                            + "-" + dayOfMonth);
                                }
                            }
                        }

                    }
                }, c.get(Calendar.YEAR), // 传入年份
                c.get(Calendar.MONTH), // 传入月份
                c.get(Calendar.DAY_OF_MONTH) // 传入天数
        );
        dialog.show();
    }

}
