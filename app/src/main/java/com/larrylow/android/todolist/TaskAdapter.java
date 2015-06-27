package com.larrylow.android.todolist;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.larrylow.android.todolist.data.TaskContract;
import com.larrylow.android.todolist.data.TaskDBHelper;

public class TaskAdapter extends CursorAdapter {

    private static Context context;
    TaskDBHelper helper;

    public TaskAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
        this.context = context;
        helper = new TaskDBHelper(context);
    }

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item_task, parent, false);
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find Views to populate in inflated template
        TextView textView = (TextView) view.findViewById(R.id.list_item_task_textview);
        Button done_button = (Button) view.findViewById(R.id.list_item_task_done_button);

        // Extract properties from cursor
        final String id = cursor.getString(MainActivityFragment.COL_TASK_ID);
        final String task = cursor.getString(MainActivityFragment.COL_TASK_NAME);

        // Populate views with extracted properties
        textView.setText(task);
        done_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create a SQL command for deleting a particular ID.
                String sql = String.format("DELETE FROM %s WHERE %s = '%s'",
                        TaskContract.TaskEntry.TABLE_NAME,
                        TaskContract.TaskEntry._ID,
                        id);
                SQLiteDatabase sqlDB = helper.getWritableDatabase();
                //Execute the delete command
                sqlDB.execSQL(sql);
                notifyDataSetChanged();

                //Query database for updated data
                Cursor cursor = sqlDB.query(TaskContract.TaskEntry.TABLE_NAME,
                        new String[]{TaskContract.TaskEntry._ID, TaskContract.TaskEntry.COLUMN_TASK},
                        null,null,null,null,null);
                //Instance method with TaskAdapter so no need to use adapter.swapCursor()
                swapCursor(cursor);
            }
        });
    }
}
