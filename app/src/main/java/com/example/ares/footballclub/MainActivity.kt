package com.example.ares.footballclub

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.*
import com.example.ares.footballclub.R.array.*
import com.example.ares.footballclub.R.color.colorAccent
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class MainActivity : AppCompatActivity(), MainView {
    override fun showLoading() {
progressBar.visible()    }

    override fun hideLoading() {
progressBar.invisible()
    }

    override fun showTeamList(data: kotlin.collections.List<Team>) {
        swipeRefresh.isRefreshing = false
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
    }

    private val teams: MutableList<Team> = mutableListOf()
    private lateinit var presenter: MainPresenter
    private lateinit var adapter: MainAdapter
    private lateinit var listTeam: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var spinner: Spinner
    private lateinit var leagueName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            spinner = spinner()
            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)

                relativeLayout {
                    lparams(width = matchParent, height = wrapContent)

                    listTeam = recyclerView {
                        lparams(width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
                    }

                    progressBar = progressBar {
                    }.lparams {
                        centerHorizontally()
                    }
                }
            }


        }
        adapter = MainAdapter(teams)
        listTeam.adapter = adapter
        val request = ApiRespository()
        val gson = Gson()
        presenter = MainPresenter(this, request, gson)
        val spinnerItem = resources.getStringArray(league)
        val spinnerAdapter = ArrayAdapter(ctx,android.R.layout.simple_spinner_dropdown_item,spinnerItem)
        spinner.adapter = spinnerAdapter
        spinner.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

            leagueName =spinner.selectedItem.toString()
                presenter.getTeamList(leagueName)

            }

        }
        swipeRefresh.onRefresh {
            presenter.getTeamList(leagueName)
        }


    }
}

    class MainPresenter(private val view: MainView, private val apiRespository: ApiRespository, private val gson: Gson) {

        fun getTeamList(league: String?) {
            view.showLoading()
            doAsync {
                val data = gson.fromJson(apiRespository.doRequest(TheSportDBApi.getTeams(league)),
                        TeamResponse::class.java)
                uiThread {
                    view.hideLoading()
                    view.showTeamList(data.teams)
                }
            }
        }
    }

    interface MainView {
        fun showLoading()
        fun hideLoading()
        fun showTeamList(data:List<Team>)
    }

