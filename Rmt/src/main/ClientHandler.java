package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread{
	
	BufferedReader clientInput=null;
	PrintStream clientOutput=null;
	Socket communicationSocket=null;
	String username;
	int kolikoJePutaRezervisao=0;

	
	public ClientHandler(Socket socket) {
		communicationSocket=socket;
	}
//	public void pomocna(int opcija) {
//		try {
//		BufferedReader clientInput = new BufferedReader(new InputStreamReader(communicationSocket.getInputStream()));
//		PrintStream clientOutput=new PrintStream(communicationSocket.getOutputStream());
//		String ime;
//		String prezime;
//		long jmbg;
//		String email;
//		String podaci;
//		boolean uspesno=false;
//		int brojKarata;
//		int kolikoJePutaRezervisao=0;
//		
//		if(opcija!=-1)
//		clientOutput.println("Izabrana "+opcija+". opcija\n");
//		
//		
//		if(opcija==2) {
//			while(kolikoJePutaRezervisao<=4) {
//			while(!uspesno) {
//			try {
//				
//				BufferedReader in=new BufferedReader (new FileReader("brojKarata.txt"));
//				
//				 brojKarata=Integer.parseInt(in.readLine());
//				 if(brojKarata==0) {
//					 clientOutput.println("Nema više raspoloživih karata!/n");
//					 break;
//				 }
//				 in.close();
//
//				 PrintWriter out= new PrintWriter(new BufferedWriter(new FileWriter("rezervacije.txt")));
//				
//				 clientOutput.println("Unesite podatke u sledecem formatu:\nIme Prezime JMBG email\n");
//			
//				podaci=clientInput.readLine();
//				String[] reci=podaci.split(" ");
//				if(reci.length!=4) {
//					clientOutput.println("Niste uneli sve trazene podatke");
//					continue;
//				}
//				ime=reci[0];
//				prezime=reci[1];
////				clientOutput.println("duzina jmbg: "+reci[2].length());
////				clientOutput.println("\n jmbg:"+reci[2]);
//				
//				try {
//					jmbg=Long.parseLong(reci[2]);
//				} catch (Exception e) {
//					
//					
//					clientOutput.println("jbmg mora biti broj");
//					continue;
//				}
////				try {
////					
////				} catch (Exception e) {
////					clientOutput.println("jbmg mora biti broj");
////					continue;
////				}
//				email=reci[3];
//				if(ime==null) {
//					clientOutput.println("Pogresno uneto ime, pokusajte ponovo\n");
//					continue;
//				}
//				if(prezime==null) {
//					clientOutput.println("Pogresno uneto prezime, pokusajte ponovo\n");
//					continue;
//				}
////			if((jmbg)!=((int)jmbg)) {
////					clientOutput.println("jbmg mora biti celobrojan");
////					continue;
////				}
//				if ((reci[2]==null)||(reci[2].length())!=13) {
//					
//					clientOutput.println("Pogresno unet jmbg, pokusajte ponovo\n"+reci[2]+"\n"+jmbg);
//					continue;
//				}
//				if(email==null) {
//					clientOutput.println("Pogresno unet email, pokusajte ponovo\n");
//					continue;
//				}
//				if(!(email.contains("@"))) {
//					clientOutput.println("Pogresno unet email, pokusajte ponovo\n");
//					continue;
//				}
//				while(podaci==null||!podaci.equals(ime+" "+prezime+" "+jmbg+" "+email)) {
//					clientOutput.println("Pogresno uneti podaci, pokusajte ponovo\n");
//					continue;
//				}
//				out.println(podaci);
//				
//				clientOutput.println("Usepsno ste izvrsili rezervaciju!\n");
//				brojKarata--;
//				uspesno=true;
//				kolikoJePutaRezervisao++;
//				
//				out.close();
//				
//				PrintWriter outBrojKarata= new PrintWriter(new BufferedWriter(new FileWriter("brojKarata.txt")));
//				
//				
//				out.write(brojKarata);
//				
//				outBrojKarata.close();
//			
//			} catch (Exception e) {
//				
//				clientOutput.println("greska");
//			}
//		}
//		}
//		}
//		
//			
//			if(opcija==1) {
//				try {
//					BufferedReader in=new BufferedReader (new FileReader("brojKarata.txt"));
//					clientOutput.println("Preostali broj karata je "+ in.readLine());
//					
//					
//					in.close();
//				} catch (Exception e) {
//					clientOutput.println("nije pronadjen fajl brojKarata");
//				}
//			
//			}
//		} catch (IOException e) {
//			Server.onlineUsers.remove(this);
//			for(ClientHandler client: Server.onlineUsers) {
//				if(client!=this) {
//					client.clientOutput.println(">>>Korisnik je napustio/la u sobu!");
//				}
//			}
//		}
//	}
	 public int kolikoJePutaRez(String nazivFajla,String podaci) throws IOException 
	    {
//	       
	      	int brojac=0;
	        
			try {
		        BufferedReader br1;

				br1 = new BufferedReader(new FileReader(nazivFajla));
			
	          
	        String line1 = br1.readLine();
	        
	        while(line1 != null)
	        {
	            
	        
	        
	                if(line1.equals(podaci))
	                {
	                    brojac++;
               
	                }
	                   
	              
	            line1 = br1.readLine();
	              
	        }
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        return brojac;

	    }
	public void zameniSadrzaj(String fajl1,String fajl2) throws IOException {
		
		//iz fajl1 citam i upisujem isto u f2
		
		PrintWriter out;
		try {
			out = new PrintWriter(new BufferedWriter(new FileWriter(fajl2)));
		
		BufferedReader in;
		
			in = new BufferedReader (new FileReader(fajl1));
		
		String red;
		while((red=in.readLine() )!= null) {
			out.println(red);
		}
		
			out.close();
			in.close();
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	 public void brisanjeReda(String nazivFajlaKojiMenjam,String linijaKojuBrisem) throws IOException 
	    {
	        PrintWriter pw = new PrintWriter("pomocni.txt");
	      

	        BufferedReader br1 = new BufferedReader(new FileReader(nazivFajlaKojiMenjam));
	          
	        String line1 = br1.readLine();
	        
	        while(line1 != null)
	        {
	            boolean znak = false;
	              
	        
	        
	                if(line1.equals(linijaKojuBrisem))
	                {
	                    znak = true;
                  
	                }
	                
	            if(!znak)
	                pw.println(line1);
	              
	            line1 = br1.readLine();
	              
	        }
	          
	        pw.flush();
	       
	        br1.close();
	        pw.close();
	        zameniSadrzaj("pomocni.txt",nazivFajlaKojiMenjam);
//	        File pocetni = new File(nazivFajlaKojiMenjam);
//	        File krajnji = new File("C:\\Users\\andj7\\eclipseworkspace\\Rmt\\pomocni.txt");
//	        String naziv="C:\\Users\\andj7\\eclipseworkspace\\Rmt\\pomocni.txt";
//	        pocetni.delete();
//	       if(krajnji.renameTo(nazivFajlaKojiMenjam)) {
//	    	   System.out.println("uspesno");
//	       }
	    }
	public boolean daLiJeTacanUP(String up) throws IOException {
		try (BufferedReader in = new BufferedReader (new FileReader("registrovaniKorisnici.txt"))) {
			String red;
			String[] podaci;
			
			while((red=in.readLine())!=null) {
				
				podaci=red.split(" ");
				if((podaci[0]+" "+podaci[1]).equals(up)) {
					return true;
				}
			}

			
			in.close();
		} catch (FileNotFoundException e) {
			
		}
		return false;

	}

	public boolean daLiPostoji(String username) throws IOException {
		try (BufferedReader in = new BufferedReader (new FileReader("registrovaniKorisnici.txt"))) {
			String red;
			String[] podaci;
			
			while((red=in.readLine())!=null) {
				
				podaci=red.split(" ");
				if(podaci[0].equals(username)) {
					return true;
				}
			}

			
			in.close();
		} catch (FileNotFoundException e) {
			
		}
		return false;

		
		
	}
	@Override
	public void run() {
		String opcija1;
		int opcija;
		
		try {
			BufferedReader clientInput = new BufferedReader(new InputStreamReader(communicationSocket.getInputStream()));
			PrintStream clientOutput=new PrintStream(communicationSocket.getOutputStream());
			
			clientOutput.println("Dobro dosao/la!\n");

			
			do {
				clientOutput.println("Izaberite opciju:\n"
						+ "1-Vidite preostali broj karata\n"
						+ "2-Izvrsite rezervaciju\n"
						+ "3-Registrujte se\n"
						+ "4-Prijavite se\n"

						+ "Za izlazak unesite ***quit\n");
				opcija1=clientInput.readLine();
				if(opcija1.equals("***quit")) {
//					clientOutput.println("prepoznajem quit");
					
					break;
				}

				try {
					opcija=Integer.parseInt(opcija1);
					
				} catch (NumberFormatException e1) {
					
					clientOutput.println("pogresno uneta opcija, molim pokusajte ponovo!");
					continue;
				}
//				clientOutput.println("Nije izasao");
				if(opcija1.equals(null)) {
					clientOutput.println("Molim izaberite opciju");
					continue;
				}
				
				clientOutput.println("Izabrana je opcija "+opcija);
			switch (opcija) {
			case 1:{
				
					try {
						BufferedReader in=new BufferedReader (new FileReader("brojKarata.txt"));
						clientOutput.println("Preostali broj karata je "+ in.readLine()+"\n");
						
						
						in.close();
					} catch (Exception e) {
						clientOutput.println("greska fajl brojKarata");
					}
					break;
				}
			
			
			case 2: {
				String ime;
				String prezime;
				long jmbg;
				String email;
				String podaci;
				boolean uspesno=false;
				int brojKarata;
				
			
				
//				 clientOutput.println("Proba 1/n");

				
					
					while(!uspesno) {
					try {
//						 clientOutput.println("Proba 2/n");
						 
						BufferedReader in=new BufferedReader (new FileReader("brojKarata.txt"));
						
						 brojKarata=Integer.parseInt(in.readLine());
						 if(brojKarata==0) {
							 clientOutput.println("Nema više raspoloživih karata!/n");
							 break;
						 }
						 in.close();
						 
						 PrintWriter out= new PrintWriter(new BufferedWriter(new FileWriter("rezervacije.txt",true)));
						
						 clientOutput.println("Unesite podatke u sledecem formatu:\nIme Prezime JMBG email\n");
					
						podaci=clientInput.readLine();
						String[] reci=podaci.split(" ");
						if(reci.length!=4) {
							clientOutput.println("Niste uneli sve trazene podatke");
							continue;
						}
						ime=reci[0];
						prezime=reci[1];
//						clientOutput.println("duzina jmbg: "+reci[2].length());
//						clientOutput.println("\n jmbg:"+reci[2]);
						
						try {
							jmbg=Long.parseLong(reci[2]);
						} catch (Exception e) {
							
							
							clientOutput.println("jbmg mora biti broj");
							continue;
						}
//						try {
//							
//						} catch (Exception e) {
//							clientOutput.println("jbmg mora biti broj");
//							continue;
//						}
						email=reci[3];
						if(ime==null) {
							clientOutput.println("Pogresno uneto ime, pokusajte ponovo\n");
							continue;
						}
						if(prezime==null) {
							clientOutput.println("Pogresno uneto prezime, pokusajte ponovo\n");
							continue;
						}
//					if((jmbg)!=((int)jmbg)) {
//							clientOutput.println("jbmg mora biti celobrojan");
//							continue;
//						}
						if ((reci[2]==null)||(reci[2].length())!=13) {
							
							clientOutput.println("Pogresno unet jmbg, pokusajte ponovo\n");
							continue;
						}
						if(email==null) {
							clientOutput.println("Pogresno unet email, pokusajte ponovo\n");
							continue;
						}
						if(!(email.contains("@"))) {
							clientOutput.println("Pogresno unet email jer ne sadrzi @, pokusajte ponovo\n");
							continue;
						}
						while(podaci==null||!podaci.equals(ime+" "+prezime+" "+jmbg+" "+email)) {
							clientOutput.println("Pogresno uneti podaci, pokusajte ponovo\n");
							continue;
						}
						out.println(podaci);
//						clientOutput.println("rezervisali ste puta"+kolikoJePutaRezervisao);
						if(kolikoJePutaRezervisao>4) {
							clientOutput.println("Ne mozete da rezervišete više od 4 puta");
							break;
						}
						clientOutput.println("Uspesno ste izvrsili rezervaciju!\n");
						brojKarata--;
						uspesno=true;
						kolikoJePutaRezervisao++;
						
						out.close();
						
						PrintWriter outBrojKarata= new PrintWriter(new BufferedWriter(new FileWriter("brojKarata.txt")));
						
						clientOutput.println("Preostali broj karata"+brojKarata+"\n");
						outBrojKarata.println(brojKarata);
						
						outBrojKarata.close();
					} catch (Exception e) {
						
						clientOutput.println("greska");
					}
					
					}					
					break;
					}
			case 3:{
				
				
				String podaci;
				String username;
				String password;
				String ime,prezime;
				Long jmbg;
				String email;
				int brojKarata;
				boolean uspesno=false;

				while(!uspesno) {
				try {
				BufferedReader in=new BufferedReader (new FileReader("brojKarata.txt"));
				
				 brojKarata=Integer.parseInt(in.readLine());
				 if(brojKarata==0) {
					 clientOutput.println("Nema više raspoloživih karata!/n");
					 break;
				 }
				 in.close();
				 
				 PrintWriter out= new PrintWriter(new BufferedWriter(new FileWriter("registrovaniKorisnici.txt",true)));
				
				 clientOutput.println("Unesite podatke u sledecem formatu:\nUsername Password Ime Prezime JMBG email\n");
				 podaci=clientInput.readLine();
					String[] reci=podaci.split(" ");
					if(reci.length!=6) {
						clientOutput.println("Niste uneli sve trazene podatke");
						continue;
					}
					username=reci[0];
					password=reci[1];
					ime=reci[2];
					prezime=reci[3];
					
					email=reci[5];
					try {
						jmbg=Long.parseLong(reci[4]);
					} catch (Exception e) {
						
						
						clientOutput.println("jbmg mora biti broj");
						continue;
					}
					if(username==null) {
						clientOutput.println("Pogresno unet username, pokusajte ponovo\n");
						continue;
					}
					
					if(daLiPostoji(username)) {
						clientOutput.println("Ovaj username vec postoji, pokusajte ponovo\n");
						continue;
					}
					
					if(password==null) {
						clientOutput.println("Pogresno unet password, pokusajte ponovo\n");
						continue;
					}
					if(ime==null) {
						clientOutput.println("Pogresno uneto ime, pokusajte ponovo\n");
						continue;
					}
					if(prezime==null) {
						clientOutput.println("Pogresno uneto prezime, pokusajte ponovo\n");
						continue;
					}
//				if((jmbg)!=((int)jmbg)) {
//						clientOutput.println("jbmg mora biti celobrojan");
//						continue;
//					}
					if ((reci[4]==null)||(reci[4].length())!=13) {
						
						clientOutput.println("Pogresno unet jmbg, pokusajte ponovo\n");
						continue;
					}
					if(email==null) {
						clientOutput.println("Pogresno unet email, pokusajte ponovo\n");
						continue;
					}
					if(!(email.contains("@"))) {
						clientOutput.println("Pogresno unet email jer ne sadrzi @, pokusajte ponovo\n");
						continue;
					}
					while(podaci==null||!podaci.equals(username+" "+password+" "+ime+" "+prezime+" "+jmbg+" "+email)) {
						clientOutput.println("Pogresno uneti podaci, pokusajte ponovo\n");
						break;
					}
					out.println(podaci);
//					clientOutput.println("rezervisali ste puta"+kolikoJePutaRezervisao);
					if(kolikoJePutaRezervisao>4) {
						clientOutput.println("Ne mozete da rezervišete više od 4 puta");
						break;
					}
					clientOutput.println("Uspesno ste se registrovali!\n");
					
					uspesno=true;
					
					
					out.close();
					
					
				 
				} catch (Exception e) {
					
					clientOutput.println("greska");
				}
				
				}					
				break;
				}
			case 4:{
				clientOutput.println("Unesite username i password u formatu:\nUsername password");
				String up=clientInput.readLine();
				
				
					while(!daLiJeTacanUP(up)) {
						clientOutput.println("Pogresan username i/ili password. Pokusajte ponovo! Vodite racuna o formatu\nUsername password");
						up=clientInput.readLine();

					}
				String[] sifra=up.split(" ");
				username=sifra[0];
				clientOutput.println("Uspesno ste se prijavili "+username+"!\nZelite li da \n"
						+ "1-Izvrsite rezervaciju obicnih karata\n"
						+ "2-Izvesite rezervaciju karata u VIP lozi\n"
						+ "3-izbrisete rezervaciju\n"
						+ "0-vrati me na pocetni meni\n");
				int odabir1=-1;
				String odabir;
				do {
				odabir=clientInput.readLine();
				
				try {
					odabir1 = Integer.parseInt(odabir);
				} catch (NumberFormatException e) {
					clientOutput.println("Uneli ste pogresnu opciju, pokusajte ponovo");
					continue;
				}

				}while((odabir1!=1)&&(odabir1!=2)&&(odabir1!=0)&&(odabir1!=3));
				
				
				
				switch(odabir1) {
				case 1:{
					String ime;
					String prezime;
					long jmbg;
					String email;
					String podaci;
					boolean uspesno=false;
					int brojKarata;
					
				
					
//					 clientOutput.println("Proba 1/n");

					
						
						while(!uspesno) {
						try {
//							 clientOutput.println("Proba 2/n");
							 
							BufferedReader in=new BufferedReader (new FileReader("brojKarata.txt"));
							
							 brojKarata=Integer.parseInt(in.readLine());
							 if(brojKarata==0) {
								 clientOutput.println("Nema više raspoloživih karata!/n");
								 break;
							 }
							 in.close();
							 
							 PrintWriter out= new PrintWriter(new BufferedWriter(new FileWriter("rezervacije.txt",true)));
							
							 clientOutput.println("Unesite podatke u sledecem formatu:\nIme Prezime JMBG email\n");
						
							podaci=clientInput.readLine();
							String[] reci=podaci.split(" ");
							if(reci.length!=4) {
								clientOutput.println("Niste uneli sve trazene podatke");
								continue;
							}
							ime=reci[0];
							prezime=reci[1];
//							clientOutput.println("duzina jmbg: "+reci[2].length());
//							clientOutput.println("\n jmbg:"+reci[2]);
							
							try {
								jmbg=Long.parseLong(reci[2]);
							} catch (Exception e) {
								
								
								clientOutput.println("jbmg mora biti broj");
								continue;
							}
//							try {
//								
//							} catch (Exception e) {
//								clientOutput.println("jbmg mora biti broj");
//								continue;
//							}
							email=reci[3];
							if(ime==null) {
								clientOutput.println("Pogresno uneto ime, pokusajte ponovo\n");
								continue;
							}
							if(prezime==null) {
								clientOutput.println("Pogresno uneto prezime, pokusajte ponovo\n");
								continue;
							}
//						if((jmbg)!=((int)jmbg)) {
//								clientOutput.println("jbmg mora biti celobrojan");
//								continue;
//							}
							if ((reci[2]==null)||(reci[2].length())!=13) {
								
								clientOutput.println("Pogresno unet jmbg, pokusajte ponovo\n");
								continue;
							}
							if(email==null) {
								clientOutput.println("Pogresno unet email, pokusajte ponovo\n");
								continue;
							}
							if(!(email.contains("@"))) {
								clientOutput.println("Pogresno unet email jer ne sadrzi @, pokusajte ponovo\n");
								continue;
							}
							while(podaci==null||!podaci.equals(ime+" "+prezime+" "+jmbg+" "+email)) {
								clientOutput.println("Pogresno uneti podaci, pokusajte ponovo\n");
								continue;
							}
							out.println(podaci);
							
							//iz fajla koliko je 
							
							
//						
							
							if(kolikoJePutaRez("rezervacije.txt", podaci)>4) {
								clientOutput.println("Ne mozete da rezervišete više od 4 puta");
								break;
							}
							clientOutput.println("Uspesno ste izvrsili rezervaciju!");
							brojKarata--;
							uspesno=true;
//							kolikoJePutaRezervisao++;
							
							out.close();
							
							PrintWriter outBrojKarata= new PrintWriter(new BufferedWriter(new FileWriter("brojKarata.txt")));
							
							clientOutput.println("Preostali broj karata"+brojKarata+"\n");
							outBrojKarata.println(brojKarata);
							
							outBrojKarata.close();
						} catch (Exception e) {
							
							clientOutput.println("greska");
						}
						
						}					
						break;
						
				}
				case 2:{
					String ime;
					String prezime;
					long jmbg;
					String email;
					String podaci;
					boolean uspesno=false;
					int brojKarata;
					
				
					
//					 clientOutput.println("Proba 1/n");

					
						
						while(!uspesno) {
						try {
//							 clientOutput.println("Proba 2/n");
							 
							BufferedReader in=new BufferedReader (new FileReader("brojVIPKarata.txt"));
							
							 brojKarata=Integer.parseInt(in.readLine());
							 if(brojKarata==0) {
								 clientOutput.println("Nema više raspoloživih karata!/n");
								 break;
							 }
							 in.close();
							 
							 PrintWriter out= new PrintWriter(new BufferedWriter(new FileWriter("rezervacijeVIP.txt",true)));
							
							 clientOutput.println("Unesite podatke u sledecem formatu:\nIme Prezime JMBG email\n");
						
							podaci=clientInput.readLine();
							String[] reci=podaci.split(" ");
							if(reci.length!=4) {
								clientOutput.println("Niste uneli sve trazene podatke");
								continue;
							}
							ime=reci[0];
							prezime=reci[1];
//							clientOutput.println("duzina jmbg: "+reci[2].length());
//							clientOutput.println("\n jmbg:"+reci[2]);
							
							try {
								jmbg=Long.parseLong(reci[2]);
							} catch (Exception e) {
								
								
								clientOutput.println("jbmg mora biti broj");
								continue;
							}
//							try {
//								
//							} catch (Exception e) {
//								clientOutput.println("jbmg mora biti broj");
//								continue;
//							}
							email=reci[3];
							if(ime==null) {
								clientOutput.println("Pogresno uneto ime, pokusajte ponovo\n");
								continue;
							}
							if(prezime==null) {
								clientOutput.println("Pogresno uneto prezime, pokusajte ponovo\n");
								continue;
							}
//						if((jmbg)!=((int)jmbg)) {
//								clientOutput.println("jbmg mora biti celobrojan");
//								continue;
//							}
							if ((reci[2]==null)||(reci[2].length())!=13) {
								
								clientOutput.println("Pogresno unet jmbg, pokusajte ponovo\n");
								continue;
							}
							if(email==null) {
								clientOutput.println("Pogresno unet email, pokusajte ponovo\n");
								continue;
							}
							if(!(email.contains("@"))) {
								clientOutput.println("Pogresno unet email jer ne sadrzi @, pokusajte ponovo\n");
								continue;
							}
							while(podaci==null||!podaci.equals(ime+" "+prezime+" "+jmbg+" "+email)) {
								clientOutput.println("Pogresno uneti podaci, pokusajte ponovo\n");
								continue;
							}
							out.println(podaci);
//							clientOutput.println("rezervisali ste puta"+kolikoJePutaRezervisao);
							if(kolikoJePutaRezervisao>4) {
								clientOutput.println("Ne mozete da rezervišete više od 4 puta");
								break;
							}
							clientOutput.println("Uspesno ste izvrsili rezervaciju!");
							brojKarata--;
							uspesno=true;
							kolikoJePutaRezervisao++;
							
							out.close();
							
							PrintWriter outBrojKarata= new PrintWriter(new BufferedWriter(new FileWriter("brojVIPKarata.txt")));
							
							clientOutput.println("Preostali broj karata"+brojKarata+"\n");
							outBrojKarata.println(brojKarata);
							
							outBrojKarata.close();
						} catch (Exception e) {
							
							clientOutput.println("greska");
						}
						
						}					
						break;
						
				}
				case 3:{
					String ime;
					String prezime;
					long jmbg;
					String email;
					String podaci;
					boolean uspesno=false;
					int brojKarata;
					
					
					 clientOutput.println("Unesite podatke u sledecem formatu:\nIme Prezime JMBG email\n");
						
						podaci=clientInput.readLine();
						String[] reci=podaci.split(" ");
						if(reci.length!=4) {
							clientOutput.println("Niste uneli sve trazene podatke");
							continue;
						}
						ime=reci[0];
						prezime=reci[1];
//						clientOutput.println("duzina jmbg: "+reci[2].length());
//						clientOutput.println("\n jmbg:"+reci[2]);
						
						try {
							jmbg=Long.parseLong(reci[2]);
						} catch (Exception e) {
							
							
							clientOutput.println("jbmg mora biti broj");
							continue;
						}
//						try {
//							
//						} catch (Exception e) {
//							clientOutput.println("jbmg mora biti broj");
//							continue;
//						}
						email=reci[3];
						if(ime==null) {
							clientOutput.println("Pogresno uneto ime, pokusajte ponovo\n");
							continue;
						}
						if(prezime==null) {
							clientOutput.println("Pogresno uneto prezime, pokusajte ponovo\n");
							continue;
						}
//					if((jmbg)!=((int)jmbg)) {
//							clientOutput.println("jbmg mora biti celobrojan");
//							continue;
//						}
						if ((reci[2]==null)||(reci[2].length())!=13) {
							
							clientOutput.println("Pogresno unet jmbg, pokusajte ponovo\n");
							continue;
						}
						if(email==null) {
							clientOutput.println("Pogresno unet email, pokusajte ponovo\n");
							continue;
						}
						if(!(email.contains("@"))) {
							clientOutput.println("Pogresno unet email jer ne sadrzi @, pokusajte ponovo\n");
							continue;
						}
						while(podaci==null||!podaci.equals(ime+" "+prezime+" "+jmbg+" "+email)) {
							clientOutput.println("Pogresno uneti podaci, pokusajte ponovo\n");
							continue;
						}
						
						clientOutput.println("Zelite li da izbrisete rezervacije za\n"
								+ "1-obicne karte\n"
								+ "2-VIP\n"
								+ "3-obe\n");
						String izabranaOpcija;
						int izabrana=-1;
						do {
							izabranaOpcija=clientInput.readLine();
							
							try {
								izabrana = Integer.parseInt(izabranaOpcija);
							} catch (NumberFormatException e) {
								clientOutput.println("Uneli ste pogresnu opciju, pokusajte ponovo");
								continue;
							}

							}while((izabrana!=1)&&(izabrana!=2)&&(izabrana!=3));
						
						switch(izabrana) {
						case 1:{
							int kolikoImaRezervacija=0;
							String red;
							try {
								BufferedReader in=new BufferedReader (new FileReader("rezervacije.txt"));
								while((red=in.readLine())!=null) {
									if(red.equals(podaci)) {
										kolikoImaRezervacija++;
									}
								}
								
								clientOutput.println("Imate rezervacije "+kolikoImaRezervacija);
								if(kolikoImaRezervacija==0) {
									clientOutput.println("Nemate rezervacije, pa ih ne mozete izbrisati");
									break;
								}
//								clientOutput.println("ovde");

								for(int i=1;i<=kolikoImaRezervacija;i++) {
									brisanjeReda("rezervacije.txt", podaci);
									
//									clientOutput.println("ovde1");

								}
								clientOutput.println("Uspesno obrisane rezervacije");

								in.close();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								clientOutput.println("losa putanja");
							}
							break;
						}
						case 2:{
							int kolikoImaRezervacija=0;
							String red;
							try {
								BufferedReader in=new BufferedReader (new FileReader("rezervacijeVIP.txt"));
								while((red=in.readLine())!=null) {
									if(red.equals(podaci)) {
										kolikoImaRezervacija++;
									}
								}
								
								clientOutput.println("Imate rezervacije "+kolikoImaRezervacija);
								if(kolikoImaRezervacija==0) {
									clientOutput.println("Nemate rezervacije, pa ih ne mozete izbrisati");
									break;
								}
//								clientOutput.println("ovde");

								for(int i=1;i<=kolikoImaRezervacija;i++) {
									brisanjeReda("rezervacijeVIP.txt", podaci);
									
//									clientOutput.println("ovde1");

								}
								clientOutput.println("Uspesno obrisane VIP rezervacije");

								in.close();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								clientOutput.println("losa putanja");
							}
							break;
						}
						case 3:{
							while(true) {
								int kolikoImaRezervacija=0;
								String red;
								try {
									BufferedReader in=new BufferedReader (new FileReader("rezervacijeVIP.txt"));
									while((red=in.readLine())!=null) {
										if(red.equals(podaci)) {
											kolikoImaRezervacija++;
										}
									}
									
//									clientOutput.println("Imate rezervacije "+kolikoImaRezervacija);
									if(kolikoImaRezervacija==0) {
										clientOutput.println("Nemate rezervacije, pa ih ne mozete izbrisati");
										break;
									}
//									clientOutput.println("ovde");

									for(int i=1;i<=kolikoImaRezervacija;i++) {
										brisanjeReda("rezervacije.txt", podaci);
										
//										clientOutput.println("ovde1");

									}
									clientOutput.println("Uspesno obrisane rezervacije");

									in.close();
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
									clientOutput.println("losa putanja");
									break;
								}
								break;
							}
							while(true) {
								int kolikoImaRezervacija=0;
								String red;
								try {
									BufferedReader in=new BufferedReader (new FileReader("rezervacijeVIP.txt"));
									while((red=in.readLine())!=null) {
										if(red.equals(podaci)) {
											kolikoImaRezervacija++;
										}
									}
									
									clientOutput.println("Imate rezervacije "+kolikoImaRezervacija);
									if(kolikoImaRezervacija==0) {
										clientOutput.println("Nemate rezervacije, pa ih ne mozete izbrisati");
										break;
									}
//									clientOutput.println("ovde");

									for(int i=1;i<=kolikoImaRezervacija;i++) {
										brisanjeReda("rezervacijeVIP.txt", podaci);
										
//										clientOutput.println("ovde1");

									}
									clientOutput.println("Uspesno obrisane VIP rezervacije");

									in.close();
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
									clientOutput.println("losa putanja");
									break;
								}
								break;
							}
						}
						}
						
						break;
				}
				case 0:{
					break;
				}
				default:{
					clientOutput.println("Uneli ste pogresnu opciju, pokusajte ponovo");

				}
				}


				
				
				
				
				break;
			}
			default:{
				clientOutput.println("Izabrali ste nepostojecu opciju");
				break;
			
			}
			}
				
			}while(true);
				
	
//			clientOutput.println("Ovde je na kraju");
			clientOutput.println(">>> Dovidjenja ");

			
			Server.onlineUsers.remove(this);

			communicationSocket.close();
			
	} catch (IOException e) {
//				Server.onlineUsers.remove(this);
//				for(ClientHandler client: Server.onlineUsers) {
//					if(client!=this) {
//						client.clientOutput.println(">>>Korisnik je napustio/la u sobu!");
//					}
//				}
			}
		
//	for(int i=0; i<= Server.onlineUsers.size();i++) {
//		if(Server.onlineUsers.equals(this)) {
//			Server.onlineUsers.remove(this);
//		}
//	}
		
	}
	

}	
		
	
				
				
		
			
			
//			String ime;
//			String prezime;
//			long jmbg;
//			String email;
//			String podaci;
//			boolean uspesno=false;
//			int brojKarata;
//			int kolikoJePutaRezervisao=0;
//			
//			if(opcija!=-1)
//			clientOutput.println("Izabrana "+opcija+". opcija\n");
//			
//			
//			if(opcija==2) {
//				while(kolikoJePutaRezervisao<=4) {
//				while(!uspesno) {
//				try {
//					
//					BufferedReader in=new BufferedReader (new FileReader("brojKarata.txt"));
//					
//					 brojKarata=Integer.parseInt(in.readLine());
//					 if(brojKarata==0) {
//						 clientOutput.println("Nema više raspoloživih karata!/n");
//						 break;
//					 }
//					 in.close();
//
//					 PrintWriter out= new PrintWriter(new BufferedWriter(new FileWriter("rezervacije.txt")));
//					
//					 clientOutput.println("Unesite podatke u sledecem formatu:\nIme Prezime JMBG email\n");
//				
//					podaci=clientInput.readLine();
//					String[] reci=podaci.split(" ");
//					if(reci.length!=4) {
//						clientOutput.println("Niste uneli sve trazene podatke");
//						continue;
//					}
//					ime=reci[0];
//					prezime=reci[1];
////					clientOutput.println("duzina jmbg: "+reci[2].length());
////					clientOutput.println("\n jmbg:"+reci[2]);
//					
//					try {
//						jmbg=Long.parseLong(reci[2]);
//					} catch (Exception e) {
//						
//						
//						clientOutput.println("jbmg mora biti broj");
//						continue;
//					}
////					try {
////						
////					} catch (Exception e) {
////						clientOutput.println("jbmg mora biti broj");
////						continue;
////					}
//					email=reci[3];
//					if(ime==null) {
//						clientOutput.println("Pogresno uneto ime, pokusajte ponovo\n");
//						continue;
//					}
//					if(prezime==null) {
//						clientOutput.println("Pogresno uneto prezime, pokusajte ponovo\n");
//						continue;
//					}
////				if((jmbg)!=((int)jmbg)) {
////						clientOutput.println("jbmg mora biti celobrojan");
////						continue;
////					}
//					if ((reci[2]==null)||(reci[2].length())!=13) {
//						
//						clientOutput.println("Pogresno unet jmbg, pokusajte ponovo\n"+reci[2]+"\n"+jmbg);
//						continue;
//					}
//					if(email==null) {
//						clientOutput.println("Pogresno unet email, pokusajte ponovo\n");
//						continue;
//					}
//					if(!(email.contains("@"))) {
//						clientOutput.println("Pogresno unet email, pokusajte ponovo\n");
//						continue;
//					}
//					while(podaci==null||!podaci.equals(ime+" "+prezime+" "+jmbg+" "+email)) {
//						clientOutput.println("Pogresno uneti podaci, pokusajte ponovo\n");
//						continue;
//					}
//					out.println(podaci);
//					
//					clientOutput.println("Usepsno ste izvrsili rezervaciju!\n");
//					brojKarata--;
//					uspesno=true;
//					kolikoJePutaRezervisao++;
//					
//					out.close();
//					
//					PrintWriter outBrojKarata= new PrintWriter(new BufferedWriter(new FileWriter("brojKarata.txt")));
//					
//					
//					out.write(brojKarata);
//					
//					outBrojKarata.close();
//					
//					
//					String opcija1;
//					do {
//					clientOutput.println("Za izlazak unesite ***quit\nIzaberite opciju:\n"
//									+ "1-Vidite preostali broj karata\n"
//									+ "2-Izvrsite rezervaciju\n");
//					 opcija1=clientInput.readLine();
//					opcija=Integer.parseInt(opcija1);
//					
//					pomocna(opcija);
//					}while(!opcija1.startsWith("***quit"));
//					
						
						
//					
//				} catch (Exception e) {
//					
//					clientOutput.println("greska");
//				}
//			}
//			}
//			}
//		
			
//			if(opcija==1) {
//				try {
//					BufferedReader in=new BufferedReader (new FileReader("brojKarata.txt"));
//					clientOutput.println("Preostali broj karata je "+ in.readLine());
//					
//					
//					in.close();
//				} catch (Exception e) {
//					clientOutput.println("nije pronadjen fajl brojKarata");
//				}
//			
//			}
			
			
//			for(ClientHandler client: Server.onlineUsers) {
//				if(client!=this) {
//					client.clientOutput.println(">>>Korisnik"+username+" je usao/la u sobu!");
//				}
//			}
			
//			String message;
//			
//			while(true) {
//				message=clientInput.readLine();
//				
//				if(message.startsWith("***quit")) {
//					break;
//				}
//				
////				for(ClientHandler client: Server.onlineUsers) {
////					if(client!=this) {
////						client.clientOutput.println("["+username+"]"+message);
////					}
////				}
//			}
//			

			
//			for(ClientHandler client: Server.onlineUsers) {
//				if(client!=this) {
//					client.clientOutput.println(">>>Korisnik"+username+" je napustio/la u sobu!");
//				}
//			}
			
			
			
			
			
			
//			
//		} catch (IOException e) {
//			Server.onlineUsers.remove(this);
//			for(ClientHandler client: Server.onlineUsers) {
//				if(client!=this) {
//					client.clientOutput.println(">>>Korisnik je napustio/la u sobu!");
//				}
//			}
//		}
//		
//	
//}

