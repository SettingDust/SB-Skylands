package uk.co.jacekk.bukkit.skylandsplus.generation;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.generator.BlockPopulator;

import java.util.Random;

public class SnowPopulator extends BlockPopulator {
	
	public void populate(World world, Random random, Chunk chunk){
		int chunkX = chunk.getX() * 16;
		int chunkZ = chunk.getZ() * 16;
		
		for (int x = 0; x < 16; ++x){
			for (int z = 0; z < 16; ++z){
				Biome biome = world.getBiome(chunkX + x, chunkZ + z);

				if (biome == Biome.ICE_PLAINS || biome == Biome.ICE_MOUNTAINS ||
						biome == Biome.FROZEN_OCEAN || biome == Biome.FROZEN_RIVER || biome == Biome.ICE_PLAINS_SPIKES){
					int y = world.getHighestBlockYAt(chunkX + x, chunkZ + z);

					if (y > 5){
						Block block = chunk.getBlock(x, y, z);

						Block down = block.getRelative(BlockFace.DOWN);
						if ( !( down.isEmpty() || down.isLiquid() ||
								down.getType() == Material.ICE ) ){
							block.setType(Material.SNOW);
						}
					}
				}
			}
		}
	}
	
}