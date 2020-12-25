package Assignment_2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public abstract class Players{
	private Players Ptype;
	private int Hp;
	private String type;
	int st;
	public Players pl;
	
	public abstract void manual(ArrayList<Players> Play, ArrayList<Players> game, ArrayList<Mafia> Mafi , ArrayList<Detective> Dete);
	public abstract void auto(ArrayList<Players> Play, ArrayList<Players> game, ArrayList<Mafia> Mafi , ArrayList<Detective> Dete);
	public Players(int n) {
		this.st = n;
	}
	public Players() {
		
	}
	public int getPtype() {
		return this.st;
	}
	public int getHp() {
		return this.Hp;
	}
	public void setHP(int hp) {
        this.Hp = hp;
    }

	public void incHp() {
		this.Hp += 500;
	}

}
