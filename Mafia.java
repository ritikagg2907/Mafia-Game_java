package Assignment_2;

import java.util.*;

public class Mafia extends Players {
	private ArrayList<Players> gamer = new ArrayList<>();
	private ArrayList<Players> Player = new ArrayList<Players>();
	private static ArrayList<Mafia> Maf = new ArrayList<>();
	Players pl =null;

	public Mafia(int n) {
		super(n);
		// TODO Auto-generated constructor stub
	}

	public Mafia() {
		// TODO Auto-generated constructor stub
	}

	final static Scanner scn = new Scanner(System.in);
	private int Hp = 2500;

	public void incHp() {
		this.Hp += 500;
	}

	public int getHp() {
		return this.Hp;
	}

	public void decHp(int n) {
		this.Hp -= n;
	}

	@Override
	public void setHP(int n) {
		this.Hp = n;
	}

	@Override
	public boolean equals(Object o1) {
		if (o1 != null && getClass() == o1.getClass()) {
			return true;
		}
		return false;
	}
	@Override
	public void manual(ArrayList<Players> Play, ArrayList<Players> game, ArrayList<Mafia> Mafi , ArrayList<Detective> Dete) {
		Maf = Mafi;
		Player = Play;
		gamer = game;
		System.out.println("Choose a target :");
		int t = scn.nextInt();
		for(int i=0;i<Player.size();i++) {
			if(t==Player.get(i).getPtype()){
				Players p = Player.get(i);
				if(p.getClass() == Maf.get(0).getClass()) {
					System.out.println("You cannot choose a Mafia. Choose another target : ");
					manual(Player, gamer, Maf, null);
				}
				else {
					int php = p.getHp();
					int div = php/Maf.size();
					//System.out.println("Div " + div);
					int total_hp = 0;
					for(int k=0;k<Maf.size();k++) {
						total_hp += Maf.get(k).getHp();
					}
					if(total_hp>=php) {
						//System.out.println("If is running");
						for(int k=0;k<Maf.size();k++) {
							Mafia m = Maf.get(k);
							if(m.getHp()>=div) {
								m.decHp(div);
							}
							else {
								div = m.getHp()-div;
								div += div/(Maf.size()-k+1);
								m.setHP(0);
							}
							p.setHP(0);
						}
						pl = Player.get(i);
					}
					else {
						php -= total_hp;
						p.setHP(php);
						for(int k=0;k<Maf.size();k++) {
							Mafia m = Maf.get(k);
							m.setHP(0);
						}
					}
				}
			}
		}
	}
	@Override
	public void auto(ArrayList<Players> Play, ArrayList<Players> game, ArrayList<Mafia> Mafi , ArrayList<Detective> Dete) {
		Maf = Mafi;
		Player = Play;
		gamer = game;
		Random rn = new Random();
		int range = gamer.size() - Maf.size();
		int index = rn.nextInt(range) + Maf.size();
		Players p = Player.get(index);
		int php = p.getHp();
		int div = php/Maf.size();
		//System.out.println("Div " + div);
		int total_hp = 0;
		for(int k=0;k<Maf.size();k++) {
			total_hp += Maf.get(k).getHp();
		}
		if(total_hp>=php) {
			//System.out.println("If is running");
			for(int k=0;k<Maf.size();k++) {
				Mafia m = Maf.get(k);
				if(m.getHp()>=div) {
					m.decHp(div);
				}
				else {
					div = m.getHp()-div;
					div += div/(Maf.size()-k+1);
					m.setHP(0);
				}
				p.setHP(0);
			}
			pl = gamer.get(index);
		}
		else {
			php -= total_hp;
			p.setHP(php);
			for(int k=0;k<Maf.size();k++) {
				Mafia m = Maf.get(k);
				m.setHP(0);
			}
		}
	}
	
	public Players getPl() {
		if(pl==null) {
			return null;
		}
		return pl;
	}
	/*
	 * public void start1(ArrayList<Mafia> Mafi, ArrayList<Players> Play,
	 * ArrayList<Players> game) { Maf = Mafi; Player = Play; gamer = game;
	 * System.out.println(gamer.size() + " Players are remaining : "); for (int i =
	 * 0; i < gamer.size(); i++) { System.out.print("Player" +
	 * gamer.get(i).getPtype() + ", "); } System.out.println(); boolean flag =
	 * false; Players pl = null; System.out.println("Choose a target :"); int t =
	 * scn.nextInt(); for(int i=0;i<Player.size();i++) {
	 * if(t==Player.get(i).getPtype()){ Players p = Player.get(i); flag = true;
	 * if(p.getClass() == Maf.get(0).getClass()) {
	 * System.out.println("You cannot choose a Mafia. Choose another target : ");
	 * start1(Maf, Player, gamer); } else {
	 * System.out.println("Detective have choosen a player to test."); Random rn =
	 * new Random(); int range = gamer.size() - 0; int index = rn.nextInt(range) +
	 * 0; Players pi = gamer.get(index); if(pi.getClass()==Maf.get(0).getClass()) {
	 * if(pi==Maf.get(0)) { System.out.println("Detective Caught Mafia(user)");
	 * gamer.remove(0); Maf.remove(0); auto(gamer, Maf); break; } else {
	 * System.out.println("Detective Caught Mafia Player" + pi.getPtype()); for(int
	 * l=0;l<Maf.size();l++) { if(pi == Maf.get(l)) { Maf.remove(l);
	 * gamer.remove(l); } } } }
	 * System.out.println("Healers have choosen someone to boost."); int php =
	 * p.getHp(); int div = php/Maf.size(); //System.out.println("Div " + div); int
	 * total_hp = 0; for(int k=0;k<Maf.size();k++) { total_hp += Maf.get(k).getHp();
	 * } if(total_hp>=php) { //System.out.println("If is running"); for(int
	 * k=0;k<Maf.size();k++) { Mafia m = Maf.get(k); if(m.getHp()>=div) {
	 * m.decHp(div); } else { div = m.getHp()-div; div += div/(Maf.size()-k+1);
	 * m.setHP(0); } p.setHP(0); } pl = getGamer().remove(i); } else { php -=
	 * total_hp; p.setHP(php); for(int k=0;k<Maf.size();k++) { Mafia m = Maf.get(k);
	 * m.setHP(0); }
	 * 
	 * } } i=0; } Player = getGamer(); }
	 * 
	 * if(!flag) { System.out.println("Player has already been terminated");
	 * start1(Maf, Player, gamer); } System.out.println("--- End of actions ---");
	 * if(pl!=null) { System.out.println("Player " + pl.getPtype() + " has died.");
	 * } System.out.println("Select a person to vote out"); int vote =
	 * scn.nextInt(); Random rn = new Random(); int range = gamer.size() - 0; int
	 * index = rn.nextInt(range) + 1; System.out.println("Player "
	 * +getGamer().get(index-1).getPtype() + " has been voted out");
	 * getGamer().remove(index-1); if(index-1==0) { Maf.remove(0); auto(gamer, Maf);
	 * } else if(index-1==1) { if(Maf.size()>=1) { Maf.remove(1); } } for(int
	 * q=0;q<Maf.size();q++) { System.out.println(Maf.get(q).getHp()); } }
	 */

	
	
	/*
	 * public int auto(ArrayList<Players> gamer, ArrayList<Mafia> Maf) {
	 * System.out.println(gamer.size() + " Players are remaining : "); for (int i =
	 * 0; i < gamer.size(); i++) { System.out.print("Player" +
	 * gamer.get(i).getPtype() + ", "); } int count =0; if(gamer.size()==1 ||
	 * Maf.size()==0) { System.out.println("Game Ended\nMafia Loses"); }
	 * while(Maf.size()>0 && Maf.size()!= gamer.size()-Maf.size()) {
	 * System.out.println("Mafias have chosen their target."); Random rn = new
	 * Random(); int range = gamer.size() - 1; int index = rn.nextInt(range) + 1;
	 * Players po = gamer.get(index); gamer.remove(index);
	 * System.out.println("Detectives have chosen a player to test."); Random rn1 =
	 * new Random(); int range1 = gamer.size() - 0; int index1 = rn.nextInt(range) +
	 * 0; Players pd = gamer.get(index1); for(int i=0;i<gamer.size();i++) { Players
	 * pf = gamer.get(i); if(pf.getClass()==Player.get(2).getClass()) { count++; } }
	 * if(count>0) { if(pd.getClass()==Maf.get(0).getClass()) { for(int
	 * l=0;l<Maf.size();l++) { if(pd == Maf.get(l)) { Maf.remove(l);
	 * gamer.remove(l); System.out.println("Mafia has been caught"); break; } } }
	 * else { } } System.out.println("Healers have chosen someone to heal.");
	 * System.out.println("--- End of Action ---"); System.out.println("Player " +
	 * po.getPtype() + " has died");
	 * System.out.println("Choose Player to vote out"); int n = scn.nextInt();
	 * Random rn2 = new Random(); int range2 = gamer.size() - 0; int index2 =
	 * rn.nextInt(range) + 0;
	 * if(gamer.get(index2).getClass()==Maf.get(0).getClass()) { Maf.remove(0); }
	 * System.out.println("Player " + gamer.get(index2).getPtype()+
	 * " has been voted out"); gamer.remove(index2);
	 * 
	 * auto(gamer, Maf); } return 0; }
	 */
	public ArrayList<Players> getPlayer() {
		return Player;
	}
	public ArrayList<Players> getGamer() {
		return gamer;
	}

	public ArrayList<Mafia> getMafia() {
		return Maf;
	}
}
