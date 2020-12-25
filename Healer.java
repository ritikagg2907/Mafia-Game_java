package Assignment_2;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Healer extends Players{
	private int Hp = 800;
	private ArrayList<Players> gamer= new ArrayList<>();
	private ArrayList<Mafia> Maf = new ArrayList<>();
	private ArrayList<Players> Player = new ArrayList<Players>();
	Scanner scn = new Scanner(System.in);
	private boolean present = false;
	Players pl = null;
	public int getHp() {
		return this.Hp;
	}
	public void incHp() {
		this.Hp += 500;
	}
	public Healer(int n) {
		super(n);
	}
	public Healer() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void setHP(int n) {
		this.Hp=n;
	}
	@Override
	public void manual(ArrayList<Players> Play, ArrayList<Players> game, ArrayList<Mafia> Mafi , ArrayList<Detective> Det) {
		Player = Play;
		gamer = game;
		Maf = Mafi;
		System.out.println("Choose someone to heal");
		int t = scn.nextInt();
		for(int i=0;i<gamer.size();i++) {
			if(t==gamer.get(i).getPtype()) {
				pl = gamer.get(i);
				present = true;
				pl.incHp();
				System.out.println("Healer has choosen Player " + pl.getPtype());
			}
		}
		if(!present) {
			System.out.println("Enter a valid Player :");
			manual(Player, gamer, Maf, null);
		}
	}
	@Override
	public void auto(ArrayList<Players> Play, ArrayList<Players> game, ArrayList<Mafia> Mafi , ArrayList<Detective> Det) {
		Player = Play;
		gamer = game;
		Maf = Mafi;
		Random rn = new Random();
		int range = gamer.size() - 0;
		int index = rn.nextInt(range) + 0;
		pl = gamer.get(index);
		pl.incHp();
	}
	public Players getPl() {
		return pl;
	}
}