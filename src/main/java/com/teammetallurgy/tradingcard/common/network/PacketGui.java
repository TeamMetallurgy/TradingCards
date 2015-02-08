package com.teammetallurgy.tradingcard.common.network;

import com.teammetallurgy.tradingcard.TradingCard;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class PacketGui implements IMessage {

    public int id;

    public PacketGui() {
    }

    public PacketGui(int id) {
        this.id = id;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        id = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(id);
    }

    public static class Handler implements IMessageHandler<PacketGui, IMessage> {

        @Override
        public IMessage onMessage(PacketGui message, MessageContext ctx) {
            EntityPlayer player = ctx.getServerHandler().playerEntity;
            player.openGui(TradingCard.instance, message.id, player.worldObj, (int) player.posX, (int) player.posY, (int) player.posZ);
            return null;
        }
    }
}
