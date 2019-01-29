package br.com.caelum.twittelumapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import br.com.caelum.twittelumapp.R
import br.com.caelum.twittelumapp.extensions.CarregadorImagem
import br.com.caelum.twittelumapp.modelo.Tweet
import kotlinx.android.synthetic.main.item_tweet.view.*

class TweetAdapter(private val tweets: List<Tweet>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = LayoutInflater.from(parent?.context)
        val tweet = tweets[position]
        val view = inflater.inflate(R.layout.item_tweet, parent, false)
        view.item_conteudo.text = tweet.mensagem
        tweet.foto?.let { foto ->
            view.item_foto.visibility = View.VISIBLE
            view.item_foto.setImageBitmap(CarregadorImagem.decodifica(foto))
        }
        return view
    }

    override fun getItem(position: Int): Any = tweets[position]

    override fun getItemId(position: Int): Long  = tweets[position].id.toLong()

    override fun getCount(): Int = tweets.size

}
