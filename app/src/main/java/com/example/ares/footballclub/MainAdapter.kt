package com.example.ares.footballclub

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*

class MainAdapter(private val teams: List<Team>) : RecyclerView.Adapter<TeamViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TeamViewHolder {
            return TeamViewHolder(TeamViewHolder.TeamUi().createView(AnkoContext.create(p0.context,p0)))
    }

    override fun getItemCount(): Int = teams.size

    override fun onBindViewHolder(p0: TeamViewHolder, p1: Int) {
     p0.bindItem(teams[p1])
    }

}
class TeamViewHolder(view: View): RecyclerView.ViewHolder(view){

    private val teamBadge: ImageView = view.find(R.id.team_badge)
    private val teamName: TextView = view.find(R.id.team_name)


    fun bindItem(teams: Team){
        Picasso.get().load(teams.teamBadge).into(teamBadge)
        teamName.text = teams.teamName

    }

class TeamUi : AnkoComponent<ViewGroup>{
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui){
            linearLayout{
                lparams(width= matchParent, height = wrapContent)
                padding = dip(16)
                orientation = LinearLayout.HORIZONTAL

                imageView {
                    id = R.id.team_badge
                }.lparams {
                    height = dip(50)
                    width = dip (50)
                }
                textView {
                    id = R.id.team_name
                    textSize = 16f
                }.lparams {
                    margin = dip (15)
                }
            }
        }
    }

}
}