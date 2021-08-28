package uz.projects.wallpaper.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import uz.projects.wallpaper.databinding.ItemPhotoBinding;
import uz.projects.wallpaper.models.ImageData;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private List<ImageData> dataList;
    private onItemClickListener listener;

    public RecyclerAdapter(List<ImageData> dataList, onItemClickListener listener) {
        this.dataList = dataList;
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(ItemPhotoBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Picasso.get().load(dataList.get(position).getImageUrl()).resize(100, 200).into(holder.binding.image);
        holder.itemView.setOnClickListener(v -> {
            listener.onItemClick(dataList.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private ItemPhotoBinding binding;

        public MyViewHolder(ItemPhotoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface onItemClickListener {
        void onItemClick(ImageData imageData);
    }
}
