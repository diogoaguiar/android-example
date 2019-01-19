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

class CommentsAdapter(
    private val context: Context,
    var comments: List<Comment>? = listOf()
) : RecyclerView.Adapter<CommentsAdapter.CommentViewHolder>() {

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

        Glide.with(context)
            .asBitmap()
            .load("https://vignette.wikia.nocookie.net/undertale-rho/images/5/5f/Placeholder.jpg/revision/latest/scale-to-width-down/480")
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
    class CommentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var avatar: ImageView = view.findViewById(R.id.iv_avatar); private set
        var title: TextView = view.findViewById(R.id.tv_title); private set
        var message: TextView = view.findViewById(R.id.tv_message); private set
    }
}