package pt.dg7.android.example.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import pt.dg7.android.example.R
import pt.dg7.android.example.models.Comment
import pt.dg7.android.example.models.Image

class MyRecyclerViewAdapter(
    private val context: Context,
    var comments: List<Comment>? = listOf(),
    var avatars: List<Image>? = listOf()
) : RecyclerView.Adapter<MyRecyclerViewAdapter.CommentViewHolder>() {

    /**
     * Create new views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_view, parent, false)
        return CommentViewHolder(view)
    }

    /**
     * Replace the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val comments = this.comments

        if (comments !== null) {
            holder.title.text = comments[position].email
            holder.message.text = comments[position].body
        }

        val avatars = this.avatars
        val url = if (avatars === null || avatars.isEmpty())
            "https://via.placeholder.com/150"
        else
            avatars[(0 until avatars.size).random()].urls.small

        Glide.with(context)
            .asBitmap()
            .load(url)
            .into(holder.avatar)
    }

    /**
     * Return the size of your dataset (invoked by the layout manager)
     */
    override fun getItemCount(): Int {
        val comments = this.comments
        return if (comments !== null) comments.size else 0
    }

    /**
     * Provide a reference to the views for each data item
     * Complex data items may need more than one view per item, and
     * you provide access to all the views for a data item in a view holder.
     * Each data item is just a string in this case that is shown in a TextView.
     */
    inner class CommentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var avatar: ImageView = view.findViewById(R.id.iv_avatar); private set
        var title: TextView = view.findViewById(R.id.tv_title); private set
        var message: TextView = view.findViewById(R.id.tv_message); private set
    }
}