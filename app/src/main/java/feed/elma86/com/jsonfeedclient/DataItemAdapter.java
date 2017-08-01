package feed.elma86.com.jsonfeedclient;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import feed.elma86.com.jsonfeedclient.model.DataItem;

public class DataItemAdapter extends RecyclerView.Adapter<DataItemAdapter.ViewHolder> {

    //intent constants
    public static final String ITEM_KEY ="item_id_key";//make sure its public
    //  public static final String ITEM_ID_KEY ="item_id_key" ;//make sure its public
    //instance vars
    private List<DataItem> mItems;
    private Context mContext;

    public DataItemAdapter(Context context, List<DataItem> items) {
        this.mContext = context;
        this.mItems = items;
    }

    @Override
    public DataItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(DataItemAdapter.ViewHolder holder, int position) {
        final DataItem item = mItems.get(position);


        try {
            holder.titletext.setText(item.getTitle());
            holder.companyText.setText(item.getCompany());
            holder.cityText.setText(item.getCity());
            holder.salarytext.setText(""+ item.getSalary());
            holder.descriptionText.setText(item.getDescription());
            holder.responsibilityText.setText(item.getResponsibility());
            holder.latText.setText(""+ item.getLatitude());
            holder.lngText.setText(""+ item.getLongitude());
            holder.phoneText.setText(item.getPhone());
            holder.provinceText.setText(item.getProvince());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //click
        holder.mView.setOnClickListener(new View.OnClickListener() {//getting viewholder class and ctor
            @Override
            public void onClick(View v) {
                //get item id frm dataItem class/2/create intent/3/make string constant and pass in extra
                 String itemId = item.getTitle(); //non parcable
                Toast.makeText(mContext, "long click: " + item.getTitle(), Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(mContext,DetailsActivity.class);
                //intent.putExtra(ITEM_KEY,item);
               // mContext.startActivity(intent);
            }
        });
        //long click
        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(mContext, "long click: " + item.getTitle(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView latText;
        public TextView lngText;
        public TextView cityText;
        public TextView provinceText;
        public TextView salarytext;
        public TextView phoneText;
        public TextView descriptionText;
        public TextView titletext;
        public TextView companyText;
        public TextView responsibilityText;
        View mView;



        public ViewHolder(View itemView) {
            super(itemView);
            latText =(TextView) itemView.findViewById(R.id.latText);
            lngText =(TextView) itemView.findViewById(R.id.lngText);
            cityText =(TextView) itemView.findViewById(R.id.cityText);
            provinceText =(TextView) itemView.findViewById(R.id.provinceText);
            salarytext =(TextView) itemView.findViewById(R.id.salaryText);
            phoneText =(TextView) itemView.findViewById(R.id.phoneText);
            descriptionText =(TextView) itemView.findViewById(R.id.descriptionText);
            titletext =(TextView) itemView.findViewById(R.id.titleText);
            companyText =(TextView) itemView.findViewById(R.id.companyText);
            responsibilityText=(TextView) itemView.findViewById(R.id.responsibilityText);

            mView = itemView;
        }
    }
}
