package Assignment_2;

import java.util.ArrayList;

public class Comon extends Players {
	private int Hp = 1000;
	
	public int getHp() {
		return this.Hp;
	}
	public void incHp() {
		this.Hp += 500;
	}
	@Override
	public void setHP(int n) {
		this.Hp=n;
	}
	public Comon(int n) {
		super(n);
	}
	@Override
	public void manual(ArrayList<Players> Play, ArrayList<Players> game, ArrayList<Mafia> Mafi,
			ArrayList<Detective> Dete) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void auto(ArrayList<Players> Play, ArrayList<Players> game, ArrayList<Mafia> Mafi,
			ArrayList<Detective> Dete) {
		// TODO Auto-generated method stub
		
	}
}
