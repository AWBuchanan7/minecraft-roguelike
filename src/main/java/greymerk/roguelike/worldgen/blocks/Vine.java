package greymerk.roguelike.worldgen.blocks;

import java.util.Random;

import greymerk.roguelike.worldgen.Cardinal;
import greymerk.roguelike.worldgen.Coord;
import greymerk.roguelike.worldgen.IWorldEditor;
import greymerk.roguelike.worldgen.MetaBlock;
import greymerk.roguelike.worldgen.shapes.RectSolid;
import net.minecraft.block.BlockVine;

public class Vine {

	public static void fill(IWorldEditor editor, Random rand, Coord start, Coord end){
		for(Coord cursor : new RectSolid(start, end)){
			set(editor, cursor);
		}
	}
	
	public static void set(IWorldEditor editor, Coord origin){
		if(!editor.isAirBlock(origin)) return;
		MetaBlock vine = BlockType.get(BlockType.VINE);
		for(Cardinal dir : Cardinal.directions){
			Coord c = new Coord(origin);
			c.add(dir);
			if(editor.canPlace(vine, c, dir)){
				setOrientation(vine, dir).set(editor, c);
				return;
			}
		}
	}
	
	public static MetaBlock setOrientation(MetaBlock vine, Cardinal dir){
		vine.withProperty(BlockVine.NORTH, Boolean.valueOf(dir == Cardinal.NORTH));
		vine.withProperty(BlockVine.EAST, Boolean.valueOf(dir == Cardinal.EAST));
		vine.withProperty(BlockVine.SOUTH, Boolean.valueOf(dir == Cardinal.SOUTH));
		vine.withProperty(BlockVine.WEST, Boolean.valueOf(dir == Cardinal.WEST));
		return vine;
	}
}
