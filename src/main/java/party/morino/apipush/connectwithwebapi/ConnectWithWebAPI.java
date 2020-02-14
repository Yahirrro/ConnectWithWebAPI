package party.morino.apipush.connectwithwebapi;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.milkbowl.vault.economy.Economy;

public final class ConnectWithWebAPI extends JavaPlugin  {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (cmd.getName().equalsIgnoreCase("auction")) {
            if (sender instanceof Player) {
                if (args.length < 3) {
                    String input = "{\"id\":\"" + args[0] +"\", \"amount\":" + args[1] + ",\"mc_id\":\"" + sender.getName() + "\", \"mc_uuid\":\""+ ((Player) sender).getUniqueId() +"\"}";
                    sender.sendMessage(input);
                    POSTtoAPI.POST("auctions/bid",input, sender);
                }
                else {
                    sender.sendMessage("必要な要素が足りていません。");
                    sender.sendMessage("'/auction ID 金額'のように入力してください");
                }
            }
            else {
                sender.sendMessage("プレイヤーからのみコマンドを実行できます。");
            }
            return true;
        }
        return false;
    }
}
