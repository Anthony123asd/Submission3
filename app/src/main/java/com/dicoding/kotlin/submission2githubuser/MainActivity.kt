package com.dicoding.kotlin.submission2githubuser

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.kotlin.submission2githubuser.data.GithubUsers
import com.dicoding.kotlin.submission2githubuser.detail.UserDetailActivity
import com.dicoding.kotlin.submission2githubuser.favuser.FavoriteUserActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var githubAdapter: GithubAdapter
    private lateinit var mainViewModel : MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        githubAdapter = GithubAdapter()
        githubAdapter.notifyDataSetChanged()


        mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)

        mainViewModel.getUsers().observe(this, Observer { users->
            if (users != null) {
                githubAdapter.setData(users)
            }
            showLoading(false)
        })

        search_results.layoutManager = LinearLayoutManager(this)
        search_results.adapter = githubAdapter

        githubAdapter.setOnItemClickCallback(object : GithubAdapter.OnItemClickCallback{
            override fun onItemClicked(user: GithubUsers?) {
                val toDetailIntent = Intent(this@MainActivity, UserDetailActivity::class.java)
                toDetailIntent.putExtra(UserDetailActivity.EXTRA_USER, user)
                startActivity(toDetailIntent)
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.search_bar, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu?.findItem(R.id.search)?.actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                welcome.visibility = View.GONE
                if (query?.isEmpty()!!) return true
                showLoading(true)
                mainViewModel.returnUserSearch(query)


                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favorite_users ->{
                val i = Intent(this, FavoriteUserActivity::class.java)
                startActivity(i)
            }
            R.id.settings -> null
        }
        return true
    }

    fun showLoading(state: Boolean) {
        if (state) {
            progressBar.visibility = View.VISIBLE
        }else {
            progressBar.visibility = View.GONE
        }
    }
}