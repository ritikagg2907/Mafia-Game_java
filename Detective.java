package Assignment_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Detective extends Players{
	final Scanner scn = new Scanner(System.in);
	private ArrayList<Players> gamers= new ArrayList<>();
	private static ArrayList<Mafia> Mafie = new ArrayList<>();
	private static ArrayList<Detective> Det = new ArrayList<>();
	private boolean keep = true;
	Players pl = null;
	private int Hp = 800;
	public void incHp() {
		this.Hp += 500;
	}
	public Detective() {
		
	}
	public int getHp() {
		return this.Hp;
	}
	public Detective(int n) {
		super(n);
	}
	@Override
	public void setHP(int n) {
		this.Hp=n;
	}
	@Override
	public void manual(ArrayList<Players> Play, ArrayList<Players> game, ArrayList<Mafia> Mafi , ArrayList<Detective> Dete) {
		Det = Dete;
		gamers = game;
		Mafie = Mafi;
		System.out.println("Mafia has choosen its target");
		System.out.println("Choose a player to test : ");
		int t = scn.nextInt();
		for(int i=0;i<gamers.size();i++) {
			if(t==gamers.get(i).getPtype()){
				Players p = gamers.get(i);
				pl = gamers.get(i);
				if(p.getClass() == Det.get(0).getClass()) {
					System.out.println("You cannot choose a Detective. Choose another player : ");
					manual(null, gamers, Mafie, Det);
				}
				else {
					if(p.getClass()== Mafie.get(0).getClass()) {
						System.out.println("Player " + p.getPtype() + " is Mafia");
					}
					else {
						System.out.println("Player " + p.getPtype() + " is not Mafia");
					}
				}
			}
		}
	}
	
	@Override
	public void auto(ArrayList<Players> Play, ArrayList<Players> game, ArrayList<Mafia> Mafi , ArrayList<Detective> Dete){
		Det = Dete;
		gamers = game;
		Mafie=Mafi;
		int count=0;
		for(int i=0;i<gamers.size();i++) {
			if(gamers.get(i).getClass()==Det.get(0).getClass()) {
				count++;
			}
		}
		System.out.println("Detectives have choosen someone to test");
		ArrayList<Players> newd = new ArrayList<>();
		for(int i=0;i<Mafie.size();i++) {
			Players p = gamers.get(i);
			newd.add(p);
		}
		int thp = 0;
		for(int i=0;i<Mafie.size();i++) {
			thp += Mafie.get(i).getHp();
		}
		for(int i=Mafie.size()+Det.size();i<gamers.size();i++) {
			newd.add(gamers.get(i));
		}
		Players p = null;
		int index=0;
		if(newd.size()>3) {
			Random rn = new Random();
			int range = newd.size()-1;
			index = rn.nextInt(range) + 1;
			p = newd.get(index);
		}
		else if(newd.size()<=3 || thp==0){
			Random rn = new Random();
			int range = newd.size()-0;
			index = rn.nextInt(range) + 0;
			p = newd.get(index);
		}
		if(count>0) {
			if(p.getClass()== Mafie.get(0).getClass()) {
				System.out.println("Player " + p.getPtype() + " is Mafia");
				if(index==0) {
					keep = false;
				}
				pl = newd.get(index);
			}
			else {
				pl = newd.get(index);
			}
		}
	}
	
	public Players getPl() {
		if(pl!=null) {
			return pl;
		}
		return pl;
	}
	/**
	 * @return the gamer
	 */
	public ArrayList<Players> getGamer() {
		return gamers;
	}
	public ArrayList<Mafia> getMafia() {
		return Mafie;
	}
	public boolean getkeep() {
		return keep;
	}
}

