

package com.mrcodekiddie.tobuytoday;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class ItemMasterAdapter extends RecyclerView.Adapter<ItemMasterAdapter.ItemMasterViewHolder> implements Filterable
{
    private Context context;
    private List<Order> itemList;
    private List<Order> itemListFull;
    private LayoutInflater inflater;

    public static HashMap<String, Order> orderHashMap = new HashMap<String, Order>();
    public static ArrayList<EditModel> editModelArrayList;

    public ItemMasterAdapter(Context context, List<Order> itemList, ArrayList<EditModel> editModelArrayList1)
    {
        this.context = context;
        this.itemList = itemList;
        itemListFull=new ArrayList<>(itemList);
        this.inflater = LayoutInflater.from(context);
        this.editModelArrayList = editModelArrayList1;
    }

    @NonNull
    @Override
    public ItemMasterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View imView = inflater.inflate(R.layout.list_item_master, viewGroup, false);
        return new ItemMasterViewHolder(imView);
    }

    @Override

    public void onBindViewHolder(@NonNull ItemMasterViewHolder itemMasterViewHolder, int i)
    {

        Order iList = itemList.get(i);
       itemMasterViewHolder.itemNameTextView.setText(iList.itemName);
       itemMasterViewHolder.inputQtyEditText.setText(editModelArrayList.get(i).getEditTextValue());
    }

    @Override
    public int getItemCount()
    {
        return itemList.size();
    }

    @Override
    public long getItemId(int position)
    {
        return super.getItemId(position);
    }

    @Override
    public int getItemViewType(int position)
    {
        return super.getItemViewType(position);
    }

    public class ItemMasterViewHolder extends RecyclerView.ViewHolder
    {
        ImageView itemImageView;
        TextView itemNameTextView, itemQtyTextView, itemPriceTextView, itemStockTextView;
        EditText inputQtyEditText;

        public ItemMasterViewHolder(@NonNull View itemView)
        {
            super(itemView);

            itemNameTextView = itemView.findViewById(R.id.mtv_item_name);
            inputQtyEditText = itemView.findViewById(R.id.et_input_item_stock);
            inputQtyEditText.addTextChangedListener(new TextWatcher()
            {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after)
                {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count)
                {
                    Log.d("adapter position", String.valueOf(getAdapterPosition()));

                   // editModelArrayList.get(getAdapterPosition()).setEditTextValue(inputQtyEditText.getText().toString());
                    String inputValue=inputQtyEditText.getText().toString();
                    Order iList = itemList.get(getAdapterPosition());
                  editModelArrayList.get(itemList.indexOf(iList)).setEditTextValue(inputValue);



                }

                @Override
                public void afterTextChanged(Editable s)
                {

                }
            });
        }
    }



    @Override
    public Filter getFilter()
    {
        return itemFilter;
    }

    private Filter itemFilter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Order> filteredItemList=new ArrayList<>();

            if(constraint==null || constraint.length()==0)
            {
                filteredItemList.addAll(itemListFull);

            }

            else
            {
                String filteredPattern=constraint.toString().toLowerCase().trim();

                for(Order item : itemListFull)
                {
                    if(item.itemName.toLowerCase().contains(filteredPattern))
                    {
                        filteredItemList.add(item);
                    }
                }
            }

            FilterResults results=new FilterResults();
            results.values=filteredItemList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results)
        {
            itemList.clear();
            itemList.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };

}
