package model;

import java.io.*;
import java.io.Serializable;

public class Pet implements Serializable, Comparable<Pet> {

	public static final int F=1;
	public static final int M=2;
	
	
	private String id;
	private String name;
	private int genre;
	private int type;
	private String date;
	
	public Pet(String id, String name, int genre, int type, String date) {
		this.id = id;
		this.name = name;
		this.genre = genre;
		this.type = type;
		this.date = date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGenre() {
		return genre;
	}

	public void setGenre(int genre) {
		this.genre = genre;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Pet [id=" + id + ", name=" + name + ", genre=" + genre + ", type=" + type + ", date=" + date + "]";
	}

	@Override
	public int compareTo(Pet p) {
		return this.getName().compareTo(p.getName());
	}
	
	
}
