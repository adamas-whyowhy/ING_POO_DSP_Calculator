package projet;
import java.io.*;
import java.lang.*;
import java.util.*;
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
public class MainProjet {
	
	private static final String FILENAME = "data.xml";
	public static Jeu jeu = new Jeu();
	
	
	public static void main(String [] args) {
		try {
			parser();
		} catch (CategoryException | ConsommationException e) {
			e.printStackTrace();
			System.err.println("Abandon du programme.");
			return;
		}
		String var1;
		do {
			Scanner scanner = new Scanner(System.in);
			int choix;
			
			System.out.println("Bienvenue. Choisissez une option :");
			System.out.println("[1] Composants tries par ordre alphabetique sans les batiments\n[2] Batiments tries par ordre alphabetique");
			System.out.println("[3] Recettes par ordre alphabetique\n[4] Recettes d'une usine donnee");
			System.out.println("[5] Ressources d'un extracteur donne \n[6] Carburants possibles d'une centrale donnee");
			System.out.println("[7] Composants de type ressource d'une recette specifique");
			System.out.println("[8] Affichage de toutes les recettes et les informations");
			System.out.print("Votre choix : ");
			choix = scanner.nextInt();
			switch(choix) {
			case 1: question_2(); break;
			case 2: question_3(); break;
			case 3: question_4(); break;
			case 4: question_5(); break;
			case 5: question_6(); break;
			case 6: question_7(); break;
			case 7: question_8(); break;
			case 8: rec(); break;
			default: System.out.println("Vous avez saisi un chiffre/nombre invalide.");
			}
			System.out.println("Voulez vous une autre option?(oui/non)");
			Scanner scanner1 = new Scanner( System.in );	
			var1 = scanner1.nextLine();
		}while(var1.equals("oui"));
	}
	
	//Methode qui trie les composants sans batiments et les affiche
	private static void question_2() {
		Scanner scanner = new Scanner(System.in);
		String reponse;
		int indice;
		ArrayList <Component> composants_sans_batiments = new ArrayList<>();
		for (Component c : jeu.composants) {
			if (!(c instanceof Building)) {
				composants_sans_batiments.add(c);
			}
		}

		Collections.sort(composants_sans_batiments);
		
		// Affichage des composants tries sans batiments
		for (Component c : composants_sans_batiments) {
			System.out.println("["+composants_sans_batiments.indexOf(c)+"] "+c.getName());
		}
		System.out.println("Voulez-vous afficher les informations completes d'un composant specifique ? (oui/non)");
		reponse = scanner.nextLine();
		if (reponse.equals("oui")) {
			System.out.print("Choisissez votre composant : ");
			indice = scanner.nextInt();
			if (indice >= composants_sans_batiments.size() || indice < 0)
				System.out.println("Votre choix de composant n'est pas valide.");
			else {
				System.out.println(composants_sans_batiments.get(indice));
			}
		}
		
	}
	
	//Methode qui trie les batiments par ordre alphabetique et les affiche
	private static void question_3() {
		//trier les batiments
		Collections.sort(jeu.batiments);
		//affichage des batiments tries
		for(Building b:jeu.batiments) {
			System.out.println("indice="+jeu.batiments.indexOf(b)+ " " + b.getName());
		}
		System.out.println("Voulez-vous afficher les informations d'un batiments specifique?(oui/non)");
		Scanner scanner = new Scanner( System.in );	
		String var = scanner.nextLine();
		if(var.equals("oui")) {
			System.out.println("veuillez entrer l'indice de votre batiment");
			Scanner scanner1 = new Scanner( System.in );	
			int a = scanner1.nextInt();
			if (a >= jeu.batiments.size() || a < 0)
				System.out.println("Votre choix de batiment n'est pas valide.");
			else {
			 for(Building b :jeu.batiments) {
			        if(b.equals(jeu.batiments.get(a))) {
			        	System.out.println(b);
			        }
			    }
				
			}
		}
		else {
			
		}
	}
	
	//Methode qui trie les recettes par ordre alphabetique et les affiche
	public static void question_4() {
		Collections.sort(jeu.recettes);
		for(Recipe r : jeu.recettes) {
			System.out.println(r + "\n");
		}
	}
	
	//Methode qui affiche toutes les recettes etles informations sur celle choisie
	private static void rec() {
		for(Recipe r: jeu.recettes) {
			System.out.println("indice="+jeu.recettes.indexOf(r)+" usine="+r.getId());
		}
		
		Scanner scanner = new Scanner( System.in );
		System.out.print( "Veuillez saisir un numero de recette: " );
        int a = scanner.nextInt();
        for(Recipe r :jeu.recettes) {
	        if(r.equals(jeu.recettes.get(a))) {
	        	System.out.println(r);
	        }
	        
	    }
	}
	
	// Methode qui affiche les recettes associees a une usine donnee
	private static void question_5() {
		for(Building b:jeu.batiments) {
			if ((b instanceof Factory) || (b instanceof FactoryCentrale))
				System.out.println("["+jeu.batiments.indexOf(b)+"] usine="+b.getName());
		}
		Scanner scanner = new Scanner( System.in );
		System.out.print( "Veuillez saisir une usine : " );
        int a = scanner.nextInt();
		
        if (a >= jeu.batiments.size() || a < 0)
			System.out.println("Votre choix d'usine n'est pas valide.");
		else {
			for(Recipe r :jeu.recettes) {
		        if(r.getProducers().equals(jeu.batiments.get(a).getId())) {
		        	jeu.recette_usine(r);
		        }
		    }
			if (!jeu.recettes_usine.isEmpty()) {
				for (Recipe r : jeu.recettes_usine) {
						System.out.println(r);
				}
			} else {
				System.out.println("Cette usine n'est associee a aucune recette.");
			}
		}
	}
	
	// Methode qui affiche les ressources extraites par un extracteur donne
	private static void question_6() {
		for(Extractor b:jeu.extracteurs) {
			System.out.println("["+jeu.extracteurs.indexOf(b)+"] "+b.getName());
		}
		Scanner scanner = new Scanner( System.in );
		System.out.print( "Veuillez saisir un extracteur : ");
        int b = scanner.nextInt();
        if (b >= jeu.extracteurs.size() || b < 0)
        	System.out.println("Votre choix d'extracteur n'est pas valide.");
        else {
	        for(Resource r :jeu.ressources) {
	            for(String s:r.getMiners()) {
	            	if(s.equals(((Component) jeu.extracteurs.get(b)).getId())) {
	            		jeu.resource_extractor.add(r);
	            	}
	            }
	        }
	        if (!jeu.resource_extractor.isEmpty()) {
		        for(Resource re:jeu.resource_extractor) {
					System.out.println(re);
				}
	        } else {
	        	System.out.println("Cet extracteur n'est associe a aucune ressource.");
	        }
        }
	}
	
	// Fonction qui affiche les carburants pour une centrale donnee
	private static void question_7() {
		Scanner scanner = new Scanner(System.in);
		int indice = -1;
		System.out.println("Liste des centrales a carburant :");
		for (CentraleCarburant c : jeu.centrales_carburant) {
			System.out.println("[" + jeu.centrales_carburant.indexOf(c) +"] " + c.getName());
		}
		System.out.print("Choisissez votre centrale : ");
		indice = scanner.nextInt();
		if (indice >= jeu.centrales_carburant.size() || indice < 0)
			System.out.println("Votre choix de centrale n'est pas valide.");
		else {
			System.out.println(jeu.centrales_carburant.get(indice));
		}
	}
	
	// Composants ressource d'une recette
	private static void question_8() {
		Scanner scanner = new Scanner(System.in);
		int indice = -1;
		System.out.println("Liste des recettes :");
		for (Recipe r : jeu.recettes) {
			System.out.println("[" + jeu.recettes.indexOf(r) +"] " + r.getName());
		}
		System.out.print("Choisissez votre recette : ");
		indice = scanner.nextInt();
		if (indice >= jeu.recettes.size() || indice < 0)
			System.out.println("Votre choix de recette n'est pas valide.");
		else {
			System.out.println("Les ingredients de type ressource de la recette sont :");
			Recipe r = jeu.recettes.get(indice);
			ArrayList<Component> decomposition = decompositionRecette(r);
			// Affichage des elements distincts
			Collections.sort(decomposition);
			for (int ind = 0; ind < decomposition.size(); ind++) {
				if (ind == 0)
					System.out.println("  >> " + decomposition.get(ind).getName());
				else if (ind != 0 && !(decomposition.get(ind).getId().equals(decomposition.get(ind-1).getId())))
					System.out.println("  >> " + decomposition.get(ind).getName());
			}
		}
	}
	
	
	private static ArrayList<Component> decompositionRecette(Recipe r) {
		ArrayList<Component> sortie = r.getIngredients();	// getIngredients() retourne un clone des ingredients de la recette
		ArrayList<Component> sortie_nouveau = r.getIngredients();
		ArrayList<Component> a_supprimer = new ArrayList<>();
		boolean contient_pas_ressource = true;
		// Tant que la liste contient un ingredient qui n'est pas une ressource
		while (contient_pas_ressource) {
			contient_pas_ressource = false;
			for (Component ingredient : sortie) {
				if (!(ingredient instanceof Resource)) {
					contient_pas_ressource = true;
					Recipe re = jeu.getRecipebyId(ingredient.getId());
					if (re != null) {
						for (Component in : re.getIngredients())
							sortie_nouveau.add(in);	// Si on ajoute directement dans la liste sortie, ConcurrentModificationException
					} else {
						System.err.println("L'ingredient " + ingredient.getName() + " n'est pas une ressource, mais n'a pas de recette associee (decomposition impossible).");
					}
					a_supprimer.add(ingredient);	// On voudra supprimer l'ingredient de la liste sortie
				}
			}
			// Clone de sortie_nouveau dans sortie
			sortie.clear();
			for (Component c : sortie_nouveau)
				sortie.add(c);
			sortie.removeAll(a_supprimer);
		}
		return sortie;
	}
	
	private static void parser() throws ConsommationException, CategoryException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();	
			Document doc = db.parse(new File(FILENAME));
	
			NodeList list = doc.getElementsByTagName("items");
			for (int temp = 0; temp < list.getLength(); temp++) {
				Node node = list.item(temp);
				
				if (node.getNodeType() == Node.ELEMENT_NODE) {	
					Element element = (Element) node;
					String category, id, name, category_fuel = "";
					int row, stack, value = 0;
					String factory_type = null;
					ArrayList<String> minedBy = new ArrayList<>();
					double factory_speed = 0, extractor_speed = 0, drain = 0, usage = 0;
					Element factory = null, fuel = null, mining = null;
					
					category = element.getElementsByTagName("category").item(0).getTextContent();
					id = element.getElementsByTagName("id").item(0).getTextContent();
					name = element.getElementsByTagName("name").item(0).getTextContent();
					row = Integer.parseInt(element.getElementsByTagName("row").item(0).getTextContent());
					stack = Integer.parseInt(element.getElementsByTagName("stack").item(0).getTextContent());
					
					if ((fuel = (Element) element.getElementsByTagName("fuel").item(0)) != null) {
						category_fuel = fuel.getElementsByTagName("category").item(0).getTextContent();
						value = Integer.parseInt(fuel.getElementsByTagName("value").item(0).getTextContent());
					}
					
					NodeList list_minedby;
					if ((list_minedby = element.getElementsByTagName("minedby")) != null) {
						for (int i = 0; i < list_minedby.getLength(); i++) {
							minedBy.add(list_minedby.item(0).getTextContent());
						}
					}
					
					if ((factory = (Element) element.getElementsByTagName("factory").item(0)) != null) {
						factory_type = factory.getElementsByTagName("type").item(0).getTextContent();
						if (factory.getElementsByTagName("value").item(0) != null)
							value = Integer.parseInt(factory.getElementsByTagName("value").item(0).getTextContent());
						if (factory.getElementsByTagName("speed").item(0) != null)
							factory_speed = Double.parseDouble(factory.getElementsByTagName("speed").item(0).getTextContent());
						else
							factory_speed = 1;
						if (factory.getElementsByTagName("drain").item(0) != null)
							drain = Double.parseDouble(factory.getElementsByTagName("drain").item(0).getTextContent());
						else
							drain = 0;
						if (factory.getElementsByTagName("usage").item(0) != null)
							usage = Double.parseDouble(factory.getElementsByTagName("usage").item(0).getTextContent());
						else
							usage = drain;
						if (factory.getElementsByTagName("category").item(0) != null) {
							category_fuel = factory.getElementsByTagName("category").item(0).getTextContent();
						}
					}
					
					if ((mining = (Element) element.getElementsByTagName("mining").item(0)) != null) {
						extractor_speed = Double.parseDouble(mining.getElementsByTagName("speed").item(0).getTextContent());
					}
					
					// Si la categorie est un composant simple
					if (category.equals("components")) {
						if (fuel == null) {
							jeu.ajouterComposantSimple(id, name, row, stack);
						} else {
							// Si c'est un carburant composant
							if (category_fuel.equals("chemical"))
								jeu.ajouterCarburantCompo(id, name, row, stack, CategoryFuelBurn.chemical, value);
							else if (category_fuel.equals("nuclear"))
								jeu.ajouterCarburantCompo(id, name, row, stack, CategoryFuelBurn.nuclear, value);
							else if (category_fuel.equals("antimatter"))
								jeu.ajouterCarburantCompo(id, name, row, stack, CategoryFuelBurn.antimatter, value);
							else
								throw new CategoryException("Le carburant " + id + " n'a pas une des categories attendues.");
						}
					}
					// Si la categorie est un batiment
					else if (category.equals("buildings")) {
						if (factory == null && mining == null) {
							// Si batiment simple
							jeu.ajouterBatiment(id, name, row, stack);
						} else if (factory != null && mining == null) {
							// Si usine simple ou centrale
							if (factory_type.equals("electric")) {
								// Usine simple
								if (drain > usage)
									throw new ConsommationException("L'usine electrique " + id + " consomme plus au repos qu'au travail.");
								jeu.ajouterUsine(id, name, row, stack, factory_type, factory_speed, usage, drain);
							}
							else if (factory_type.equals("burner")) {
								// Centrale a carburant
								ArrayList<Fuel> liste_carburants = new ArrayList<>();
								if (category_fuel.equals("chemical")) {
									for (Fuel f : jeu.carburants) {
										if (f.getCategory() == CategoryFuelBurn.chemical)
											liste_carburants.add(f);
									}
									jeu.ajouterCentraleCarburant(id, name, row, stack, CategoryFuelBurn.chemical, factory_type, factory_speed, value, liste_carburants);
								} else if (category_fuel.equals("nuclear")) {
									for (Fuel f : jeu.carburants) {
										if (f.getCategory() == CategoryFuelBurn.nuclear)
											liste_carburants.add(f);
									}
									jeu.ajouterCentraleCarburant(id, name, row, stack, CategoryFuelBurn.nuclear, factory_type, factory_speed, value, liste_carburants);
								} else if (category_fuel.equals("antimatter")) {
									for (Fuel f : jeu.carburants) {
										if (f.getCategory() == CategoryFuelBurn.antimatter)
											liste_carburants.add(f);
									}
									jeu.ajouterCentraleCarburant(id, name, row, stack, CategoryFuelBurn.antimatter, factory_type, factory_speed, value, liste_carburants);
								} else
									throw new CategoryException("La centrale n'a pas une des categories attendues.");
							} else if (factory_type.equals("electric-production")) {
								// Centrale electrique
								jeu.ajouterCentraleElectrique(id, name, row, stack, factory_type, factory_speed, value);
								
							}
						} else if (factory != null && mining != null) {
							// Si extracteur electrique
							if (drain > usage)
								throw new ConsommationException("L'extracteur electrique consomme plus au repos qu'au travail.");
							jeu.ajouterExtracteurElectrique(id, name, row, stack, factory_type, factory_speed, usage, drain);
						} else if (factory == null && mining != null) {
							// Si extracteur classique
							jeu.ajouterExtracteurClassique(id, name, row, stack, extractor_speed, "electric", 1, 0, 0);
						}
					}
					// Si la categorie est une ressource simple
					else if (category.equals("resource")) {
						if (fuel == null) {
							jeu.ajouterRessource(id,name,row,stack,minedBy);
						} else {
							// Si c'est un carburant resource
							if (category_fuel.equals("chemical"))
								jeu.ajouterCarburantRessource(id, name, row, stack, CategoryFuelBurn.chemical, minedBy, value);
							else if (category_fuel.equals("nuclear"))
								jeu.ajouterCarburantRessource(id, name, row, stack, CategoryFuelBurn.nuclear, minedBy, value);
							else if (category_fuel.equals("antimatter"))
								jeu.ajouterCarburantRessource(id, name, row, stack, CategoryFuelBurn.antimatter, minedBy, value);
							else
								throw new CategoryException("Le carburant ressource n'a pas une des categories attendues.");
						}
					}

				}

			}
			
			
			// Parcours de toutes les recettes
			list = doc.getElementsByTagName("recipes");
			for (int temp = 0; temp < list.getLength(); temp++) {
				Node node = list.item(temp);
				
				if (node.getNodeType() == Node.ELEMENT_NODE) {	
					
					Element element = (Element) node;
					
					double time;
					String id, name, producers;
					Element in, out;
					NodeList liste_ingredient;
					
					ArrayList<Component> ingredients = new ArrayList<>();
					ArrayList<Double> quantites_in = new ArrayList<>();
					ArrayList<Component> produits = new ArrayList<>();
					ArrayList<Double> quantites_out = new ArrayList<>();
					
					id = element.getElementsByTagName("id").item(0).getTextContent();
					name = element.getElementsByTagName("name").item(0).getTextContent();
					time = Double.parseDouble(element.getElementsByTagName("time").item(0).getTextContent());
					producers = element.getElementsByTagName("producers").item(0).getTextContent();
					
					in = (Element) element.getElementsByTagName("in").item(0);
					
					liste_ingredient = in.getElementsByTagName("*");
					
					for(int i = 0; i < liste_ingredient.getLength(); i++) {
						Element e = (Element)liste_ingredient.item(i);
						String id_ingredient = e.getNodeName();
						Component ingredient = jeu.getComposantbyId(id_ingredient);
						double qte = Double.parseDouble(in.getElementsByTagName(id_ingredient).item(0).getTextContent());
						if (ingredient != null) {
							ingredients.add(ingredient);
							quantites_in.add(qte);
						}
					}
					
					// Si recette complexe
					if ((out = (Element) element.getElementsByTagName("out").item(0)) != null) {
						liste_ingredient = out.getElementsByTagName("*");
						for(int i = 0; i < liste_ingredient.getLength(); i++) {
							Element e = (Element)liste_ingredient.item(i);
							String id_ingredient = e.getNodeName();
							Component ingredient = jeu.getComposantbyId(id_ingredient);
							double qte = Double.parseDouble(out.getElementsByTagName(id_ingredient).item(0).getTextContent());
							if (ingredient != null) {
								produits.add(ingredient);
								quantites_out.add(qte);
							} else {
								System.out.println("ingredient " + id_ingredient + " pas trouve");
							}
						}
						
						jeu.ajouterRecetteComplexe(id, name, time, producers, ingredients, quantites_in, produits, quantites_out);
					} 
					// Si recette simple
					else {
						jeu.ajouterRecette(id, name, time, producers, ingredients, quantites_in);
					}
					
				}
			}
		} catch(ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}
}