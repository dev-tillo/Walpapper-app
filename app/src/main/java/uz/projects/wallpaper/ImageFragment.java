package uz.projects.wallpaper;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;
import java.util.List;

import eightbitlab.com.blurview.RenderScriptBlur;
import uz.projects.wallpaper.databinding.BottomDialogBinding;
import uz.projects.wallpaper.databinding.FragmentImageBinding;
import uz.projects.wallpaper.models.Category;
import uz.projects.wallpaper.models.ImageData;
import uz.projects.wallpaper.preference.MySharedPreference;

public class ImageFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public ImageFragment() {
        // Required empty public constructor
    }


    public static ImageFragment newInstance(String param1, String param2) {
        ImageFragment fragment = new ImageFragment();
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

    private FragmentImageBinding binding;
    private MySharedPreference preference;
    private List<Category> categoryList;
    private Gson gson = new Gson();

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentImageBinding.inflate(inflater, container, false);
        Bundle bundle = getArguments();
        ImageData imageData = (ImageData) bundle.getSerializable("image");
        if (imageData.isLiked()) {
            binding.imgLike.setImageResource(R.drawable.ic_heart);
        } else {
            binding.imgLike.setImageResource(R.drawable.ic_liked);
        }
        preference = MySharedPreference.getInstance(requireContext());
        String listString = preference.getNumberListString();
        Type type = new TypeToken<List<Category>>() {
        }.getType();
        categoryList = gson.fromJson(listString, type);
        Picasso.get().load(imageData.getImageUrl()).into(binding.image);
        Animation fromTop = AnimationUtils.loadAnimation(requireContext(), R.anim.from_top);
        binding.iconBack.startAnimation(fromTop);
        binding.iconShare.startAnimation(fromTop);
        binding.iconAbout.startAnimation(fromTop);
        Animation fromBottom = AnimationUtils.loadAnimation(requireContext(), R.anim.from_bottom);
        binding.iconDownload.startAnimation(fromBottom);
        binding.iconPut.startAnimation(fromBottom);
        binding.iconCustomize.startAnimation(fromBottom);
        binding.iconLike.startAnimation(fromBottom);
        binding.iconBack.setOnClickListener(v -> {
            Navigation.findNavController(v).popBackStack();
            onDestroyView1();
        });
        binding.iconDownload.setOnClickListener(v -> {
            Toast.makeText(requireContext(), "Downloading...", Toast.LENGTH_SHORT).show();
        });
        binding.iconShare.setOnClickListener(v -> {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, imageData.getImageUrl());
            sendIntent.setType("text/plain");
            Intent shareIntent = Intent.createChooser(sendIntent, null);
            startActivity(shareIntent);
        });
        binding.iconAbout.setOnClickListener(v -> {
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(requireContext(), R.style.BottomSheetStyle);
            BottomDialogBinding bottomDialogBinding = BottomDialogBinding.inflate(getLayoutInflater());
            bottomDialogBinding.authorTv.setText(imageData.getAuthor());
            bottomSheetDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            bottomSheetDialog.setCancelable(true);
            bottomSheetDialog.setContentView(bottomDialogBinding.getRoot());
            float radius = 20;
            bottomSheetDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            bottomDialogBinding.blurViewDialog.setupWith(container)
                    .setBlurAlgorithm(new RenderScriptBlur(requireContext()))
                    .setBlurRadius(radius)
                    .setBlurAutoUpdate(true)
                    .setHasFixedTransformationMatrix(true);
            bottomSheetDialog.show();
        });
        binding.iconLike.setOnClickListener(v -> {
            if (imageData.isLiked()) {
                binding.imgLike.setImageResource(R.drawable.ic_liked);
                for (int i = 0; i < categoryList.size(); i++) {
                    List<ImageData> imageDataList = categoryList.get(i).getImageDataList();
                    for (int j = 0; j < imageDataList.size(); j++) {
                        if (imageDataList.get(j).getImageUrl().equalsIgnoreCase(imageData.getImageUrl())) {
                            categoryList.get(i).getImageDataList().get(j).setLiked(false);
                        }
                    }
                }
                imageData.setLiked(false);
                String s = gson.toJson(categoryList);
                preference.setUserList(s);
            } else {
                binding.imgLike.setImageResource(R.drawable.ic_heart);
                for (int i = 0; i < categoryList.size(); i++) {
                    List<ImageData> imageDataList = categoryList.get(i).getImageDataList();
                    for (int j = 0; j < imageDataList.size(); j++) {
                        if (imageDataList.get(j).getImageUrl().equalsIgnoreCase(imageData.getImageUrl())) {
                            categoryList.get(i).getImageDataList().get(j).setLiked(true);
                        }
                    }
                }
                imageData.setLiked(true);
                String s = gson.toJson(categoryList);
                preference.setUserList(s);
            }

        });

        binding.iconPut.setOnClickListener(v -> {
            Bundle bundle1 = new Bundle();
            bundle1.putSerializable("image", imageData);
            Navigation.findNavController(v).navigate(R.id.action_imageFragment_to_setWallpaperFragment, bundle1);
        });

        return binding.getRoot();
    }

    public void onDestroyView1() {
        super.onDestroyView();
        binding = null;
        ((MainActivity) getActivity()).showBlurView();
    }

}