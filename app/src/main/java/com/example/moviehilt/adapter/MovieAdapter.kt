package com.example.moviehilt.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviehilt.R
import com.example.moviehilt.Util.Cons.POSTER_MAIN_URL
import com.example.moviehilt.model.movie
import kotlinx.android.synthetic.main.row_item.view.*


class MovieAdapter(private val dataSet: movie) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        val movieDescription: TextView

        init {
            // Define click listener for the ViewHolder's View.
            textView = view.findViewById(R.id.movieName)
            movieDescription = view.findViewById(R.id.description)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.row_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.textView.text = dataSet.results[position].original_title
        viewHolder.movieDescription.text = dataSet.results[position].overview

        val url = POSTER_MAIN_URL + dataSet.results[position].poster_path
        viewHolder.itemView.apply {
            Glide.with(this).load(url).into(viewHolder.itemView.moviePoster)
        }

        viewHolder.itemView.setOnClickListener {

            val bundle = Bundle()
            bundle.putSerializable("movieId",""+dataSet.results.get(position).id)
            val navigationController = Navigation.findNavController(viewHolder.itemView)
            navigationController.navigate(R.id.action_listMovieFragment_to_detailMovieFragment,bundle!!)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.results.size

}
