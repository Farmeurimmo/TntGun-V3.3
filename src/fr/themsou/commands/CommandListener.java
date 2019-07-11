package fr.themsou.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import fr.themsou.main.main;
import fr.themsou.methodes.info;

public class CommandListener implements Listener{
	
	
	main pl;
	public CommandListener(main pl) {
		this.pl = pl;
	}
	
	
	@EventHandler
	public void OnCmd(PlayerCommandPreprocessEvent e){
		
		Player p = e.getPlayer();
		String msg = e.getMessage();
		String[] args = msg.split(" ");
		
		GradeCmd CGrade = new GradeCmd();
		
		int i = CGrade.getPlayerPermition(p.getName());
		
		if(main.config.getInt(p.getName() + ".status") == 1){
			if(p.getGameMode() == GameMode.SURVIVAL){
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////// ROLE PLAY ////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				if(p.getWorld() == Bukkit.getWorld("world") || p.getWorld() == Bukkit.getWorld("world_nether") || p.getWorld() == Bukkit.getWorld("world_the_end")){
//
//	COMMANDES JOUEURS +
//	
					if(args[0].equalsIgnoreCase("/hub") ||
						args[0].equalsIgnoreCase("/spawn") ||
						args[0].equalsIgnoreCase("/lobby") ||
						args[0].equalsIgnoreCase("/?") ||
						args[0].equalsIgnoreCase("/help") ||
						args[0].equalsIgnoreCase("/tete") ||
						args[0].equalsIgnoreCase("/ec") ||
						args[0].equalsIgnoreCase("/craft") ||
						args[0].equalsIgnoreCase("/nick")  || 
						args[0].equalsIgnoreCase("/skin") ||
						args[0].equalsIgnoreCase("/tpa") ||
						args[0].equalsIgnoreCase("/tphere") ||
						args[0].equalsIgnoreCase("/tpayes") ||
						args[0].equalsIgnoreCase("/tphereyes") ||
						args[0].equalsIgnoreCase("/money") ||
						args[0].equalsIgnoreCase("/home") ||
						args[0].equalsIgnoreCase("/sethome") ||
						args[0].equalsIgnoreCase("/delhome") ||
						args[0].equalsIgnoreCase("/r") ||
						args[0].equalsIgnoreCase("/msg") ||
						args[0].equalsIgnoreCase("/m") ||
						args[0].equalsIgnoreCase("/claim") ||
						args[0].equalsIgnoreCase("/itemsell") ||
						args[0].equalsIgnoreCase("/a") ||
						args[0].equalsIgnoreCase("/discord") ||
						args[0].equalsIgnoreCase("/reg") ||
						args[0].equalsIgnoreCase("/register") ||
						args[0].equalsIgnoreCase("/pay") ||
						args[0].equalsIgnoreCase("/night") ||
						args[0].equalsIgnoreCase("/feed") ||
						args[0].equalsIgnoreCase("/ent")){}

//								
//	COMMANDES BUILDER +
//
					else if(args[0].equalsIgnoreCase("/g")){
						if(i <= 3){
							p.sendMessage("§cCette commande n'existe pas ou ne vous est pas autorisée !");
							e.setCancelled(true);
						}
					}
//
//	COMMANDES AUTRE
//
					else if(args[0].equalsIgnoreCase("/infoview")){
						
						e.setCancelled(true);
						new info().viewPlayerInfo(p, args[1], args[2]);
						
					}else{
						e.setCancelled(true);
						p.sendMessage("§cCette commande n'existe pas ou ne vous est pas autorisée !");
					}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////// BEDWARS //////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				}else if(p.getWorld() == Bukkit.getWorld("BedWars")){
					
					if(args[0].equalsIgnoreCase("/hub")
					|| args[0].equalsIgnoreCase("/lobby")
					|| args[0].equalsIgnoreCase("/msg")
					|| args[0].equalsIgnoreCase("/m")
					|| args[0].equalsIgnoreCase("/r")
					|| args[0].equalsIgnoreCase("/?")
					|| args[0].equalsIgnoreCase("/help")
					|| args[0].equalsIgnoreCase("/nick")
					|| args[0].equalsIgnoreCase("/link")
					|| args[0].equalsIgnoreCase("/a")
					|| args[0].equalsIgnoreCase("/discord")
					|| args[0].equalsIgnoreCase("/reg")
					|| args[0].equalsIgnoreCase("/register")
					|| args[0].equalsIgnoreCase("/skin")){}
					
//												
//	COMMANDES BUILDER +
//
				else if(args[0].equalsIgnoreCase("/g")){
					if(i <= 3){
						p.sendMessage("§cCette commande n'existe pas ou ne vous est pas autorisée !");
						e.setCancelled(true);
					}
				}
//
//	COMMANDES AUTRE
//
				else if(args[0].equalsIgnoreCase("/infoview")){
					
					e.setCancelled(true);
					new info().viewPlayerInfo(p, args[1], args[2]);
					
				}else{
					e.setCancelled(true);
					p.sendMessage("§cCette commande n'existe pas ou ne vous est pas autorisée !");
				}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////// HUB //////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				}else{
				
					if(args[0].equalsIgnoreCase("/hub")
						|| args[0].equalsIgnoreCase("/lobby")
						|| args[0].equalsIgnoreCase("/msg")
						|| args[0].equalsIgnoreCase("/m")
						|| args[0].equalsIgnoreCase("/r")
						|| args[0].equalsIgnoreCase("/?")
						|| args[0].equalsIgnoreCase("/help")
						|| args[0].equalsIgnoreCase("/nick")
						|| args[0].equalsIgnoreCase("/a")
						|| args[0].equalsIgnoreCase("/discord")
						|| args[0].equalsIgnoreCase("/reg")
						|| args[0].equalsIgnoreCase("/register")
						|| args[0].equalsIgnoreCase("/skin")){}
					
//						
//COMMANDES BUILDER +
//
						else if(args[0].equalsIgnoreCase("/g")){
							if(i <= 3){
								p.sendMessage("§cCette commande n'existe pas ou ne vous est pas autorisée !");
								e.setCancelled(true);
							}
						}
//
//COMMANDES AUTRE
//
						else if(args[0].equalsIgnoreCase("/infoview")){
							
							e.setCancelled(true);
							new info().viewPlayerInfo(p, args[1], args[2]);
							
						}else{
							e.setCancelled(true);
							p.sendMessage("§cCette commande n'existe pas ou ne vous est pas autorisée !");
							
							
						}
					}
				}
			
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////// GAMEMODE 1 ///////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			else{
				
				
				if(args[0].equalsIgnoreCase("/ban") || args[0].equalsIgnoreCase("/tempban") || args[0].equalsIgnoreCase("/banip") || args[0].equalsIgnoreCase("/unban") || args[0].equalsIgnoreCase("/mute")){
					
					e.setCancelled(true);
					p.sendMessage("§cVeuillez utiliser le /a !");
					
				}else if(args[0].equalsIgnoreCase("/infoview")){
					
					e.setCancelled(true);
					new info().viewPlayerInfo(p, args[1], args[2]);
					
				}
			}
		}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////// GLOGIN ///////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		else if(args[0].equalsIgnoreCase("/l") || args[0].equalsIgnoreCase("/login") || args[0].equalsIgnoreCase("/reg") || args[0].equalsIgnoreCase("/register")){
		
		}else{
			e.setCancelled(true);
		}
	}
}
