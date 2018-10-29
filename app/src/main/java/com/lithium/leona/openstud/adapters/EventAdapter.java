package com.lithium.leona.openstud.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lithium.leona.openstud.R;

import org.threeten.bp.format.DateTimeFormatter;
import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lithium.openstud.driver.core.Event;
import lithium.openstud.driver.core.EventType;
import lithium.openstud.driver.core.ExamReservation;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventHolder> {

    private List<Event> events;
    private Context context;
    private ReservationAdapterListener ral;

    public EventAdapter(Context context, List<Event> events, ReservationAdapterListener ral) {
        this.events = events;
        this.context = context;
        this.ral = ral;
    }

    @NonNull
    @Override
    public EventHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_row_event, parent, false);
        EventHolder holder = new EventHolder(view);
        holder.setContext(context);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull EventHolder holder, int position) {
        Event res = events.get(position);
        holder.setDetails(res);
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    class EventHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.name)
        TextView txtName;
        @BindView(R.id.startDate)
        TextView txtStartDate;
        @BindView(R.id.endDate)
        TextView txtEndDate;
        @BindView(R.id.where)
        TextView txtWhere;
        @BindView(R.id.nameTeacher)
        TextView txtTeacher;
        private Context context;

        private void setContext(Context context) {
            this.context = context;
        }

        EventHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @SuppressLint("ResourceType")
        void setDetails(final Event ev) {
            if(ev.getWhere() == null || ev.getWhere().trim().isEmpty()) txtWhere.setVisibility(View.GONE);
            else txtWhere.setText(context.getResources().getString(R.string.info_extra_reservation_format, ev.getWhere()));
            txtTeacher.setText(context.getResources().getString(R.string.teacher_event, ev.getTeacher()));
            if(ev.getEventType() == EventType.DOABLE || ev.getEventType() == EventType.RESERVED) {
                txtName.setText(ev.getDescription());

                txtStartDate.setVisibility(View.GONE);
                txtEndDate.setVisibility(View.GONE);
            }
            else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                String name = ev.getDescription().replace("\n", " ");
                int startIdx = name.indexOf(" ");
                int endIdx = name.indexOf("Docente:");
                if (startIdx != -1 && endIdx != -1) {
                    if (name.endsWith(" ")) {
                        name = name.substring(0,name.length()-1);
                    }
                    txtName.setText(name.substring(startIdx,endIdx));
                }
                else txtName.setText(ev.getDescription());
                txtStartDate.setText(context.getResources().getString(R.string.start_lesson, ev.getStart().format(formatter)));
                txtEndDate.setText(context.getResources().getString(R.string.end_lesson, ev.getEnd().format(formatter)));
            }
        }
    }

    public interface ReservationAdapterListener {


        void addCalendarOnClick(ExamReservation res);
    }
}