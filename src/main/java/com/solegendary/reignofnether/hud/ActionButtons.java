package com.solegendary.reignofnether.hud;

import com.solegendary.reignofnether.cursor.CursorClientEvents;
import com.solegendary.reignofnether.keybinds.Keybinding;
import com.solegendary.reignofnether.unit.UnitClientEvents;

// static list of generic unit actions (attack, move, stop, etc.)
public class ActionButtons {

    private static final int itemIconSize = 14;
    private static final int iconFrameSize = Button.iconFrameSize;

    public static final Button attack = new Button(
            "Attack",
            itemIconSize,
            "textures/icons/items/sword.png",
            Keybinding.attack,
            () -> CursorClientEvents.getLeftClickAction() == ActionName.ATTACK,
            () -> false,
            () -> CursorClientEvents.setLeftClickAction(ActionName.ATTACK)
    );
    public static final Button stop = new Button(
            "Stop",
            itemIconSize,
            "textures/icons/items/barrier.png",
            Keybinding.stop,
            () -> false, // except if currently clicked on
            () -> false,
            () -> UnitClientEvents.sendCommand(ActionName.STOP)
    );
    public static final Button hold = new Button(
            "Hold Position",
            itemIconSize,
            "textures/icons/items/chestplate.png",
            Keybinding.hold,
            () -> false, // TODO: if all selected units are holding position (but would need to get this from server?)
            () -> false,
            () -> UnitClientEvents.sendCommand(ActionName.HOLD)
    );
    public static final Button move = new Button(
            "Move",
            itemIconSize,
            "textures/icons/items/boots.png",
            Keybinding.move,
            () -> CursorClientEvents.getLeftClickAction() == ActionName.MOVE,
            () -> false,
            () -> CursorClientEvents.setLeftClickAction(ActionName.MOVE)
    );
}
