package uz.projects.wallpaper;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;

import uz.projects.wallpaper.databinding.FragmentSetWallpaperBinding;
import uz.projects.wallpaper.models.ImageData;


public class SetWallpaperFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public SetWallpaperFragment() {

    }

    public static SetWallpaperFragment newInstance(String param1, String param2) {
        SetWallpaperFragment fragment = new SetWallpaperFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainActivity) getActivity()).hideBlurView();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private FragmentSetWallpaperBinding binding;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSetWallpaperBinding.inflate(inflater, container, false);
        Bundle bundle = getArguments();
        ImageData imageData = (ImageData) bundle.getSerializable("image");
        Picasso.get().load(imageData.getImageUrl()).into(binding.image);
        Animation fromTop = AnimationUtils.loadAnimation(requireContext(), R.anim.from_top);
        Animation fromBottom = AnimationUtils.loadAnimation(requireContext(), R.anim.from_bottom);
        binding.iconBack.startAnimation(fromTop);
        binding.iconBoth.startAnimation(fromBottom);
        binding.iconLock.startAnimation(fromBottom);
        binding.iconHome.startAnimation(fromBottom);

        binding.iconBack.setOnClickListener(v -> {
            Navigation.findNavController(v).popBackStack();
        });

        binding.iconHome.setOnClickListener(v -> {
            final Bitmap[] result = {null};
            Picasso.get().load(imageData.getImageUrl()).into(new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    result[0] = bitmap;
                }

                @Override
                public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {

                }
            });
            WallpaperManager wallpaperManager = WallpaperManager.getInstance(requireContext());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                try {
                    wallpaperManager.setBitmap(result[0], null, false, WallpaperManager.FLAG_SYSTEM);
                    Toast.makeText(requireContext(), "Successfully set", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        binding.iconLock.setOnClickListener(v -> {
            final Bitmap[] result = {null};
            Picasso.get().load(imageData.getImageUrl()).into(new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    result[0] = bitmap;
                }

                @Override
                public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {

                }
            });
            WallpaperManager wallpaperManager = WallpaperManager.getInstance(requireContext());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                try {
                    wallpaperManager.setBitmap(result[0], null, false, WallpaperManager.FLAG_LOCK);
                    Toast.makeText(requireContext(), "Successfully set", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        binding.iconBoth.setOnClickListener(v -> {
            final Bitmap[] result = {null};
            Picasso.get().load(imageData.getImageUrl()).into(new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    result[0] = bitmap;
                }

                @Override
                public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {

                }
            });
            WallpaperManager wallpaperManager = WallpaperManager.getInstance(requireContext());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                try {
                    wallpaperManager.setBitmap(result[0], null, false, WallpaperManager.FLAG_LOCK | WallpaperManager.FLAG_SYSTEM);
                    Toast.makeText(requireContext(), "Successfully set", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}