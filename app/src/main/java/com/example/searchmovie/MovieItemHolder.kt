package com.example.searchmovie

import android.content.Context
import android.content.Intent
import android.text.Html
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.signature.ObjectKey
import com.example.searchmovie.databinding.LayoutMovieItemBinding
import com.example.searchmovie.model.MovieItem
import com.example.searchmovie.utils.Constants

class MovieItemHolder (val binding:LayoutMovieItemBinding,val context: Context):RecyclerView.ViewHolder(binding.root){
    fun bindWithMovie(item:MovieItem){
        Log.d(Constants.TAG,"MovieItemHolder - bindWithMovie() called/${item.title}")

        with(binding){
            itemTitle.text= context.getString(R.string.movie_title)+Html.fromHtml(item.title, Html.FROM_HTML_MODE_LEGACY).toString()
            movieItem=item
            loadPhotoImage(itemImageView,item.image)
            setItemClickListener(itemLayout,item.link)
        }
    }
    fun loadPhotoImage(view: ImageView, imageUrl: String?) {
        val options = RequestOptions()
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)//메모리나 디스크에 캐싱하지 않는다.
            .signature(ObjectKey(System.currentTimeMillis()))
        Glide.with(context)
            .load(imageUrl)//표시할 이미지
            .placeholder(R.drawable.ic_image_url_empty)
            .error(R.drawable.ic_image_url_empty)//이미지가 없거나 오류일때 나오는 기본 화면
            .apply(options)
            .fitCenter()
            .into(view)//표시할 이미지 뷰

    }
    fun setItemClickListener( itemLayout:LinearLayout, loadUrl:String? ){
        itemLayout.setOnClickListener{
            //액티비티가아닌 viewholderd에서 사용하기 때문에 context가 필요
            val nextIntent = Intent(context, WebViewActivity::class.java)
            nextIntent.putExtra("view_url", loadUrl)
            context.startActivity(nextIntent)
        }
    }

}