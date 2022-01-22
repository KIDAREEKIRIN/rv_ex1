package com.personal.rv_ex1;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.CustomViewHolder>{

    private ArrayList<MainData> arrayList = null;
    private Context context;

    public interface OnItemClickListener {
        void onItemClick(View v, int pos);
    }

    private OnItemClickListener mListener = null;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public MainAdapter(ArrayList<MainData> arrayList)

    {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MainAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext(); // 정의 여부에 따라서 밑의 from 괄호 안의 내용이 달라짐.
        View view = LayoutInflater.from(context).inflate(R.layout.item_list,parent,false);
        // 항상 new holder를 반환할 것.(새로운 인스턴스만 반환하기)
        CustomViewHolder holder = new CustomViewHolder(view); // CustomViewHolder에 대한 부분. 새롭게 정의하여 이후에 return 값을 holder로 받음.

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.CustomViewHolder holder, int position) {

        holder.iv_profile.setImageResource(arrayList.get(position).getIv_profile());
        holder.tv_name.setText(arrayList.get(position).getTv_name());
        holder.tv_content.setText(arrayList.get(position).getTv_content());

        // 람다식.
        holder.itemView.setOnClickListener(v -> {
            int pos = holder.getAbsoluteAdapterPosition();
            if(pos != RecyclerView.NO_POSITION) {
                arrayList.set(pos, arrayList.get(pos));
                Log.d(TAG, "onBindViewHolder: 이제 토스트 띄우기");
                Toast.makeText(context.getApplicationContext(), "item clicked"+ pos+1, Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onBindViewHolder: 토스트까지 띄움");
                notifyItemChanged(pos);
                Log.d(TAG, "onBindViewHolder: itemchanged");
            }
        });

//        holder.itemView.setTag(position);
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // getText() 까지는 Object 형태라서 뒤에 toString()을 붙여 string값으로 바꿔준다.
//                String curName = holder.tv_name.getText().toString();
//                Toast.makeText(v.getContext(), curName, Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                remove(holder.getAbsoluteAdapterPosition());
//                return true;
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    // remove 메서드 만들기.
    public void remove(int position){
        try {
            arrayList.remove(position);
            notifyItemRemoved(position);
        } catch (IndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected ImageView iv_profile;
        protected TextView tv_name;
        protected TextView tv_content;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.iv_profile = (ImageView) itemView.findViewById(R.id.iv_profile);
            this.tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            this.tv_content = (TextView) itemView.findViewById(R.id.tv_content);


//
//            iv_profile = itemView.findViewById(R.id.iv_profile);
//            tv_name = itemView.findViewById(R.id.tv_name);
//            tv_content = itemView.findViewById(R.id.tv_content);
        }


    }
}
