package com.solegendary.reignofnether.building.buildings.monsters;

import com.solegendary.reignofnether.building.BuildingBlock;
import com.solegendary.reignofnether.building.BuildingBlockData;
import com.solegendary.reignofnether.building.BuildingClientEvents;
import com.solegendary.reignofnether.building.ProductionBuilding;
import com.solegendary.reignofnether.hud.AbilityButton;
import com.solegendary.reignofnether.keybinds.Keybinding;
import com.solegendary.reignofnether.keybinds.Keybindings;
import com.solegendary.reignofnether.research.ResearchClient;
import com.solegendary.reignofnether.research.researchItems.ResearchEvokerVexes;
import com.solegendary.reignofnether.research.researchItems.ResearchEvokers;
import com.solegendary.reignofnether.research.researchItems.ResearchLingeringPotions;
import com.solegendary.reignofnether.resources.ResourceCost;
import com.solegendary.reignofnether.resources.ResourceCosts;
import com.solegendary.reignofnether.unit.units.monsters.CreeperUnitProd;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Rotation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.solegendary.reignofnether.building.BuildingUtils.getAbsoluteBlockData;

public class Dungeon extends ProductionBuilding {

    public final static String buildingName = "Dungeon";
    public final static String structureName = "dungeon";
    public final static ResourceCost cost = ResourceCosts.DUNGEON;

    public Dungeon(Level level, BlockPos originPos, Rotation rotation, String ownerName) {
        super(level, originPos, rotation, ownerName, getAbsoluteBlockData(getRelativeBlockData(level), level, originPos, rotation), false);
        this.name = buildingName;
        this.ownerName = ownerName;
        this.portraitBlock = Blocks.SPAWNER;
        this.icon = new ResourceLocation("minecraft", "textures/block/spawner.png");

        this.foodCost = cost.food;
        this.woodCost = cost.wood;
        this.oreCost = cost.ore;
        this.popSupply = cost.population;

        this.startingBlockTypes.add(Blocks.DEEPSLATE_BRICK_STAIRS);

        this.explodeChance = 0.2f;

        if (level.isClientSide())
            this.productionButtons = Arrays.asList(
                CreeperUnitProd.getStartButton(this, Keybindings.keyQ)
            );
    }

    public static ArrayList<BuildingBlock> getRelativeBlockData(LevelAccessor level) {
        return BuildingBlockData.getBuildingBlocks(structureName, level);
    }

    public static AbilityButton getBuildButton(Keybinding hotkey) {
        return new AbilityButton(
            Dungeon.buildingName,
            new ResourceLocation("minecraft", "textures/block/spawner.png"),
            hotkey,
            () -> BuildingClientEvents.getBuildingToPlace() == Dungeon.class,
            () -> false,
            () -> BuildingClientEvents.hasFinishedBuilding(Mausoleum.buildingName) ||
                    ResearchClient.hasCheat("modifythephasevariance"),
            () -> BuildingClientEvents.setBuildingToPlace(Dungeon.class),
            null,
            List.of(
                FormattedCharSequence.forward(Dungeon.buildingName, Style.EMPTY.withBold(true)),
                ResourceCosts.getFormattedCost(cost),
                FormattedCharSequence.forward("", Style.EMPTY),
                FormattedCharSequence.forward("A fiery cage containing the power to create creepers.", Style.EMPTY)
            ),
            null
        );
    }

    @Override
    public BlockPos getIndoorSpawnPoint(ServerLevel level) {
        return super.getIndoorSpawnPoint(level).offset(-1,0,0);
    }
}
