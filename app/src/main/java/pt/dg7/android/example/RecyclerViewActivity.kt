package pt.dg7.android.example

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_recycler_view.*
import pt.dg7.android.example.adapters.CommentsAdapter
import pt.dg7.android.example.models.Comment
import pt.dg7.android.example.viewmodels.RecyclerViewViewModel

class RecyclerViewActivity : AppCompatActivity() {
    companion object {
        const val TAG = "RecyclerViewActivity"
    }

    private lateinit var viewModel: RecyclerViewViewModel
    private lateinit var adapter: CommentsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        // Initialize ViewModel
        viewModel = ViewModelProviders.of(this).get(RecyclerViewViewModel::class.java)

        // RecyclerView
        adapter = CommentsAdapter(this, viewModel.comments.value)
        rv_options.adapter = adapter
        rv_options.layoutManager = LinearLayoutManager(this)

        viewModel.comments.observe(this, Observer {
            adapter.comments = it
            adapter.notifyDataSetChanged()
        })
    }
}
