package com.bailemeng.app.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;


/**
 * 项目名称：BaiLeMeng
 * 类描述：RecyclerView基础Adapter封装
 * 创建人：abc
 * 创建时间 2016/09/23 09:29
 */

public abstract class RecyclerBaseAdapter<VH extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<VH> {
	
	private OnItemClickListener itemClickListener;
	
	@Override
	public void onBindViewHolder(final VH holder, int position) {
		onBindHolder(holder, position);
		holder.itemView.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v) {
				if (itemClickListener != null) itemClickListener.onItemClick(holder.getAdapterPosition());
			}
		});
		holder.itemView.setOnLongClickListener(new View.OnLongClickListener()
		{
			@Override
			public boolean onLongClick(View v) {
				if (itemClickListener != null) itemClickListener.onItemLongClick(holder.getAdapterPosition());
				return false;
			}
		});
	}
	
	public interface OnItemClickListener{
		void onItemClick(int position);
		
		void onItemLongClick(int position);
	}
	
	public void setOnItemClickListener(OnItemClickListener listener) {
		this.itemClickListener = listener;
	}
	
	protected abstract void onBindHolder(final VH holder, int position);
}
