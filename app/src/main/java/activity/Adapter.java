package activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.addstudentdetailsproject.R;


import java.util.List;

import model.StudentModel;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private  Context context;

    private final List<StudentModel> studentModel;


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView txtview_stdname,txtview_stdmobno,txtview_stdclg2,txtview_stdaddress;
        CardView cardView;
        View itemView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView     ;

            txtview_stdname = itemView.findViewById(R.id.txtview_stdname);
            txtview_stdmobno = itemView.findViewById(R.id.txtview_stdmobno);
            txtview_stdclg2 = itemView.findViewById(R.id.txtview_stdclg2);
            txtview_stdaddress = itemView.findViewById(R.id.txtview_stdaddress);

            /* on cardview */
            cardView = itemView.findViewById(R.id.cardView1);


        }
    }

    public Adapter(Context context, List<StudentModel>studentModel){
        this.studentModel=studentModel;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_item_activity, viewGroup, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int position) {

        final StudentModel stdmodel = studentModel.get(position);
        myViewHolder.txtview_stdname.setText(stdmodel.getStdname());
        myViewHolder.txtview_stdmobno.setText(stdmodel.getStdphno());
        myViewHolder.txtview_stdclg2.setText(stdmodel.getStdclg());
        myViewHolder.txtview_stdaddress.setText(stdmodel.getStdadd());

        /**  update method */

        myViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(context, Update_Delete.class);

                intent.putExtra("Name",studentModel.get(myViewHolder.getAdapterPosition()).getStdname());
                intent.putExtra("MobileNo",studentModel.get(myViewHolder.getAdapterPosition()).getStdphno());
                intent.putExtra("Clgname",studentModel.get(myViewHolder.getAdapterPosition()).getStdclg());
                intent.putExtra("StudentAddress",studentModel.get(myViewHolder.getAdapterPosition()).getStdadd());

                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return studentModel.size();
    }


}
