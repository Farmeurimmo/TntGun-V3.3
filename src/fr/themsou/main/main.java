package fr.themsou.main;

import java.io.File;	
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.WorldCreator;
import org.bukkit.boss.BossBar;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.dynmap.DynmapAPI;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;

import fr.themsou.BedWars.BedWars;
import fr.themsou.BedWars.menu;
import fr.themsou.TntWars.RunParty;
import fr.themsou.TntWars.inv;
import fr.themsou.discord.vocal.VocalEvents;
import fr.themsou.listener.BreakListener;
import fr.themsou.listener.ChangeWorldListener;
import fr.themsou.listener.ChatListener;
import fr.themsou.listener.CloseInventory;
import fr.themsou.listener.CommandListener;
import fr.themsou.listener.CraftListener;
import fr.themsou.listener.CustomEvent;
import fr.themsou.listener.DamageListener;
import fr.themsou.listener.DeadListener;
import fr.themsou.listener.EnchantListener;
import fr.themsou.listener.InventoryListener;
import fr.themsou.listener.JoinListener;
import fr.themsou.listener.ListPingListener;
import fr.themsou.listener.MobGriefListener;
import fr.themsou.listener.MobSpawnListener;
import fr.themsou.listener.MoveListener;
import fr.themsou.listener.QuitListener;
import fr.themsou.listener.TabCommandListener;
import fr.themsou.listener.furnaceListener;
import fr.themsou.listener.interactListener;
import fr.themsou.methodes.Boss;
import fr.themsou.methodes.PInfos;
import fr.themsou.methodes.realDate;
import fr.themsou.methodes.timer;
import fr.themsou.methodes.tpa;
import fr.themsou.methodes.vip;
import fr.themsou.rp.ent.Commands;
import fr.themsou.rp.tools.setcraft;
import net.milkbowl.vault.economy.Economy;

public class main extends JavaPlugin implements Listener {
	
	private FileConfiguration conf = getConfig();
	public static FileConfiguration config;
	public static FileConfiguration configuration;
	public static WorldEditPlugin worldEditPlugin = (WorldEditPlugin)Bukkit.getPluginManager().getPlugin("WorldEdit");
	public static DynmapAPI dynmap = ((DynmapAPI) Bukkit.getPluginManager().getPlugin("dynmap"));
	public static Economy economy = null;
	public static Inventory menu = Bukkit.createInventory(null, 5*9, "§4MODES DE JEUX");
	public static Inventory Lapmenu = Bukkit.createInventory(null, 6*9, "§4BedWars - Menu");
	public static Inventory rpmenu = Bukkit.createInventory(null, 6*9, "§4MENU");
	public static Inventory rpshop = Bukkit.createInventory(null, 6*9, "§4SHOP");
	public static Inventory admin = Bukkit.createInventory(null, 6*9, "§4ADMIN");
	public static Inventory TntWars = Bukkit.createInventory(null, 3*9, "§4TntWars - Séléction de map §eVIP");
	public static Inventory LapMenuCmd = Bukkit.createInventory(null, 6*9, "§4COMMANDES");
	public static SQLConnexion CSQLConnexion;
	public static ArrayList<Player> TntWarsFille = new ArrayList<>();
	public static ArrayList<Player> TntWarsFillea = new ArrayList<>();
	public static ArrayList<Player> TntWarsFilleb = new ArrayList<>();
	public static ArrayList<Player> TntWarsCurrent = new ArrayList<>();
	public static int bedWarsEmerald = 90;
	public static int bedWarsDiamond = 60;
	public static int timerMinuts = 0;
	public static int ddos = 2;
	public static File logblock = new File("plugins/TntGun/logblock.yml");
	public main mainclass = this;
	public static HashMap<String, ItemStack[]> entLog = new HashMap<>();
	public static HashMap<String, BossBar> bars = new HashMap<>();
	
	public main getinstance(){
		return this;
	}
	
	@Override
	public void onLoad() {
		super.onLoad();
		
		
	}
	

	@SuppressWarnings("deprecation")
	@Override
	public void onEnable(){
		
		if(!logblock.exists()){
			try{
				logblock.createNewFile();
			}catch(IOException e){ e.printStackTrace(); }
		}
		
		File configFile = new File("plugins/TntGun/configuration.yml");
		if(!configFile.exists()){
			try{
				configFile.createNewFile();
			}catch(IOException e){ e.printStackTrace(); }
		}
        
		
		
		configuration = YamlConfiguration.loadConfiguration(configFile);
		
		
		super.onEnable();
		System.out.println("Plugin TntGun Activé !");
		
		Bukkit.getServer().getWorlds().add(Bukkit.getWorld("BedWars"));
		Bukkit.getServer().getWorlds().add(Bukkit.getWorld("hub"));
		Bukkit.getServer().getWorlds().add(Bukkit.getWorld("TntWars"));
		
		if(Bukkit.getOnlinePlayers().size() == 0){
			new WorldCreator("BedWars").createWorld();
			new WorldCreator("BedWars-save").createWorld();
			new WorldCreator("hub").createWorld();
			new WorldCreator("TntWars").createWorld();
			Bukkit.getServer().getWorlds().add(Bukkit.getWorld("BedWars"));
			Bukkit.getServer().getWorlds().add(Bukkit.getWorld("hub"));
			Bukkit.getServer().getWorlds().add(Bukkit.getWorld("TntWars"));
		}
		
		
		config = conf;
		
		CSQLConnexion = new SQLConnexion("jdbc:mysql://", "sql1.privateheberg.com", "SERVER5568MC", "SERVER5568MC", "h9KpbteB");
		
		CSQLConnexion.connexion();
		
		
		timer();
		timer2();
		
		if(main.config.getInt("bedwars.list.status") == 2){
			main.config.set("bedwars.list.status", 0);
		}
		
		setcraft CSetcraft = new setcraft(this);
		fr.themsou.inv.menu CMenu = new fr.themsou.inv.menu();
		inv Cinv = new inv();
		menu CLapmenu = new menu();
		
		CMenu.setMainInventory();
		Cinv.setTntWarsInventory();
		CLapmenu.setInventory(Lapmenu);
		CLapmenu.setCmdInventory(LapMenuCmd);
		
		
		
		if(!setupEconomy()) Bukkit.shutdown();
		
		
		getCommand("grade").setExecutor(new TabCommandListener(this));
		getCommand("g").setExecutor(new TabCommandListener(this));
		getCommand("setmoney").setExecutor(new TabCommandListener(this));
		getCommand("loadSchematic").setExecutor(new TabCommandListener(this));
		getCommand("config").setExecutor(new TabCommandListener(this));
		getCommand("tag").setExecutor(new TabCommandListener(this));
		getCommand("world").setExecutor(new TabCommandListener(this));
		getCommand("boss").setExecutor(new TabCommandListener(this));
		getCommand("getstat").setExecutor(new TabCommandListener(this));
		
		getCommand("discord").setExecutor(new TabCommandListener(this));
		getCommand("a").setExecutor(new TabCommandListener(this));
		getCommand("?").setExecutor(new TabCommandListener(this));
		getCommand("help").setExecutor(new TabCommandListener(this));
		getCommand("spawn").setExecutor(new TabCommandListener(this));
		getCommand("hub").setExecutor(new TabCommandListener(this));
		getCommand("lobby").setExecutor(new TabCommandListener(this));
		getCommand("l").setExecutor(new TabCommandListener(this));
		getCommand("login").setExecutor(new TabCommandListener(this));
		getCommand("reg").setExecutor(new TabCommandListener(this));
		getCommand("register").setExecutor(new TabCommandListener(this));
		getCommand("money").setExecutor(new TabCommandListener(this));
		
		getCommand("ent").setExecutor(new TabCommandListener(this));
		getCommand("ent").setTabCompleter(new Commands());
		
		getCommand("claim").setExecutor(new TabCommandListener(this));
		
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new ListPingListener(), this);
		pm.registerEvents(new JoinListener(this), this);
		pm.registerEvents(new InventoryListener(this), this);
		pm.registerEvents(new EnchantListener(), this);
		pm.registerEvents(new BreakListener(), this);
		pm.registerEvents(new DamageListener(this), this);
		pm.registerEvents(new DeadListener(), this);
		pm.registerEvents(new CommandListener(this), this);
		pm.registerEvents(new MobGriefListener(), this);
		pm.registerEvents(new furnaceListener(), this);
		pm.registerEvents(new QuitListener(), this);
		pm.registerEvents(new MoveListener(this), this);
		pm.registerEvents(new CraftListener(), this);
		pm.registerEvents(new ChangeWorldListener(), this);
		pm.registerEvents(new ChatListener(this), this);
		pm.registerEvents(new tpa(), this);
		pm.registerEvents(new vip(this), this);
		pm.registerEvents(new interactListener(this), this);
		pm.registerEvents(new MobSpawnListener(), this);
		pm.registerEvents(new CloseInventory(), this);
		
		
		CSetcraft.setcrafts();
		
		
		Date date = new realDate().getRealDate();
		String stopDate = main.config.getString("stat.list.server.stoptime");
		int hours = Integer.parseInt(stopDate.split("-")[1].split(":")[0]);
		int minut = Integer.parseInt(stopDate.split(":")[1]);
		
		if(date.getHours() != hours || date.getMinutes() > (minut + 15)){
			int stopMinuts = hours * 60 + minut;
			PInfos.putStats(stopMinuts, -1);
			PInfos.putStats(date.getHours() * 60 + date.getMinutes() - 15, -1);
			PInfos.putStats(stopMinuts, 0);
		}else{
			PInfos.putStatsNone();
		}
		
		Bukkit.getScheduler().runTaskLater(this, new Runnable(){
			
			@Override
			public void run(){
				
				new fr.themsou.rp.claim.dynmap().refreshMarkerArea();
				
			}
		}, 200);
		
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void onDisable() {
		Date date = new realDate().getRealDate();
		
		main.config.set("stat.list.server.stoptime", date.getDate() + "-" + date.getHours() + ":" + date.getMinutes());
		Bukkit.broadcastMessage("§6Le serveur est en train de redémarer !");
		Bukkit.broadcastMessage("§cIl se peut que vous ayez un \"RollBack\"");
		
		
		new VocalEvents().playlistEnd();
		
		CSQLConnexion.disconect();
		
		conf = config;
		saveConfig();
		
		new Boss().removeAll();
		
		System.out.println("Plugin TntGun Désactivé !");
		super.onDisable();
		
		fr.themsou.methodes.Inventory CInventory = new fr.themsou.methodes.Inventory();
		for(Player p : Bukkit.getOnlinePlayers()){
			if(PInfos.getGame(p).equals("RP") && p.getGameMode() == GameMode.SURVIVAL){
				CInventory.savePlayerInventory(p, "rp");
			}
		}
		
		
	}
	
	private void timer(){
		
		Bukkit.getScheduler().runTaskTimer(this, new Runnable() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				
				Date date = new realDate().getRealDate();
				timer Ctimer = new fr.themsou.methodes.timer();
				
				Ctimer.pear6S(mainclass);
				
				if(date.getMinutes() == 0 && main.config.getBoolean("stat.list.issend.hour") == false){ // HOUR
					main.config.set("stat.list.issend.hour", true);
					Ctimer.pearHour(getinstance());
					
					if(date.getHours() == 0 && main.config.getBoolean("stat.list.issend.day") == false){ // DAY
						main.config.set("stat.list.issend.day", true);
						Ctimer.pearDay(getinstance());
						
						if(date.getDay() == 1 && main.config.getBoolean("stat.list.issend.week") == false){ // WEEK
							main.config.set("stat.list.issend.week", true);
							Ctimer.pearWeek(getinstance());
							
						}if(date.getDate() == 0 && main.config.getBoolean("stat.list.issend.mounth") == false){ // MOUNTH
							main.config.set("stat.list.issend.mounth", true);
							Ctimer.pearMounth(getinstance());
							
						}
							
						
					}
				}
				
				if((date.getMinutes() == 15 || date.getMinutes() == 30 || date.getMinutes() == 45 || date.getMinutes() == 0) && main.config.getBoolean("stat.list.issend.quart") == false){
					main.config.set("stat.list.issend.quart", true);
					Ctimer.pearQuart(getinstance());
				}
				
					
				if(date.getMinutes() == 16 || date.getMinutes() == 31 || date.getMinutes() == 46 || date.getMinutes() == 1){ // HOUR
			
					main.config.set("stat.list.issend.hour", false);
					main.config.set("stat.list.issend.quart", false);
				
					if(date.getHours() == 1){ // DAY
						main.config.set("stat.list.issend.day", false);
						
						if(date.getDay() == 2){ // WEEK
							main.config.set("stat.list.issend.week", false);
							
						}if(date.getDate() == 1){ // MOUNTH
							main.config.set("stat.list.issend.mounth", false);
							
						}
					}
				}
				
				
				
				
				
			}
			
		},120, 120);
		
	}
	
	
	private void timer2() {
		
		Bukkit.getScheduler().runTaskTimer(this, new Runnable() {
			@Override
			public void run() {
				
				new CustomEvent().second();
				new fr.themsou.methodes.timer().pear1S(getinstance());
				new Boss().tick();
				
			    BedWars CBedWars = new BedWars();
			    CBedWars.run(getinstance());
			    
			    if(TntWarsFille.size() >= 1){
			    	if(TntWarsFillea.size() == 1){
			    		TntWarsFillea.add(TntWarsFille.get(0));
						TntWarsFille.remove(0);
					}else if(TntWarsFilleb.size() == 1){
			    		TntWarsFilleb.add(TntWarsFille.get(0));
						TntWarsFille.remove(0);
					}
				}
			    
			    
				if(TntWarsFille.size() >= 2){
					RunParty CRunParty = new RunParty();
					if(new Random().nextBoolean() == true){
						
						TntWarsFillea.add(TntWarsFille.get(0));
						TntWarsFille.remove(0);
						TntWarsFillea.add(TntWarsFille.get(0));
						TntWarsFille.remove(0);
						
						CRunParty.startpartya(mainclass);
					}else{
						
						TntWarsFilleb.add(TntWarsFille.get(0));
						TntWarsFille.remove(0);
						TntWarsFilleb.add(TntWarsFille.get(0));
						TntWarsFille.remove(0);
						
						CRunParty.startpartyb(mainclass);
					}
					
				}
				if(TntWarsFillea.size() >= 2){
					RunParty CRunParty = new RunParty();
					CRunParty.startpartya(mainclass);
				}
				if(TntWarsFilleb.size() >= 2){
					RunParty CRunParty = new RunParty();
					CRunParty.startpartyb(mainclass);
				}
				
				
			}
		},20, 20);
	}
	
	boolean setupEconomy(){
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }
 
        return (economy != null);
    }
}
