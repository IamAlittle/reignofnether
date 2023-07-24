package com.solegendary.reignofnether.hud;

import com.solegendary.reignofnether.ReignOfNether;
import com.solegendary.reignofnether.cursor.CursorClientEvents;
import com.solegendary.reignofnether.keybinds.Keybindings;
import com.solegendary.reignofnether.resources.ResourceName;
import com.solegendary.reignofnether.unit.UnitAction;
import com.solegendary.reignofnether.unit.UnitClientEvents;
import com.solegendary.reignofnether.unit.units.interfaces.Unit;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.entity.LivingEntity;

import java.util.List;

import static com.solegendary.reignofnether.unit.UnitClientEvents.sendUnitCommand;

// static list of generic unit actions (build, attack, move, stop, etc.)
public class ActionButtons {

    public static final Button BUILD_REPAIR = new Button(
            "Build/Repair",
            Button.itemIconSize,
            new ResourceLocation(ReignOfNether.MOD_ID, "textures/icons/items/shovel.png"),
            Keybindings.build,
            () -> CursorClientEvents.getLeftClickAction() == UnitAction.BUILD_REPAIR,
            () -> false,
            () -> true,
            () -> CursorClientEvents.setLeftClickAction(UnitAction.BUILD_REPAIR),
            null,
            List.of(FormattedCharSequence.forward("Build", Style.EMPTY))
    );
    public static final Button GATHER = new Button(
            "Gather",
            Button.itemIconSize,
            null, // changes depending on the gather target
            Keybindings.gather,
            () -> UnitClientEvents.getSelectedUnitResourceTarget() != ResourceName.NONE,
            () -> false,
            () -> true,
            () -> sendUnitCommand(UnitAction.TOGGLE_GATHER_TARGET),
            null,
            null
    );
    public static final Button ATTACK = new Button(
        "Attack",
        Button.itemIconSize,
        new ResourceLocation(ReignOfNether.MOD_ID, "textures/icons/items/sword.png"),
        Keybindings.attack,
        () -> CursorClientEvents.getLeftClickAction() == UnitAction.ATTACK,
        () -> false,
        () -> true,
        () -> CursorClientEvents.setLeftClickAction(UnitAction.ATTACK),
        null,
        List.of(FormattedCharSequence.forward("Attack", Style.EMPTY))
    );
    public static final Button STOP = new Button(
        "Stop",
        Button.itemIconSize,
        new ResourceLocation(ReignOfNether.MOD_ID, "textures/icons/items/barrier.png"),
        Keybindings.stop,
        () -> false, // except if currently clicked on
        () -> false,
        () -> true,
        () -> sendUnitCommand(UnitAction.STOP),
        null,
        List.of(FormattedCharSequence.forward("Stop", Style.EMPTY))
    );
    public static final Button HOLD = new Button(
        "Hold Position",
        Button.itemIconSize,
        new ResourceLocation(ReignOfNether.MOD_ID, "textures/icons/items/chestplate.png"),
        Keybindings.hold,
        () -> {
            LivingEntity entity = HudClientEvents.hudSelectedEntity;
            return entity instanceof Unit unit && unit.getHoldPosition();
        },
        () -> false,
        () -> true,
        () -> sendUnitCommand(UnitAction.HOLD),
        null,
        List.of(FormattedCharSequence.forward("Hold Position", Style.EMPTY))
    );
    public static final Button MOVE = new Button(
        "Move",
        Button.itemIconSize,
        new ResourceLocation(ReignOfNether.MOD_ID, "textures/icons/items/boots.png"),
        Keybindings.move,
        () -> CursorClientEvents.getLeftClickAction() == UnitAction.MOVE,
        () -> false,
        () -> true,
        () -> CursorClientEvents.setLeftClickAction(UnitAction.MOVE),
        null,
        List.of(FormattedCharSequence.forward("Move", Style.EMPTY))
    );
}
