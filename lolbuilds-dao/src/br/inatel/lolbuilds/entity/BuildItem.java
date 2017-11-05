package br.inatel.lolbuilds.entity;

import java.io.Serializable;

public class BuildItem implements Serializable {
	private static final long serialVersionUID = 1L;

	private int build_id;

	private int item_id;

	public BuildItem() {
	}

	public int getBuildId() {
		return build_id;
	}

	public void setBuildId(int build_id) {
		this.build_id = build_id;
	}

	public int getItemId() {
		return item_id;
	}

	public void setItemId(int item_id) {
		this.item_id = item_id;
	}		
}
