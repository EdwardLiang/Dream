package infrastructure;

import java.util.HashMap;

import entities.Entity;

public class Frame {
	HashMap<Entity,EntityData> eData;
	public Frame(){
		eData = new HashMap<Entity,EntityData>();
	}
	public void addEntity(Entity e){
		eData.put(e, new EntityData(e));
	}
	public HashMap<Entity,EntityData> getData(){
		return eData;
	}
}