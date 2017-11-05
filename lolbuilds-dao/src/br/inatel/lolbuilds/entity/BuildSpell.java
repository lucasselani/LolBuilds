package br.inatel.lolbuilds.entity;

import java.io.Serializable;

public class BuildSpell implements Serializable {
	private static final long serialVersionUID = 1L;

	private int build_id;

	private int spell_id;

	public BuildSpell() {
	}

	public int getBuildId() {
		return build_id;
	}

	public void setBuildId(int build_id) {
		this.build_id = build_id;
	}

	public int getSpellId() {
		return spell_id;
	}

	public void setSpellId(int spell_id) {
		this.spell_id = spell_id;
	}	
}
