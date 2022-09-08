package com.example.adaptador

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demoretrofitdos.R
import com.example.models.Publicacion


class PublicacionAdapter (private val dataSet: ArrayList<Publicacion>) :
    RecyclerView.Adapter<PublicacionAdapter .ViewHolder>() {


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.text_row_item, viewGroup, false)

        return ViewHolder(view)
    }


    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.enlazarItem(dataSet[position])
    }


    override fun getItemCount() = dataSet.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val txtId:      TextView
        val txtTitle:   TextView
        val txtBody:    TextView
        val txtUser:     TextView

        init {

            txtId =    view.findViewById(R.id.txtId)        // id en xml
            txtTitle = view.findViewById(R.id.txttitle)
            txtBody =  view.findViewById(R.id.txtBody)
            txtUser =   view.findViewById(R.id.txtUser)


        }

        fun enlazarItem(p:Publicacion){

            txtId.text=     p.id.toString()
            txtTitle.text=  p.title
            txtBody.text=   p.body
            txtUser.text=   p.userId.toString()

        }
    }



}
