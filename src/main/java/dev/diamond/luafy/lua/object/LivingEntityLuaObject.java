package dev.diamond.luafy.lua.object;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dev.diamond.luafy.lua.LuaTypeConversions;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;
import org.luaj.vm2.lib.ZeroArgFunction;


public class LivingEntityLuaObject extends EntityLuaObject {


    public final LivingEntity living;

    public LivingEntityLuaObject(LivingEntity living) {
        super(living);
        this.living = living;
    }
    
    @Override
    public void create() {
        super.create();

        set("get_mainhand", new GetStackFunc(EquipmentSlot.MAINHAND));
        set("get_offhand", new GetStackFunc(EquipmentSlot.OFFHAND));

        set("get_head_stack", new GetStackFunc(EquipmentSlot.HEAD));
        set("get_chest_stack", new GetStackFunc(EquipmentSlot.CHEST));
        set("get_legs_stack", new GetStackFunc(EquipmentSlot.LEGS));
        set("get_boots_stack", new GetStackFunc(EquipmentSlot.FEET));
    }

    public class GetStackFunc extends ZeroArgFunction {

        private final EquipmentSlot slot;

        public GetStackFunc(EquipmentSlot slot) {
            this.slot = slot;
        }

        @Override
        public LuaValue call() {
            return new ItemStackLuaObject(living.getEquippedStack(slot));
        }
    }
}
