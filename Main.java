package Assignment_2;

import java.awt.*;
import java.util.*;
import java.io.*;
import java.math.*;

public class Main {
	static Scanner scn = new Scanner(System.in);
	private static ArrayList<Players> Player = new ArrayList<Players>();
	private static ArrayList<Detective> Det = new ArrayList<>();
	private static ArrayList<Healer> Heal = new ArrayList<>();
	private static ArrayList<Mafia> Maf = new ArrayList<>();
	private static ArrayList<Comon> Com = new ArrayList<>();
	private static ArrayList<Integer> arr = new ArrayList<>();
	private static ArrayList<Players> gamer = new ArrayList<>();
	public static void main(String[] args) {
		System.out.println("Enter number of Players : ");
		int npl = scn.nextInt();
		if (npl < 6) {
			while (npl < 6) {
				System.out.println("Number of Players must be greater than or equal to 6, enter again : ");
				npl = scn.nextInt();
			}
		}
		for (int i = 1; i <= npl; i++) {
			arr.add(i);
		}
		for (int i = 0; i < npl / 5; i++) {
			Random rn = new Random();
			int range = arr.size() - 0;
			int index = rn.nextInt(range) + 1;
			int l = arr.get(index - 1);
			Mafia m = new Mafia(l);
			Maf.add(m);
			arr.remove(index - 1);
			Player.add(m);
		}
		for (int i = npl / 5; i < npl / 5 + npl / 5; i++) {
			Random rn = new Random();
			int range = arr.size() - 0;
			int index = rn.nextInt(range) + 1;
			int l = arr.get(index - 1);
			Detective m = new Detective(l);
			Det.add(m);
			arr.remove(index - 1);
			Player.add(m);
		}
		if (npl / 10 >= 1) {
			for (int i = (npl / 5) + (npl / 5); i < (npl / 5) + (npl / 5) + (npl / 10); i *= 10) {
				Random rn = new Random();
				int range = arr.size() - 0;
				int index = rn.nextInt(range) + 1;
				int l = arr.get(index - 1);
				Healer m = new Healer(l);
				Heal.add(m);
				arr.remove(index - 1);
				Player.add(m);
				npl = npl / 10;
			}
		} else {
			Random rn = new Random();
			int range = arr.size() - 0;
			int index = rn.nextInt(range) + 1;
			int l = arr.get(index - 1);
			Healer m = new Healer(l);
			Heal.add(m);
			arr.remove(index - 1);
			Player.add(m);
		}
		for (int i = 0; i < arr.size(); i++) {
			int l = arr.get(i);
			Comon m = new Comon(l);
			Com.add(m);
			Player.add(m);
		}
		ArrayList<Players> gamer = (ArrayList) Player.clone();
		ArrayList<Players> gamer2 = (ArrayList) gamer.clone();
		ArrayList<Mafia> Maf2 = (ArrayList) Maf.clone();
		ArrayList<Healer> Heal2 = (ArrayList) Heal.clone();
		int n;
		System.out.println("Choose a Character ");
		System.out.println(
				"1) Mafia\r\n" + "2) Detective\r\n" + "3) Healer\r\n" + "4) Commoner\r\n" + "5) Assign Randomly");
		n = scn.nextInt();
		while (true) {
			if (n == 1) {
				System.out.println("You are Player" + Maf.get(0).getPtype());
				System.out.println("You are a Mafia. Other Mafias are: ");
				for (int i = 1; i < Maf.size(); i++) {
					System.out.println("Player" + Maf.get(i).getPtype());
				}
				break;
			} else if (n == 2) {
				System.out.println("You are Player" + Det.get(0).getPtype());
				System.out.println("You are a Detective. Other Detective are: ");
				for (int i = 1; i < Det.size(); i++) {
					System.out.println("Player" + Det.get(i).getPtype());
				}
				break;
			} else if (n == 3) {
				System.out.println("You are Player" + Heal.get(0).getPtype());
				System.out.println("You are a Healer. Other Healer are: ");
				for (int i = 1; i < Heal.size(); i++) {
					System.out.println("Player" + Heal.get(i).getPtype());
				}
				break;
			} else if (n == 4) {
				System.out.println("You are a Player " + Com.get(0).getPtype() + ", a Commoner");
				break;

			} else if (n == 5) {
				Random rn = new Random();
				int index = rn.nextInt(2) + 1;
				n = index;

			} else {
				System.out.println("Enter valid number");
			}
		}
		boolean died = false;
		int j = 1;
		boolean killed = true;
		while (Maf.size() < gamer.size() - Maf.size() && Maf.size() != 0) {

			boolean saved = false;
			System.out.println("----------------------------------------------------Round " + j
					+ "----------------------------------------------------");
			j++;
			System.out.println(gamer.size() + " Players are remaining : ");
			for (int i = 0; i < gamer.size(); i++) {
				System.out.print("Player" + gamer.get(i).getPtype() + ", ");
			}
			System.out.println();
			if (n == 1) {
				boolean present= false;
				if (killed) {
					Mafia m = new Mafia();
					m.manual(Player, gamer, Maf, Det);
					Players mk = m.getPl();
					Detective d = new Detective();
					d.auto(Player, gamer, Maf, Det);
					Maf = (ArrayList) Maf2.clone();
					Players dc = d.getPl();
					System.out.println("Healers have choosen someone to heal");
					if (dc != null) {
						if (dc.getClass() == Maf.get(0).getClass()) {
							if (dc == Maf.get(0)) {
								System.out.println("Player " + Maf.get(0).getPtype() + " has been caught");
								Maf.remove(0);
								Maf2.remove(0);
								gamer2.remove(0);
								gamer.remove(0);
								killed = false;
								if (Maf.size() == 0 || Maf.size() == gamer.size() / 2) {
									break;
								}
								continue;
							} else {
								for (int i = 0; i < Maf.size(); i++) {
									if (dc == Maf.get(i)) {
										Maf.remove(i);
										Maf2.remove(i);
										gamer2.remove(i);
										gamer.remove(i);
									}
								}
								System.out.println("Player " + dc.getPtype() + " has been caught");
								continue;
							}
						}
					}
					if (Heal.size() > 0) {
						Healer h = new Healer();
						h.auto(Player, gamer, Maf, Det);
						Players healed = h.getPl();
						if (healed != mk) {
							for (int i = 0; i < gamer.size(); i++) {
								if (mk == gamer.get(i)) {
									gamer.remove(i);
									System.out.println("Player " + m.getPl().getPtype() + " has died");
								}
							}
						} else {
							System.out.println("Attacked player has been saved by Healer");
							saved = true;
						}
					}
					if (Heal.size() > 0 && mk != null) {
						if (mk.getClass() == Heal.get(0).getClass()) {
							Heal.remove(0);
						}
					}
					for (int i = 0; i < gamer.size(); i++) {
						if (mk == gamer.get(i)) {
							if (!saved) {
								gamer.remove(i);
								System.out.println("Player " + m.getPl().getPtype() + " has died");
							} else {
								System.out.println("No one has died");
							}
						}
					}
					System.out.println("Choose a Player to vote :");
					int vote = scn.nextInt();
					for(int i=0;i<gamer.size();i++) {
						if(gamer.get(i).getPtype()==vote) {
							present=true;
						}
					}
					if(!present) {
						System.out.println("Choose a Player to vote :");
						vote = scn.nextInt();
					}
					Random r = new Random();
					int range = gamer.size() - 1;
					int index = r.nextInt(range) + 1;
					System.out.println("Player " + gamer.get(index).getPtype() + " has been voted out");
					if (gamer.get(index).getClass() == Maf.get(0).getClass()) {
						for (int i = 0; i < Maf.size(); i++) {
							if (gamer.get(index) == Maf.get(i)) {
								Maf.remove(i);
								Maf2.remove(i);
								gamer2.remove(i);
								gamer.remove(i);
							}
						}
					} else {
						gamer.remove(index);
					}
				}
			}
			if (n == 2) {
				boolean present = false;
				if (!died) {
					Mafia m = new Mafia();
					m.auto(Player, gamer, Maf, Det);
					Players mk = m.getPl();
					Detective d = new Detective();
					d.manual(Player, gamer, Maf, Det);
					Maf = (ArrayList) Maf2.clone();
					Players dc = d.getPl();
					if (dc.getClass() == Maf.get(0).getClass()) {
						if (dc == Maf.get(0)) {
							System.out.println("Player " + Maf.get(0).getPtype() + " has been caught");
							Maf.remove(0);
							Maf2.remove(0);
							gamer2.remove(0);
							gamer.remove(0);
							killed = false;
							if (Maf.size() == 0 || Maf.size() == gamer.size() / 2) {
								break;
							}
							continue;
						} else {
							for (int i = 0; i < Maf.size(); i++) {
								if (dc == Maf.get(i)) {
									Maf.remove(i);
									Maf2.remove(i);
									gamer2.remove(i);
									gamer.remove(i);
								}
							}
							System.out.println("Player " + dc.getPtype() + " has been caught");
							continue;
						}
					}
					if (Heal.size() > 0) {
						Healer h = new Healer();
						h.auto(Player, gamer, Maf, Det);
						Players healed = h.getPl();
						if (healed != mk) {
							for (int i = 0; i < gamer.size(); i++) {
								if (mk == gamer.get(i)) {
									gamer.remove(i);
									System.out.println("Player " + m.getPl().getPtype() + " has died");
								}
							}
						} else {
							System.out.println("Attacked player has been saved by Healer");
							saved = true;
						}
					}
					if (Heal.size() > 0 && mk != null) {
						if (mk.getClass() == Heal.get(0).getClass()) {
							Heal.remove(0);
						}
					}
					for (int i = 0; i < gamer.size(); i++) {
						if (mk == gamer.get(i)) {
							if (!saved) {
								gamer.remove(i);
								System.out.println("Player " + m.getPl().getPtype() + " has died");
							} else {
								System.out.println("No one has died");
							}
						}
					}
					if (mk.getPtype() == Det.get(0).getPtype()) {
						died = true;
						continue;
					}
					System.out.println("Choose a Player to vote :");
					int vote = scn.nextInt();
					for(int i=0;i<gamer.size();i++) {
						if(gamer.get(i).getPtype()==vote) {
							present=true;
						}
					}
					if(!present) {
						System.out.println("Choose a Player to vote :");
						vote = scn.nextInt();
					}
					int index = getRan(2, gamer);
					System.out.println("Player " + gamer.get(index).getPtype() + " has been voted out");
					if (gamer.get(index).getClass() == Maf.get(0).getClass()) {
						for (int i = 0; i < Maf.size(); i++) {
							if (gamer.get(index) == Maf.get(i)) {
								Maf.remove(i);
								Maf2.remove(i);
								gamer2.remove(i);
								gamer.remove(i);
							}
						}
					} else if (gamer.get(index) == Det.get(0)) {
						died = true;
						gamer.remove(index);
						continue;
					} else {
						gamer.remove(index);
					}
				} else {
					Mafia m = new Mafia();
					m.auto(Player, gamer, Maf, Det);
					Players mk = m.getPl();
					Detective d = new Detective();
					d.auto(Player, gamer, Maf, Det);
					Maf = (ArrayList) Maf2.clone();
					Players dc = d.getPl();
					if(dc!=null) {
						if (dc.getClass() == Maf.get(0).getClass()) {
							if (dc == Maf.get(0)) {
								System.out.println("Player " + Maf.get(0).getPtype() + " has been caught");
								Maf.remove(0);
								Maf2.remove(0);
								gamer2.remove(0);
								gamer.remove(0);
								killed = false;
								if (Maf.size() == 0 || Maf.size() == gamer.size() / 2) {
									break;
								}
								continue;
							} else {
								for (int i = 0; i < Maf.size(); i++) {
									if (dc == Maf.get(i)) {
										Maf.remove(i);
										Maf2.remove(i);
										gamer2.remove(i);
										gamer.remove(i);
									}
								}
								System.out.println("Player " + dc.getPtype() + " has been caught");
								continue;
							}
						}
					}
					if (Heal.size() > 0) {
						Healer h = new Healer();
						h.auto(Player, gamer, Maf, Det);
						Players healed = h.getPl();
						if (healed != mk) {
							for (int i = 0; i < gamer.size(); i++) {
								if (mk == gamer.get(i)) {
									gamer.remove(i);
									System.out.println("Player " + m.getPl().getPtype() + " has died");
								}
							}
						} else {
							System.out.println("Attacked player has been saved by Healer");
							saved = true;
						}
					}
					if (Heal.size() > 0 && mk != null) {
						if (mk.getClass() == Heal.get(0).getClass()) {
							Heal.remove(0);
						}
					}
					for (int i = 0; i < gamer.size(); i++) {
						if (mk == gamer.get(i)) {
							if (!saved) {
								gamer.remove(i);
								System.out.println("Player " + m.getPl().getPtype() + " has died");
							} else {
								System.out.println("No one has died");
							}
						}
					}
					int index = getRan(2, gamer);
					System.out.println("Player " + gamer.get(index).getPtype() + " has been voted out");
					if (gamer.get(index).getClass() == Maf.get(0).getClass()) {
						for (int i = 0; i < Maf.size(); i++) {
							if (gamer.get(index) == Maf.get(i)) {
								Maf.remove(i);
								Maf2.remove(i);
								gamer2.remove(i);
								gamer.remove(i);
							}
						}
					} else {
						gamer.remove(index);
					}
				}
			}
			if (n == 3) {
				boolean present = false;
				if (!died) {
					Mafia m = new Mafia();
					System.out.println("Mafia has choosen its target ");
					m.auto(Player, gamer, Maf, Det);
					Players mk = m.getPl();
					Detective d = new Detective();
					d.auto(Player, gamer, Maf, Det);
					Maf = (ArrayList) Maf2.clone();
					Players dc = d.getPl();
					if (dc != null) {
						if (dc.getClass() == Maf.get(0).getClass()) {
							if (dc == Maf.get(0)) {
								System.out.println("Player " + Maf.get(0).getPtype() + " has been caught");
								Maf.remove(0);
								Maf2.remove(0);
								gamer2.remove(0);
								gamer.remove(0);
								killed = false;
								if (Maf.size() == 0 || Maf.size() == gamer.size() / 2) {
									break;
								}
								continue;
							} else {
								for (int i = 0; i < Maf.size(); i++) {
									if (dc == Maf.get(i)) {
										Maf.remove(i);
										Maf2.remove(i);
										gamer2.remove(i);
										gamer.remove(i);
									}
								}
								System.out.println("Player " + dc.getPtype() + " has been caught");
								continue;
							}
						}
					}
					if (Heal.size() > 0) {
						Healer h = new Healer();
						h.manual(Player, gamer, Maf, Det);
						Players healed = h.getPl();
						if (healed != mk) {
							for (int i = 0; i < gamer.size(); i++) {
								if (mk == gamer.get(i)) {
									gamer.remove(i);
									System.out.println("Player " + m.getPl().getPtype() + " has died");
								}
							}
						} else {
							System.out.println("Attacked player has been saved by Healer");
							saved = true;
						}
					}
					if (Heal.size() > 0 && mk != null) {
						if (mk.getClass() == Heal.get(0).getClass()) {
							Heal.remove(0);
						}
					}
					for (int i = 0; i < gamer.size(); i++) {
						if (mk == gamer.get(i)) {
							if (!saved) {
								gamer.remove(i);
								System.out.println("Player " + m.getPl().getPtype() + " has died");
							} else {
								System.out.println("No one has died");
							}
						}
					}
					if (Heal.size() > 0) {
						System.out.println("Choose a Player to vote :");
						int vote = scn.nextInt();
						for(int i=0;i<gamer.size();i++) {
							if(gamer.get(i).getPtype()==vote) {
								present=true;
							}
						}
						if(!present) {
							System.out.println("Choose a Player to vote :");
							vote = scn.nextInt();
						}
						int index = getRan(2, gamer);
						System.out.println("Player " + gamer.get(index).getPtype() + " has been voted out");
						if (gamer.get(index).getClass() == Maf.get(0).getClass()) {
							for (int i = 0; i < Maf.size(); i++) {
								if (gamer.get(index) == Maf.get(i)) {
									Maf.remove(i);
									Maf2.remove(i);
									gamer2.remove(i);
									gamer.remove(i);
								}
							}
						} else if (gamer.get(index).getClass() == Heal2.get(0).getClass()) {
							Heal.remove(0);
							gamer.remove(index);
							died = true;
						} else {
							gamer.remove(index);
						}
					} else {
						int index = getRan(2, gamer);
						System.out.println("Player " + gamer.get(index).getPtype() + " has been voted out");
						if (gamer.get(index).getClass() == Heal2.get(0).getClass()) {
							for (int i = 0; i < Maf.size(); i++) {
								if (gamer.get(index) == Maf.get(i)) {
									Heal.remove(i);
									gamer2.remove(i);
									gamer.remove(i);
								}
							}
						} else if (gamer.get(index).getClass() == Heal2.get(0).getClass()) {
							Heal.remove(0);
							gamer.remove(index);
							died = true;
						} else {
							gamer.remove(index);
						}
					}
				} else {
					Mafia m = new Mafia();
					System.out.println("Mafia has choosen its target ");
					m.auto(Player, gamer, Maf, Det);
					Players mk = m.getPl();
					Detective d = new Detective();
					d.auto(Player, gamer, Maf, Det);
					Maf = (ArrayList) Maf2.clone();
					Players dc = d.getPl();
					if (dc != null) {
						if (dc.getClass() == Maf.get(0).getClass()) {
							if (dc == Maf.get(0)) {
								System.out.println("Player " + Maf.get(0).getPtype() + " has been caught");
								Maf.remove(0);
								Maf2.remove(0);
								gamer2.remove(0);
								gamer.remove(0);
								killed = false;
								if (Maf.size() == 0 || Maf.size() == gamer.size() / 2) {
									break;
								}
								continue;
							} else {
								for (int i = 0; i < Maf.size(); i++) {
									if (dc == Maf.get(i)) {
										Maf.remove(i);
										Maf2.remove(i);
										gamer2.remove(i);
										gamer.remove(i);
									}
								}
								System.out.println("Player " + dc.getPtype() + " has been caught");
								continue;
							}
						}
					}
					if (Heal.size() > 0) {
						Healer h = new Healer();
						h.manual(Player, gamer, Maf, Det);
						Players healed = h.getPl();
						if (healed != mk) {
							for (int i = 0; i < gamer.size(); i++) {
								if (mk == gamer.get(i)) {
									gamer.remove(i);
									System.out.println("Player " + m.getPl().getPtype() + " has died");
								}
							}
						} else {
							System.out.println("Attacked player has been saved by Healer");
							saved = true;
						}
					}
					if (Heal.size() > 0 && mk != null) {
						if (mk.getClass() == Heal.get(0).getClass()) {
							Heal.remove(0);
						}
					}
					for (int i = 0; i < gamer.size(); i++) {
						if (mk == gamer.get(i)) {
							if (!saved) {
								gamer.remove(i);
								System.out.println("Player " + m.getPl().getPtype() + " has died");
							} else {
								System.out.println("No one has died");
							}
						}
					}
					if (Heal.size() > 0) {
						int index = getRan(2, gamer);
						System.out.println("Player " + gamer.get(index).getPtype() + " has been voted out");
						if (gamer.get(index).getClass() == Maf.get(0).getClass()) {
							for (int i = 0; i < Maf.size(); i++) {
								if (gamer.get(index) == Maf.get(i)) {
									Maf.remove(i);
									Maf2.remove(i);
									gamer2.remove(i);
									gamer.remove(i);
								}
							}
						} else {
							gamer.remove(index);
						}
					} else {
						int index = getRan(2, gamer);
						System.out.println("Player " + gamer.get(index).getPtype() + " has been voted out");
						if (gamer.get(index).getClass() == Heal2.get(0).getClass()) {
							for (int i = 0; i < Maf.size(); i++) {
								if (gamer.get(index) == Maf.get(i)) {
									Heal.remove(i);
									gamer2.remove(i);
									gamer.remove(i);
								}
							}
						} else {
							gamer.remove(index);
						}
					}
				}
			}
			if (n == 4) {
				boolean present = false;
				if (!died) {
					System.out.println("Choose Someone to vote : ");
					int vote = scn.nextInt();
					for(int i=0;i<gamer.size();i++) {
						if(gamer.get(i).getPtype()==vote) {
							present=true;
						}
					}
					if(!present) {
						System.out.println("Choose a Player to vote :");
						vote = scn.nextInt();
					}
					Mafia m = new Mafia();
					Detective d = new Detective();
					Healer h = new Healer();
					m.auto(Player, gamer, Maf, Det);
					Players re = m.getPl();
					System.out.println("Mafias have choosen a target");
					System.out.println("Detective has choosen a player to test ");
					System.out.println("Healers have choosen someone to heal");
					h.auto(Player, gamer, Maf, Det);
					if(re!=null) {
						if (re.getPtype() == Com.get(0).getPtype()) {
							System.out.println("You died!!!");
							Com.remove(0);
							for (int i = 0; i < gamer.size(); i++) {
								if (gamer.get(i).getPtype() == re.getPtype()) {
									// System.out.println("Player " + gamer.get(i).getPtype() + " has died");
									gamer.remove(i);
								}
							}
							died = true;
						}
					}
					for (int i = 0; i < gamer.size(); i++) {
						if (gamer.get(i).getPtype() == re.getPtype()) {
							System.out.println("Player " + gamer.get(i).getPtype() + " has died");
							gamer.remove(i);
						}
					}
					Random r = new Random();
					int range = gamer.size() - 1;
					int index = r.nextInt(range) + 1;
					Players pl = gamer.get(index);
					if (pl == Com.get(0)) {
						System.out.println("You have been voted out");
						died = true;
						gamer.remove(index);
					}
					else if(pl.getClass()==Maf.get(0).getClass()) {
						if(index<Maf.size()) {
							Maf.remove(index);
							System.out.println("Player " + gamer.get(index).getPtype() + " has been voted out");
							gamer.remove(index);
						}
					}
					else {
						System.out.println("Player " + gamer.get(index).getPtype() + " has been voted out");
						gamer.remove(index);
					}
				} else if (died) {
					Mafia m = new Mafia();
					Detective d = new Detective();
					Healer h = new Healer();
					m.auto(Player, gamer, Maf, Det);
					Players re = m.getPl();
					System.out.println("Mafias have choosen a target");
					System.out.println("Detective has choosen a player to test ");
					System.out.println("Healers have choosen someone to heal");
					h.auto(Player, gamer, Maf, Det);
					if(re!=null) {
						for (int i = 0; i < gamer.size(); i++) {
							if (gamer.get(i).getPtype() == re.getPtype()) {
								System.out.println("Player " + gamer.get(i).getPtype() + " has died");
								gamer.remove(i);
							}
						}
					}
					Random r = new Random();
					int range = gamer.size() - 1;
					int index = r.nextInt(range) + 1;
					Players pl = gamer.get(index);
					if(pl.getClass()==Maf.get(0).getClass()) {
						if(index<Maf.size()) {
							Maf.remove(index);
						}
					}
					System.out.println("Player " + gamer.get(index).getPtype() + " has been voted out");
					gamer.remove(index);

				}
			}
		}
		System.out.println(
				"XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX  Game ends  XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		if (Maf.size() == 0) {
			System.out.println("Mafia Loses\nAll Mafia has been elimanated");
		} else {
			System.out.println("Mafia wins");
		}
		for (int i = 0; i < Player.size(); i++) {
			System.out.println("Player " + Player.get(i).getPtype() + " was a " + Player.get(i).getClass());
		}
	}

	public static int getRan(int n, ArrayList<Players> gamer) {
		Random r = new Random();
		int range = gamer.size() - 1;
		int index = r.nextInt(range) + 1;
		if (index == n) {
			getRan(n, gamer);
		} else if (index != n) {
			return n;
		}
		return index;
	}
}