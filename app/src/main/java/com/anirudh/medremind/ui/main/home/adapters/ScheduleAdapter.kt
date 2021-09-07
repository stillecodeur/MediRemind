package com.anirudh.medremind.ui.main.home.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anirudh.medremind.R
import com.anirudh.medremind.data.ScheduleListModel
import com.bumptech.glide.Glide

class ScheduleAdapter(context: Context, list: List<ScheduleListModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_VIDEO_WATCH = 1
        const val VIEW_TYPE_VIDEO_WATCHED = 2
        const val VIEW_TYPE_MEDICINE_TAKE = 3
        const val VIEW_TYPE_MEDICINE_TAKEN = 4
    }

    private val context: Context = context
    var list: List<ScheduleListModel> = list


    private inner class VideoWatchHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var tvVideoName: TextView = itemView.findViewById(R.id.tv_video_name)
        var tvVideoDuration: TextView = itemView.findViewById(R.id.tv_video_duration)
        var imvVideoThumb: ImageView = itemView.findViewById(R.id.imv_thumb)
        var tvWatch: TextView = itemView.findViewById(R.id.tv_watch)
        fun bind(position: Int) {
            val recyclerViewModel = list[position]
            tvVideoName.text = recyclerViewModel.video?.title
            Glide.with(context)  //2
                .load(recyclerViewModel.video?.thumbnail) //3
                .centerCrop() //4
                .placeholder(R.drawable.ic_image) //5
                .error(R.drawable.ic_broken_image) //6
                .fallback(R.drawable.ic_image) //7
                .into(imvVideoThumb) //8

            tvWatch.setOnClickListener {
                recyclerViewModel.sessionList?.done=true
                notifyDataSetChanged()
            }
        }
    }

    private inner class VideoWatchedHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var tvVideoName: TextView = itemView.findViewById(R.id.tv_video_name)
        var tvVideoDuration: TextView = itemView.findViewById(R.id.tv_video_duration)
        fun bind(position: Int) {
            val recyclerViewModel = list[position]
            tvVideoName.text = recyclerViewModel.video?.title
        }
    }

    private inner class MedicineTakeHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var tvMedicineName: TextView = itemView.findViewById(R.id.tv_medicine_name)
        var tvMedicineDose: TextView = itemView.findViewById(R.id.tv_dose)
        var tvMedicineUnit: TextView = itemView.findViewById(R.id.tv_medi_unit)
        var tvMedicineMeal: TextView = itemView.findViewById(R.id.tv_meal_type)
        var tvTake: TextView = itemView.findViewById(R.id.tv_take)
        fun bind(position: Int) {
            val recyclerViewModel = list[position]
            tvMedicineName.text = recyclerViewModel.drug?.brandName
            tvMedicineDose.text = recyclerViewModel.drug?.dose.toString()
            tvMedicineUnit.text = recyclerViewModel.drug?.unit
            tvMedicineMeal.text = recyclerViewModel.sessionList?.foodContext

        }
    }

    private inner class MedicineTakendHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var tvMedicineName: TextView = itemView.findViewById(R.id.tv_medicine_name)
        var tvMedicineDose: TextView = itemView.findViewById(R.id.tv_dose)
        var tvMedicineUnit: TextView = itemView.findViewById(R.id.tv_medi_unit)
        fun bind(position: Int) {
            val recyclerViewModel = list[position]
            tvMedicineName.text = recyclerViewModel.drug?.brandName
            tvMedicineDose.text = recyclerViewModel.drug?.dose.toString()
            tvMedicineUnit.text = recyclerViewModel.drug?.unit
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == VIEW_TYPE_VIDEO_WATCH) {
            return VideoWatchHolder(
                LayoutInflater.from(context).inflate(R.layout.video_row_watch, parent, false)
            )
        } else if (viewType == VIEW_TYPE_VIDEO_WATCHED) {
            return VideoWatchedHolder(
                LayoutInflater.from(context).inflate(R.layout.video_row_watched, parent, false)
            )
        } else if (viewType == VIEW_TYPE_MEDICINE_TAKE) {
            return MedicineTakeHolder(
                LayoutInflater.from(context).inflate(R.layout.medicine_row_watch, parent, false)
            )
        }
        return return MedicineTakendHolder(
            LayoutInflater.from(context).inflate(R.layout.medicine_row_watched, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = list[position]
        if (model.schedule?.type.equals(context.getString(R.string.medicine))) {
            if (model.sessionList?.done!!) {
                (holder as MedicineTakendHolder).bind(position)
            } else {
                (holder as MedicineTakeHolder).bind(position)
                holder.tvTake.setOnClickListener {
                    list[position].sessionList?.done=true
                    notifyDataSetChanged()
                }
            }
        } else if (model.schedule?.type.equals(context.getString(R.string.vod))) {
            if (model.sessionList?.done!!) {
                (holder as VideoWatchedHolder).bind(position)
            } else {
                (holder as VideoWatchHolder).bind(position)
            }
        }


    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        val model = list[position]
        var viewType: Int = 0
        if (model.schedule?.type.equals(context.getString(R.string.medicine))) {
            if (model.sessionList?.done!!) {
                viewType = VIEW_TYPE_MEDICINE_TAKEN
            } else {
                viewType = VIEW_TYPE_MEDICINE_TAKE
            }
        } else if (model.schedule?.type.equals(context.getString(R.string.vod))) {
            if (model.sessionList?.done!!) {
                viewType = VIEW_TYPE_VIDEO_WATCHED
            } else {
                viewType = VIEW_TYPE_VIDEO_WATCH
            }
        }
        return viewType
    }
}