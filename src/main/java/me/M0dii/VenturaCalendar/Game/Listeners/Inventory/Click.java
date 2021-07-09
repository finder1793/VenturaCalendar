package me.M0dii.VenturaCalendar.Game.Listeners.Inventory;

import me.M0dii.VenturaCalendar.Base.ItemUtils.ItemProperties;
import me.M0dii.VenturaCalendar.Base.ItemUtils.Items;
import me.M0dii.VenturaCalendar.Base.Utils.Utils;
import me.M0dii.VenturaCalendar.Game.Config.CommandConfig;
import me.M0dii.VenturaCalendar.Game.Config.Messages;
import me.M0dii.VenturaCalendar.Game.GUI.Calendar;
import me.M0dii.VenturaCalendar.Game.GUI.InventoryProperties;
import me.M0dii.VenturaCalendar.VenturaCalendar;
import me.M0dii.VenturaCalendar.Game.GUI.Storage;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;

public class Click
{
	public Click(InventoryClickEvent e)
	{
		Player player = (Player) e.getWhoClicked();
		
		Inventory inv = e.getClickedInventory();
		ItemStack item = e.getCurrentItem();
		
		if(inv != null && inv.getHolder() instanceof Calendar)
		{
			e.setCancelled(true);
			
			HashMap<Items, HashMap<ItemProperties, Object>> itemProperties =
					(HashMap<Items, HashMap<ItemProperties, Object>>)
					VenturaCalendar.getCalendarConfig().getCalendarProperties()
					.get(InventoryProperties.ITEMS);
			
			HashMap<ItemProperties, Object> today = itemProperties.get(Items.TODAY);
			
			Material m = (Material)today.get(ItemProperties.MATERIAL);
			
			if(item != null && m.equals(item.getType()))
			{
				VenturaCalendar.instance.getLogger().info("Today Button");
				
				CommandConfig cc = VenturaCalendar.getCConfig();
				
				if(cc.getBoolean("rewards.enabled"))
				{
					if(VenturaCalendar.redeem(player.getUniqueId()))
					{
						for(String cmd : cc.getListString("rewards.commands"))
							sendCommand((Player)e.getWhoClicked(), (Player)e.getWhoClicked(), cmd);
					}
					else Utils.sendMsg(e.getWhoClicked(), Messages.REDEEMED);
				}
			}
		}
		
		if(VenturaCalendar.storages.containsKey(player))
		{
			e.setCancelled(true);
			
			Storage storage = VenturaCalendar.storages.get(player);
		}
	}
	
	private void sendCommand(Player sender, Player placeholderHolder, String cmd)
	{
		cmd = PlaceholderAPI.setPlaceholders(placeholderHolder, cmd)
				.replace("%sender_name%", sender.getName());
		
		if(cmd.startsWith("["))
		{
			String sendAs = cmd.substring(cmd.indexOf("["), cmd.indexOf("]") + 2);
			
			cmd = cmd.substring(cmd.indexOf("]") + 2);
			
			if(sendAs.equalsIgnoreCase("[PLAYER] "))
				Bukkit.dispatchCommand(sender, cmd);
			else if(sendAs.equalsIgnoreCase("[CONSOLE] "))
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
						cmd.replace("[CONSOLE] ", ""));
		}
		else Bukkit.dispatchCommand(sender, cmd);
	}

}
