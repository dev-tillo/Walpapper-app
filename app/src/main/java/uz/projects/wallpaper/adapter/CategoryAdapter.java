package uz.projects.wallpaper.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import uz.projects.wallpaper.models.Category;
import uz.projects.wallpaper.ui.home.BlankFragment;

public class CategoryAdapter extends FragmentStateAdapter {

    List<Category> categoryList;

    public CategoryAdapter(@NonNull @NotNull FragmentActivity fragmentActivity, List<Category> categoryList) {
        super(fragmentActivity);
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return BlankFragment.newInstance(categoryList.get(position));
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }
}
